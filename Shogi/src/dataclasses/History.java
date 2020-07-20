package dataclasses;

import java.util.LinkedList;

/**
 * This class represents the history a shogi game, that keeps a specified number
 * of rounds safed.
 * 
 */
public class History {

	private final int length;
	private final LinkedList<String> entries = new LinkedList<String>();

	/**
	 * The constructor of this class.
	 * 
	 * @param length the maximum number of rounds the history can safe.
	 */
	public History(int length) {
		this.length = length;
	}

	/**
	 * Getter of the entries.
	 * 
	 * @return the entries
	 */
	public LinkedList<String> getEntries() {
		return entries;
	}

	/**
	 * Adds a new entry to the history at the end of the list and deletes the oldest
	 * entry if the maximum number of entries would be exceeded.
	 * 
	 * @param entry that will be added to the history.
	 */
	public void addEntry(String entry) {
		if (entries.size() == length) {
			entries.removeFirst();
		}
		entries.add(entry);
	}
}
