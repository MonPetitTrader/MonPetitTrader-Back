package com.mpt.backend.controller;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpt.backend.repository.CompanyPerformanceHistoryRepository;
import com.mpt.backend.service.ValueGeneratorService;
import com.mpt.backend.service.utils.FloatModifier;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/real-time-quote")
public class SseQuotationController {

	private static final Logger logger = Logger.getLogger(SseQuotationController.class.getName());
	private final ValueGeneratorService valueGeneratorService;
	
	@Autowired
	private CompanyPerformanceHistoryRepository cphRepository;
	
	@Autowired
    public SseQuotationController(ValueGeneratorService valueGeneratorService) {
        this.valueGeneratorService = valueGeneratorService;
    }

	@ExceptionHandler(IOException.class)
	public void handleIOException() {
	}

	@GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Float> streamQuotes(@PathVariable String id) {
		Float initialValue = cphRepository.findTopByCieIdOrderByDateDesc(id).getClose();
		long threadID = Thread.currentThread().threadId();
		
		return valueGeneratorService.generateSequence(initialValue, FloatModifier.randomNormalModifier)
				.doOnSubscribe(s -> logger.info("Connection opened: Thread ID = " + threadID))
				.doOnCancel(() -> logger.info("Connection closed: Thread ID = " + threadID))
				.doOnError(e -> logger.warning("Error occurred, closing connection: Thread ID = " + threadID))
				.onErrorComplete();

	}
	
}
