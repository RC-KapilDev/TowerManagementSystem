package com.towermanagement.maintenanceservice.controller;

import com.towermanagement.maintenanceservice.model.MaintenanceReport;
import com.towermanagement.maintenanceservice.service.MaintenanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceReportController {

    private final MaintenanceReportService maintenanceReportService;

    public MaintenanceReportController(MaintenanceReportService maintenanceReportService) {
        this.maintenanceReportService = maintenanceReportService;
    }

    @GetMapping
    public List<MaintenanceReport> getAllMaintenanceReports() {
        try {
            return maintenanceReportService.getAllMaintenanceReports();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve maintenance reports", e);
        }
    }

    @PostMapping
    public MaintenanceReport createMaintenanceReport(@RequestBody MaintenanceReport maintenanceReport) {
        try {
            return maintenanceReportService.createMaintenanceReport(maintenanceReport);
        } catch (ResponseStatusException e) {
            throw e; // Re-throw the exception for proper HTTP status and message
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create maintenance report", e);
        }
    }

    @PutMapping("/{id}")
    public MaintenanceReport updateMaintenanceReport(
            @PathVariable Integer id,
            @RequestBody MaintenanceReport maintenanceReport) {
        try {
            return maintenanceReportService.updateMaintenanceReport(id, maintenanceReport);
        } catch (ResponseStatusException e) {
            throw e; // Re-throw the exception for proper HTTP status and message
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update maintenance report", e);
        }
    }

    @GetMapping("/tower/{towerId}")
    public List<MaintenanceReport> getReportsByTowerId(@PathVariable Integer towerId) {
        try {
            return maintenanceReportService.getReportsByTowerId(towerId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve maintenance reports by tower ID", e);
        }
    }

    @GetMapping("/priority/{priority}")
    public List<MaintenanceReport> getReportsByPriority(@PathVariable String priority) {
        try {
            return maintenanceReportService.getReportsByPriority(priority);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve maintenance reports by priority", e);
        }
    }
}
