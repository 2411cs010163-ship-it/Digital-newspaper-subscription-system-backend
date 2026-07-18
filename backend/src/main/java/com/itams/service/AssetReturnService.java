package com.itams.service;

import com.itams.entity.Asset;
import com.itams.entity.AssetAllocation;
import com.itams.entity.AssetReturn;
import com.itams.repository.AssetAllocationRepository;
import com.itams.repository.AssetRepository;
import com.itams.repository.AssetReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetReturnService {

    @Autowired
    private AssetReturnRepository returnRepository;

    @Autowired
    private AssetAllocationRepository allocationRepository;

    @Autowired
    private AssetRepository assetRepository;

    public List<AssetReturn> getAll() {
        return returnRepository.findAll();
    }

    public AssetReturn getById(Long id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset return not found with id: " + id));
    }

    // Process return of an allocated asset
    public AssetReturn processReturn(Long allocationId, AssetReturn.Condition condition, String remarks) {
        AssetAllocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + allocationId));

        if (allocation.getStatus() == AssetAllocation.AllocationStatus.RETURNED) {
            throw new RuntimeException("This asset allocation has already been returned.");
        }

        AssetReturn assetReturn = new AssetReturn();
        assetReturn.setAllocation(allocation);
        assetReturn.setReturnDate(LocalDate.now());
        assetReturn.setAssetCondition(condition);
        assetReturn.setRemarks(remarks);

        allocation.setStatus(AssetAllocation.AllocationStatus.RETURNED);
        allocation.setReturnedDate(LocalDate.now());
        allocationRepository.save(allocation);

        Asset asset = allocation.getAsset();
        asset.setStatus(condition == AssetReturn.Condition.GOOD
                ? Asset.AssetStatus.AVAILABLE
                : Asset.AssetStatus.IN_MAINTENANCE);
        assetRepository.save(asset);

        return returnRepository.save(assetReturn);
    }
}
