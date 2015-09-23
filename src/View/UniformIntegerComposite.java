package View;

import helper.classes.RealChartClass;
import helper.classes.VerifyText;
import integer.distributions.GeometricDistributionLogic;
import integer.distributions.UniformIntegerDistributionLogic;

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

public class UniformIntegerComposite extends Composite{

	Text lower;
	Text upper;
	RealChartClass chart;
	
	String firstLabel = "Lower Bound";
	String secondLabel = "UpperBound";
	
	public UniformIntegerComposite(Composite parent, int style, RealChartClass chart) {
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
		
		lower = new Text(this, SWT.BORDER);
		lower.setLayoutData(data);
		lower.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyTrials(e);
			}
		});
		
		Label populationLabel = new Label(this, SWT.NONE);
		populationLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		upper = new Text(this, SWT.BORDER);
		upper.setLayoutData(data);
		upper.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyTrials(e);
			}
		});
		
		Button calculate = new Button(this, SWT.PUSH);
		calculate.setText("Calculate");
		calculate.addSelectionListener(new SelectionAdapter() {
			
			//TODO Link to Logic
			@Override
			public void widgetSelected(SelectionEvent e){
				if (!lower.getText().equals("") && !upper.getText().equals("")) {
					UniformIntegerDistributionLogic uni = new UniformIntegerDistributionLogic(
					Integer.parseInt(lower.getText()), Integer.parseInt(upper.getText()));
					double[] ySeries = uni.createYSeries();
					double[] xSeries = uni.createXSeries();
					chart.fillChart(ySeries, xSeries);
				}
			}
		});
	}
}
