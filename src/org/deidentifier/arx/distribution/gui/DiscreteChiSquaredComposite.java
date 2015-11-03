package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.deidentifier.arx.distribution.helpers.WrappedRealDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class DiscreteChiSquaredComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int degreesOfFreedom;
	
	public DiscreteChiSquaredComposite(Composite parent) {
		super(parent);
		degreesOfFreedom = createIntegerField("Degrees of freedom", 3, 1, Integer.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.degreesOfFreedom);
		
		if (param1 == null) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1);
		if (param1 != 1) {
			return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new ChiSquaredDistribution(param1)));
		}
		else {
			return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new ChiSquaredDistribution(param1)));
		}
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private double[] getMinimumMaximum(double degreesOfFreedom) {
		ChiSquaredDistribution distribution = new ChiSquaredDistribution(degreesOfFreedom);
		double x2 = getXWhereYLessThanOrEqualTo(distribution, 0, 0.0001d);
		return new double[]{0, x2};
		
	}
}