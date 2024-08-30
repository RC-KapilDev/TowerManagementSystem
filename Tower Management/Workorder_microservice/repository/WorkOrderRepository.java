package com.tower.workorderservice.repository;



import com.tower.workorderservice.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {
    List<WorkOrder> findByStatus(String status);
    List<WorkOrder> findByUserId(Integer userId);
}
