/**

 *@ClassName : WelcomeScreen.java

 *

 *@author :  Amit Dhanorkar

 *

 *@Version :  1.0

 *

 *@Date :  Dec 14,2021

 *

 */

package com.lockedme.welcomescreen;

import java.util.Properties;

import com.lockedme.util.GetProperties;

/**
 * This class is a single tone class which will create single object
 * don't modify the constructor modifier 
 * getInstance() method will returns the object of this class
 */
public class WelcomeScreen {

	private static WelcomeScreen welcomeScreen = new WelcomeScreen();
	private static Properties pr = GetProperties.getProperties();
	public static final String APP_NAME = pr.getProperty("lockedme.APP_NAME");
	public static final String DEVELOPER_NAME = pr.getProperty("lockedme.DEVELOPER_NAME");
	private static final String DIR_PATH = pr.getProperty("lockedme.FILE_DIRECTORY_PATH");

	/**
	 * This class is single tone class so we declared private constructor
	 */
	private WelcomeScreen() { }

	/**
	 * This method will returns the object of this class
	 * @return welcomeScreen
	 */
	public static WelcomeScreen getInstance() {
		return welcomeScreen;
	}

	/**
	 * By overriding clone method we can create only one object for this class
	 * and it doesn't support for cloning this object	 
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Print Welcome Screen message and shows uses of this application
	 */
	public void printWelcomeScreen() {
		String companyDetails = "*****************************************************\n"
				+ " \t\t Welcome to "+APP_NAME+" \n" + " This application was developed by "+DEVELOPER_NAME+".\n"
				+ "*****************************************************\n";
		String appFunction = "You can use this application to :-\n"
				+ "• Retrieve all file names in the "+"\"" + DIR_PATH + "\""+" folder\n"
				+ "• Search, add, or delete files in the "+"\""+ DIR_PATH + "\""+" folder.\n"
				+ "\n**Please be careful to ensure the correct filename is provided for searching or deleting files.**\n";

		System.out.println(companyDetails);
		System.out.println(appFunction);
	}

	/**
	 * Display Main menu option
	 */
	public void displayMenu() {
		String menu = "\n\n****** Select any option number from below and press Enter ******\n\n"
				+ "1) Retrieve all files from "+"\""+ DIR_PATH + "\""+" folder\n" + "2) Display menu for File operations\n"
				+ "3) Exit program\n";
		System.out.println(menu);

	}

	/**
	 * Display Add, Delete, Search etc File Operation Menu Options
	 */
	public void displayFileOperationMenuOptions() {
		String fileMenu = "\n\n****** Select any option number from below and press Enter ******\n\n"
				+ "1) Add a file to "+"\""+ DIR_PATH + "\""+" folder\n" + "2) Delete a file from "+"\""+ DIR_PATH + "\""+" folder\n"
				+ "3) Search for a file from "+"\""+ DIR_PATH + "\""+" folder\n" + "4) Show Previous Menu\n" + "5) Exit program\n";

		System.out.println(fileMenu);
	}

}
