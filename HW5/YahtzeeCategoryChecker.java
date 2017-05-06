import acm.program.GraphicsProgram;
import acm.io.*;
import acm.program.*;
import acm.util.*;

//everything is very simple logic. don't know what to comment

public class YahtzeeCategoryChecker extends GraphicsProgram implements
		YahtzeeConstants {

	//check every category. 
	public boolean checkCategory(int[] dice, int category) {
		if (category == 1)
			return checkOnes(dice);
		if (category == 2)
			return checkTwos(dice);
		if (category == 3)
			return checkThrees(dice);
		if (category == 4)
			return checkFours(dice);
		if (category == 5)
			return checkFives(dice);
		if (category == 6)
			return checkSixes(dice);
		if (category == 9)
			return checkThreeOfAKind(dice);
		if (category == 10)
			return checkFourOfAKind(dice);
		if (category == 11)
			return checkFullHouse(dice);
		if (category == 12)
			return checkSmallStraight(dice);
		if (category == 13)
			return checkLargeStraight(dice);
		if (category == 14)
			return checkYahtzee(dice);
		if (category == 15)
			return checkChance(dice);
		else
			return false;
	}

	private boolean checkOnes(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 1)
				return true;
		}
		return false;
	}

	private boolean checkTwos(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 2)
				return true;
		}
		return false;
	}

	private boolean checkThrees(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 3)
				return true;
		}
		return false;
	}

	private boolean checkFours(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 4)
				return true;
		}
		return false;
	}

	private boolean checkFives(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 5)
				return true;
		}
		return false;
	}

	private boolean checkSixes(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 6)
				return true;
		}
		return false;
	}

	private boolean checkThreeOfAKind(int[] dice) {
		for (int j = 1; j < 7; j++) {
			int n = 0;
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == j)
					n++;
				if (n == 3)
					return true;
			}
		}
		return false;
	}

	private boolean checkFourOfAKind(int[] dice) {
		for (int j = 1; j < 7; j++) {
			int n = 0;
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == j)
					n++;
				if (n == 4)
					return true;
			}
		}
		return false;
	}

	private boolean checkYahtzee(int[] dice) {
		for (int j = 1; j < 7; j++) {
			int n = 0;
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == j)
					n++;
				if (n == 5)
					return true;
			}
		}
		return false;
	}

	private boolean checkSmallStraight(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			for (int o = 0; o < N_DICE; o++) {
				if (o == i+1) {
					for (int u = 0; u < N_DICE; u++) {
						if (u == o+1) {
							for (int p = 0; p < N_DICE; p++) {
								if (p == u+1)
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean checkLargeStraight(int[] dice) {
		for (int i = 0; i < N_DICE; i++) {
			for (int o = 0; o < N_DICE; o++) {
				if (o == i+1) {
					for (int u = 0; u < N_DICE; u++) {
						if (u == o+1) {
							for (int p = 0; p < N_DICE; p++) {
								if (p == u+1) {
									for (int l = 0; l < N_DICE; l++) {
										if (l == p+1)
											return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean checkChance(int[] dice) {
		return true;
	}

	//check for three of a kind and than check if other two are same
	private boolean checkFullHouse(int[] dice) {
		for (int j = 1; j < 7; j++) {
			int n = 0;
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == j)
					n++;
				if (n == 3) {
					for (int x = 1; x < 7; x++) {
						int m = 0;
						for (int z = 0; z < N_DICE; z++) {
							if (dice[z] == x)
								m++;
							if (m == 2 && m != j) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
