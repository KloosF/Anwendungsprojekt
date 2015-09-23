package integer.distributions;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonDistributionLogic {
	public PoissonDistribution poi;
	private int iterations;
	
	public PoissonDistributionLogic(double p, double epsilon, int maxIterations){
		poi = new PoissonDistribution(p, epsilon, maxIterations);
		iterations = maxIterations;
	}
	
	public double calculate(int x){
		return poi.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[iterations + 1];
		for (int i = 0; i <= iterations; i++) {
			ySeries[i] = poi.probability(i);
			System.out.println(poi.probability(i));
		}
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
