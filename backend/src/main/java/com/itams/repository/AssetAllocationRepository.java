package com.itams.repository;

import com.itams.entity.AssetAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation, Long> {
    List<AssetAllocation> findByEmployeeId(Long employeeId);
    List<AssetAllocation> findByAssetId(Long assetId);
    List<AssetAllocation> findByStatus(AssetAllocation.AllocationStatus status);
}
