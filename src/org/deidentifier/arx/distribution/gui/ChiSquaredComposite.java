package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.ContinuousDistribution;
import org.eclipse.swt.widgets.Composite;

public class ChiSquaredComposite extends AbstractDistributionComposite<Double>{

	/** Field*/
	private final int degreesOfFreedom;

	private boolean discrete;
	
	public ChiSquaredComposite(Composite parent) {
		super(parent);
		degreesOfFreedom = createIntegerField("Degrees of freedom", 3, 1, Integer.MAX_VALUE);
	}
	
	@Override
	protected AbstractDistribution<Double> createResult() {
		
		Double param1 = super.getDouble(this.degreesOfFreedom);
		
		if (param1 == null) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1);
		if (param1 != 1) {
			return new ContinuousDistribution(minmax[0], minmax[1], discrete,
					  new ChiSquaredDistribution(param1));
		}
		else {
			return new ContinuousDistribution(minmax[0], minmax[1], discrete,
					  new ChiSquaredDistribution(param1));
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
	
	/**
	 * Sets the discrete flag if distribution should be handled and shown as one
	 * @param isDiscrete
	 */
	public void setDiscrete(boolean isDiscrete) {
		discrete = isDiscrete;
		setResult(createResult());
	}
}
