package integer.distributions;

import org.apache.commons.math3.distribution.PascalDistribution;

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
	
	public double[] createYSeries(){
		double[] ySeries = new double[r + 1];
		for (int i = 0; i <= r; i++) {
			ySeries[i] = pas.probability(i);
			System.out.println(pas.probability(i));
		}
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
}
