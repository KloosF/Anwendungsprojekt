package View;

import helper.classes.ChartClass;
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

import real.distributions.CauchyDistributionLogic;
import real.distributions.NormalDistributionLogic;

public class CauchyComposite extends Composite{
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
	
	Text median;
	Text scale;
	Text inverseCumAccuracy;
	ChartClass chart;
	
	String firstLabel = "Median";
	String secondLabel = "Scale Parameter";
	String thirdLabel = "Inverse cumulative probability accuracy";
	
	public CauchyComposite(Composite parent, int style, ChartClass chart) {
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
		
		median = new Text(this, SWT.BORDER);
		median.setLayoutData(data);
		median.setText("6");
		median.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Label populationLabel = new Label(this, SWT.NONE);
		populationLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		scale = new Text(this, SWT.BORDER);
		scale.setLayoutData(data);
		scale.setText("2");
		scale.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Label successLabel = new Label(this, SWT.NONE);
		successLabel.setText(thirdLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		inverseCumAccuracy = new Text(this, SWT.BORDER);
		inverseCumAccuracy.setLayoutData(data);
		inverseCumAccuracy.setText(DEFAULT_INVERSE_ABSOLUTE_ACCURACY + "");
		inverseCumAccuracy.addVerifyListener(new VerifyListener() {
			
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
				if (!inverseCumAccuracy.getText().equals("") && !median.getText().equals("") && !scale.getText().equals("")) {
					CauchyDistributionLogic cauchy = new CauchyDistributionLogic(Double.parseDouble(median.getText()), 
							Double.parseDouble(scale.getText()),
							Double.parseDouble(inverseCumAccuracy.getText()));
					double[] ySeries = cauchy.createYSeries(chart);
					double[] xSeries = cauchy.createXSeries();
					chart.fillChartReal(ySeries, xSeries);
				}
			}
		});
	}
}
