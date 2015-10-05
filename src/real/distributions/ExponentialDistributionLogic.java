package real.distributions;

import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExponentialDistributionLogic {
	public ExponentialDistribution exp;
	private double inv;
	
	public ExponentialDistributionLogic(double mean, double inverseCumAccuracy){
		exp = new ExponentialDistribution(mean, inverseCumAccuracy);
		inv = inverseCumAccuracy;
	}
	
	public double calculate(int x){
		return exp.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = exp.cumulativeProbability(i);
			//TODO: delete everywhere
			System.out.println(ySeries[i]);
		}
		System.out.println("---------------");
		return ySeries;
	}
	
	public double[] createXSeries(){
		double[] xSeries = new double[22];
		for (int i = 0; i <= 10; i++) {
			xSeries[i] = i/2;
			//TODO: delete everywhere
		}
		return xSeries;
	}
}
