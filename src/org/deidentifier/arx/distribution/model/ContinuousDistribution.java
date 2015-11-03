package org.deidentifier.arx.distribution.model;

import org.apache.commons.math3.distribution.AbstractRealDistribution;

public class ContinuousDistribution extends AbstractDistribution<Double>{

	private double min;
	private double max;
	private AbstractRealDistribution distribution;
	
	public ContinuousDistribution(double min, double max, double yLimit, AbstractRealDistribution distribution) {
		super(yLimit);
		this.min = min;
		this.max = max;
		this.distribution = distribution;
	}

	public ContinuousDistribution(double min, double max, AbstractRealDistribution distribution) {
		this(min, max, 0d, distribution);
	}

	@Override
	public Double getMinimum() {
		return min;
	}

	@Override
	public Double getMaximum() {
		return max;
	}

	@Override
	public double getValue(Double value) {
		return distribution.density(value);
	}
}
