package org.deidentifier.arx.distribution.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class DistributionConfigurator{

	/** Field */
	private final Composite root;

	/** Field */
	private final Group config;

	/** Field */
	private final DistributionPlot plot;
	
	/**
	 * Creates a new instance
	 * @param parent
	 */
	public DistributionConfigurator(Composite parent) {
		
		this.root = new Composite(parent, SWT.NONE);
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		root.setLayout(grid);
		
		Label comboLabel = new Label(root, SWT.NONE);
		comboLabel.setText("Select distribution");
		comboLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		this.config = new Group(root, SWT.NONE);
		this.config.setText("Parameters");
		this.config.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 4));
		this.config.setLayout(new FillLayout());
		
		final Combo combo = new Combo(root, SWT.READ_ONLY);
		combo.setItems(new String[]{
				"Binomial distribution (discrete)",
				"Geometric distribution (discrete)",
				"Poisson distribution (discrete)",
				"Pascal distribution (discrete)",
				"Normal distribution (discrete)",
				"Cauchy distribution (discrete)",
				"Chi-squared distribution (discrete)",
				"Exponential distribution (discrete)",
				"Laplace distribution (discrete)",
				"Normal distribution (continuous)",
				"Cauchy distribution (continuous)",
				"Chi-squared distribution (continuous)",
				"Exponential distribution (continuous)",
				"Laplace distribution (continuous)"
				
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index= combo.getSelectionIndex();
				if (index != -1) {
					for (Control c : config.getChildren()) {
						c.dispose();
					}
					AbstractDistributionComposite<?> composite = selectComposite(index);
					
					if (composite != null) {
						listenForChanges(composite);
						update(composite);
						root.layout(true);
					}
				}
			}
		});

		this.plot = new DistributionPlot(this.root);
		this.plot.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
	}

	private void listenForChanges(final AbstractDistributionComposite<?> composite) {
		composite.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				update(composite);
			}

		});
	}

	private void update(AbstractDistributionComposite<?> composite) {
		if (composite.getResult() == null) {
			plot.clear();
		} else {
			plot.update(composite.getResult());
		}
		root.layout(true);
	}
	
	/**
	 * Set layout data
	 * @param data
	 */
	public void setLayoutData(Object data) {
		root.setLayoutData(data);
	}
	
	/**
	 * selects the wanted distribution to combo box index
	 * @param index
	 * @return
	 */
	private AbstractDistributionComposite<?> selectComposite(int index){
		switch(index) {
		case 0:
			return new BinomialComposite(config);
		case 1:
			return new GeometricComposite(config);
		case 2:
			return new PoissonComposite(config);
		case 3:
			return new PascalComposite(config);
		case 4:
			NormalComposite norm = new NormalComposite(config);
			norm.setDiscrete(true);
			return norm;
		case 5:
			CauchyComposite cauchy = new CauchyComposite(config);
			cauchy.setDiscrete(true);
			return cauchy;
		case 6:
			ChiSquaredComposite chi = new ChiSquaredComposite(config);
			chi.setDiscrete(true);
			return chi;
		case 7:
			ExponentialComposite exp = new ExponentialComposite(config);
			exp.setDiscrete(true);
			return exp;
		case 8:
			LaplaceComposite laplace = new LaplaceComposite(config);
			laplace.setDiscrete(true);
			return laplace;
		case 9:
			NormalComposite normC = new NormalComposite(config);
			normC.setDiscrete(false);
			return normC;
		case 10:
			CauchyComposite cauchyC = new CauchyComposite(config);
			cauchyC.setDiscrete(false);
			return cauchyC;
		case 11:
			ChiSquaredComposite chiC = new ChiSquaredComposite(config);
			chiC.setDiscrete(false);
			return chiC;
		case 12:
			ExponentialComposite expC = new ExponentialComposite(config);
			expC.setDiscrete(false);
			return expC;
		case 13:
			LaplaceComposite laplaceC = new LaplaceComposite(config);
			laplaceC.setDiscrete(false);
			return laplaceC;
		default:
			return null;
		}
	}
}
