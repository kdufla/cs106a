/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	// word which player has to guess
	private String word;
	RandomGenerator rgen = new RandomGenerator();
	// length of hidden word
	private int leng;
	// number of
	private static final int LIVES = 8;
	// list of wrong letters
	private String wrongLetter;

	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		HangmanLexicon lex = new HangmanLexicon();
		//println(lex.getWordCount());
		//println(lex.getWord(3453));
		game(lex);
	}

	// here are initialization, game process as method and exit/play again
	private void game(HangmanLexicon lex) {
		while (true) {
			canvas.reset();
			wrongLetter = "";
			word = lex.getWord(rgen.nextInt(0, lex.getWordCount()));
			leng = word.length();
			char letter = 0;
			// how know part of word looks like
			String look = "";
			for (int i = 0; i < leng; i++) {
				look = look + "-";
			}
			int live = LIVES;
			// major part
			process(letter, look, live);
			finalChoise();
		}
	}

	private void process(char letter, String look, int live) {
		while (true) {
			canvas.displayWord(look);
			// if bool is false, player guessed letter, so he won't lose lives
			Boolean bool = true;
			println("word looks like this: " + look);
			/*
			 * player can write anything but game will only accept first
			 * character of string written by player. only if first character is
			 * letter/
			 */
			letter = readLine("your guess: ").toUpperCase().charAt(0);

			for (int e = 0; e < leng; e++) {
				// check every character of word and replace "-" with letter.
				if (word.charAt(e) == letter) {
					String s = "" + letter;
					look = look.substring(0, e) + s
							+ look.substring(e + 1, leng);
					bool = false;
				}
				if (look.charAt(e) == letter)
					bool = false;
			}
			// decrease lives and write wrong guesses
			if (bool) {
				live--;
				// if letter written by player isn't letter game wont accept it.
				if ((letter >= 'a' && letter <= 'z')
						|| (letter >= 'A' && letter <= 'Z'))
					;
				else {
					println("illegal symbol");
					live++;
				}
				wrongLetter = wrongLetter + letter;
				canvas.noteIncorrectGuess(live, wrongLetter);
			}
			
			// count left spaces to know if there still are letters tu guess
			if (countLeftSpaces(look))
				break;
			// if player is out of lives he lost.
			if (lost(live))
				break;
		}
	}

	// play again or exit game
	private void finalChoise() {
		String stop = "";
		stop = readLine("write no if you don't want to play again. write last name of your first gf/bf to play again: ");
		if (stop.equals("no"))
			exit();
	}

	private boolean countLeftSpaces(String look) {
		// number of unknown spaces in word
		int q = 0;
		for (int o = 0; o < leng; o++) {
			if (look.charAt(o) == '-')
				q++;
		}
		// if everything is known, player won.
		if (q == 0) {
			println("your word is " + word + ". you won!");
			return true;
		}
		return false;
	}

	// if player is out of lives game will end.
	private boolean lost(int live) {
		if (live <= 0) {
			println("your word was " + word + ". you lost :(");
			return true;
		}
		println("you still have " + live + " lives");
		return false;
	}
}
