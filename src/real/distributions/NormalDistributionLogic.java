package real.distributions;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionLogic {
	public NormalDistribution norm;
	
	public NormalDistributionLogic(double mean, double sd, double inverseCumAccuracy){
		norm = new NormalDistribution(mean, sd, inverseCumAccuracy);
	}
	
	public double calculate(int x){
		return norm.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = norm.density(i);
			//TODO: delete everywhere
			System.out.println(norm.density(i));
		}
		System.out.println("-----------");
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
