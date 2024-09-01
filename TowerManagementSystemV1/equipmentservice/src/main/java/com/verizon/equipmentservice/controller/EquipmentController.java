package com.verizon.equipmentservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.verizon.equipmentservice.entity.Equipment;
import com.verizon.equipmentservice.service.CombinedService;
import com.verizon.equipmentservice.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private   CombinedService combinedService;
  

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Equipment>> getEquipmentByName(@PathVariable String name) {
        return ResponseEntity.ok(equipmentService.getEquipmentByName(name));
    }

    @GetMapping("/manufacture/{manufacture}")
    public ResponseEntity<List<Equipment>> getEquipmentByManufacture(@PathVariable String manufacture) {
        return ResponseEntity.ok(equipmentService.getEquipmentByManufacture(manufacture));
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(equipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Integer id, @RequestBody Equipment equipmentDetails) {
        return ResponseEntity.ok(equipmentService.updateEquipment(id, equipmentDetails));
    }

    @PatchMapping("/{id}/claim")
    public ResponseEntity<Void> claimEquipment(@PathVariable Integer id) {
        equipmentService.claimEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {
        equipmentService.softDeleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/workorder/{towerId}")
    public ResponseEntity<List<Equipment>> getAllEquipmentsByWorkOrderId(@PathVariable Integer towerId) {
        List<Equipment> equipments = equipmentService.getallEquipmentsByWorkorderId(towerId);
        return ResponseEntity.ok(equipments);
    }

    @GetMapping("/user/{userId}")
    public List<Equipment> getEquipmentsByUserId(@PathVariable int userId) {
        return combinedService.fetchEquipmentsForUser(userId);
    }

    @GetMapping("/tower/{towerId}")
    public List<Equipment> getEquipmentsByTowerID(@PathVariable int towerId) {
        return equipmentService.getallEquipmentsByTowerId(towerId);
    }
}
