package real.distributions;

import org.apache.commons.math3.distribution.LogNormalDistribution;

public class LogNormalDistributionLogic {
	public LogNormalDistribution logNorm;
	private double inv;
	
	public LogNormalDistributionLogic(double scale, double shape, double inverseCumAccuracy){
		logNorm = new LogNormalDistribution(scale, shape, inverseCumAccuracy);
		inv = inverseCumAccuracy;
	}
	
	public double calculate(int x){
		return logNorm.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[11];
		for (int i = 0; i <= 10; i++) {
			ySeries[i] = logNorm.density(i);
			//TODO: delete everywhere
			System.out.println(logNorm.density(i));
		}
		System.out.println("-------------");
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
