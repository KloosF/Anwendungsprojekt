package integer.distributions;

import org.apache.commons.math3.distribution.GeometricDistribution;

public class GeometricDistributionLogic {

	public GeometricDistribution geo;
	
	public GeometricDistributionLogic(double p){
		geo = new GeometricDistribution(p);
	}
	
	public double calculate(int x){
		return geo.probability(x);
	}
	
	public double[] createYSeries(int n){
		double[] ySeries = new double[n+1];
		for (int i = 0; i <= n; i++) {
			ySeries[i] = geo.probability(i);
			System.out.println(geo.probability(i));
		}
		return ySeries;
	}
	
	public double[] createXSeries(int n){
		double[] xSeries = new double[n+1];
		for (int i = 0; i <= n; i++) {
			xSeries[i] = i;
			//TODO: delete everywhere
		}
		return xSeries;
	}
}
