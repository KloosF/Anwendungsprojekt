package org.deidentifier.arx.distribution.model;

public abstract class AbstractDistribution<T> {

	private final double yLimit;
	
	public AbstractDistribution(double yLimit) {
		this.yLimit = yLimit;
	}
	
	public abstract T getMinimum();
	public abstract T getMaximum();
	public abstract double getValue(T value);
	public boolean limitYRange(){
		return yLimit != 0d;
	}
	public double getLimitY(){
		return yLimit;
	}
}
