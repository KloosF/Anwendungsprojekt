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
	ChartClass chart;
	
	String firstLabel = "Degrees of Freedom";
	
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
		degreesOfFreedom.setText("5");
		degreesOfFreedom.addVerifyListener(new VerifyListener() {
			
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
				if (!degreesOfFreedom.getText().equals("")) {
					ChiSquaredDistributionLogic chi = new ChiSquaredDistributionLogic(Double.parseDouble(degreesOfFreedom.getText()));
					double[] ySeries = chi.createYSeries(chart, Double.parseDouble(degreesOfFreedom.getText()));
					double[] xSeries = chi.createXSeries();
					chart.fillChartReal(ySeries, xSeries);
				}
			}
		});
	}
}
