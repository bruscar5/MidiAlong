package midiAlong;
import javax.sound.midi.*;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.jfugue.player.Player;
import org.jfugue.theory.Note;
import org.staccato.StaccatoParser;
import org.jfugue.pattern.Pattern;
import org.jfugue.parser.NoteEventListener;

import org.jfugue.pattern.PatternProducer;

public class MidiPlayer extends Thread  {
	private Sequencer sequencer;
	private List<Note> notes;
	private List<Double> durations;
	private int [] listeners;
	private String midiString;
	private Player player;
	private JTextArea textArea;
	private int controller [] = new int[128];
	private Pattern pattern;
	private Tracker tracker;
	
	public MidiPlayer() {
	}
	public void run () {
		try {
			notes = new ArrayList<Note>();
			durations = new ArrayList<Double>();
			for (int i = 0; i < controller.length; i++) {
				controller[i] = i;
			}
			sequencer = MidiSystem.getSequencer();
			if (sequencer == null) {
				System.err.println("Sequencer device not supported");
				return;
			}
			InputStream is = new BufferedInputStream(new FileInputStream(new File(midiString)));
			
			
			//sequencer.open();
			MidiPatternProducer prod = new MidiPatternProducer();
			prod.setPattern(new File(midiString));
			
			StaccatoParser staccatoParser = new StaccatoParser();
			NoteListener a = new NoteListener();
			staccatoParser.addParserListener(a);
			staccatoParser.parse(prod);
			
			tracker.setTotalDuration(a.getTotalDuration());
			tracker.setDurations(a.getDurations());
			tracker.setRunning(1);
			tracker.setNote(a.getNotes());
			tracker.setTDuration(a.getTotalDuration());
			
			tracker.start();
			
			
			player = new Player();
			player.play(prod);
			

			//sequencer.setSequence(is);
			//sequencer.start();
		}
		catch (MidiUnavailableException | IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void stopSequence() {
		player.getManagedPlayer().finish();
	}
	
	public void setMidiString(String x) {
		midiString = x;
	}
	
	
	public String getMidiString(String x) {
		return midiString;
	}
	
	public void setTextArea(JTextArea a) {
		textArea = a;
	}
	
	public void onNoteStarted(Note a) {
		notes.add(a);
		System.out.println(a.toString());
		durations.add(a.getDuration());
	}
	
	public Tracker getTracker() {
		return tracker;
	}
	
	public void setTracker(Tracker b) {
		tracker = b;
	}
}
	
