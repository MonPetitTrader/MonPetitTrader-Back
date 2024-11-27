package com.mpt.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpt.backend.model.CompanyPerformanceHistory;
import com.mpt.backend.repository.CompanyPerformanceHistoryRepository;

@RestController
@RequestMapping("/quote")
public class HistoryQuotation {
	
	@Autowired
	private CompanyPerformanceHistoryRepository cphRepository;
	
	@GetMapping("/{id}/latest")
	public CompanyPerformanceHistory getLatestQuote(@PathVariable String id) {
		return cphRepository.findTopByCieIdOrderByDateDesc(id);
	}
	
	@GetMapping("/{id}/all")
	public List<CompanyPerformanceHistory> getAllQuotes(@PathVariable String id){
		return cphRepository.findByCieId(id);
	}
}
