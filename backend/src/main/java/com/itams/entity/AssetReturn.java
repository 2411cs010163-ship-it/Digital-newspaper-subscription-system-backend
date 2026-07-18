package com.itams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "asset_returns")
@Getter
@Setter
public class AssetReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "allocation_id", nullable = false)
    private AssetAllocation allocation;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private Condition assetCondition; // GOOD, DAMAGED, NEEDS_REPAIR

    @Column(length = 1000)
    private String remarks;

    public enum Condition {
        GOOD, DAMAGED, NEEDS_REPAIR
    }
}
