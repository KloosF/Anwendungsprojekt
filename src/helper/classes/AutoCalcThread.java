package helper.classes;

import org.swtchart.Chart;

import integer.distributions.BinomialDistributionLogic;

public class AutoCalcThread implements Runnable{

	int ID;
	String[] texts;
	ChartClass chart;
	
	public AutoCalcThread(int distributionID, String[] textBoxes, ChartClass chart) {
		ID = distributionID;
		texts = textBoxes;
		this.chart = chart;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean allFilled = true;
		for (int i = 0; i < texts.length; i++) {
			if (texts[i].equals("")) {
				allFilled = false;
			}
		}
		if (allFilled && !VerifyText.getVerificationFlag()) {
			switch (ID) {
			case 0:
				BinomialDistributionLogic bin = new BinomialDistributionLogic(
						Integer.parseInt(texts[0]), 
						Double.parseDouble(texts[1]));
				double[] ySeries = bin.createYSeries();
				int[] xSeries = bin.createXSeries();
				chart.fillChartInteger(ySeries, xSeries);
				break;
			case 1:
				
				break;
			case 2:
	
				break;
			case 3:
	
				break;

			default:
				break;
			}
		}
	}

}
