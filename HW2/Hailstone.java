/*
 * File: Hailstone.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		println("this program uses special calculations and gets same answer for every number");
		int process = 0;
		int n = readInt("enter number: ");
		// this calculation is only for positive integers. this loop is to deny
		// access for negatives and zero.
		while (n <= 0) {
			println("you know, that this calculations are only for positive integers. please, stop searching bugs and try again:");
			n = readInt("enter number: ");
		}
		while (true) {
			/*
			 * this loop takes number n and after calculations makes it 1. if n
			 * is even n=n/2 and if n is odd n=3n+1 until n=1. it prints
			 * calculations and number of processes (integer process).
			 */
			if (n == 1)
				break;
			if (n % 2 == 0) {
				println(n + " is even so I take half: " + n / 2);
				n = n / 2;
			} else {
				println(n + " is odd so I make 3n+1: " + (3 * n + 1));
				n = 3 * n + 1;
			}
			// this is for counting processes.
			process++;
		}
		println("it took " + process + " process to reach 1.");
	}
}
