package com.mpt.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpt.backend.model.CompanyPerformanceHistory;
import com.mpt.backend.model._CompanyPerformanceHistoryId;

public interface CompanyPerformanceHistoryRepository extends JpaRepository<CompanyPerformanceHistory, _CompanyPerformanceHistoryId> {

}
