package View;

import helper.classes.ChartClass;
import helper.classes.VerifyText;
import integer.distributions.BinomialDistributionLogic;
import integer.distributions.GeometricDistributionLogic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GeometricComposite extends Composite{
	
	Text tries;
	Text probability;
	ChartClass chart;
	
	public GeometricComposite(Composite parent, int style, ChartClass chart) {
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
		data.horizontalSpan = 2;
		
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
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		
		Label probabilityLabel = new Label(this, SWT.NONE);
		probabilityLabel.setText("Probability");
		
		probability = new Text(this, SWT.BORDER);
		probability.setLayoutData(data);
		probability.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				Text text = (Text)e.getSource();

	            // get old text and create new text by using the VerifyEvent.text
	            final String oldS = text.getText();
	            String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

	            //states if String is double
	            boolean isDouble = true;
	            try
	            {
	            	//empty String is not parsable so check for that first
	            	if (!newS.equals("")) {
	            		Double newSDouble = Double.parseDouble(newS);
	            		
	            		//probability should not be greater than 1, also 1. should not be writable
	            		if (newSDouble > 1 || newS.equals("1.")) {
							isDouble = false;
						}
					}
	            	
	            	//take d and f out of double
	            	if (newS.contains("f") || newS.contains("d")) {
						isDouble = false;
					}
	            }
	            //if parse throws exception
	            catch(NumberFormatException ex)
	            {
	            	isDouble = false;
	            }

	            if(!isDouble){
	                e.doit = false;
	            }
			}
		});
		
		Button calculate = new Button(this, SWT.PUSH);
		calculate.setText("Calculate");
		calculate.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e){
				if (!tries.getText().equals("") && !probability.getText().equals("")) {
					GeometricDistributionLogic geo = new GeometricDistributionLogic(
							Double.parseDouble(probability.getText()));
					double[] ySeries = geo.createYSeries(Integer.parseInt(tries.getText()));
					double[] xSeries = geo.createXSeries(Integer.parseInt(tries.getText()));
					chart.fillChart(ySeries, xSeries, false);
				}
			}
		});
	}
}
