/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		while (true) {
			nPlayers = dialog.readInt("Enter number of players");
			if (nPlayers <= MAX_PLAYERS && nPlayers > 0)
				break;
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		// cycle for rounds
		for (int r = 0; r < N_SCORING_CATEGORIES; r++) {
			// cycle for players
			for (int p = 1; p <= nPlayers; p++) {
				display.printMessage("Round "
						+ (r + 1)
						+ ". "
						+ playerNames[p - 1]
						+ "'s turn. Click \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(p);
				dice = changeDiceValues(dice);
				display.displayDice(dice);
				// turns
				for (int turns = 0; turns < 2; turns++) {
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
					display.waitForPlayerToSelectDice();
					dice = changeSelectedValues(dice);
					display.displayDice(dice);
				}
				display.printMessage("Select Category");
				// select category
				int category = selectCategory(p);
				if (categoryChecker.checkCategory(dice, category)) {
					writePoints(category, p);
				} else {
					//write zero
					display.updateScorecard(category, p, 0);
				}
				//save selected category in array
				arrSelectedCateg[p][category] = category;
			}
		}

	}

	private void writePoints(int category, int p) {
		// upper score
		if (category < 7) {
			upperScore[p] = upperScore[p] + pointCounter.points(dice, category);
			display.updateScorecard(UPPER_SCORE, p, upperScore[p]);
		}
		// lower score
		if (category > 7) {
			lowerScore[p] = lowerScore[p] + pointCounter.points(dice, category);
			display.updateScorecard(LOWER_SCORE, p, lowerScore[p]);
		}
		// bonus points
		if (upperScore[p] >= 63) {
			upperScore[p] = upperScore[p] + 35;
			display.updateScorecard(UPPER_BONUS, p, 35);
		}
		// total score
		display.updateScorecard(TOTAL, p, upperScore[p] + lowerScore[p]);
		// round score
		display.updateScorecard(category, p,
				pointCounter.points(dice, category));

	}

	// set random values to every member of array
	private int[] changeDiceValues(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		return dice;
	}

	// set random values to every marked member of array
	private int[] changeSelectedValues(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i))
				dice[i] = rgen.nextInt(1, 6);
		}
		return dice;
	}

	// if category is used it makes user chose another category
	private int selectCategory(int p) {
		while (true) {
			int category = display.waitForPlayerToSelectCategory();
			// player selected category. if it isn't used, method will return it
			if (categoryIsNotUsed(category, p))
				return category;
			display.printMessage("You cant choose same category more than once. Choose again.");
		}
	}

	// check category if it's used
	private boolean categoryIsNotUsed(int category, int p) {
		// in the beginning every member of array equals zero. if it's not zero
		// it equals category number and means that it was changed.
		if (arrSelectedCateg[p][category] == category) {
			return false;
		}
		return true;
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice = new int[N_DICE];
	// list of categories selected by players
	private int[][] arrSelectedCateg = new int[MAX_PLAYERS][N_CATEGORIES];
	// point counter
	YahtzeePointCounter pointCounter = new YahtzeePointCounter();
	// category checker
	YahtzeeCategoryChecker categoryChecker = new YahtzeeCategoryChecker();
	private int[] upperScore = new int[MAX_PLAYERS];
	private int[] lowerScore = new int[MAX_PLAYERS];

}
