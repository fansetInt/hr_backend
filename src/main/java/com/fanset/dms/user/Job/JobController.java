package com.fanset.dms.user.Job;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
@Tag(name = "JOBS")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Create a new job
    @RequestMapping("/create")
    public ResponseEntity<String> createJob(@Valid JobDtoRequest jobDtoRequest) {
        return ResponseEntity.ok(jobService.createJob(jobDtoRequest));
    }

    // Update an existing job
    @RequestMapping("/update")
    public ResponseEntity<String> updateJob(JobDtoRequest jobDtoRequest) {
        return ResponseEntity.ok(jobService.updateJob(jobDtoRequest));
    }

    // Delete a job
    @RequestMapping("/delete{jobId}")
    public ResponseEntity<String> deleteJob(@RequestParam Long jobId) {
        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }

}
