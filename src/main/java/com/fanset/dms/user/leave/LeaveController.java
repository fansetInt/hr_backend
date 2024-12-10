package com.fanset.dms.user.leave;


import com.fanset.dms.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/leave")
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@Tag(name = "LEAVE")
@RestController
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    @Operation(summary = "leave submitting ")
    public ResponseEntity<String> submitLeave(@Valid @RequestBody LeaveRequestDto request) {
        var response = leaveService.submitLeaveRequest(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{leaveId}/approve")
    @Operation(summary = "leave approval ")
    public ResponseEntity<String> approveLeave(@PathVariable Long leaveId) {

        System.out.println(leaveId);
        System.out.println(leaveId);
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User principal = (User) authentication.getPrincipal();

        var response = leaveService.approve(principal.getUserId(), leaveId);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/{leaveId}/reject")
    @Operation(summary = "leave approval ")
    public ResponseEntity<String> rejectLeave(@PathVariable Long leaveId,
                                              @RequestBody @Valid RejectRequestDto reason) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String approver= principal.getJobRecordSet().get(0).getTitle();
        var response = leaveService.rejectLeaveRequest( leaveId,approver,"reject");
        return ResponseEntity.ok(response);
    }



    @GetMapping("")
    @Operation(summary = "leave approval ")
    public ResponseEntity<Page<LeaveRequest>> getLeaveFor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String approver= principal.getJobRecordSet().get(0).getTitle();
        Long departmentId = (long) principal.getDepartment().getId();

        var response = leaveService.getPendingLeaveRequests(departmentId,approver);
        return ResponseEntity.ok(response);
    }

}