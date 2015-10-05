package integer.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.GeometricDistribution;

public class GeometricDistributionLogic extends IntegerDistribution{

	public GeometricDistribution geo;
	
	public GeometricDistributionLogic(double p){
		geo = new GeometricDistribution(p);
	}
	
	@Override
	public double calculate(int x){
		return geo.probability(x);
	}
	
	public double[] createYSeries(ChartClass chart){
		double[] ySeries = MinMaxHelpers.calculateIntegerMaxX(this, 0, chart);
		return ySeries;
	}
	
	public int[] createXSeries(int n){
		int[] xSeries = new int[n+1];
		for (int i = 0; i <= n; i++) {
			xSeries[i] = i;
			//TODO: delete everywhere
		}
		return xSeries;
	}
}
