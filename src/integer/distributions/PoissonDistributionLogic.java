package integer.distributions;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonDistributionLogic {
	public PoissonDistribution poi;
	
	public PoissonDistributionLogic(double p){
		poi = new PoissonDistribution(p);
	}
	
	public double calculate(int x){
		return poi.probability(x);
	}
	
	//TODO
	public double[] createYSeries(){
		double[] ySeries = new double[20 + 1];
		for (int i = 0; i <= 20; i++) {
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
