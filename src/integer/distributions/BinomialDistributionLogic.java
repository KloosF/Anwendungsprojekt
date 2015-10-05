package integer.distributions;

import org.apache.commons.math3.distribution.*;

public class BinomialDistributionLogic extends IntegerDistribution{

	public BinomialDistribution bin;
	
	public BinomialDistributionLogic(int trials, double p){
		//implicitly creates random seed
		bin = new BinomialDistribution(trials, p);
	}
	
	@Override
	public double calculate(int x){
		return bin.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[bin.getNumberOfTrials()+1];
		for (int i = 0; i <= bin.getNumberOfTrials(); i++) {
			ySeries[i] = bin.probability(i);
		}
		return ySeries;
	}
	
	public int[] createXSeries(){
		int[] xSeries = new int[bin.getNumberOfTrials()];
		for (int i = 0; i < bin.getNumberOfTrials(); i++) {
			xSeries[i] = i;
			//TODO: delete everywhere
		}
		return xSeries;
	}
}
