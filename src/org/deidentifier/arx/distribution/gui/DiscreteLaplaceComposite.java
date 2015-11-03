package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.deidentifier.arx.distribution.helpers.WrappedRealDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class DiscreteLaplaceComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int location;
	/** Field*/
	private final int scale;

	public DiscreteLaplaceComposite(Composite parent) {
		super(parent);
		location = createDoubleField("Location parameter", 5d, -Double.MAX_VALUE, Double.MAX_VALUE);
		scale = createDoubleField("Scale parameter", 0.8d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.location);
		Double param2 = super.getDouble(this.scale);
		
		if (param1 == null || param2 == null || param2 == 0) {
			return null;
		}
		
		if (param2 <= 0d) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1, param2);
		return new DiscreteDistribution((int) Math.floor(minmax[0]), (int) Math.round(minmax[1]), new WrappedRealDistribution(new LaplaceDistribution(param1, param2)));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private double[] getMinimumMaximum(double location, double scale) {
		LaplaceDistribution distribution = new LaplaceDistribution(location, scale);
		double x2 = getXWhereYLessThanOrEqualTo(distribution, location, 0.0001d);
		double x1 = location - (x2 - location); 
		return new double[]{x1, x2};	
	}
}
