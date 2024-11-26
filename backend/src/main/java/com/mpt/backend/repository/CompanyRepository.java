package com.mpt.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpt.backend.model.Company;

public interface CompanyRepository extends JpaRepository<Company, String>  {

}
