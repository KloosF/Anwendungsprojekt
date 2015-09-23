package View;


import helper.classes.DistributionSelect;
import helper.classes.ChartClass;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.internal.win32.TCHITTESTINFO;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.swtchart.ext.internal.properties.ChartPage;
import org.swtchart.*;
import org.swtchart.ISeries.SeriesType;


public class MainWindow {
	
	static Combo typeCombo;
	static Combo fkt;
	static Combo constructorCombo;
	static Group dynParameters;
	public static ChartClass chart;
	static MainWindow window;
	DistributionSelect dist = new DistributionSelect();
	
	public static void main(String[] args) {
		
		window = new MainWindow();
		
		Display display = Display.getDefault();
		final Shell shell = new Shell(display);
		
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		
		shell.setLayout(grid);
		shell.setText("Verteilungsfunktionen Window");
		shell.setSize(600, 500);
		
		Label fktTypeLabel = new Label(shell, SWT.NONE);
		fktTypeLabel.setText("Typ der Verteilungsfunktion");
		
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		
		dynParameters = new Group(shell, SWT.NONE);
		dynParameters.setText("Parameter");
		data.verticalSpan = 8;
		dynParameters.setLayoutData(data);
		dynParameters.setLayout(new FillLayout());
		
		typeCombo = new Combo(shell, SWT.READ_ONLY);
		
		data = new GridData(SWT.FILL, SWT.FILL, true, false);
		
		typeCombo.setLayoutData(data);
		window.fillDistributionTypeCombo();
		
		Label fktLabel = new Label(shell, SWT.NONE);
		fktLabel.setText("Verteilungsfunktion");
		
		fkt = new Combo(shell, SWT.READ_ONLY);
		fkt.setLayoutData(data);
		
		chart = new ChartClass(shell, SWT.NONE);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	public void fillDistributionTypeCombo(){
		//TODO: Combobox mit einzelnen Verteilungsfunktionen füllen, sowie nichtbeschreibbar machen
		typeCombo.add("IntegerDistribution");
		typeCombo.add("RealDistribution");
		typeCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				window.disposeChildrenOfGroup();
				fillDistributionCombo(typeCombo.getSelectionIndex());
			}
		});
	}

	//Befüllen der Combobox
	public void fillDistributionCombo(int i){
		fkt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				window.disposeChildrenOfGroup();
				dist.chooseRealIntergerDistribution(fkt.getSelectionIndex(), dynParameters, typeCombo.getSelectionIndex());
			}
		});
		
		if (i == 0) {
			fkt.setItems(new String[]{
					"BinomialDistribution",
					"GeometricDistribution",
					"PascalDistribution",
					"PoissonDistribution",
					"UniformIntegerDistribution", });
			
			//erstes Element auswählen
			fkt.select(0);
			Composite params = new BinomialComposite(dynParameters, SWT.NONE, chart);
			dynParameters.getParent().layout();
		}
		else if (i == 1) {
			fkt.setItems(new String[]{
					"CauchyDistribution",
					"ChiSquaredDistribution",
					"ExponentialDistribution",
					"LaplaceDistribution",
					"LogNormalDistribution",
					"NormalDistribution"});
			
			//erstes Element auswählen
			fkt.select(0);
			//TODO: automatically load parameter comp for first distribution
		}
		
	}

	public void disposeChildrenOfGroup(){
		Control[] c = dynParameters.getChildren();
		for(int i = c.length - 1; i>=0;i--)
		{
			c[i].dispose();
		}
	}
}
