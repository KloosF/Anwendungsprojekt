package real.distributions;

import org.apache.commons.math3.distribution.LaplaceDistribution;

public class LaplaceDistributionLogic {
	public LaplaceDistribution laplace;
	
	public LaplaceDistributionLogic(double mu, double beta){
		laplace = new LaplaceDistribution(mu, beta);
	}
	
	public double calculate(int x){
		return laplace.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = laplace.cumulativeProbability(i);
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
