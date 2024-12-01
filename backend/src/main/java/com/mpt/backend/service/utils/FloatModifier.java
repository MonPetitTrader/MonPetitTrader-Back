package com.mpt.backend.service.utils;

import java.util.Random;
import java.util.function.Function;

public class FloatModifier {
	private static final Random random = new Random();
	
	public static Function<Float, Float> randomNormalModifier = (Float x) -> {
		// Generate a normally distributed random value with mean 0 and standard deviation 0.005
	    double randomValue = random.nextGaussian() * 0.005;
	    return x * (1 + (float) randomValue);
	};
}