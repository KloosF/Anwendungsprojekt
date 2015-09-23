package View;

import helper.classes.RealChartClass;
import helper.classes.VerifyText;
import integer.distributions.PoissonDistributionLogic;

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

public class PoissonComposite extends Composite{

	public static final double DEFAULT_EPSILON = 1e-12;
	public static final int DEFAULT_MAX_ITERATIONS = 10000000;
	
	Text poissonMean;
	Text epsilon;
	Text maxIterations;
	RealChartClass chart;
	
	String firstLabel = "Poisson Mean";
	String secondLabel = "Convergence Criterion";
	String thirdLabel = "Max. Number of Iterations";
	
	public PoissonComposite(Composite parent, int style, RealChartClass chart) {
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
		
		poissonMean = new Text(this, SWT.BORDER);
		poissonMean.setLayoutData(data);
		poissonMean.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Label populationLabel = new Label(this, SWT.NONE);
		populationLabel.setText(secondLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		epsilon = new Text(this, SWT.BORDER);
		epsilon.setLayoutData(data);
		epsilon.setText(DEFAULT_EPSILON + "");
		epsilon.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				VerifyText.verifyMean(e);
			}
		});
		
		Label successLabel = new Label(this, SWT.NONE);
		successLabel.setText(thirdLabel);
		
		data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.horizontalSpan = 2;
		
		maxIterations = new Text(this, SWT.BORDER);
		maxIterations.setLayoutData(data);
		maxIterations.setText(DEFAULT_MAX_ITERATIONS + "");
		maxIterations.addVerifyListener(new VerifyListener() {
			
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
				if (!maxIterations.getText().equals("") && !poissonMean.getText().equals("") && !epsilon.getText().equals("")) {
					PoissonDistributionLogic poi = new PoissonDistributionLogic(Double.parseDouble(poissonMean.getText()), 
							Double.parseDouble(epsilon.getText()),
							Integer.parseInt(maxIterations.getText()));
					double[] ySeries = poi.createYSeries();
					double[] xSeries = poi.createXSeries();
					chart.fillChart(ySeries, xSeries);
				}
			}
		});
	}
}
