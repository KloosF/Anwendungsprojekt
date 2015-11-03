package org.deidentifier.arx.distribution.gui;

import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.deidentifier.arx.distribution.model.ContinuousDistribution;
import org.deidentifier.arx.distribution.model.DiscreteDistribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.Chart;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;
import org.swtchart.Range;
import org.swtchart.internal.series.LineSeries;

public class DistributionPlot{
	
	private Chart chart;

	public DistributionPlot(Composite parent) {
		chart = new Chart(parent, SWT.NONE);
	}

	public void setLayoutData(Object data) {
		chart.setLayoutData(data);
	}

	public void update(AbstractDistribution<?> result) {
		clear();
		if (result instanceof ContinuousDistribution) {
			update((ContinuousDistribution)result);
		} else {
			update((DiscreteDistribution)result);
		}
	}
	
	public void clear(){
		chart.getParent().setRedraw(false);
		Object data = chart.getLayoutData();
		Chart _chart = new Chart(chart.getParent(), SWT.NONE);
		chart.dispose();
		_chart.setLayoutData(data);
		this.chart = _chart;
		chart.getParent().setRedraw(true);
	}

	public void update(ContinuousDistribution result) {

		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.LINE, "values");
		double[] xSeries = getXSeries(result);
		((LineSeries) series).setSymbolType(PlotSymbolType.NONE);
		series.setXSeries(xSeries);
		series.setYSeries(getYSeries(xSeries, result));
		chart.getAxisSet().adjustRange();
		
		if (result.getLimitY() != 0) {
			chart.getAxisSet().getYAxis(0).setRange(new Range(0, result.getLimitY()));
		}
		
		chart.getLegend().setVisible(false);
		chart.redraw();
	}

	public void update(DiscreteDistribution result) {

		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.BAR, "values");
		double[] xSeries = getXSeries(result);
		series.setXSeries(xSeries);
		series.setYSeries(getYSeries(xSeries, result));
		chart.getAxisSet().adjustRange();
		chart.getLegend().setVisible(false);
		chart.redraw();
	}

	private double[] getXSeries(DiscreteDistribution result) {

		double[] array = new double[result.getMaximum() - result.getMinimum() + 1];
		int index = 0;
		for (int x = result.getMinimum(); x <= result.getMaximum(); x++){
			array[index++] = x;
		}
		return array;
	}
	
	private double[] getYSeries(double[] xSeries, DiscreteDistribution result) {

		double[] array = new double[xSeries.length];
		for (int i = 0; i < xSeries.length; i++){
			array[i] = result.getValue((int) xSeries[i]);
		}
		return array;
	}

	private double[] getXSeries(ContinuousDistribution result) {

		int width = chart.getParent().getBounds().width;
		double delta = (result.getMaximum() - result.getMinimum()) / (double) width;
		double[] array = new double[width + 1];
		for (int i = 0; i <= width; i++) {
			array[i] = result.getMinimum() + (double) i * delta;
		}
		return array;
	}
	
	private double[] getYSeries(double[] xSeries, ContinuousDistribution result) {
		
		double[] array = new double[xSeries.length];
		for (int i = 0; i < xSeries.length; i++) {
			array[i] = result.getValue(xSeries[i]);
		}
		return array;
	}
}
