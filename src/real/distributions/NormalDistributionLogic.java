package real.distributions;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionLogic {
	public NormalDistribution norm;
	private double inv;
	
	public NormalDistributionLogic(double mean, double sd, double inverseCumAccuracy){
		norm = new NormalDistribution(mean, sd, inverseCumAccuracy);
		inv = inverseCumAccuracy;
	}
	
	public double calculate(int x){
		return norm.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[22];
		for (double i = 0; i <= 10; i = i+0.5) {
			ySeries[(int)i*2] = norm.density(i-5);
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
