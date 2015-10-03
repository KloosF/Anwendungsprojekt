package helper.classes;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Templates;

import integer.distributions.IntegerLogic;

import org.omg.CORBA.Bounds;
import org.swtchart.Chart;
import org.swtchart.Range;

public class MinMaxHelpers {

	public static void maxRange(ChartClass chart){
		
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
	
	private static boolean pointCloseToXAxis(ChartClass chart, int yCoordinate){
		
		//Bounds always 40 pixels more than actual height of chart
		// -42 so that there is a little buffer
		System.out.println("Bounds - 62: " + (chart.getBounds().height - 62));
		System.out.println("yCoordinate:  " + yCoordinate);
		if (yCoordinate > chart.getBounds().height - 62) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static int compareLastThreeYCoordinates(ChartClass chart, int i)
	{
		int result = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y - 
				chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y) + 
				(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y - 
						chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-2).y);
		return result;
	}
	
	private static int compareNextThreeYCoordinates(ChartClass chart, int i)
	{
		int result = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y - 
				chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+1).y) + 
				(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+1).y - 
						chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i+2).y);
		return result;
	}
	
	public static double[] calculateIntegerMaxX(IntegerLogic logic, int start, ChartClass chart)
	{
		//initiale Serie erstellen
		int[] xSeries = new int[10];
		for (int i = 0; i < xSeries.length; i++) {
			xSeries[i] = i;
		}
		
		int xHochpunkt = start;
		int counter = start;
		int max;
		
		//testen ob genügend Zahlen im Array
		if (xSeries.length < 2) {
			double[] ySeries = new double[xSeries.length];
			for (int i = 0; i < xSeries.length; i++) {
				ySeries[i] = logic.calculate(i);
			}
			return ySeries;
		}
		//testen ob Verteilung gleich abfällt
		if (logic.calculate(xSeries[counter])-logic.calculate(xSeries[counter+1]) <= 0) {
			
			//Höhepunkt der Verteilung suchen
			while(logic.calculate(xSeries[counter])-logic.calculate(xSeries[counter+1]) <= 0)
			{
				counter++;
				if ((counter+2) > xSeries.length) {
					
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
		//counter zeigt direkt auf Höhepunkt, deshalb diesen mitnehmen
		if (counter == xSeries.length-1 || counter == xSeries.length-2) {
			int[] tmp = new int[2*xSeries.length];
			for (int i = 0; i < tmp.length; i++) {
				tmp[i] = i + xSeries[counter];
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
		
		System.out.println("chart gezeichnet");
 		
		//Maximum finden
		int pixelDifference = 1;
		while(!(pixelDifference == 0) && MinMaxHelpers.pointCloseToXAxis(chart, chart.getSeriesSet().getSeries()[0].getPixelCoordinates(counter).y))
		{
			System.out.println("in while schleife, counter: " + counter);
			//System.out.println("y-Koordinate: " + chart.getSeriesSet().getSeries()[0].getPixelCoordinates(counter).y);
			
			if (counter + 2 >= ySeries.length) {
				
				counter = xSeries.length;
				//x-Werte Array erweitern und befüllen, aktuelle Zahlen übernehmen
				int[] tmp = new int[3*xSeries.length];
				for (int i = 0; i < xSeries.length; i++) {
					tmp[i] = xSeries[i];
				}
				for (int i = xSeries.length; i < tmp.length; i++) {
					tmp[i] = i + xSeries[counter-1] + 2;
				}
				xSeries = new int[tmp.length];
				xSeries = tmp;
				
				//y-Werte Array neu befüllen
				ySeries = new double[xSeries.length];
				for (int i = 0; i < ySeries.length; i++) {
					ySeries[i] = logic.calculate(xSeries[i]);
				}
				
				//chart neu zeichnen für neue Berechnungen
				chart.fillChartInteger(ySeries, xSeries);
			}
			
			//herausfinden ob Höhe der Säulen fast gleich
			pixelDifference = compareNextThreeYCoordinates(chart, counter);
			
			counter++;
		}
		System.out.println("y-Koordinate: " + chart.getSeriesSet().getSeries()[0].getPixelCoordinates(counter).y);
		max = xSeries.length;
		
		//finale ySerie berechnen, von start bis max
		ySeries = new double[xSeries[max-1] + 1];
		for (int i = 0; i < ySeries.length; i++) {
			ySeries[i] = logic.calculate(i);
		}
		return ySeries;
	}
}
