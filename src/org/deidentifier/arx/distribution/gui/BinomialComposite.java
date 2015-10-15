package org.deidentifier.arx.distribution.gui;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.widgets.Composite;

public class BinomialComposite extends AbstractDistributionComposite<Integer> {
	
	/** Field*/
	private final int numberOfTries;
	/** Field*/
	private final int probabilityOfSuccess;

	public BinomialComposite(Composite parent) {
		super(parent);
		numberOfTries = createIntegerField("Number of tries", 20, 0, Integer.MAX_VALUE);
		probabilityOfSuccess = createDoubleField("Probability of success", 0.5d, 0d, 1d);
		setResult(createResult());
	}
	
	@Override
	protected AbstractDistribution<Integer> createResult() {
		
		Integer param1 = null;
		Double param2 = null;
		
		param1 = super.getInteger(this.numberOfTries);
		param2 = super.getDouble(this.probabilityOfSuccess);
		
		if (param1 == null || param2 == null) {
			return null;
		}
		
		return new DiscreteDistribution(0, param1.intValue(), new BinomialDistribution(param1, param2));
	}
}
