package com.fanset.dms.user.Job;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface JobRepository extends JpaRepository<JobRecord,Long>{

    JobRecord findByTitle(String heading);
}
