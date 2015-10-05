package helper.classes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;

import View.*;

public class DistributionSelect {
	
	private static void selectIntegerDistributionComposite(int selectionIndex, Group group){
		if (selectionIndex == 0) {
			new BinomialComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 1) {
			new GeometricComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 2) {
			new PascalComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 3) {
			new PoissonComposite(group, SWT.NONE, MainWindow.chart);
		}
	}
	
	private static void selectRealDistributionComposite(int selectionIndex, Group group){
		if (selectionIndex == 0) {
			new CauchyComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 1) {
			new ChiSquaredComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 2) {
			new ExponentialComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 3) {
			new LaplaceComposite(group, SWT.NONE, MainWindow.chart);
		}
		else if (selectionIndex == 4) {
			new NormalComposite(group, SWT.NONE, MainWindow.chart);
		}
	}
	
	public static void chooseRealIntergerDistribution(int selectionIndex, Group group, int distributionType){
		if (distributionType == 0) {
			selectIntegerDistributionComposite(selectionIndex, group);
		}
		else if (distributionType == 1) {
			selectRealDistributionComposite(selectionIndex, group);
		}
	}
}
