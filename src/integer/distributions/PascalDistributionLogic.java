package integer.distributions;

import helper.classes.ChartClass;
import helper.classes.MinMaxHelpers;

import org.apache.commons.math3.distribution.PascalDistribution;
import org.swtchart.Chart;

public class PascalDistributionLogic extends IntegerLogic{
	public PascalDistribution pas;
	private int r;
	
	public PascalDistributionLogic(int r, double p){
		pas = new PascalDistribution(r,p);
		this.r = r;
	}
	
	public double calculate(int x){
		return pas.probability(x);
	}
	
	public double[] createYSeries(ChartClass chart){
		double[] ySeries = MinMaxHelpers.calculateIntegerMaxX(this, 0, chart);
		return ySeries;
	}
	
	public int[] createXSeries(){
		int[] xSeries = new int[r + 1];
		for (int i = 0; i <= r; i++) {
			xSeries[i] = i;
			//TODO: delete everywhere
		}
		return xSeries;
	}

	@Override
	public double[] createYSeries() {
		// TODO Auto-generated method stub
		return null;
	}
}
