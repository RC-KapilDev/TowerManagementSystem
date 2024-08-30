package com.towermanagement.workorderservice.repository;


import com.towermanagement.workorderservice.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {

    List<WorkOrder> findByStatusAndDeletedStatusFalse(String status);

    List<WorkOrder> findByUserAndDeletedStatusFalse(Integer userId);

    List<WorkOrder> findByDeletedStatusFalse();

    // Optional<WorkOrder> findByIdAndDeletedStatusFalse(Integer workorderId);
}

