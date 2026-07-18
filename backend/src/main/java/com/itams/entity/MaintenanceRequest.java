package com.itams.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_requests")
@Getter
@Setter
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(length = 1000)
    private String issueDescription;

    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status; // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    @Column(length = 1000)
    private String resolutionNotes;

    private LocalDate resolvedDate;

    public enum RequestStatus {
        OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }
}
