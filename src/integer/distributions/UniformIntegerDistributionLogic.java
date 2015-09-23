package integer.distributions;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;

public class UniformIntegerDistributionLogic {
	public UniformIntegerDistribution uni;
	private int upper;
	private int lower;
	
	public UniformIntegerDistributionLogic(int lower, int upper){
		uni = new UniformIntegerDistribution(lower,upper);
		this.upper = upper;
		this.lower = lower;
	}
	
	public double calculate(int x){
		return uni.probability(x);
	}
	
	public double[] createYSeries(){
		double[] ySeries = new double[upper + 1];
		for (int i = lower; i <= upper; i++) {
			ySeries[i] = uni.probability(i);
			System.out.println(uni.probability(i));
		}
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
}
