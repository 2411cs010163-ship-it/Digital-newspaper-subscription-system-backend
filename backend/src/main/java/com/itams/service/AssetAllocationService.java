package com.itams.service;

import com.itams.entity.Asset;
import com.itams.entity.AssetAllocation;
import com.itams.entity.Employee;
import com.itams.repository.AssetAllocationRepository;
import com.itams.repository.AssetRepository;
import com.itams.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetAllocationService {

    @Autowired
    private AssetAllocationRepository allocationRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<AssetAllocation> getAll() {
        return allocationRepository.findAll();
    }

    public AssetAllocation getById(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + id));
    }

    public List<AssetAllocation> getByEmployee(Long employeeId) {
        return allocationRepository.findByEmployeeId(employeeId);
    }

    // Allocate an available asset to an employee
    public AssetAllocation allocate(Long assetId, Long employeeId, String remarks) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + assetId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        if (asset.getStatus() != Asset.AssetStatus.AVAILABLE) {
            throw new RuntimeException("Asset is not available for allocation. Current status: " + asset.getStatus());
        }

        AssetAllocation allocation = new AssetAllocation();
        allocation.setAsset(asset);
        allocation.setEmployee(employee);
        allocation.setAllocatedDate(LocalDate.now());
        allocation.setStatus(AssetAllocation.AllocationStatus.ACTIVE);
        allocation.setAllocationRemarks(remarks);

        asset.setStatus(Asset.AssetStatus.ALLOCATED);
        assetRepository.save(asset);

        return allocationRepository.save(allocation);
    }

    public void delete(Long id) {
        allocationRepository.deleteById(id);
    }
}
