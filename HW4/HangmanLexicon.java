/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;

public class HangmanLexicon {
	public int lexNum;
	public String[] arLexicon;

	public HangmanLexicon() {
		// I think user won't use bigger lexicon than one with 1000000 words
		arLexicon = new String[1000000];
		lexNum = 0;
		try {
			BufferedReader Words = new BufferedReader(new FileReader(
					"HangmanLexicon.txt"));
			while (true) {
				String word = Words.readLine();
				if (word == null)
					break;
				arLexicon[lexNum++] = word;
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexNum;
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {

		return arLexicon[index];
	}
}
