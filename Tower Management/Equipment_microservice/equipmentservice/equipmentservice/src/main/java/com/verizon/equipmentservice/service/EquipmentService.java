package com.verizon.equipmentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.equipmentservice.entity.Equipment;
import com.verizon.equipmentservice.repository.EquipmentRepository;



@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Integer id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public List<Equipment> getEquipmentsByManufacture(String manufacture) {
        return equipmentRepository.findByManufacture(manufacture);
    }

    public void deleteEquipment(Integer id) {
        equipmentRepository.deleteById(id);
    }
}
