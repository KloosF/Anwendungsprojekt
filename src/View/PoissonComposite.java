package View;

import helper.classes.ChartClass;
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
	
	Text poissonMean;
	ChartClass chart;
	
	String firstLabel = "Poisson Mean";
	
	public PoissonComposite(Composite parent, int style, ChartClass chart) {
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
		poissonMean.setText("10");
		poissonMean.addVerifyListener(new VerifyListener() {
			
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
				if (!poissonMean.getText().equals("")) {
					PoissonDistributionLogic poi = new PoissonDistributionLogic(Double.parseDouble(poissonMean.getText()));
					double[] ySeries = poi.createYSeries(chart);
					int[] xSeries = poi.createXSeries();
					chart.fillChartInteger(ySeries, xSeries);
				}
			}
		});
	}
}
