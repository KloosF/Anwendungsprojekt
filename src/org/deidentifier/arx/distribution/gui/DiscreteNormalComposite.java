package org.deidentifier.arx.distribution.gui;

import helper.classes.WrappedRealDistribution;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class DiscreteNormalComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int mean;
	/** Field*/
	private final int stdDev;

	public DiscreteNormalComposite(Composite parent) {
		super(parent);
		mean = createDoubleField("Mean", 5d, -Double.MAX_VALUE, Double.MAX_VALUE);
		stdDev = createDoubleField("Standard deviation", 0.8d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.mean);
		Double param2 = super.getDouble(this.stdDev);
		
		if (param1 == null || param2 == null || param2 == 0) {
			return null;
		}
		
		if (param2 <= 0d) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1, param2);
		return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new NormalDistribution(param1, param2)));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private double[] getMinimumMaximum(double mean, double stddev) {
		NormalDistribution distribution = new NormalDistribution(mean, stddev);
		double x2 = getXWhereYLessThanOrEqualTo(distribution, mean, 0.0001d);
		double x1 = mean - (x2 - mean); 
		return new double[]{x1, x2};	
	}
}