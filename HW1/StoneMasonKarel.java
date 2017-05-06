/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends Karel {

	public void run() {
		while (frontIsClear()) {
			bricks();
			next();
			finish();
		}
	}

	private void back() {
		turnLeft();
		turnLeft();
	}
/*
 * pre-condition: TaRis qveda wertilzea, aRmosavleTiT iyureba;
 * post-condition: SekeTebuli TaRis qveda wertilzea, aRmosavleTiT iyureba;
 */
	private void bricks() {
		turnLeft();
		while (frontIsClear()) {
			if (beepersPresent()) {
				move();
				//tu beeperi devs meore rom ar daados
			} else {
				putBeeper();
				move();
				//sadac ar devs iq debs
			}
		}
		if (noBeepersPresent()) {
			putBeeper();
			//while bolo wertils ar amowmebs da boloze Tu ar devs rom dados
		}
		back();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}

	private void next() {
		//Semdeg svetze gadadis
		for (int i = 0; i < 4; i++) {
			if (frontIsClear()) {
				move();
			}
		}
	}

	private void finish() {
		//Tu kedelTanaa amowmebs 4T ukan svetia Tu ara. Tu ki, anu kedelTanac sveti unda iyos, Tu ara - maSin ubralod kedelTan dgeba da programa mTavrdeba (anu samyaros sigrZe 4s moduliT 1 araa)
		if (frontIsClear()) {
		} else {
			back();
			move4();
			if (beepersPresent()) {
				// im SemTxvevebSi, roca samyaros sigrZe 4n+1 a da me6 xazze dawerili while bolo svets ar avsebs
				back();
				move4();
				bricks();
			} else {
				back();
				move4();
			}
		}
	} // is workspace gamixseni eclipsis   ertiwami gavixseneb rogor unda proektis damateba

	private void move4() {
		move();
		move();
		move();
		move();
	}
}