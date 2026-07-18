package com.itams.controller;

import com.itams.entity.AssetAllocation;
import com.itams.service.AssetAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allocations")
public class AssetAllocationController {

    @Autowired
    private AssetAllocationService allocationService;

    @GetMapping
    public List<AssetAllocation> getAll() {
        return allocationService.getAll();
    }

    @GetMapping("/{id}")
    public AssetAllocation getById(@PathVariable Long id) {
        return allocationService.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<AssetAllocation> getByEmployee(@PathVariable Long employeeId) {
        return allocationService.getByEmployee(employeeId);
    }

    // body: { "assetId": 1, "employeeId": 2, "remarks": "..." }
    @PostMapping
    public AssetAllocation allocate(@RequestBody Map<String, Object> body) {
        Long assetId = Long.valueOf(body.get("assetId").toString());
        Long employeeId = Long.valueOf(body.get("employeeId").toString());
        String remarks = body.get("remarks") != null ? body.get("remarks").toString() : null;
        return allocationService.allocate(assetId, employeeId, remarks);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        allocationService.delete(id);
    }
}
