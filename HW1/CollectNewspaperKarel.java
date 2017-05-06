/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {

	public void run() {		
		moveToBeeper();
		pickBeeper();
		backToStart();
	}
	/*
	 * pre-condition: start, face-east
	 * post-condition: beeper, face-east
	 */
	private void moveToBeeper(){
		moveR();
		turnLeft();
		move3();
	}
	/*
	 * pre-condition: beeper, face-east
	 * post-condition: start, face-east
	 */
	private void backToStart(){
		back();
		move3();
		moveR();
		turnR();
	}

	private void turnR() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	//move 3 times
	private void move3() {
		move();
		move();
		move();
	}
	private void back() {
		turnLeft();
		turnLeft();
	}
	//move right
	private void moveR() {
		turnR();
		move();
	}
} 
