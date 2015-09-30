package helper.classes;

import org.swtchart.Chart;
import org.swtchart.Range;

public class MinMaxHelpers {

	public static void maxRange(Chart chart){
		
		int min = 0;
		int max = chart.getSeriesSet().getSeries()[0].getYSeries().length - 1;
		int minMaxHelp = 0;
		
		if (max == 0) {
			//TODO was passiert wenn keine Werte übergeben
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
				minMaxHelp = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i).y - 
						chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y) + 
						(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-1).y - 
								chart.getSeriesSet().getSeries()[0].getPixelCoordinates(i-2).y);
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
						minMaxHelp = (chart.getSeriesSet().getSeries()[0].getPixelCoordinates(j).y - 
								chart.getSeriesSet().getSeries()[0].getPixelCoordinates(j+1).y) + 
								(chart.getSeriesSet().getSeries()[0].getPixelCoordinates(j+1).y - 
										chart.getSeriesSet().getSeries()[0].getPixelCoordinates(j+2).y);
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
}
