package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.ContinuousDistribution;
import org.eclipse.swt.widgets.Composite;

public class LaplaceComposite extends AbstractDistributionComposite<Double>{

	/** Field*/
	private final int location;
	/** Field*/
	private final int scale;

	public LaplaceComposite(Composite parent) {
		super(parent);
		location = createDoubleField("Location parameter", 5d, -Double.MAX_VALUE, Double.MAX_VALUE);
		scale = createDoubleField("Scale parameter", 0.2d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Double> createResult() {
		
		Double param1 = super.getDouble(this.location);
		Double param2 = super.getDouble(this.scale);
		
		if (param1 == null || param2 == null || param2 == 0) {
			return null;
		}
		
		if (param2 <= 0d) {
			return null;
		}
		
		double[] minmax = getMinimumMaximum(param1, param2);
		return new ContinuousDistribution(minmax[0], minmax[1], 
										  new LaplaceDistribution(param1, param2));
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
