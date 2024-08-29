package com.towermanagement.tower.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.towermanagement.tower.model.Tower;
import com.towermanagement.tower.model.TowerStatus;
import com.towermanagement.tower.repository.TowerRepository;

@Service
public class TowerService {
    @Autowired
    private TowerRepository towerRepository;

    public List<Tower> getAllTowers() {
        return towerRepository.findAll();
    }

    public Tower addTower(Tower tower) {
        return towerRepository.save(tower);
    }

    public Optional<Tower> getTowerById(Integer id) {
        return towerRepository.findById(id);
    }

    public List<Tower> getTowersByPincode(Integer pincode) {
        return towerRepository.findByPincode(pincode);
    }

    public List<Tower> getTowersByStatus(TowerStatus status) {
        return towerRepository.findByStatus(status);
    }

    public Tower updateTower(Integer id, Tower tower) {
        if (towerRepository.existsById(id)) 
        {
            tower.setTower_id(id);
            return towerRepository.save(tower);
        }
        return null;
    }
}
