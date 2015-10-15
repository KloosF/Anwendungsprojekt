package helper.classes;

import org.deidentifier.arx.distribution.gui.BinomialComposite;
import org.deidentifier.arx.distribution.gui.MainWindow;
import org.deidentifier.arx.distribution.gui.NormalComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;

public class DistributionSelect {
	
	private static void selectIntegerDistributionComposite(int selectionIndex, Group group){
		if (selectionIndex == 0) {
			new BinomialComposite(group);
		}
	}
	
	private static void selectRealDistributionComposite(int selectionIndex, Group group){
		if (selectionIndex == 0) {
			new NormalComposite(group);
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
