package org.deidentifier.arx.distribution.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.deidentifier.arx.distribution.model.AbstractDistribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractDistributionComposite<T>{

	/** Field */
	private final Composite root;
	/** Field */
	private final Map<Integer, Text> elements = new HashMap<Integer, Text>();
	/** Field */
	private final List<SelectionListener> listeners = new ArrayList<SelectionListener>();
	/** Field */
	private AbstractDistribution<T> result = null;
	
	/**
	 * Creates a new instance
	 * @param parent
	 */
	public AbstractDistributionComposite(Composite parent) {
		
		this.root = new Composite(parent, SWT.NONE);
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		root.setLayout(grid);
	}
	
	/**
	 * Create double field
	 * @param label
	 * @param initial
	 * @param min
	 * @param max
	 * @return
	 */
	protected int createDoubleField(String label, double initial, final double min, final double max) {

		final Text txt = createText(label, String.valueOf(initial));
		txt.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent event) {
				try {
					Double value = min - 1;
					if (event.text.equals("f") || event.text.equals("d")) {
						event.doit = false;
					}
					else if (!event.text.equals("")) {
						value = Double.valueOf(txt.getText() + event.text);
					}
					else {
						value = Double.valueOf(txt.getText());
					}
					if (value < min || value > max) {
						event.doit = false;
					}
				} catch (Exception e) {
					event.doit = false;
				}
			}
		});
		
		int id = elements.size();
		elements.put(id, txt);
		return id;
	}
	
	/**
	 * Create text field
	 * @param label
	 * @param initial
	 * @param min
	 * @param max
	 * @return
	 */
	protected int createIntegerField(String label, int initial, final int min, final int max) {

		final Text txt = createText(label, String.valueOf(initial));
		txt.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent event) {
				try {
					int value = min - 1;
					if (!event.text.equals("")) {
						value = Integer.valueOf(txt.getText() + event.text);
					}
					else {
						value = Integer.valueOf(txt.getText());
					}

					if (value < min || value > max) {
						event.doit = false;
					}
				} catch (Exception e) {
					event.doit = false;
				}
			}
		});
		
		int id = elements.size();
		elements.put(id, txt);
		return id;
	}

	/**
	 * Create text field
	 * @param label
	 * @param initial
	 * @return
	 */
	private Text createText(String label, String initial) {

		Label lbl = new Label(root, SWT.NONE);
		lbl.setText(label);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		final Text txt = new Text(root, SWT.BORDER);
		txt.setText(initial);
		txt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		txt.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent e) {
				result = createResult();
				for (SelectionListener listener : listeners) {
					Event event = new Event();
					event.widget = root;
					listener.widgetSelected(new SelectionEvent(event));
				}
			}
		});
		
		return txt;
	}
	
	/**
	 * Return the resulting distribution
	 * @return
	 */
	public AbstractDistribution<T> getResult(){
		return this.result;
	}

	/**
	 * Return double value
	 * @param id
	 * @return
	 */
	protected Double getDouble(int id) {
		try{
			return Double.valueOf(this.elements.get(id).getText());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Return integer value
	 * @param id
	 * @return
	 */
	protected Integer getInteger(int id) {
		try{
			return Integer.valueOf(this.elements.get(id).getText());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Set layout data
	 * @param data
	 */
	public void setLayoutData(Object data) {
		root.setLayoutData(data);
	}
	
	public void setResult(AbstractDistribution<T> result){
		this.result = result;
	}

	/**
	 * Adds a selection listener
	 * @param e
	 * @return
	 */
	public boolean addSelectionListener(SelectionListener e) {
		return listeners.add(e);
	}

	/**
	 * Removes a selection listener
	 * @param e
	 * @return
	 */
	public boolean removeSelectionListener(SelectionListener e) {
		return listeners.remove(e);
	}

	/**
	 * Return the resulting distribution, if parameters are OK, null otherwise
	 * @return
	 */
	protected abstract AbstractDistribution<T> createResult();

	/**
	 * Find root
	 * @param distribution
	 * @param start
	 * @param threshold
	 * @return
	 */
	protected int getXWhereYLessThanOrEqualTo(AbstractIntegerDistribution distribution, int start, double threshold) {

		int end = Integer.MAX_VALUE;
		int x = start;
		while (start < end) { 
			x = (start + end) / 2;
			double value = distribution.probability(x);
			if (value < threshold) {
				end = x - 1;
			} else {
				start = x + 1;
			}
		}
		return x;
	}
	
	/**
	 * Find root
	 * @param distribution
	 * @param start
	 * @param threshold
	 * @return
	 */
	protected double getXWhereYLessThanOrEqualTo(AbstractRealDistribution distribution, double start, double threshold) {

		double end = Double.MAX_VALUE;
		double x = start;
		double value = 0d;
		while (Math.abs(start - end) > threshold) {

			x = (start + end) / 2d;
			value = distribution.density(x);
			if (value < threshold) {
				end = x;
			} else {
				start = x;
			}
		}
		return x;
	}
}
