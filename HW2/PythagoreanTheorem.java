/*
 * File: PythagoreanTheorem.java
 * Name: giorgi gvelesiani	
 * Section Leader: davit akopovi
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("this progrm can find hypotenuse of any right-angled triangle whith integer cathetuses:");
		println("it's geometry, so you won't be useing negative numbers");
		println("enter integer values to calculate pythagorean theorem:");
		// a is first cathetus
		int a = readInt("first cathetus = ");
		// b is second cathetus
		int b = readInt("second cathetus = ");
		// it's geometry and there's no such thing as negative length. this
		// "while" will approve only positive numbers.
		while (a < 0 || b < 0) {
			println("I said only positive numbers. you won't be able to use negatives, so stop trying. try again positives: ");
			a = readInt("first cathetus = ");
			b = readInt("second cathetus = ");
		}
		// this is hypotenuse. it's double because sqrt of sum is not always
		// integer.
		double c = Math.sqrt((a * a + b * b));
		println("hypotenuse = " + c);
	}
}
