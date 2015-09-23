package real.distributions;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class ChiSquaredDistributionLogic {
	public ChiSquaredDistribution chi;
	
	public ChiSquaredDistributionLogic(double degreesOfFreedom, double inverseCumAccuracy){
		chi = new ChiSquaredDistribution(degreesOfFreedom, inverseCumAccuracy);
	}
	
	public double calculate(int x){
		return chi.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[11];
		for (int i = 0; i <= 10; i++) {
			ySeries[i] = chi.density(i);
			//TODO: delete everywhere
			System.out.println(chi.density(i));
		}
		System.out.println("--------------");
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
