package midiAlong;
import java.util.ArrayList;
import java.util.List;

import org.jfugue.theory.Note;

public class PitchToNote {
	List<Note> notes; 
	
	public PitchToNote() {
		notes = new ArrayList<Note>();
		
		for (int i = 0; i < 88; i++) {
			Note note = new Note();
			byte n = (byte)(i + 21);
			note.setValue(n);
			notes.add(note);
		}
	}
	
	public void printNotes() {
		for (int i = 0; i < 88; i++) {
			System.out.println(notes.get(i).toString());
			System.out.println(Note.getFrequencyForNote(notes.get(i).toString()));
		}
	}
	
	public String estimateNote(double freq) {
		double minimumDiff = 10000;
		double actualFreq;
		double diff;
		int index = 0;
		for (int i = 0; i < 88; i++) {
			actualFreq = Note.getFrequencyForNote(notes.get(i).toString());
			diff = Math.abs(freq - actualFreq);
			if (diff < minimumDiff) {
				minimumDiff = diff;
				index = i;
			}
		}
		
		/*for (int i = 0; i < 88; i++) {
			Note note = notes.get(i);
			double noteFreq = Note.getFrequencyForNote(i + 21);
			double error = freq - noteFreq;
			int n = (int)(freq);
			if (n == (int)(Note.getFrequencyForNote(notes.get(i).toString()))) {
				System.out.println("Note matched: " + notes.get(i).toString());
			}
			if (Math.pow(error, 2) < 10) {
				return note.toString();
			}
		} */
	return notes.get(index).toString();
	}
	
	
	public static void main (String [] args) {
		PitchToNote ex = new PitchToNote();
		ex.printNotes();
	}
}
