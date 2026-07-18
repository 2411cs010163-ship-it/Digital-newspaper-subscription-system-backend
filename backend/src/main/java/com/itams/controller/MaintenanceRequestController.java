package com.itams.controller;

import com.itams.entity.MaintenanceRequest;
import com.itams.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance-requests")
public class MaintenanceRequestController {

    @Autowired
    private MaintenanceRequestService requestService;

    @GetMapping
    public List<MaintenanceRequest> getAll() {
        return requestService.getAll();
    }

    @GetMapping("/{id}")
    public MaintenanceRequest getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    // body: { "assetId": 1, "employeeId": 2, "issueDescription": "..." }
    @PostMapping
    public MaintenanceRequest create(@RequestBody Map<String, Object> body) {
        Long assetId = Long.valueOf(body.get("assetId").toString());
        Long employeeId = Long.valueOf(body.get("employeeId").toString());
        String issueDescription = body.get("issueDescription").toString();
        return requestService.create(assetId, employeeId, issueDescription);
    }

    // body: { "status": "RESOLVED", "resolutionNotes": "..." }
    @PatchMapping("/{id}/status")
    public MaintenanceRequest updateStatus(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        MaintenanceRequest.RequestStatus status =
                MaintenanceRequest.RequestStatus.valueOf(body.get("status").toString());
        String notes = body.get("resolutionNotes") != null ? body.get("resolutionNotes").toString() : null;
        return requestService.updateStatus(id, status, notes);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        requestService.delete(id);
    }
}
