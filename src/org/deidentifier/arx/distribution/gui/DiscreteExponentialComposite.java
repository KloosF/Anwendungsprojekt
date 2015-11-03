package org.deidentifier.arx.distribution.gui;

import helper.classes.WrappedRealDistribution;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class DiscreteExponentialComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int mean;

	public DiscreteExponentialComposite(Composite parent) {
		super(parent);
		mean = createDoubleField("Rate parameter", 1d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.mean);
		
		if (param1 == null || param1 == 0) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1);
		return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new ExponentialDistribution(param1)));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private double[] getMinimumMaximum(double mean) {
		ExponentialDistribution distribution = new ExponentialDistribution(mean);
		double x2 = getXWhereYLessThanOrEqualTo(distribution, 0, 0.0001d);
		return new double[]{0, x2};
		
	}
}
