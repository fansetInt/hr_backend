package com.fanset.dms.user.leave;


import com.fanset.dms.user.leave.aprroval.Approval;
import com.fanset.dms.user.leave.leaverecord.LeaveRecord;
import com.fanset.dms.utils.ApplicationConstants;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional(readOnly = true)
public class LeaveService {

    private final EntityManager entityManager;
    private final LeaveRepository leaveRepository;


    public LeaveService(EntityManager entityManager, LeaveRepository leaveRepository) {
        this.entityManager = entityManager;
        this.leaveRepository = leaveRepository;
    }


    public String applyForLeave(final LeaveRequestDto leaveRequestDto){
        LeaveRecord leaveRecord = new LeaveRecord();
        LeaveRequest leaveRequest = new LeaveRequest();
        Approval approval = new Approval();


        //check the type of leave type
        // if annual subtract from  accrues

        LeaveType type = leaveRequestDto.leaveType();
        if (type != LeaveType.ANNUAL){
            // subtract from accrued leaves
        }

        // if sick check for doctors letter not subtracted if not

        //if study  allow 14 days  after showing the time  months from
        // if paternity allow 1 month from
        // if sick leave allow 1 month from
        // if special leave allow 2 weeks from

        // if medical leave allow 1 week from

        // if unpaid leave allow 1 day from

        // if other leave allow 2 days from

        // if leave is not allowed return appropriate message

        // if leave is allowed
        // save leave record
        // save leave request
        // save approval
        // return success message
        return "";
    }



    private HashMap<Long, Integer> getTheTypeOfLeave(LeaveType leaveType) {
        HashMap<Long, Integer> leaveDaysMap = new HashMap<>();
        int leaveDays = switch (leaveType) {
            case ANNUAL, VACATION -> ApplicationConstants.MAXIMUM_NUMBER_OF_LEAVE_DAYS_IN_MONTH;
            case SICK -> ApplicationConstants.STUDY_DAYS;
            case MATERNITY -> ApplicationConstants.MATERNITY_DAYS_IN_MONTH;
            default -> 0; // Default value if the type doesn't match any predefined case
        };

        leaveDaysMap.put(System.currentTimeMillis(), leaveDays); // Example key (current timestamp)
        return leaveDaysMap;
    }

    public HashMap<LeaveType,Double> getLeaveType(LeaveType leaveType) {
        HashMap<LeaveType, Double> leaveTypeDaysMap = new HashMap<>();
        int leaveDays = switch (leaveType) {
            case ANNUAL, VACATION -> ApplicationConstants.MAXIMUM_NUMBER_OF_LEAVE_DAYS_IN_MONTH;
            case SICK -> ApplicationConstants.STUDY_DAYS;
            case MATERNITY -> ApplicationConstants.MATERNITY_DAYS_IN_MONTH;
            default -> 0; // Default value if the type doesn't match any predefined case
        };
        Double leaveDays = (double) leaveDays;
//        double leaveDays = getTheTypeOfLeave(leaveType).values().stream().findFirst().orElse(0);

//        leaveTypeDaysMap.put(leaveType, leaveDays); // Example key (leave type)
        return leaveTypeDaysMap;
    }

}
