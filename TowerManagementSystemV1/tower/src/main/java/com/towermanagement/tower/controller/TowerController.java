package com.towermanagement.tower.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.towermanagement.tower.model.Tower;
import com.towermanagement.tower.service.TowerService;

@RestController
@RequestMapping("/api/towers")

public class TowerController {
    
    @Autowired
    private TowerService towerService;

    @GetMapping
    public ResponseEntity<List<Tower>> getAllTowers() {
        List<Tower> activeTowers = towerService.getAllTowers();
        return ResponseEntity.ok(activeTowers);
    }

    @PostMapping
    public Tower addTower(@RequestBody Tower tower) 
    {
        return towerService.addTower(tower);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Tower> getEntityById(@PathVariable Integer id) {
        return towerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTower(@PathVariable Integer id, @RequestBody Tower tower) {
        try {
            Tower updatedTower = towerService.updateTower(id, tower);
            return ResponseEntity.ok(updatedTower);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/pincode/{pincode}")
    public ResponseEntity<?> getTowersByPincode(@PathVariable Integer pincode) {
        List<Tower> towers = towerService.getTowersByPincode(pincode);
        if (towers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No towers found for the given pincode: " + pincode);
        }
        return ResponseEntity.ok(towers);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getTowersByStatus(@PathVariable String status) {
        List<Tower> towers = towerService.getTowersByStatus(status);
        if (towers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No towers found with status: " + status);
        }
        return ResponseEntity.ok(towers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> softDeleteTower(@PathVariable Integer id) {
        towerService.softDeleteTower(id);
        return ResponseEntity.ok("Tower with ID " + id + " has been soft deleted.");
    }

   @GetMapping("/location/{location}")
public ResponseEntity<?> getTowersByLocation(@PathVariable String location) {
    List<Tower> towers = towerService.getTowersByLocation(location);
    if (towers.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No towers found for the given location: " + location);
    }
    return ResponseEntity.ok(towers);
}




}

