package com.dai.firstjobapp.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dai.firstjobapp.company.Company;
import com.dai.firstjobapp.company.CompanyRepository;
import com.dai.firstjobapp.company.CompanyService;
import com.dai.firstjobapp.job.Job;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company companyToUpdate = optionalCompany.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());

            if (companyToUpdate.getJobs() != null) {
                companyToUpdate.getJobs().clear(); // orphanRemoval sẽ xóa job cũ
            }

            if (company.getJobs() != null) {
                for (Job job : company.getJobs()) {
                    job.setCompany(companyToUpdate); // gán đúng reference ngược
                    companyToUpdate.getJobs().add(job); // thêm vào list của company
                }
            }
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
