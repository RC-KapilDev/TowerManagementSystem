package com.verizon.equipmentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.equipmentservice.entity.Equipment;
import com.verizon.equipmentservice.entity.TowerDTO;
import com.verizon.equipmentservice.repository.EquipmentRepository;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private TowerService towerService;

    /**
     * Save equipment after verifying that the tower exists.
     * @param equipment The equipment to save.
     * @return The saved equipment or null if the tower does not exist.
     */
    public Equipment saveEquipment(Equipment equipment) {
        // Check if the tower exists before saving the equipment
        TowerDTO tower = towerService.getTowerIfExists(equipment.getTowerId());
        if (tower != null) {
            return equipmentRepository.save(equipment);
        } else {
            // Tower does not exist, so return null or handle accordingly
            return null;
        }
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
