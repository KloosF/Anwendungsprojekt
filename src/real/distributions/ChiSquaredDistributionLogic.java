package real.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;
import integer.distributions.IntegerDistribution;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.swtchart.Range;

public class ChiSquaredDistributionLogic extends RealDistibution{
	public ChiSquaredDistribution chi;
	
	public ChiSquaredDistributionLogic(double degreesOfFreedom, double inverseCumAccuracy){
		chi = new ChiSquaredDistribution(degreesOfFreedom, inverseCumAccuracy);
	}
	
	@Override
	public double calculate(double x){
		return chi.density(x);
	}
	
	public double[] createYSeries(ChartClass chart){
		MinMaxHelpers.calculateRealMaxX(this, 0, chart);
		Range range = MinMaxHelpers.maxRange(chart);
		double[] ySeries = MinMaxHelpers.calculateRealYSeries(range, chart, this);
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
}
