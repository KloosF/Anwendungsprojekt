package integer.distributions;

import org.apache.commons.math3.distribution.*;

public class BinomialDistributionLogic {

	public BinomialDistribution bin;
	
	public BinomialDistributionLogic(int trials, double p){
		//implicitly creates random seed
		bin = new BinomialDistribution(trials, p);
	}
	
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
	
	public double[] createXSeries(){
		double[] xSeries = new double[bin.getNumberOfTrials()*2+1];
		for (int i = 0; i <= bin.getNumberOfTrials()*2; i++) {
			xSeries[i] = i/2;
			//TODO: delete everywhere
		}
		return xSeries;
	}
}
