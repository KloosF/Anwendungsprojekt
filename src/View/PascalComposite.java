package View;

import helper.classes.ChartClass;
import helper.classes.VerifyText;
import integer.distributions.BinomialDistributionLogic;
import integer.distributions.GeometricDistributionLogic;
import integer.distributions.PascalDistributionLogic;

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

public class PascalComposite extends Composite{

	Text probability;
	Text numberOfSuccess;
	ChartClass chart;
	
	String firstLabel = "Number of Success";
	String secondLabel = "Probability";
	
	public PascalComposite(Composite parent, int style, ChartClass chart) {
		super(parent, style);
		this.chart = chart;
		createComponents();
		parent.layout(true, true);
	}

	public void createComponents(){
		
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		setLayout(grid);	
		
		Label successLabel = new Label(this, SWT.NONE);
		successLabel.setText(firstLabel);
		
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		numberOfSuccess = new Text(this, SWT.BORDER);
		numberOfSuccess.setLayoutData(data);
		numberOfSuccess.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyTrials(e);
			}
		});
		
		Label probabilityLabel = new Label(this, SWT.NONE);
		probabilityLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
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
			
			//TODO Link to Logic
			@Override
			public void widgetSelected(SelectionEvent e){
				if (!numberOfSuccess.getText().equals("") && !probability.getText().equals("")) {
					PascalDistributionLogic bin = new PascalDistributionLogic(
							Integer.parseInt(numberOfSuccess.getText()), 
							Double.parseDouble(probability.getText()));
					double[] ySeries = bin.createYSeries();
					double[] xSeries = bin.createXSeries();
					chart.fillChart(ySeries, xSeries, false);
				}
			}
		});
	}
}
