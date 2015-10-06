package real.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;
import integer.distributions.IntegerDistribution;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.swtchart.Range;

public class ChiSquaredDistributionLogic extends RealDistibution{
	public ChiSquaredDistribution chi;
	
	public ChiSquaredDistributionLogic(double degreesOfFreedom){
		chi = new ChiSquaredDistribution(degreesOfFreedom);
	}
	
	@Override
	public double calculate(double x){
		return chi.density(x);
	}
	
	public double[] createYSeries(ChartClass chart){
		double[] xSeries = MinMaxHelpers.calculateRealMaxX(this, 0, chart);
		double[] ySeries = new double[xSeries.length];
		for (int i = 0; i < xSeries.length; i++) {
			ySeries[i] = chi.density(xSeries[i]);
		}
		//chart.fillChartReal(ySeries, null);
		//Range range = MinMaxHelpers.maxRange(chart);
		//chart.getAxisSet().getXAxis(0).setRange(range);
		//ySeries = MinMaxHelpers.calculateRealYSeries(range, chart, this);
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
	
	public double[] createYSeries(){
		double[] ySeries = new double[21];
		for (int i = 0; i <= 20; i++) {
			ySeries[i] = chi.cumulativeProbability(i);
			//TODO: delete everywhere
			System.out.println(ySeries[i]);
		}
		System.out.println("---------------");
		return ySeries;
	}

	@Override
	public double calculateCumulative(double x) {
		return chi.cumulativeProbability(x);
	}
}
