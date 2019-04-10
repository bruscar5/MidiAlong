package midiAlong;

import java.util.ArrayList;
import java.util.List;

import org.jfugue.theory.Note;

public class Song {
	List<Measure> song;
	double numMeasures;
	
	public Song() {
		song = new ArrayList<Measure>();
	}
	
	public void newSong(List<Note> a) {
		Measure measure = new Measure();
		System.out.println("hi");
		for (int i = 0; i < a.size(); i++) {
			if (measure.addNote(a.get(i))) {
			} else {
				song.add(measure);
				numMeasures++;
				measure = new Measure();
				measure.addNote(a.get(i));
			}
		}
	}
	
	public Measure getNextMeasure() {
		Measure temp = song.get(0);
		song.remove(0);
		return temp;
	}
	
	public boolean isEmpty() {
		if (song.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
