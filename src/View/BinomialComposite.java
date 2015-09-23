package View;
import helper.classes.ChartClass;
import helper.classes.VerifyText;
import integer.distributions.BinomialDistributionLogic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.swtchart.Chart;

public class BinomialComposite extends Composite{

	Text tries;
	Text probability;
	ChartClass chart;
	
	public BinomialComposite(Composite parent, int style, ChartClass chart) {
		super(parent, style);
		this.chart = chart;
		createComponents();
		parent.layout(true, true);
	}

	public void createComponents(){
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		setLayout(grid);
		
		GridData data = new GridData(SWT.FILL, SWT.CENTER, false, false);

		
		Label triesLabel = new Label(this, SWT.NONE);
		triesLabel.setText("Number of Tries");
		triesLabel.setLayoutData(data);
		
		data = new GridData(SWT.FILL, SWT.FILL, true, false);
		data.horizontalSpan = 2;
		
		tries = new Text(this, SWT.BORDER);
		tries.setLayoutData(data);
		tries.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyTrials(e);
			}
		});
		
		
		Label probabilityLabel = new Label(this, SWT.NONE);
		probabilityLabel.setText("Probability");
		
		probability = new Text(this, SWT.BORDER);
		probability.setLayoutData(data);
		probability.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyProbability(e);
			}
		});
		
		Button calculate = new Button(this, SWT.PUSH);
		calculate.setText("Calculate");
		calculate.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e){
				if (!tries.getText().equals("") && !probability.getText().equals("")) {
					BinomialDistributionLogic bin = new BinomialDistributionLogic(
							Integer.parseInt(tries.getText()), 
							Double.parseDouble(probability.getText()));
					double[] ySeries = bin.createYSeries();
					double[] xSeries = bin.createXSeries();
					chart.fillChart(ySeries, xSeries, false);
				}
			}
		});
	}
}
