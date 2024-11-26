package com.fanset.dms.user.Job;


//import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class JobService implements IJobService  {


    private final JobRepository jobRepository;
    private final EntityManager entityManager;

    public JobService(JobRepository jobRepository, EntityManager entityManager) {
        this.jobRepository = jobRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public String updateJob(JobDtoRequest jobDtoRequest){
        JobRecord jobRecord = new JobRecord(jobDtoRequest.heading());
        entityManager.merge(jobRecord);
        return "done";
    }


    @Transactional
    public String createJob(final JobDtoRequest jobDtoRequest){
        // find if already existing job
//        JobRecord existingJob = jobRepository.findByTitle(jobDtoRequest.heading());
//        if (existingJob!=null){
//            throw new IllegalStateException("Job with this title already exists");
//        }
        // create new job record and save it
        JobRecord jobRecord = new JobRecord();
        jobRecord.setTitle(jobDtoRequest.heading());
        System.out.println(jobDtoRequest.heading());
        jobRepository.save(jobRecord);
        return "done";
    }



    @Transactional
    public String deleteJob(final  Long jobId){
        // find if already existing job
        JobRecord existingJob = jobRepository.getReferenceById(jobId);
        if (existingJob!=null){
            throw new IllegalStateException("Job with this title already exists");
        }
        // create new job record and save it
       jobRepository.deleteById(jobId);
        return "done";
    }
    public JobRecord getJobRecord(final String jobId){
        return jobRepository.getReferenceById(Long.parseLong(jobId));
    }

    @Override
    public List<JobRecord> getAllJobs() {
        return jobRepository.findAll();
    }
}
