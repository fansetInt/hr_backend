package com.fanset.dms.user.department;


import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EntityManager entityManager;

    public DepartmentService(DepartmentRepository departmentRepository, EntityManager entityManager) {
        this.departmentRepository = departmentRepository;
        this.entityManager = entityManager;
    }




    @Transactional
    //create a new department
    public String createDepartment(DepartmentRequestDto departmentRequestDto){
        DepartmentRecord departmentRecord = new DepartmentRecord(departmentRequestDto.name(),departmentRequestDto.noOfEmployees());
        entityManager.persist(departmentRecord);
        return "Department created successfully";
    }
    //

    @Transactional
    //update an existing department
    public String updateDepartment(Long departmentId,DepartmentRequestDto departmentRequestDto){
        DepartmentRecord departmentRecord = departmentRepository.getReferenceById(departmentId);
        departmentRecord.setName(departmentRequestDto.name());
        departmentRecord.setNoOfEmployees(departmentRequestDto.noOfEmployees());
        entityManager.merge(departmentRecord);
        return "Department updated successfully";
    }



    //find by id
    public Optional<DepartmentRecord> findById(Long departmentId){
        return departmentRepository.findById(departmentId);
    }


    //get all
    public List<DepartmentRecord> getAllDepartments(){
        return departmentRepository.findAll();
    }

}
