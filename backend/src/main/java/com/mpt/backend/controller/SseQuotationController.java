package com.mpt.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpt.backend.model.CompanyPerformanceHistory;
import com.mpt.backend.repository.CompanyPerformanceHistoryRepository;
import com.mpt.backend.service.ValueGeneratorService;
import com.mpt.backend.service.utils.FloatModifier;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
@RequestMapping("/real-time-quote")
public class SseQuotationController {

	private final ValueGeneratorService valueGeneratorService;
	
	@Autowired
	private CompanyPerformanceHistoryRepository cphRepository;
	
	@Autowired
    public SseQuotationController(ValueGeneratorService valueGeneratorService) {
        this.valueGeneratorService = valueGeneratorService;
    }

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Float> streamQuotes(@PathVariable String id) {
		Float initialValue = cphRepository.findTopByCieIdOrderByDateDesc(id).getClose();
		return valueGeneratorService.generateSequence(initialValue, FloatModifier.randomNormalModifier).onErrorComplete();
	}
	
}
