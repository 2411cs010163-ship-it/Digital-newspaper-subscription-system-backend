package com.itams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "asset_allocations")
@Getter
@Setter
public class AssetAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate allocatedDate;

    private LocalDate returnedDate;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status; // ACTIVE, RETURNED

    private String allocationRemarks;

    public enum AllocationStatus {
        ACTIVE, RETURNED
    }
}
