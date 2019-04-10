package midiAlong;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfugue.theory.Note;

public class Tracker extends Thread {
	private Note note;
	private JPanel panel;
	private List<Double> durations;
	private List<Note> notes;
	private Note currentNote;
	private Note currentNoteSung;
	private JTextArea textArea;
	private double totalDuration;
	private double duration;
	private int running;
	
	public void run() {
		this.notifyAll();
	/*try {
	while (running == 1) {
		if (!durations.isEmpty()) {
			duration = durations.get(0);
			durations.remove(0);
		}
		
		if (!notes.isEmpty()) {
			currentNote = notes.get(0);
			notes.remove(0);
		}

		if (duration == (1.0)) {
			this.sleep((int)(duration * 2400));
		} else if (duration == (2.0/4.0)) {
			this.sleep((int)(duration * 2400));
		} else if (duration == (1.0/4.0)) {
			this.sleep((int)(duration * 2400));
		} else if (duration == (1.0/8.0)) {
			this.sleep((int)(duration * 2400));
		} else if (duration == (1.0/16.0)) {
			this.sleep((int)(duration * 2400));
		} else if (duration == (1.0/32.0)) {
			this.sleep((int)(duration * 2400));
		}
		System.out.println(currentNote.getOriginalString());
		totalDuration = totalDuration - duration;
		String noteString = " " + currentNote.getOriginalString() + " ";
		//textArea.append(noteString);
		if (totalDuration == 0) {
			running = 0;
		}
	} this.interrupt();
	} catch(InterruptedException e) {
		e.printStackTrace();
		}*/
	}
	
	
	public synchronized Note getCurrentNote() {
		return currentNote;
	}
	
	public synchronized double getDuration() {
		return duration;
	}
	
	public synchronized double getTotalDuration() {
		return totalDuration;
	}
	
	public synchronized void setNote(List<Note> a) {
		notes = a;
	}
	
	public synchronized List<Note> getNote() {
		return notes;
	}
	
	public synchronized void setDurations(List<Double> dur) {
		durations = dur;
	}
	
	public synchronized void setTotalDuration(double x) {
		totalDuration = x;
	}
	
	public void setTextArea(JTextArea a) {
		textArea = a;
	}
	
	public void setRunning(int i) {
		running = i;
	}
	
	public boolean isRunning( ) {
		if (running == 1) {
			return true;
		} else {
			return false;
		}
	}
}
