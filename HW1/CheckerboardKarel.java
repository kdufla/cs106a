/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends Karel {

	public void run() {
		board();
		finish();
		last();
	}

	private void board() {
		//left bolo wertilamde carielia luwi simaRlisebSi da bolo striqonze araa carieli kenti simaRlisebSi
		while (leftIsClear()) {
			putBeeper();
			next();
			next();
		}
	}

	private void finish() {
		//Tu luwi simaRlis samyaroa board() yovrelTvis Cerdeba iq sadac saWiroa beeperis dadeba da mTeli bolo striqoni Sesavsebia. 
		while (frontIsClear()) {
			putBeeper();
			next();
			next();
		}
	}

	private void last() {
		//tu bolos dado, gadavida dagadavida bolo wertilze ar daudia. amitom Semowmeba unda aseTi SemTxveva ho ar aris.
		if (facingEast()) {
			//luw striqons yovelTvis marcxniv amTavrebs Tan SeuZlebelia luwis pirvel svetze beeper idos. Wadraki ar gamova mase. marto kents Tu sWirdeba kenti aseT dros facingEast amTavrebs.
			back();
			move();
			if (noBeepersPresent()) {
				back();
				move();
				putBeeper();
			} else {
				back();
				move();
			}
		}
	}

	private void next() {
		//igive move aris ubralod roca kedelTanaa zeviT adis
		if (frontIsClear()) {
			move();
		} else {
			if (facingEast()) {
				turnLeft();
				if (frontIsClear()) {
					move();
					turnLeft();
				}
			} else {
				turnR();
				if (frontIsClear()) {
					move();
					turnR();
				}
			}
		}
	}

	private void turnR() {
		turnLeft();
		turnLeft();
		turnLeft();
	}

	private void back() {
		turnLeft();
		turnLeft();
	}
}