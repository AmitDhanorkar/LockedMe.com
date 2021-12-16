/**

 *@ClassName : LockedMeMain.java

 *

 *@author :  Amit Dhanorkar

 *

 *@Version :  1.0

 *

 *@Date :  Dec 14,2021

 *

 */
package com.lockedme.main;

import com.lockedme.handler.FileOperation;
import com.lockedme.handler.MenuOptionHandler;
import com.lockedme.welcomescreen.WelcomeScreen;

public class LockedMeMain {

	public static void main(String[] args) {
		FileOperation.getInstance().createDirectoryIfNotExists();
		WelcomeScreen.getInstance().printWelcomeScreen();
		MenuOptionHandler.getInstance().welcomeScreenMenuOption();

	}

}
