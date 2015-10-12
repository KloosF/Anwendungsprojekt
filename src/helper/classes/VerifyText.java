package helper.classes;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

public class VerifyText {

	//hilft dabei zu prüfen ob gerade an einem anderen textfeld gearbeitet wird
	private static boolean verificationInProgress = false;
	
	public static void verifyProbability(VerifyEvent e){
		
		verificationInProgress = true;
		
		Text text = (Text)e.getSource();

        // get old text and create new text by using the VerifyEvent.text
        final String oldS = text.getText();
        String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

        //states if String is double
        boolean isDouble = true;
        try
        {
        	//empty String is not parsable so check for that first
        	if (!newS.equals("")) {
        		Double newSDouble = Double.parseDouble(newS);
        		
        		//probability should not be greater than 1, also 1. should not be writable
        		if (newSDouble > 1 || newS.equals("1.")) {
					isDouble = false;
				}
			}
        	
        	//take d and f out of double
        	if (newS.contains("f") || newS.contains("d")) {
				isDouble = false;
			}
        }
        //if parse throws exception
        catch(NumberFormatException ex)
        {
        	isDouble = false;
        }

        if(!isDouble){
            e.doit = false;
        }
        
      //Bearbeitung beendet
        verificationInProgress = false;
	}
	
	public static void verifyTrials(VerifyEvent e){
		
		verificationInProgress = true;
		
		String text = e.text;
		char[] chars = new char[text.length()];
		text.getChars(0, chars.length, chars, 0);
		for (int i = 0; i < chars.length; i++) {
			if(!('0' <= chars[i] && chars[i] <= '9')){
				e.doit = false;
				return;
			}
		}
		
		//Bearbeitung beendet
        verificationInProgress = false;
	}
	
	public static void verifyMean(VerifyEvent e){
		
		verificationInProgress = true;
		
		Text text = (Text)e.getSource();

        // get old text and create new text by using the VerifyEvent.text
        final String oldS = text.getText();
        String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

        //states if String is double
        boolean isDouble = true;
        try
        {
        	//empty String is not parsable so check for that first
        	if (!newS.equals("")) {
        		Double newSDouble = Double.parseDouble(newS);
			}
        	
        	//take d and f out of double
        	if (newS.contains("f") || newS.contains("d")) {
				isDouble = false;
			}
        }
        //if parse throws exception
        catch(NumberFormatException ex)
        {
        	isDouble = false;
        }

        if(!isDouble){
            e.doit = false;
        }
        
        //Bearbeitung beendet
        verificationInProgress = false;
	}
	
	public static boolean getVerificationFlag(){
		return verificationInProgress;
	}
}
