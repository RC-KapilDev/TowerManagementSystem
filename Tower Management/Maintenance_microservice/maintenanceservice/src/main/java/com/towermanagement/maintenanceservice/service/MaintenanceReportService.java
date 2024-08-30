package com.towermanagement.maintenanceservice.service;


import com.towermanagement.maintenanceservice.model.MaintenanceReport;
import com.towermanagement.maintenanceservice.repository.MaintenanceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service

public class MaintenanceReportService {

    private final MaintenanceReportRepository maintenanceReportRepository;
    private final UserService userService;
    private final TowerService towerService;
    private final WorkorderService workorderService;

   
    public MaintenanceReportService(MaintenanceReportRepository maintenanceReportRepository, UserService userService, TowerService towerService, WorkorderService workorderService) {
        this.maintenanceReportRepository = maintenanceReportRepository;
        this.userService = userService;
        this.towerService=towerService;
        this.workorderService=workorderService;
    }

    public List<MaintenanceReport> getAllMaintenanceReports() {
        return maintenanceReportRepository.findAll();
    }

    public Optional<MaintenanceReport> getMaintenanceReportById(Integer id) {
        return maintenanceReportRepository.findById(id);
    }

    public MaintenanceReport createMaintenanceReport(MaintenanceReport maintenanceReport) {
        // Validate if the user exists
        userService.validateUserExists(maintenanceReport.getUser());

        //Validate if the tower exists
        towerService.validateTowerExists(maintenanceReport.getTowerInfo());

        //Validate if the workorder exists
        workorderService.validateWorkOrderExists(maintenanceReport.getWorkOrder());

        

        return maintenanceReportRepository.save(maintenanceReport);
    }

    public MaintenanceReport updateMaintenanceReport(Integer id, MaintenanceReport updatedReport) {
        // Validate if the user exists
        userService.validateUserExists(updatedReport.getUser());

         //Validate if the tower exists

        towerService.validateTowerExists(updatedReport.getTowerInfo());

        //Validate if the workorder exists
        workorderService.validateWorkOrderExists(updatedReport.getWorkOrder());

        return maintenanceReportRepository.findById(id)
                .map(report -> {
                    report.setUser(updatedReport.getUser());
                    report.setWorkOrder(updatedReport.getWorkOrder());
                    report.setTowerInfo(updatedReport.getTowerInfo());
                    report.setEquipmentRequired(updatedReport.getEquipmentRequired());
                    report.setIssuesFaced(updatedReport.getIssuesFaced());
                    report.setPriority(updatedReport.getPriority());
                    report.setDeletedStatus(updatedReport.getDeletedStatus());
                    return maintenanceReportRepository.save(report);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance Report not found"));
    }

    public List<MaintenanceReport> getReportsByTowerId(Integer towerId) {
        return maintenanceReportRepository.findByTowerInfo(towerId);
    }

    public List<MaintenanceReport> getReportsByPriority(String priority) {
        return maintenanceReportRepository.findByPriority(priority);
    }
}
