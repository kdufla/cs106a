import java.io.*;
import java.util.*;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	HashMap<String, NameSurferEntry> namesAndRanks = new HashMap<String, NameSurferEntry>();

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			BufferedReader readName = new BufferedReader(new FileReader(
					filename));
			while (true) {
				String name = readName.readLine();
				if (name == null)
					break;
				NameSurferEntry entry = new NameSurferEntry(name);
				namesAndRanks.put(entry.getName().toLowerCase(), entry);
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		if (namesAndRanks.containsKey(name))
			return namesAndRanks.get(name);
		return null;
	}
}
