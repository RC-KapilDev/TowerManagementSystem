package com.verizon.equipmentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verizon.equipmentservice.entity.Equipment;



public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> findByManufacture(String manufacture);
    List<Equipment> findByEquipmentName(String equipmentName);
}
