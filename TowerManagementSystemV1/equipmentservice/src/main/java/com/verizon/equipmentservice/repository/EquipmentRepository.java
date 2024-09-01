package com.verizon.equipmentservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.verizon.equipmentservice.entity.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> findByEquipmentNameIgnoreCaseAndDeletedStatusFalse(String name);
    List<Equipment> findByManufactureIgnoreCaseAndDeletedStatusFalse(String manufacture);
    List<Equipment> findByDeletedStatusFalse();
    List<Equipment> findByWorkorderIdAndDeletedStatusFalse(Integer towerId);
    List<Equipment> findByTowerIdAndDeletedStatusFalse(Integer towerId);

  
}

