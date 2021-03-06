package org.deidentifier.arx.distribution.helpers;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.AbstractRealDistribution;

public class WrappedRealDistribution extends AbstractIntegerDistribution {

    /**SVUID*/
    private static final long serialVersionUID = -2799756793358404985L;

    private final AbstractRealDistribution distribution;

    @SuppressWarnings("deprecation")
    public WrappedRealDistribution(AbstractRealDistribution distribution) {
        this.distribution = distribution;
    }

    @Override
    public double cumulativeProbability(int arg0) {
        return this.distribution.cumulativeProbability(arg0);
    }

    @Override
    public double getNumericalMean() {
        return this.distribution.getNumericalMean();
    }

    @Override
    public double getNumericalVariance() {
        return this.distribution.getNumericalVariance();
    }

    @Override
    public int getSupportLowerBound() {
        return (int)Math.floor(this.distribution.getSupportLowerBound());
    }

    @Override
    public int getSupportUpperBound() {
        return (int)Math.ceil(this.distribution.getSupportUpperBound());
    }

    @Override
    public boolean isSupportConnected() {
        return this.distribution.isSupportConnected();
    }

    @Override
    public double probability(int arg0) {
            return (this.distribution.cumulativeProbability(arg0 + 1 - Double.MIN_VALUE) - this.distribution.cumulativeProbability(arg0 - Double.MIN_VALUE));
    }
} 
