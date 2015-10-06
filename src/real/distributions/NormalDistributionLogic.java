package real.distributions;

import integer.distributions.IntegerDistribution;
import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.swtchart.Range;

public class NormalDistributionLogic extends RealDistibution{
	public NormalDistribution norm;
	
	public NormalDistributionLogic(double mean, double sd, double inverseCumAccuracy){
		norm = new NormalDistribution(mean, sd, inverseCumAccuracy);
	}
	
	@Override
	public double calculate(double x){
		return norm.density(x);
	}
	
	public double[] createXSeries(){
		double[] xSeries = new double[22];
		for (int i = 0; i <= 10; i++) {
			xSeries[i] = i/2;
			//TODO: delete everywhere
		}
		return xSeries;
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = norm.density(i);
			//TODO: delete everywhere
			System.out.println(ySeries[i]);
		}
		System.out.println("---------------");
		return ySeries;
	}

	@Override
	public double calculateCumulative(double x) {
		return norm.cumulativeProbability(x);
	}
	
	public double[] createYSeries(ChartClass chart){
		double[] xSeries = MinMaxHelpers.calculateRealMaxX(this, 0, chart);
		double[] ySeries = new double[xSeries.length];
		for (int i = 0; i < xSeries.length; i++) {
			ySeries[i] = norm.density(xSeries[i]);
		}
		//chart.fillChartReal(ySeries, null);
		//Range range = MinMaxHelpers.maxRange(chart);
		//chart.getAxisSet().getXAxis(0).setRange(range);
		//ySeries = MinMaxHelpers.calculateRealYSeries(range, chart, this);
		return ySeries;
	}
}
