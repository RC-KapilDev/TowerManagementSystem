package com.towermanagement.tower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.towermanagement.tower.model.Tower;
import com.towermanagement.tower.model.TowerStatus;

public interface TowerRepository extends JpaRepository<Tower, Integer>
{
    List<Tower> findByLocation(String location);
    List<Tower> findByStatus(TowerStatus status);    

    public List<Tower> findByPincode(Integer pincode);
}
