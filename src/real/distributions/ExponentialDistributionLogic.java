package real.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExponentialDistributionLogic extends RealDistibution{
	public ExponentialDistribution exp;
	
	public ExponentialDistributionLogic(double mean, double inverseCumAccuracy){
		exp = new ExponentialDistribution(mean, inverseCumAccuracy);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = exp.cumulativeProbability(i);
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
	
	public double[] createYSeries(ChartClass chart){
		double[] xSeries = MinMaxHelpers.calculateRealMaxX(this, 0, chart);
		double[] ySeries = new double[xSeries.length];
		for (int i = 0; i < xSeries.length; i++) {
			ySeries[i] = exp.density(xSeries[i]);
		}
		//chart.fillChartReal(ySeries, null);
		//Range range = MinMaxHelpers.maxRange(chart);
		//chart.getAxisSet().getXAxis(0).setRange(range);
		//ySeries = MinMaxHelpers.calculateRealYSeries(range, chart, this);
		return ySeries;
	}

	@Override
	public double calculate(double x) {
		return exp.density(x);
	}

	@Override
	public double calculateCumulative(double x) {
		return exp.cumulativeProbability(x);
	}
}
