package com.dai.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
    // This interface will automatically provide CRUD operations for Job entities
    // No additional methods are needed unless custom queries are required
}
