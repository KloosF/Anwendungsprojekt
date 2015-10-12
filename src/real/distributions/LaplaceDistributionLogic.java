package real.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.LaplaceDistribution;

public class LaplaceDistributionLogic extends RealDistibution{
	public LaplaceDistribution laplace;
	
	public LaplaceDistributionLogic(double mu, double beta){
		laplace = new LaplaceDistribution(mu, beta);
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
	
	public double[] createYSeries(ChartClass chart, Double start){
		double[] halfXSeries = MinMaxHelpers.calculateRealMaxX(this, start, chart);
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
			ySeries[i] = laplace.density(xSeries[i]);
		}
		//chart.fillChartReal(ySeries, null);
		//Range range = MinMaxHelpers.maxRange(chart);
		//chart.getAxisSet().getXAxis(0).setRange(range);
		//ySeries = MinMaxHelpers.calculateRealYSeries(range, chart, this);
		return ySeries;
	}

	@Override
	public double calculate(double x) {
		return laplace.density(x);
	}

	@Override
	public double calculateCumulative(double x) {
		return laplace.cumulativeProbability(x);
	}
}
