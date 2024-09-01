package com.verizon.equipmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.verizon.equipmentservice.entity.Equipment;
import com.verizon.equipmentservice.repository.EquipmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ValidationService validationService;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findByDeletedStatusFalse();
    }

    public Equipment getEquipmentById(Integer id) {
        return equipmentRepository.findById(id)
                .filter(equipment -> !equipment.getDeletedStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));
    }

    public List<Equipment> getEquipmentByName(String name) {
        return equipmentRepository.findByEquipmentNameIgnoreCaseAndDeletedStatusFalse(name);
    }

    public List<Equipment> getEquipmentByManufacture(String manufacture) {
        return equipmentRepository.findByManufactureIgnoreCaseAndDeletedStatusFalse(manufacture);
    }

    public Equipment createEquipment(Equipment equipment) {
        validationService.validateTowerExists(equipment.getTowerId());
        validationService.validateWorkOrderExists(equipment.getWorkorderId());
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Integer id, Equipment equipmentDetails) {
        validationService.validateTowerExists(equipmentDetails.getTowerId());
        validationService.validateWorkOrderExists(equipmentDetails.getWorkorderId());
        Equipment equipment = equipmentRepository.findById(id)
                .filter(e -> !e.getDeletedStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));

        equipment.setSerialNumber(equipmentDetails.getSerialNumber());
        equipment.setManufacture(equipmentDetails.getManufacture());
        equipment.setModel(equipmentDetails.getModel());
        equipment.setEquipmentName(equipmentDetails.getEquipmentName());
        equipment.setClaimed(equipmentDetails.getClaimed());
        return equipmentRepository.save(equipment);
    }

    public void claimEquipment(Integer id) {
        Equipment equipment = equipmentRepository.findById(id)
                .filter(e -> !e.getDeletedStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));

        equipment.setClaimed(true);
        equipmentRepository.save(equipment);
    }

    public void softDeleteEquipment(Integer id) {
        Equipment equipment = equipmentRepository.findById(id)
                .filter(e -> !e.getDeletedStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));

        equipment.setDeletedStatus(true);
        equipmentRepository.save(equipment);
    }

    public List<Equipment> getallEquipmentsByWorkorderId(Integer towerId){
        return equipmentRepository.findByWorkorderIdAndDeletedStatusFalse(towerId);
    }

    public List<Equipment> getallEquipmentsByTowerId(Integer towerId){
        return equipmentRepository.findByTowerIdAndDeletedStatusFalse(towerId);
    }
}
