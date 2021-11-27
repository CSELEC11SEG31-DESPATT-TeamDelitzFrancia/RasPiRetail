package com.raspi.retail.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Contains methods that get input data from the user, as Java doesn't have a prebuilt way for doing so.
 */
public class KBInput {

	/**
	 * creates an input stream that can access inputted characters from the CLI
	 * @return input stream that can be used to extract a line of characters inputted by the user
	 */
	private static BufferedReader getReader() {

		return new BufferedReader(
			new InputStreamReader(System.in));

	}

	/**
	 * read user input as a string
	 * @param prompt - Custom message before the input
	 * @return output string
	 */
	public static String readString(String prompt) {

		String input = "";

		System.out.print(prompt);

		try {
			input = getReader().readLine();
		} catch (IOException exc) {
			System.err.println(exc.getMessage());
		}

		return input;
	}

	/**
	 * read user input as an integer
	 * @param prompt - Custom message before the input
	 * @return output integer
	 */
	public static int readInt(String prompt) {

		int input = 0;

		System.out.print(prompt);


		try {
			input = Integer.parseInt(getReader().readLine());
		} catch (IOException exc) {
			System.err.println(exc.getMessage());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Input!\n" + nfe.getLocalizedMessage());
		}

		return input;

	}

	/**
	 * read user input as a double
	 * @param prompt - Custom message before the input
	 * @return output integer
	 */
	public static double readDouble(String prompt) {

		double input = 0;

		System.out.print(prompt);

		try {
			input = Double.parseDouble(getReader().readLine());
		} catch (IOException exc) {
			System.err.println(exc.getMessage());
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Input!\n" + nfe.getLocalizedMessage());
		}

		return input;

	}

}


