package org.deidentifier.arx.distribution.gui;

import org.apache.commons.math3.distribution.PascalDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class PascalComposite extends AbstractDistributionComposite<Integer>{

	/** Field*/
	private final int probability;
	/** Field*/
	private final int numberOfSuccess;

	public PascalComposite(Composite parent) {
		super(parent);
		probability = createDoubleField("Probability", 0.5d, 0d, Double.MAX_VALUE);
		numberOfSuccess = createIntegerField("Number of success", 3, 1, Integer.MAX_VALUE);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Double param1 = super.getDouble(this.probability);
		Integer param2 = super.getInteger(this.numberOfSuccess);
		
		if (param1 == null || param2 == null) {
			return null;
		}
		
		if (param2 <= 0d) {
			return null;
		}
		
		int[] minmax = getMinimumMaximum(param1, param2);
		return new DiscreteDistribution(0, minmax[1], 
										  new PascalDistribution(param2, param1));
	}

	/**
	 * Get minimum and maximum
	 * @param mean
	 * @param stddev
	 * @return
	 */
	private int[] getMinimumMaximum(double probability, int numberOfSuccess) {
		PascalDistribution distribution = new PascalDistribution(numberOfSuccess, probability);
		int x2 = getXWhereYLessThanOrEqualTo(distribution, 0, 0.0001d);
		return new int[]{0, x2};	
	}

}
