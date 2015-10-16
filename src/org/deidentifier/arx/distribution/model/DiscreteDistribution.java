package org.deidentifier.arx.distribution.model;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class DiscreteDistribution extends AbstractDistribution<Integer>{

	private int min;
	private int max;
	private AbstractIntegerDistribution distribution;
	
	public DiscreteDistribution(int min, int max,
			AbstractIntegerDistribution distribution) {
		this.min = min;
		this.max = max;
		this.distribution = distribution;
	}

	@Override
	public Integer getMinimum() {
		return min;
	}

	@Override
	public Integer getMaximum() {
		return max;
	}

	@Override
	public double getValue(Integer value) {
		return distribution.probability(value);
	}
}
