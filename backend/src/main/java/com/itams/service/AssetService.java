package com.itams.service;

import com.itams.entity.Asset;
import com.itams.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    public Asset getById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }

    public List<Asset> getAvailable() {
        return assetRepository.findByStatus(Asset.AssetStatus.AVAILABLE);
    }

    public Asset create(Asset asset) {
        if (asset.getStatus() == null) {
            asset.setStatus(Asset.AssetStatus.AVAILABLE);
        }
        return assetRepository.save(asset);
    }

    public Asset update(Long id, Asset updated) {
        Asset existing = getById(id);
        existing.setAssetTag(updated.getAssetTag());
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setSerialNumber(updated.getSerialNumber());
        existing.setVendor(updated.getVendor());
        existing.setModel(updated.getModel());
        existing.setPurchaseDate(updated.getPurchaseDate());
        existing.setWarrantyExpiry(updated.getWarrantyExpiry());
        existing.setCost(updated.getCost());
        existing.setStatus(updated.getStatus());
        existing.setRemarks(updated.getRemarks());
        return assetRepository.save(existing);
    }

    public void delete(Long id) {
        assetRepository.deleteById(id);
    }
}
