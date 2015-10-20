package org.deidentifier.arx.distribution.gui;

import helper.classes.WrappedRealDistribution;

import org.apache.commons.math3.distribution.CauchyDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class DiscreteCauchyComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int median;
	/** Field*/
	private final int scale;
	
	public DiscreteCauchyComposite(Composite parent) {
		super(parent);
		median = createDoubleField("Location parameter", 5d, -Double.MAX_VALUE, Double.MAX_VALUE);
		scale = createDoubleField("Scale parameter", 0.5d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.median);
		Double param2 = super.getDouble(this.scale);
		
		if (param1 == null || param2 == null || param2 == 0) {
			return null;
		}
		
		if (param2 <= 0d) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1, param2);
		return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new CauchyDistribution(param1, param2)));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private double[] getMinimumMaximum(double median, double scale) {
		CauchyDistribution distribution = new CauchyDistribution(median, scale);
		double x2 = getXWhereYLessThanOrEqualTo(distribution, median, 0.0001d);
		double x1 = median - (x2 - median); 
		return new double[]{x1, x2};
		
	}
}