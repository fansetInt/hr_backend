package com.fanset.dms.user.department;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@Tag(name = "DEPARTMENT")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DepartmentRequestDto departmentRequestDto){
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequestDto));
    }
    //update

    @PatchMapping
    public ResponseEntity<String> update(@RequestParam Long departmentId,
                                         @Valid DepartmentRequestDto departmentRequestDto){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId,departmentRequestDto));
    }

    //get by id

    @GetMapping()
    public ResponseEntity<DepartmentRecord> getById(@RequestParam Long departmentId){
        return departmentService.findById(departmentId)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    //get all
    @GetMapping("/all")
    public ResponseEntity<List<DepartmentRecord>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    public ResponseEntity<List<DepartmentRecord>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
