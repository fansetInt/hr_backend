package com.fanset.dms.user.Job;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class JobRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;

    public JobRecord() {
    }

    public JobRecord(String heading) {
        this.title = heading;
    }

    public Long getId() {
        return id;
    }
    public JobRecord setId(Long id) {
        this.id = id;
        return this;
    }
    public String getTitle() {
        return title;
    }
    public JobRecord setTitle(String title) {
        this.title = title;
        return this;
    }
}
