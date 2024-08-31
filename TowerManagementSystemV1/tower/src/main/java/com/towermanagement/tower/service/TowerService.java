package com.towermanagement.tower.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.towermanagement.tower.model.Tower;
import com.towermanagement.tower.repository.TowerRepository;

import jakarta.transaction.Transactional;

@Service
public class TowerService {
    @Autowired
    private TowerRepository towerRepository;

    public List<Tower> getAllTowers() {
        return towerRepository.findByDeletedStatusFalse();
    }

    public Tower addTower(Tower tower) {
        return towerRepository.save(tower);
    }

    public Optional<Tower> getById(Integer id) {
        return towerRepository.findByIdAndDeletedFalse(id);
    }

    public List<Tower> getTowersByPincode(Integer pincode) {
        return towerRepository.findByPincode(pincode);
    }

    public List<Tower> getTowersByStatus(String status) {
        return towerRepository.findByStatus(status);
    }

    public Tower updateTower(Integer id, Tower tower) {
        if (towerRepository.existsById(id)) {
            tower.setTower_id(id); // Assuming 'tower_id' is renamed to 'towerId' in the Tower entity
            return towerRepository.save(tower);
        } else {
            throw new IllegalArgumentException("Given ID " + id + " is not available in the database.");
        }
    }
    public List<Tower> getTowersByLocation(String location) {
        return towerRepository.findByLocation(location);
    }
    
    
    

    @Transactional
    public void softDeleteTower(Integer id) {
        towerRepository.softDeleteTower(id);
    }

}
