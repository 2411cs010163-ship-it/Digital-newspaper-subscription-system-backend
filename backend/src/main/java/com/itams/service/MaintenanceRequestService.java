package com.itams.service;

import com.itams.entity.Asset;
import com.itams.entity.Employee;
import com.itams.entity.MaintenanceRequest;
import com.itams.repository.AssetRepository;
import com.itams.repository.EmployeeRepository;
import com.itams.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaintenanceRequestService {

    @Autowired
    private MaintenanceRequestRepository requestRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<MaintenanceRequest> getAll() {
        return requestRepository.findAll();
    }

    public MaintenanceRequest getById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found with id: " + id));
    }

    // Raise a new maintenance request; asset moves to IN_MAINTENANCE
    public MaintenanceRequest create(Long assetId, Long employeeId, String issueDescription) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        MaintenanceRequest request = new MaintenanceRequest();
        request.setAsset(asset);
        request.setEmployee(employee);
        request.setIssueDescription(issueDescription);
        request.setRequestDate(LocalDate.now());
        request.setStatus(MaintenanceRequest.RequestStatus.OPEN);

        asset.setStatus(Asset.AssetStatus.IN_MAINTENANCE);
        assetRepository.save(asset);

        return requestRepository.save(request);
    }

    // Update status / resolve a request
    public MaintenanceRequest updateStatus(Long id, MaintenanceRequest.RequestStatus status, String resolutionNotes) {
        MaintenanceRequest request = getById(id);
        request.setStatus(status);
        request.setResolutionNotes(resolutionNotes);

        if (status == MaintenanceRequest.RequestStatus.RESOLVED || status == MaintenanceRequest.RequestStatus.CLOSED) {
            request.setResolvedDate(LocalDate.now());
            Asset asset = request.getAsset();
            asset.setStatus(Asset.AssetStatus.AVAILABLE);
            assetRepository.save(asset);
        }

        return requestRepository.save(request);
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
