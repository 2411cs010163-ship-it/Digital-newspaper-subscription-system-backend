package com.itams.repository;

import com.itams.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {
    List<MaintenanceRequest> findByStatus(MaintenanceRequest.RequestStatus status);
    List<MaintenanceRequest> findByEmployeeId(Long employeeId);
}
