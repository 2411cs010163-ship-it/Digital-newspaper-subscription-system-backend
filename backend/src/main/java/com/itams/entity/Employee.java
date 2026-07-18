package com.itams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    private String department;

    private String designation;

    @Column(name = "employee_code", unique = true)
    private String employeeCode;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<AssetAllocation> allocations;
}
