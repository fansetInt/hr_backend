package com.fanset.dms.user.department;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "department_record")
@Data
public class DepartmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    private int noOfEmployees;

    public DepartmentRecord(String name, int noOfEmployees) {
        this.name = name;
        this.noOfEmployees = noOfEmployees;
    }

    public DepartmentRecord() {
    }
}