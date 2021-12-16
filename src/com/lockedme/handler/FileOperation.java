/**

 *@ClassName : FileOperation.java

 *

 *@author :  Amit Dhanorkar

 *

 *@Version :  1.0

 *

 *@Date :  Dec 14,2021

 *

 */

package com.lockedme.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lockedme.util.GetProperties;

/**
 * This class is a single tone class which will create single object
 * don't modify the constructor modifier 
 * getInstance() method will returns the object of this class
 */
public class FileOperation {

	private static FileOperation fileOperation = new FileOperation();
	private static Properties pr = GetProperties.getProperties();
	private static final String DIR_PATH = pr.getProperty("lockedme.FILE_DIRECTORY_PATH");

	/**
	 * This class is single tone class so we declared private constructor
	 */
	private FileOperation() { }

	/**
	 * This method will returns the object of this class
	 * @return fileOperation
	 */
	public static FileOperation getInstance() {
		return fileOperation;
	}

	/**
	 * By overriding clone method we can create only one object for this class
	 * and it doesn't support for cloning this object	 
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Create Directory if not exists
	 */
	public void createDirectoryIfNotExists() {
		File file = new File(DIR_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * Method to display all files present inside directory
	 */
	public void displayAllFilesPresentInDirectory() {
		File file = new File(DIR_PATH);
		File[] files = file.listFiles();
		List<File> filesList = Arrays.asList(files);

		Collections.sort(filesList);

		if(files != null && files.length > 0) {
			System.out.println("Displaying all files in ascending order\n");
			for (File file2 : filesList) {
				System.out.println(file2.getName());
			}
		} else
			System.out.println("Dirctory is Empty!");
	}

	/**
	 * Creates New File named by this abstract pathname if
	 * and only if a file with this name does not yet exist.
	 * 
	 * @param fileNameToAdd A fileNameToAdd string
	 * @param sc {@code Scanner}
	 * 
	 */
	public void addNewFile(String fileNameToAdd, Scanner sc) {
		File f = new File(DIR_PATH, fileNameToAdd);
		FileOutputStream outputStream = null;
		try {
			if(f.createNewFile()) {
				System.out.println(fileNameToAdd + " created successfully");
				System.out.println("Would you like to add some content to the file? (Y/N)");
				String choice = sc.next().toLowerCase();

				sc.nextLine();
				if (choice.equals("y")) {
					System.out.println("\n\nInput content and press enter\n");
					String content = sc.nextLine();
					outputStream = new FileOutputStream(f);
					outputStream.write(content.getBytes());
					System.out.println("\nContent written to file " + fileNameToAdd);
				}
			} else {
				System.out.println("File already exist in directory with this name.");
			}
		} catch (IOException e) {
			System.out.println("Failed to create file " + fileNameToAdd);
			e.printStackTrace();
		}
	}

	/**
	 * Display files location of present files in directory. If
	 * file is not available in directory then print appropriate message.
	 * 
	 * @param fileName A fileName to display its location
	 * @param directoryPath A directoryPath where files placed
	 * @return listOfFileNames A listOfFileNames present in directoryPath
	 * @throws Exception 
	 */
	public List<String> displayFileLocation(String fileName, String directoryPath) throws Exception {
		List<String> listOfFileNames = new ArrayList<>();
		FileOperation.getInstance().searchFileInDirectory(fileName, directoryPath, listOfFileNames);

		if(listOfFileNames.isEmpty())
			System.out.println("\n\n***** Could not find any file with given file name as \"" + fileName + 
					"\" *****\n Please check the file name is correct.");
		else {
			System.out.println("Found File at below location");

			List<String> files = IntStream.range(0, listOfFileNames.size())
					.mapToObj(index -> (index + 1) + ": " + listOfFileNames.get(index)).collect(Collectors.toList());

			files.forEach(System.out::println);
		}

		return listOfFileNames;
	}

	/**
	 * Search the file or directory denoted by this pathname in created directory.
	 * 
	 * @param fileName A fileName to search
	 * @param directoryPath A directoryPath where files placed
	 * @param listOfFileNames A listOfFileNames present in directoryPath
	 * @throws Exception
	 * 
	 */
	private void searchFileInDirectory(String fileName, String directoryPath, List<String> listOfFileNames) throws Exception {
		File dir = new File(directoryPath);
		File[] files = dir.listFiles();
		List<File> filesList = Arrays.asList(files);

		if (files != null && files.length > 0) {
			for (File file : filesList) {

				if (file.getName().startsWith(fileName)) {
					listOfFileNames.add(file.getAbsolutePath());
				}

				if (file.isDirectory()) {
					searchFileInDirectory(file.getAbsolutePath(), fileName, listOfFileNames);
				}
			}
		}

	}


	/**
	 * Deletes the file or directory denoted by this pathname.  If
     * this pathname denotes a directory, then the directory must be empty in
     * order to be deleted
     * 
	 * @param directoryPath
	 */
	public void deleteFile(String directoryPath) throws Exception {
		File f = new File(directoryPath);
		File[] files = f.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {

				String fileName = file.getName() + " at " + file.getParent();
				if (file.isDirectory()) {
					deleteFile(file.getAbsolutePath());
				}

				if (file.delete()) { 
					System.out.println(fileName + " deleted successfully from directory");
				} else {
					System.out.println("Failed to delete " + fileName);
				}
			}
		}

		String currFileName = f.getName() + " at " + f.getParent();
		if (f.delete())
			System.out.println(currFileName + " deleted successfully  from directory");
		else 
			System.out.println("Failed to delete " + currFileName);

	}
}