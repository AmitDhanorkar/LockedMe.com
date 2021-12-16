/**

 *@ClassName : MenuOptionHandler.java

 *

 *@author :  Amit Dhanorkar

 *

 *@Version :  1.0

 *

 *@Date :  Dec 14,2021

 *

 */

package com.lockedme.handler;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.lockedme.util.GetProperties;
import com.lockedme.welcomescreen.WelcomeScreen;

/**
 * This class is a single tone class which will create single object
 * don't modify the constructor modifier 
 * getInstance() method will returns the object of this class
 */
public class MenuOptionHandler {

	private static MenuOptionHandler handler = new MenuOptionHandler();
	private static Properties pr = GetProperties.getProperties();
	private static final String DIR_PATH = pr.getProperty("lockedme.FILE_DIRECTORY_PATH");

	/**
	 * This class is single tone class so we declared private constructor
	 */
	private MenuOptionHandler() { }

	/**
	 * This method will returns the object of this class
	 * @return handler
	 */
	public static MenuOptionHandler getInstance() {
		return handler;
	}

	/**
	 * By overriding clone method we can create only one object for this class
	 * and it doesn't support for cloning this object	 
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}

	/**
	 * This method have operation related to Welcome Screen menu.
	 * Used do...while and switch case to work with welcome Screen menu respectively with option 1, 2 and 3.
	 */
	public void welcomeScreenMenuOption() {
		boolean menuFlag = true;
		Scanner sc = new Scanner(System.in);

		do {
			try {

				WelcomeScreen.getInstance().displayMenu();
				int userInput = sc.nextInt();
				switch (userInput) {

				case 1:
					FileOperation.getInstance().displayAllFilesPresentInDirectory();
					break;

				case 2:
					MenuOptionHandler.getInstance().fileOperationMenuOption();
					break;

				case 3:
					System.out.println("Application logout Successfully!");
					menuFlag = false;
					sc.close();
					System.exit(0);
					break;

				default:
					System.out.println("Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println("******Exception*******"+e.getClass().getName());
				MenuOptionHandler.getInstance().welcomeScreenMenuOption();
			}
		} while (menuFlag);
	}

	/**
	 * This method have operation related to add, delete, search etc.
	 * Used do...while and switch case to work with File menu operation respectively with option 1, 2, 3, 4 and 5.
	 */
	public void fileOperationMenuOption() {
		boolean fileMenuFlag = true;
		Scanner sc = new Scanner(System.in);

		do {
			try {
				WelcomeScreen.getInstance().displayFileOperationMenuOptions();
				int userInput = sc.nextInt();
				switch (userInput) {
				
				case 1:
					System.out.println("Enter the name of the file to be added to the "+"\""+ DIR_PATH + "\""+" folder");
					String fileNameToAdd = sc.next();
					FileOperation.getInstance().addNewFile(fileNameToAdd, sc);
					break;
					
				case 2:
					System.out.println("Enter the name of the file to be deleted to the "+"\""+ DIR_PATH + "\""+" folder");
					String fileNameToDelete = sc.next();
					List<String> filesToDelete = FileOperation.getInstance().displayFileLocation(fileNameToDelete, DIR_PATH);

					String deletionMessage = "\nSelect index of which file to delete?"
							+ "\n(Enter 0 if you want to delete all elements)";
					System.out.println(deletionMessage);

					int inputIndexNum = sc.nextInt();
					if(inputIndexNum != 0)
						FileOperation.getInstance().deleteFile(filesToDelete.get(inputIndexNum - 1));
					else {
						for (String path : filesToDelete) {
							FileOperation.getInstance().deleteFile(path);
						}
					}

					break;

				case 3:
					System.out.println("Enter the name of the file to be searched from "+"\""+ DIR_PATH + "\""+" folder");
					String fileName = sc.next();
					
					FileOperation.getInstance().displayFileLocation(fileName, DIR_PATH);
					break;
				
				case 4:
					return;
					
				case 5:
					System.out.println("Application logout Successfully!");
					fileMenuFlag = false;
					sc.close();
					System.exit(0);
					break;
					
				default:
					System.out.println("***Invalid Option!!*** \n\n Please select a valid option from above.");
				}
			} catch (Exception e) {
				System.out.println("******Exception*******"+e.getClass().getName());
				MenuOptionHandler.getInstance().fileOperationMenuOption();
			}
		} while (fileMenuFlag);
	}

}
