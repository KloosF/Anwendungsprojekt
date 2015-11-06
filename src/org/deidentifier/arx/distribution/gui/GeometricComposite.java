package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.GeometricDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class GeometricComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int probability;

	public GeometricComposite(Composite parent) {
		super(parent);
		probability = createDoubleField("Probability", 0.5d, 0d, 1d);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.probability);
		
		if (param1 == null || param1 == 0) {
			return null;
		}
		
		int[] minmax = getMinimumMaximum(param1);
		return new DiscreteDistribution(0, minmax[1], 
										  new GeometricDistribution(param1));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private int[] getMinimumMaximum(double probability) {
		GeometricDistribution distribution = new GeometricDistribution(probability);
		int x2 = getXWhereYLessThanOrEqualTo(distribution, 0, 0.001d);
		return new int[]{0, x2};
	}
}
