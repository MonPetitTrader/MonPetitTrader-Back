package com.mpt.backend.service;

import java.time.Duration;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ValueGeneratorService {

	public ValueGeneratorService() {
	}

	public Flux<Float> generateSequence(float initialValue, Function<Float, Float> f) {
		return Flux.<Float, Float>generate(() -> initialValue, (lastValue, sink) -> {
			Float newValue = f.apply(lastValue);
			sink.next(newValue);
			return newValue;
		}).delayElements(Duration.ofSeconds(10));
	}
}
