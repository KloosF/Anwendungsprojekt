package real.distributions;

import integer.distributions.IntegerDistribution;
import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.swtchart.Range;

public class NormalDistributionLogic extends RealDistibution{
	public NormalDistribution norm;
	
	public NormalDistributionLogic(double mean, double sd){
		norm = new NormalDistribution(mean, sd);
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
		double[] halfXSeries = MinMaxHelpers.calculateRealMaxX(this, 0, chart);
		double[] xSeries = new double[2*halfXSeries.length - 1];
		
		//wird gebraucht um linke hälfte von xSeries zu berechnen
		int first = halfXSeries.length - 1;
		
		int k;
		for (k = 0; k < halfXSeries.length; k++) {
			xSeries[k] = halfXSeries[k] - first;
		}
		for (int i = 1; i < halfXSeries.length; i++) {
			xSeries[k] = halfXSeries[i];
			k++;
		}
		
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
