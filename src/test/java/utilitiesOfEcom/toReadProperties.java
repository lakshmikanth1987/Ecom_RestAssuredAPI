package utilitiesOfEcom;

import java.util.ResourceBundle;

public class toReadProperties {
	
	public static ResourceBundle getProperties() {
		ResourceBundle routes= ResourceBundle.getBundle("routes"); //Load the Properties 
		return routes;
	}

}
