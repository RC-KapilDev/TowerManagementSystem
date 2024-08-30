package com.towermanagement.tower.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.towermanagement.tower.model.Tower;

public interface TowerRepository extends JpaRepository<Tower, Integer>
{
   
    @Query("SELECT e FROM Tower e WHERE e.id = :id AND e.deletedStatus = false")
    Optional<Tower> findByIdAndDeletedFalse(@Param("id")Integer id);

    List<Tower> findByStatus(String status);    

    public List<Tower> findByPincode(Integer pincode);

    @Modifying
    @Query("UPDATE Tower t SET t.deletedStatus = true WHERE t.tower_id = :id")
    void softDeleteTower(@Param("id") Integer id);
    List<Tower> findByDeletedStatusFalse();

    @Query("SELECT t FROM Tower t WHERE t.location = :location AND t.deletedStatus = false")
    List<Tower> findByLocation(@Param("location") String location);

    
}
