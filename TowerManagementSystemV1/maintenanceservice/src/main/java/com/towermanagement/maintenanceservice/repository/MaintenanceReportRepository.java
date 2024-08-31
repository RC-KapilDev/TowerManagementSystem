package com.towermanagement.maintenanceservice.repository;


import com.towermanagement.maintenanceservice.model.MaintenanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceReportRepository extends JpaRepository<MaintenanceReport, Integer> {
    List<MaintenanceReport> findByTowerInfo(Integer towerId);
    List<MaintenanceReport> findByPriority(String priority);
}

