package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.PoissonDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class PoissonComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int mean;

	public PoissonComposite(Composite parent) {
		super(parent);
		mean = createDoubleField("Mean", 5d, 0d, Double.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.mean);
		
		if (param1 == null || param1 == 0) {
			return null;
		}
		
		int[] minmax = getMinimumMaximum(param1);
		return new DiscreteDistribution(0, minmax[1], 
										  new PoissonDistribution(param1));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private int[] getMinimumMaximum(double mean) {
		PoissonDistribution distribution = new PoissonDistribution(mean);
		int x2 = getXWhereYLessThanOrEqualTo(distribution, 0, 0.0001d);
		return new int[]{0, x2};	
	}

}
