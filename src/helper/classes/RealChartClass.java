package helper.classes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries;
import org.swtchart.ISeriesSet;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.internal.series.SeriesSet;

public class RealChartClass extends Composite{

	
	Chart chart;
	GridData data;
	Composite comp;
	
	
	public RealChartClass(Composite parent, int style) {
		super(parent, style);
		comp = parent;
		makeChart();
		chart.getTitle().setVisible(false);
		chart.getAxisSet().getXAxis(0).getTitle().setVisible(false);
		chart.getAxisSet().getYAxis(0).getTitle().setVisible(false);
		parent.layout(true, true);
	}

	
	public void makeChart(){
		chart = new Chart(comp, SWT.NONE);
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = 2;
		chart.setLayoutData(data);
	}
	
	public void fillChart(double[] ySeries, double[] xSeries){
		
		//ILineSeries series = (ILineSeries) chart.getSeriesSet().createSeries(SeriesType.LINE, "line series");
		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.LINE, "values");
		//series.setXSeries(xSeries);
		series.setYSeries(ySeries);
		//series.setAntialias(SWT.ON);
		
		chart.getAxisSet().adjustRange();
		
		chart.getLegend().setVisible(false);
		chart.redraw();
		
	}
}
