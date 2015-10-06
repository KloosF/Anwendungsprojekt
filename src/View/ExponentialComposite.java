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

import real.distributions.ExponentialDistributionLogic;
import real.distributions.NormalDistributionLogic;

public class ExponentialComposite extends Composite{
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
	
	Text mean;
	ChartClass chart;
	
	String firstLabel = "Mean";
	
	public ExponentialComposite(Composite parent, int style, ChartClass chart) {
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
		
		mean = new Text(this, SWT.BORDER);
		mean.setLayoutData(data);
		mean.setText("5");
		mean.addVerifyListener(new VerifyListener() {
			
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
				if (!mean.getText().equals("")) {
					ExponentialDistributionLogic exp = new ExponentialDistributionLogic(Double.parseDouble(mean.getText()));
					double[] ySeries = exp.createYSeries(chart);
					double[] xSeries = exp.createXSeries();
					chart.fillChartReal(ySeries, xSeries);
				}
			}
		});
	}
}
