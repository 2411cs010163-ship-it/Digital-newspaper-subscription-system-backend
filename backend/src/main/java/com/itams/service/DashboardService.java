package com.itams.service;

import com.itams.entity.Asset;
import com.itams.entity.AssetAllocation;
import com.itams.entity.MaintenanceRequest;
import com.itams.repository.AssetAllocationRepository;
import com.itams.repository.AssetRepository;
import com.itams.repository.EmployeeRepository;
import com.itams.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetAllocationRepository allocationRepository;

    @Autowired
    private MaintenanceRequestRepository maintenanceRequestRepository;

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        List<Asset> allAssets = assetRepository.findAll();

        summary.put("totalAssets", allAssets.size());
        summary.put("totalEmployees", employeeRepository.count());
        summary.put("activeAllocations",
                allocationRepository.findByStatus(AssetAllocation.AllocationStatus.ACTIVE).size());
        summary.put("openMaintenanceRequests",
                maintenanceRequestRepository.findByStatus(MaintenanceRequest.RequestStatus.OPEN).size());

        Map<String, Long> assetsByStatus = allAssets.stream()
                .collect(Collectors.groupingBy(a -> a.getStatus().name(), Collectors.counting()));
        summary.put("assetsByStatus", assetsByStatus);

        Map<String, Long> assetsByType = allAssets.stream()
                .collect(Collectors.groupingBy(a -> a.getType().name(), Collectors.counting()));
        summary.put("assetsByType", assetsByType);

        return summary;
    }
}
