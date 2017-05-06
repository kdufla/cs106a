/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends Karel {

	public void run() {
		findMiddle();
		goToBotMiddle();
		putBeeper();
	}
	private void findMiddle(){
		//2 zeviT, 1 marjvniv anu zeda kedlis SuaSi miva.
		turnLeft();
		while(frontIsClear()){
			move();
			if(frontIsClear()){
				//Tu luwi sigrZisaa bolos 1 zeviT, 1 marjvniv gamodis da Sua ori wertilidan marjvenaze midis.
				move();
			}
			turnR();
			move();
			turnLeft();
		}
	}
	private void goToBotMiddle(){
		//Sua svetze ukve aris. axla zeda striqonidan qvedaze unda Camovides
		back();
		while(frontIsClear()){
			move();
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
