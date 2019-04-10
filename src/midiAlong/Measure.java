package midiAlong;

import java.util.ArrayList;
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
		measure = new ArrayList<Note>();
		measureLength = 1;
	}
	
	public boolean addNote(Note a) {
		if (measureLength +  a.getDuration() > 1) {
			full = true;
			return false;
		} else {
			measure.add(a);
			empty = false;
			measureLength = measureLength + a.getDuration();
			return true;
		}
	}
	
	public Note getNextNote() {
		if (measureLength <= 0) {
			empty = true;
			throw new ArrayIndexOutOfBoundsException("Out of bounds");
		}
		Note temp = measure.get(0);
		measure.remove(0);
		measureLength = measureLength - temp.getDuration();
		return temp;
	}
	
	public boolean isEmpty() {
		if (measure.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String m = "";
		while (!measure.isEmpty()) {
			Note temp = measure.get(0);
			measure.remove(0);
			m.concat(temp.toString());
			m.concat(" , ");
		}
		return m;
	}

}
