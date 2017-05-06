/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.io.*;
import java.util.*;

import acm.graphics.GImage;
import acm.util.ErrorException;

public class FacePamphletDatabase implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * database.
	 * it's about importing saved profiles
	 */
	public FacePamphletDatabase() {
		ArrayList<String> baseAll = new ArrayList<String>();
		try {
			BufferedReader readName = new BufferedReader(new FileReader(
					DATA_FILE));
			while (true) {
				String line = readName.readLine();
				if (line == null)
					break;
				StringTokenizer st = new StringTokenizer(line, "@");
			     while (st.hasMoreTokens()) {
			    	 baseAll.add(st.nextToken());
			     }
				FacePamphletProfile entry = new FacePamphletProfile(baseAll.get(0),baseAll.get(1), baseAll.get(2));
				entry.setImage(baseAll.get(3));
				entry.setStatus(baseAll.get(4));
				for(int i = 5;i<baseAll.size(); i++){
					entry.addFriend(baseAll.get(i));
				}
				profileBase.put(entry.getName().toLowerCase(), entry);
				baseAll.clear();
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/**
	 * This method adds the given profile to the database. If the name
	 * associated with the profile is the same as an existing name in the
	 * database, the existing profile is replaced by the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		if (profileBase.containsKey(profile.getName()))
			profileBase.remove(profile.getName());
		profileBase.put(profile.getName(), profile);
		rewriteData();
	}

	/**
	 * This method returns the profile associated with the given name in the
	 * database. If there is no profile in the database with the given name, the
	 * method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		return profileBase.get(name);
	}

	/**
	 * This method removes the profile associated with the given name from the
	 * database. It also updates the list of friends of all other profiles in
	 * the database to make sure that this name is removed from the list of
	 * friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then the
	 * database is unchanged after calling this method.
	 */
	public boolean deleteProfile(String name) {
		if (profileBase.containsKey(name)) {
			profileBase.remove(name);
			rewriteData();
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if there is a profile in the database that has
	 * the given name. It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		if (profileBase.containsKey(name))
			return true;
		return false;
	}

	//renew data os saved users
	public void rewriteData(){
		try{
			BufferedWriter write = new BufferedWriter(new FileWriter(
					DATA_FILE));
	        write.flush();
	        Iterator<String> it = profileBase.keySet().iterator();
			while(it.hasNext()){
				FacePamphletProfile entry = profileBase.get(it.next());
				write.write(entry.toString());
				write.newLine();
			}
			write.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		
	}
	
	HashMap<String, FacePamphletProfile> profileBase = new HashMap<String, FacePamphletProfile>();
}
