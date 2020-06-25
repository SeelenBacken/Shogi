package dataclasses;

import java.util.LinkedList;

public class History {

	private final int length;
	private final LinkedList<String> entries = new LinkedList<String>();

	public History(int length) {
		this.length = length;
	}

	public void addEntry(String entry) {
		if (entries.size() == length) {
			entries.removeFirst();
		}
		entries.add(entry);
	}

	public LinkedList<String> getEntries() {
		return entries;
	}
}
