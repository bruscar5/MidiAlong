package midiAlong;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.pattern.Pattern;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;

public class MidiPatternProducer implements PatternProducer {
	private Pattern pattern;
	
	public void setPattern(File a) {
		try {
		pattern = MidiFileManager.loadPatternFromMidi(a);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Pattern getPattern() {
		return pattern;
	}

}
