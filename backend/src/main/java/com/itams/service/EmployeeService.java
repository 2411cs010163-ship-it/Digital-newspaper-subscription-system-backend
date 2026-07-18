package com.itams.service;

import com.itams.entity.Employee;
import com.itams.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        existing.setDesignation(updated.getDesignation());
        existing.setEmployeeCode(updated.getEmployeeCode());
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
