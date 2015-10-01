package helper.classes;

import integer.distributions.IntegerLogic;

import org.swtchart.Chart;
import org.swtchart.Range;

public class MinMaxHelpers {

	public static void maxRange(Chart chart){
		
		int min = 0;
		int max = chart.getSeriesSet().getSeries()[0].getYSeries().length - 1;
		int minMaxHelp = 0;
		
		if (max == 0) {
			//TODO was passiert wenn keine Werte übergeben
			chart.getSeriesSet().getSeries()[0].setYSeries(new double[0]);
			return;
		}
		else if (max == 1 || max == 2) {
			chart.getAxisSet().adjustRange();
		}
		else
		{
			//get the minimum
			int i = 2;
			System.out.println("bounds: " + chart.getBounds().height);
			System.out.println("y coordinate: " + chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y);
			while(!(minMaxHelp < -1 || minMaxHelp > 1) && i < chart.getSeriesSet().getSeries()[0].getYSeries().length && MinMaxHelpers.pointCloseToXAxis(chart, chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y))
			{
				minMaxHelp = compareLastThreeYCoordinates(chart, i);
				
				if (minMaxHelp == 0) {
					min = i;
				}
				i++;
			}
			
			//check if min equals max
			if (min == max) {
				//TODO show nothing on chart
			}
			else
			{
				if (min < max - 1) {
					if (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(max).y - chart.getSeriesSet().getSeries()[0].getPixelCoordinates(max - 1).y == 0) {
						max -= 1;
					}
				}
				if (min < max - 2) {
					int j = max-2;
					minMaxHelp = 0;
					while(!(minMaxHelp < -1 || minMaxHelp > 1) && j>min && MinMaxHelpers.pointCloseToXAxis(chart, chart.getSeriesSet().getSeries()[0].getPixelCoordinates(j).y))
					{
						minMaxHelp = compareNextThreeYCoordinates(chart, j);
						
						if (minMaxHelp == 0) {
							max = j;
						}
						j--;
					}
				}
			}
			System.out.println("min: " + min + "; max: " + max);
			chart.getAxisSet().getXAxis(0).setRange(new Range(min, max));
			chart.redraw();
		}			
	}
	
	private static boolean pointCloseToXAxis(Chart chart, int yCoordinate){
		
		//Bounds always 40 pixels more than actual height of chart
		// -42 so that there is a little buffer
		if (yCoordinate > chart.getBounds().height - 42) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static int compareLastThreeYCoordinates(Chart chart, int i)
	{
		int result = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y - 
				chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y) + 
				(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y - 
						chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-2).y);
		return result;
	}
	
	private static int compareNextThreeYCoordinates(Chart chart, int i)
	{
		int result = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y - 
				chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+1).y) + 
				(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+1).y - 
						chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+2).y);
		return result;
	}
	
	private static double compareLastThreeValues(IntegerLogic logic, int i)
	{
		double result = (logic.calculate(i) - logic.calculate(i-1)) + (logic.calculate(i-1) - logic.calculate(i-2));
		return result;
	}
	
	private static double compareNextThreeValues(IntegerLogic logic, int i)
	{
		double result = (logic.calculate(i) - logic.calculate(i+1)) + (logic.calculate(i+1) - logic.calculate(i+2));
		return result;
	}
	
	public static void calculateIntegerMaxX(int[] xSeries, IntegerLogic logic, int start, ChartClass chart)
	{
		int xHochpunkt = start;
		int counter = start;
		int anfangX = xSeries[start];
		
		//testen ob genügend Zahlen im Array
		if (xSeries.length < 2) {
			return;
		}
		//testen ob Verteilung gleich abfällt
		if (logic.calculate(xSeries[counter])-logic.calculate(xSeries[counter+1]) <= 0) {
			
			//Höhepunkt der Verteilung suchen
			while(logic.calculate(xSeries[counter])-logic.calculate(xSeries[counter+1]) <= 0)
			{
				counter++;
				if ((counter+1) < xSeries.length) {
					
					//ursprüngliche Länge *2 um Suche zu beschleunigen
					int[] tmp = new int[2*xSeries.length];
					for (int i = 0; i < tmp.length; i++) {
						tmp[i] = i + xSeries[counter] + 1;
					}
					xSeries = new int[tmp.length];
					xSeries = tmp;
					counter = 0;
				}
			}
			//Hochpunkt gefunden, ab hier nur noch bergab
			xHochpunkt = xSeries[counter];
		}
		
		//überprüfen ob array mit xWerten "zu klein"
		if (counter == xSeries.length-1 || counter == xSeries.length-2) {
			int[] tmp = new int[2*xSeries.length];
			for (int i = 0; i < tmp.length; i++) {
				tmp[i] = i + xSeries[counter] + 1;
			}
			xSeries = new int[tmp.length];
			xSeries = tmp;
			counter = 0;
		}
		
		//TODO: evtl Möglichkeit bei der gesammte Chart gezeichnet wird, nicht nur ab Höhepunkt
		
		double[] ySeries = new double[xSeries.length];
		for (int i = 0; i < ySeries.length; i++) {
			ySeries[i] = logic.calculate(xSeries[i]);
		}
		
		//chart einmal zeichnen um Vergleiche anstellen zu können
		chart.fillChartInteger(ySeries, xSeries);
		
		//Maximum finden

	}
}
