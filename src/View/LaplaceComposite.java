package View;

import helper.classes.RealChartClass;
import helper.classes.VerifyText;

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

import real.distributions.LaplaceDistributionLogic;
import real.distributions.NormalDistributionLogic;

public class LaplaceComposite extends Composite{
	
	Text mu;
	Text beta;
	RealChartClass chart;
	
	String firstLabel = "Location Parameter";
	String secondLabel = "Scale Parameter (must be positive)";
	
	public LaplaceComposite(Composite parent, int style, RealChartClass chart) {
		super(parent, style);
		this.chart = chart;
		createComponents();
		parent.layout(true, true);
	}

	public void createComponents(){
		
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		setLayout(grid);	
		
		Label probabilityLabel = new Label(this, SWT.NONE);
		probabilityLabel.setText(firstLabel);
		
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		mu = new Text(this, SWT.BORDER);
		mu.setLayoutData(data);
		mu.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Label populationLabel = new Label(this, SWT.NONE);
		populationLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		beta = new Text(this, SWT.BORDER);
		beta.setLayoutData(data);
		beta.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Button calculate = new Button(this, SWT.PUSH);
		calculate.setText("Calculate");
		calculate.addSelectionListener(new SelectionAdapter() {
			
			//TODO Link to Logic
			@Override
			public void widgetSelected(SelectionEvent e){
				if (!mu.getText().equals("") && !beta.getText().equals("")) {
					LaplaceDistributionLogic laplace = new LaplaceDistributionLogic(Double.parseDouble(mu.getText()), 
							Double.parseDouble(beta.getText()));
					double[] ySeries = laplace.createYSeries();
					double[] xSeries = laplace.createXSeries();
					chart.fillChart(ySeries, xSeries);
				}
			}
		});
	}
}
