package com.fanset.dms.user.department;


import com.fanset.dms.user.Job.JobRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<DepartmentRecord,Long> {
    @Query("Select D FROM DepartmentRecord d WHERE d.name =:name")
    DepartmentRecord findByName(@Param("name") String name);



}
