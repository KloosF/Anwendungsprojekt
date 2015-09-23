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

import real.distributions.ChiSquaredDistributionLogic;
import real.distributions.NormalDistributionLogic;

public class ChiSquaredComposite extends Composite{
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
	
	Text degreesOfFreedom;
	Text inverseCumAccuracy;
	ChartClass chart;
	
	String firstLabel = "Degrees of Freedom";
	String thirdLabel = "Inverse cumulative probability accuracy";
	
	public ChiSquaredComposite(Composite parent, int style, ChartClass chart) {
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
		
		degreesOfFreedom = new Text(this, SWT.BORDER);
		degreesOfFreedom.setLayoutData(data);
		degreesOfFreedom.addVerifyListener(new VerifyListener() {
			
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
				if (!inverseCumAccuracy.getText().equals("") && !degreesOfFreedom.getText().equals("")) {
					ChiSquaredDistributionLogic chi = new ChiSquaredDistributionLogic(Double.parseDouble(degreesOfFreedom.getText()),
							Double.parseDouble(inverseCumAccuracy.getText()));
					double[] ySeries = chi.createYSeries();
					double[] xSeries = chi.createXSeries();
					chart.fillChart(ySeries, xSeries, true);
				}
			}
		});
	}
}
