package com.itams.controller;

import com.itams.entity.Asset;
import com.itams.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<Asset> getAll() {
        return assetService.getAll();
    }

    @GetMapping("/available")
    public List<Asset> getAvailable() {
        return assetService.getAvailable();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetService.getById(id);
    }

    @PostMapping
    public Asset create(@RequestBody Asset asset) {
        return assetService.create(asset);
    }

    @PutMapping("/{id}")
    public Asset update(@PathVariable Long id, @RequestBody Asset asset) {
        return assetService.update(id, asset);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        assetService.delete(id);
    }
}
