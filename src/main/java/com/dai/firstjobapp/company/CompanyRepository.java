package com.dai.firstjobapp.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository  extends JpaRepository<Company, Long> {
    
}
