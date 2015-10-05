package integer.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonDistributionLogic extends IntegerDistribution{
	public PoissonDistribution poi;
	
	public PoissonDistributionLogic(double p){
		poi = new PoissonDistribution(p);
	}
	
	@Override
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
	
	public int[] createXSeries(){
		int[] xSeries = new int[22];
		for (int i = 0; i <= 10; i++) {
			xSeries[i] = i/2;
			//TODO: delete everywhere
		}
		return xSeries;
	}
	
	public double[] createYSeries(ChartClass chart){
		double[] ySeries = MinMaxHelpers.calculateIntegerMaxX(this, 0, chart);
		return ySeries;
	}
}
