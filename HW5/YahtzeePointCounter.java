import acm.program.GraphicsProgram;
import acm.io.*;
import acm.program.*;
import acm.util.*;

public class YahtzeePointCounter extends GraphicsProgram implements
		YahtzeeConstants {

	// if n return points for n category. that's all.
	public int points(int[] dice, int category) {
		if (category == 1)
			return countOnes(dice);
		if (category == 2)
			return countTwos(dice);
		if (category == 3)
			return countThrees(dice);
		if (category == 4)
			return countFours(dice);
		if (category == 5)
			return countFives(dice);
		if (category == 6)
			return countSixes(dice);
		if (category == 9)
			return sum(dice);
		if (category == 10)
			return sum(dice);
		if (category == 11)
			return 25;
		if (category == 12)
			return 30;
		if (category == 13)
			return 40;
		if (category == 14)
			return 50;
		else
			return sum(dice);
	}

	// count ones and return points
	private int countOnes(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 1)
				n++;
		}
		return n * 1;
	}

	private int countTwos(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 2)
				n++;
		}
		return n * 2;
	}

	private int countThrees(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 3)
				n++;
		}
		return n * 3;
	}

	private int countFours(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 4)
				n++;
		}
		return n * 4;
	}

	private int countFives(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 5)
				n++;
		}
		return n * 5;
	}

	private int countSixes(int[] arr) {
		int n = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (arr[i] == 6)
				n++;
		}
		return n * 6;
	}

	private int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < N_DICE; i++) {
			sum = sum + arr[i];
		}
		return sum;
	}

}
