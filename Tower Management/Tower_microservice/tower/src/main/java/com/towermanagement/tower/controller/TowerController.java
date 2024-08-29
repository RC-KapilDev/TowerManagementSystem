package com.towermanagement.tower.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.towermanagement.tower.model.Tower;
import com.towermanagement.tower.model.TowerStatus;
import com.towermanagement.tower.service.TowerService;

@RestController
@RequestMapping("/towers")

public class TowerController {
    
    @Autowired
    private TowerService towerService;

    @GetMapping
    public List<Tower> getAllTowers() {
        return towerService.getAllTowers();
    }

    @PostMapping
    public Tower addTower(@RequestBody Tower tower) {
        return towerService.addTower(tower);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tower> getTowerById(@PathVariable Integer id) {
        Optional<Tower> tower = towerService.getTowerById(id);
        return tower.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tower> updateTower(@PathVariable Integer id, @RequestBody Tower tower) {
        Tower updatedTower = towerService.updateTower(id, tower);
        if (updatedTower != null) {
            return ResponseEntity.ok(updatedTower);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pincode/{pincode}")
    public List<Tower> getTowersByLocation(@PathVariable Integer pincode) {
        return towerService.getTowersByPincode(pincode);
    }

    @GetMapping("/status/{status}")
    public List<Tower> getTowersByStatus(@PathVariable TowerStatus status) {
        return towerService.getTowersByStatus(status);
    }
}

