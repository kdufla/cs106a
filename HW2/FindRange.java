/*
 * File: FindRange.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	// SENTINEL is special sign for this program to stop
	private static final int SENTINEL = 0;

	public void run() {
		println("write numbers and this program will find which is largest and which is smallest. write 0 to tell program, that you want to see answer");
		int value = readInt("enter value: ");
		while (value == SENTINEL) {
			/*
			 * if value equals SENTINEL there's no largest or smallest between
			 * nothing. program gives user another chance. first value has to be
			 * anything but 0, so while first value is 0 program keeps showing
			 * error.
			 */
			println("error: you had to write numbers first! try again:");
			value = readInt("enter value: ");
		}
		/*
		 * there's only one value, so if user decides to see result this number
		 * will be both, largest and smallest. largest is largest and smallest
		 * is smallest.
		 */
		int largest = value;
		int smallest = value;
		/*
		 * this indefinite loop continues until user decides that values he/she
		 * entered are enough. after entering 0 (value != SENTINEL) is false, so
		 * "if" does nothing and than "while" does nothing too.
		 */
		while (value != SENTINEL) {
			value = readInt("enter value: ");
			if (value != SENTINEL) {
				largest = max(largest, value);
				smallest = min(smallest, value);
			}
		}
		println("largest: " + largest);
		println("smalest: " + smallest);
	}

	// this method shows which number is largest between two integers.
	private int max(int val1, int val2) {
		if (val1 > val2)
			return val1;
		else
			return val2;
	}

	// this method shows which number is smallest between two integers.
	private int min(int val1, int val2) {
		if (val1 < val2)
			return val1;
		else
			return val2;
	}
}
