package real.distributions;

import org.apache.commons.math3.distribution.CauchyDistribution;

public class CauchyDistributionLogic {
	public CauchyDistribution cauchy;
	
	public CauchyDistributionLogic(double median, double scale, double inverseCumAccuracy){
		cauchy = new CauchyDistribution(median, scale, inverseCumAccuracy);
	}
	
	public double calculate(int x){
		return cauchy.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[11];
		for (int i = 0; i <= 10; i++) {
			ySeries[i] = cauchy.density(i);
			//TODO: delete everywhere
			System.out.println(cauchy.density(i));
		}
		System.out.println("-----------------");
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
