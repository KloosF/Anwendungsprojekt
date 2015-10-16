package org.deidentifier.arx.distribution.model;

import org.apache.commons.math3.distribution.AbstractRealDistribution;

public class ContinuousDistribution extends AbstractDistribution<Double>{

	private double min;
	private double max;
	private boolean asDiscrete;
	private AbstractRealDistribution distribution;
	
	public ContinuousDistribution(double min, double max, boolean asDiscrete,
			AbstractRealDistribution distribution) {
		this.min = min;
		this.max = max;
		this.asDiscrete = asDiscrete;
		this.distribution = distribution;
	}

	@Override
	public Double getMinimum() {
		return min;
	}

	@Override
	public Double getMaximum() {
		return max;
	}
	
	public boolean getDiscreteFlag(){
		return asDiscrete;
	}

	@Override
	public double getValue(Double value) {
		return distribution.density(value);
	}
}
