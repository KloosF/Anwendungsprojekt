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

import real.distributions.NormalDistributionLogic;

public class NormalComposite extends Composite{
	public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
	
	Text mean;
	Text sd;
	ChartClass chart;
	
	String firstLabel = "Normal Mean";
	String secondLabel = "Standard Deviation";
	
	public NormalComposite(Composite parent, int style, ChartClass chart) {
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
		
		Label populationLabel = new Label(this, SWT.NONE);
		populationLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		sd = new Text(this, SWT.BORDER);
		sd.setLayoutData(data);
		sd.setText("1");
		sd.addVerifyListener(new VerifyListener() {
			
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
				if (!mean.getText().equals("") && !sd.getText().equals("")) {
					NormalDistributionLogic norm = new NormalDistributionLogic(Double.parseDouble(mean.getText()), 
							Double.parseDouble(sd.getText()));
					double[] ySeries = norm.createYSeries(chart, Double.parseDouble(mean.getText()));
					double[] xSeries = norm.createXSeries();
					chart.fillChartReal(ySeries, xSeries);
				}
			}
		});
	}
}
