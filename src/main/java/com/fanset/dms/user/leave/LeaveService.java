package com.fanset.dms.user.leave;


import com.fanset.dms.user.Job.JobRecord;
import com.fanset.dms.user.enums.Role;
import com.fanset.dms.user.leave.aprroval.Approval;
import com.fanset.dms.user.leave.leaverecord.LeaveRecord;
import com.fanset.dms.user.model.User;
import com.fanset.dms.user.repository.UserRepository;
import com.fanset.dms.utils.ApplicationConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LeaveService {

    private final EntityManager entityManager;
    private final LeaveRequestRepository leaveRepository;
    private final UserRepository userRepository;


    public LeaveService(EntityManager entityManager, LeaveRequestRepository leaveRepository, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
    }



    @Transactional
    public String submitLeaveRequest(final LeaveRequestDto leaveRequestDto){

        Optional<User> user = userRepository.findById(leaveRequestDto.userId());
        if (!user.isPresent()) {
            return "User not found";
        }

//        LeaveRecord leaveRecord = new LeaveRecord();
        LeaveRequest leaveRequest = new LeaveRequest(
                user.get(),
                leaveRequestDto.leaveType(),
                leaveRequestDto.startDate(),
                leaveRequestDto.endDate(),
                leaveRequestDto.dayTaken(),
                leaveRequestDto.reason()
        );

        leaveRequest.setStatus(LeaveStatus.PENDING_MANAGER_APPROVAL);
        leaveRequest.setApproval("Manager");
        leaveRequest.setSubmitedAt(LocalDateTime.now());
        leaveRequest.setUpdatedAt(LocalDateTime.now());
        LeaveRequest leave = leaveRepository.save(leaveRequest);
//        entityManager.persist(leaveRequest);

        return "done and the id is "+leave.getId();
    }


    @Transactional
    public String approve(Long userID,Long leaveId){
        Optional<User> user = userRepository.findById(userID);
        if (!user.isPresent()) {
            return "User not found";
        }

        System.out.println("approve service"+leaveId );
        Optional<LeaveRequest> leaveRequest = leaveRepository.findById(leaveId);
        if(!leaveRequest.isPresent()){
            return "Leave request not found";
        }

        System.out.println("approve service::"+leaveId );

        List<JobRecord> job = user.get().getJobRecordSet();
        if (job.isEmpty()) {
            return "Job list is empty, no details available";
        }
        String jobdetails = job.get(0).getTitle();
        System.out.println( "job details::"+jobdetails);
        if (jobdetails == null || jobdetails.isEmpty()) {
            return "No job details found";
        }
        LeaveRequest leaveRequest1 = approveLeaveRequest(leaveId, jobdetails);
        return "done application";
    }


//
//    private HashMap<Long, Integer> getTheTypeOfLeave(LeaveType leaveType) {
//        HashMap<Long, Integer> leaveDaysMap = new HashMap<>();
//        int leaveDays = switch (leaveType) {
//            case ANNUAL, VACATION -> ApplicationConstants.MAXIMUM_NUMBER_OF_LEAVE_DAYS_IN_MONTH;
//            case SICK -> ApplicationConstants.STUDY_DAYS;
//            case MATERNITY -> ApplicationConstants.MATERNITY_DAYS_IN_MONTH;
//            default -> 0; // Default value if the type doesn't match any predefined case
//        };
//
//        leaveDaysMap.put(System.currentTimeMillis(), leaveDays); // Example key (current timestamp)
//        return leaveDaysMap;
//    }

//    public HashMap<LeaveType,Double> getLeaveType(LeaveType leaveType) {
//        HashMap<LeaveType, Double> leaveTypeDaysMap = new HashMap<>();
//        int leaveDays = switch (leaveType) {
//            case ANNUAL, VACATION -> ApplicationConstants.MAXIMUM_NUMBER_OF_LEAVE_DAYS_IN_MONTH;
//            case SICK -> ApplicationConstants.STUDY_DAYS;
//            case MATERNITY -> ApplicationConstants.MATERNITY_DAYS_IN_MONTH;
//            default -> 0; // Default value if the type doesn't match any predefined case
//        };
//
//
//        Double nyew = (double) leaveDays;
//        leaveTypeDaysMap.put(leaveType, nyew);
//        return leaveTypeDaysMap;
//    }



    @Transactional
    // Approve a leave request
    public LeaveRequest approveLeaveRequest(Long requestId, String approver) {
        LeaveRequest leaveRequest = leaveRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        switch (leaveRequest.getStatus()) {
            case PENDING_MANAGER_APPROVAL:
                if (!"Manager".equalsIgnoreCase(approver)) {
                    throw new IllegalArgumentException("Only the manager can approve this request");
                }
                leaveRequest.setStatus(LeaveStatus.PENDING_HR_APPROVAL);
                leaveRequest.setApproval("HR");
                break;

            case PENDING_HR_APPROVAL:
                if (!"HR".equalsIgnoreCase(approver)) {
                    throw new IllegalArgumentException("Only HR can approve this request");
                }
                leaveRequest.setStatus(LeaveStatus.PENDING_CHIEF_APPROVAL);
                leaveRequest.setApproval("Chief");
                break;

            case PENDING_CHIEF_APPROVAL:
                if (!"Chief".equalsIgnoreCase(approver)) {
                    throw new IllegalArgumentException("Only the Chief can approve this request");
                }
                leaveRequest.setStatus(LeaveStatus.APPROVED);
                User employee = leaveRequest.getEmployee();
                if(leaveRequest.getLeaveType()== LeaveType.ANNUAL
                || leaveRequest.getLeaveType()== LeaveType.COMPASSIONATE){
                    employee.setRemainingDays(employee.getRemainingDays() - leaveRequest.getDaystotaldays());
                    userRepository.save(employee);
                }
                leaveRequest.setApproval(null); // Final stage
                break;
            default:
                throw new IllegalStateException("Cannot approve a request in its current state");
        }

        leaveRequest.setUpdatedAt(LocalDateTime.now());
        return leaveRepository.save(leaveRequest);
    }

    // Reject a leave request
    public String rejectLeaveRequest(Long requestId, String approver,String  rejectionReason) {
        LeaveRequest leaveRequest = leaveRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        if (!approver.equalsIgnoreCase(leaveRequest.getApproval())) {
            throw new IllegalArgumentException("Only the current approver can reject this request");
        }
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        leaveRequest.setUpdatedAt(LocalDateTime.now());
        leaveRequest.setRejectionReason(rejectionReason);
        leaveRepository.save(leaveRequest);
        return "reject succesesfully";
    }


    public Page<LeaveRequest> getPendingLeaveRequests(Long departmentId, String approver) {
        Pageable pageable = PageRequest.of(0, 10); // First page, 10 items per page
        Page<LeaveRequest> leaveRequests = leaveRepository.findByDepartmentIdAndApprover(departmentId, approver, pageable);

        return leaveRequests;
    }

}
