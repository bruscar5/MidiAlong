package midiAlong;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfugue.theory.Note;

public class Tracker extends Thread {
	Object lock = new Object();
	private Note note;
	private List<Double> durations;
	private List<Note> notes = new ArrayList<Note>();
	private List<Note> notesCopy;
	private Note currentNote;
	private Note currentNotePlayed;
	private Note currentNoteSung;
	private JTextArea textArea;
	private double totalDuration;
	private double duration;
	private int running;
	
	public Tracker() {
		currentNote = new Note(1);
		currentNotePlayed = new Note(1);
	}
	
	public void run() {
	try {
	while (running == 1) {
		if (!durations.isEmpty()) {
			duration = durations.get(0);
			durations.remove(0);
		}
		
		if (!notes.isEmpty()) {
			currentNotePlayed = notes.get(0);
			notes.remove(0);
		}

		if (duration == (1.0)) {
			Thread.sleep((int)(duration * 2400));
		} else if (duration == (2.0/4.0)) {
			Thread.sleep((int)(duration * 2400));
		} else if (duration == (1.0/4.0)) {
			Thread.sleep((int)(duration * 2400));
		} else if (duration == (1.0/8.0)) {
			Thread.sleep((int)(duration * 2400));
		} else if (duration == (1.0/16.0)) {
			Thread.sleep((int)(duration * 2400));
		} else if (duration == (1.0/32.0)) {
			Thread.sleep((int)(duration * 2400));
		}
		
		System.out.println("current note on tracker: " + currentNotePlayed.toString());
		
	} this.interrupt();
	} catch(InterruptedException e) {
		e.printStackTrace();
		}
	} 
	
	
	public synchronized Note getCurrentNotePlayed() throws InterruptedException {
		if (currentNotePlayed == null) {
			wait();
		}
		return currentNotePlayed;
	}
	
	public synchronized void setCurrentNotePlayed(Note a) {
		currentNotePlayed = a;
		notifyAll();
		return; 
	}
	
	public synchronized Note getCurrentNote() throws InterruptedException {
		if (currentNote == null)
			wait();
		return currentNote;
	}
	
	public synchronized void setCurrentNote(Note a) {
		currentNote = a;
		System.out.println("Current note set");
		notifyAll();
		return;
	}
	
	public synchronized double getTDuration() throws InterruptedException {
		if (totalDuration == 0) {
			System.out.println("waiting...");
			wait();
		}
		return totalDuration;
	}
	
	public synchronized void setTDuration(double duration) {
		totalDuration = duration;
		notifyAll();
		return;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public double getTotalDuration() {
		return totalDuration;
	}
	
	public synchronized void setNote(List<Note> a) throws InterruptedException {

		notes = a;
		System.out.println(a.get(0).toString());
		System.out.println("Song set. Notifying other threads");
		notifyAll();
		return;
	
	}
	
	public synchronized List<Note> getNote() throws InterruptedException {
			System.out.println("Waiting for song.");
			System.out.println(this.getThreadGroup().toString());
			wait();
			System.out.println("Returned");
			return notes;

	}
	
	public void setDurations(List<Double> dur) {
		durations = dur;
	}
	
	public void setTotalDuration(double x) {
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
