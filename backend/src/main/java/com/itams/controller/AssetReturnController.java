package com.itams.controller;

import com.itams.entity.AssetReturn;
import com.itams.service.AssetReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/returns")
public class AssetReturnController {

    @Autowired
    private AssetReturnService returnService;

    @GetMapping
    public List<AssetReturn> getAll() {
        return returnService.getAll();
    }

    @GetMapping("/{id}")
    public AssetReturn getById(@PathVariable Long id) {
        return returnService.getById(id);
    }

    // body: { "allocationId": 1, "condition": "GOOD", "remarks": "..." }
    @PostMapping
    public AssetReturn processReturn(@RequestBody Map<String, Object> body) {
        Long allocationId = Long.valueOf(body.get("allocationId").toString());
        AssetReturn.Condition condition = AssetReturn.Condition.valueOf(body.get("condition").toString());
        String remarks = body.get("remarks") != null ? body.get("remarks").toString() : null;
        return returnService.processReturn(allocationId, condition, remarks);
    }
}
