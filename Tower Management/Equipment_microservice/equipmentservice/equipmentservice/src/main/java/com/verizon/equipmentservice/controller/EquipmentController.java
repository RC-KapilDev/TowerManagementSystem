package com.verizon.equipmentservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.verizon.equipmentservice.entity.Equipment;
import com.verizon.equipmentservice.service.EquipmentService;

@RestController
@RequestMapping("equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment savedEquipment = equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(savedEquipment);
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Integer id, @RequestBody Equipment equipment) {
        Equipment existingEquipment = equipmentService.getEquipmentById(id);

        if (existingEquipment == null) {
            return ResponseEntity.notFound().build(); // Return 404 if equipment not found
        }

        // Update the existing equipment fields with the new values
        existingEquipment.setWorkorderId(equipment.getWorkorderId());
        existingEquipment.setTowerId(equipment.getTowerId());
        existingEquipment.setSerialNumber(equipment.getSerialNumber());
        existingEquipment.setManufacture(equipment.getManufacture());
        existingEquipment.setModel(equipment.getModel());
        existingEquipment.setEquipmentName(equipment.getEquipmentName());
        existingEquipment.setDeletedStatus(equipment.getDeletedStatus());
        existingEquipment.setClaimed(equipment.getClaimed());

        Equipment updatedEquipment = equipmentService.saveEquipment(existingEquipment);
        if(updatedEquipment==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(updatedEquipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manufacture/{manufacture}")
    public ResponseEntity<List<Equipment>> getEquipmentsByManufacture(@PathVariable String manufacture) {
        return ResponseEntity.ok(equipmentService.getEquipmentsByManufacture(manufacture));
    }
}
