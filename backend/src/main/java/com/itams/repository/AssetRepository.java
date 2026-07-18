package com.itams.repository;

import com.itams.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(Asset.AssetStatus status);
    List<Asset> findByType(Asset.AssetType type);
}
