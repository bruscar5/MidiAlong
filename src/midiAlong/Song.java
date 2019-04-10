package midiAlong;

import java.util.ArrayList;
import java.util.List;

import org.jfugue.theory.Note;


public class Song {
	Note [] song;
	int [] Measure;
	int numMeasures;
	int [] measures;
	double currentMeasure;
	boolean full;
	boolean empty;
	
	public Song() {
		full = false;
		empty = true;
		currentMeasure = 1;
		numMeasures = 0;
	}
	
	public void newSong(List<Note> a) {
		song = new Note[a.size()];
		song = a.toArray(song);
		measures = new int[song.length];
		for (int i = 0; i < song.length; i++) {
			if (currentMeasure - song[i].getDuration() <= 0) {
				numMeasures++;
			}
			measures[i] = numMeasures;
		}
	}
	
	public Note [] getSong() {
		return song.clone();
	}
	
	
	public String toString() {
		String s = "";
		for (int i = 0; i < song.length ; i++) {
			s.concat(song[i].toString());	
			s.concat(" , ");
		}
		return s;
	}
	
	
	public static void main (String [] args) {
		
	}
}
