package com.mpt.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mpt.backend.model.CompanyPerformanceHistory;
import com.mpt.backend.model._CompanyPerformanceHistoryId;

public interface CompanyPerformanceHistoryRepository extends JpaRepository<CompanyPerformanceHistory, _CompanyPerformanceHistoryId> {
	
	List<CompanyPerformanceHistory> findByCieId(String cieId);
	//Get the latest quote
	CompanyPerformanceHistory findTopByCieIdOrderByDateDesc(String cieId);
	
}
