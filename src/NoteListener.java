package midiAlong;
import org.jfugue.parser.ParserListenerAdapter;
import org.jfugue.theory.Note;
import org.jfugue.theory.TimeSignature;
import java.util.ArrayList;
import java.util.List;

public class NoteListener extends ParserListenerAdapter {
	private List<Note> notes;
	private List<Double> durations;
	private double totalDuration;
	
	public NoteListener() {
		super();
		notes = new ArrayList<Note>();
		durations = new ArrayList<Double>();
		totalDuration = 0;
	}
	
	@Override
	public void onNoteParsed(Note note) {
		notes.add(note);
		durations.add(note.getDuration());
		totalDuration = totalDuration + note.getDuration();
	}
	
	public List<Note> getNotes() {
		return this.notes;
	}
	
	public double getTotalDuration() {
		return totalDuration;
	}
	
	public List<Double> getDurations() {
		return durations;
	}
}
