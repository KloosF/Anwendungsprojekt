package org.deidentifier.arx.distribution.model;

public abstract class AbstractDistribution<T> {

	public abstract T getMinimum();
	public abstract T getMaximum();
	public abstract double getValue(T value);
}
