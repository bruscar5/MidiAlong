package midiAlong;

import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MusicNote.Note;

public class ResourceThread extends Thread {
	private static final int NUM_NOTES = 88;
	
	private boolean [] midiNotesOn = new boolean[NUM_NOTES];
	private boolean [] songNotesOn = new boolean[NUM_NOTES];
	
	public ResourceThread() {
		for (int i = 0; i < NUM_NOTES; i++) {
			midiNotesOn[i] = false;
			songNotesOn[i] = false;
		}
	}
	
	public void setNoteOn(int arr, int index) {
		if (arr == 1)
			songNotesOn[index] = true;
		else
			midiNotesOn[index] = true;
	}
	
	public boolean[] getNotesOn(int arr) {
		if (arr == 1)
			return songNotesOn;
		else
			return midiNotesOn;
	}

}
