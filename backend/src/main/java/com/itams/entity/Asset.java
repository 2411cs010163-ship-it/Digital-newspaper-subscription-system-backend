package com.itams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "assets")
@Getter
@Setter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_tag", unique = true, nullable = false)
    private String assetTag;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetType type; // LAPTOP, DESKTOP, SOFTWARE_LICENSE, HARDWARE, OTHER

    private String serialNumber;

    private String vendor;

    private String model;

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiry;

    private Double cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status; // AVAILABLE, ALLOCATED, IN_MAINTENANCE, RETIRED

    private String remarks;

    public enum AssetType {
        LAPTOP, DESKTOP, SOFTWARE_LICENSE, HARDWARE, OTHER
    }

    public enum AssetStatus {
        AVAILABLE, ALLOCATED, IN_MAINTENANCE, RETIRED
    }
}
