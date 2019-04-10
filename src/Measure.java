package midiAlong;

import java.util.List;

import org.jfugue.theory.Note;

public class Measure {
	double measureLength;
	List<Note> measure;
	boolean full;
	boolean empty;
	
	public Measure() {
		full = false;
		empty = true;
		measureLength = 1;
	}
	
	public boolean addNote(Note a) {
		if (measureLength - a.getDuration() < 0) {
			full = true;
			return false;
		} else {
			measure.add(a);
			measureLength = measureLength - a.getDuration();
			return true;
		}
	}
	
	public Note getNextNote() {
		Note temp = measure.get(0);
		measure.remove(0);
		measureLength = measureLength - temp.getDuration();
		if (measureLength <= 0)
			empty = true;
		return temp;
	}
	
	public boolean isEmpty() {
		return empty;
	}
}
