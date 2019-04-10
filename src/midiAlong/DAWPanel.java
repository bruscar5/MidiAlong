package midiAlong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.jfugue.theory.Note;

@SuppressWarnings("serial")
public class DAWPanel extends JPanel implements Runnable {
	static final int NUM_MIDI = 88;
	int WIDTH_CONSTANT = 12000;
	static final int ROW_HEIGHT = 30;
	static final int MEASURE_WIDTH = 200;
	
	boolean feedback;	
	
	Tracker tracker;
	
	CursorViewTracker cvt;
	
	double noteStart;
	double measureSum;
	double noteLength;
	double noteY;

	int score;
	int currentX;
	int currentWidth;
	int center;
	
	NoteArea [] notePos;
	
	JViewport jvp;
	
	Cursor cursor;
	Song song;
	Note [] notes;
	Note currentNote;
	int currentNoteStart;
	int currentMeasure;
	int startFlag;
	double accuracy;
	double totalDuration;
	
	Color [] rowColor; 
	
	//initialize array of x and y positions for midi cells on which notes will be painted
	public DAWPanel(Tracker b) {
		setPreferredSize(new Dimension(WIDTH_CONSTANT , ROW_HEIGHT * 88));
		setVisible(true);
		
		currentNote = new Note(60);
		
		startFlag = 1;
		
		
		tracker = b;
		noteY = 0;
		noteLength = 0;
	}
	
	public void run() {
		try {
			song = new Song();
			song.newSong(tracker.getNote());
			totalDuration = tracker.getTDuration();
			} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 

		
		while (true) {
			try {
				repaint();
				revalidate();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
	
	//paints the background and notes of current song from midi file
	@Override
	public void paint(Graphics g) {
		if (feedback) {
			paintWithFeedback(g);
		} else {
			paintWithoutFeedback(g);
		}
	}
	
	public void paintWithFeedback(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setBackground(Color.WHITE);
		Shape rr = new Rectangle2D.Double(0, 0, WIDTH_CONSTANT, ROW_HEIGHT);
		g2d.setColor(Color.BLACK);
		g2d.fill(rr);
		notes = song.getSong();
		if (startFlag == 1) {
			notePos = new NoteArea[notes.length];
		}
		
		try {
		//System.exit(0);
			for (int i = 0; i < NUM_MIDI; i++) {
				//g2d.fill3DRect(xPos[i], yPos[i], (int)(WIDTH_CONSTANT), ROW_HEIGHT, false);
				
				Shape rect = new Rectangle2D.Double(0, i * ROW_HEIGHT, WIDTH_CONSTANT, ROW_HEIGHT);
				Shape line = new Rectangle2D.Double(0, i * ROW_HEIGHT + 5, WIDTH_CONSTANT, 5);
				g2d.setColor(rowColor[i]);
				g2d.fill(rect);
				g2d.setColor(Color.BLACK);
				g2d.fill(line);
				g2d.setColor(Color.WHITE);
				

			}
			

				if (!currentNote.toString().equals(tracker.getCurrentNote())) {
					currentNoteStart = (int)cursor.getCursor();
					currentNote.setValue(tracker.getCurrentNote().getValue());
					//System.out.println(currentNote.toString());
				} 
				g2d.setColor(Color.RED);
				Shape current = new Line2D.Double(0, ROW_HEIGHT * 88 - (((currentNote.getValue()) - 11) * ROW_HEIGHT + (ROW_HEIGHT * 2)) - 10, WIDTH_CONSTANT, ROW_HEIGHT * 88 - (((currentNote.getValue()) - 11) * ROW_HEIGHT + (ROW_HEIGHT * 2)) - 10);
				g2d.draw(current);
				
				 
			
		for (int i = 0; i < notes.length; i++) {
				//System.out.println(notes[i].getValue());
				noteLength = notes[i].getDuration() * MEASURE_WIDTH;
				
				Shape noteOn = new Rectangle2D.Double(i * MEASURE_WIDTH + 20, ROW_HEIGHT * 88 - (((notes[i].getValue()) - 21) * ROW_HEIGHT + 20), noteLength, ROW_HEIGHT / 2);
				g2d.drawString(UtilityMethods.setPitch(notes[i].getValue()), i * MEASURE_WIDTH + 20, ROW_HEIGHT * 88 - (((notes[i].getValue()) - 21) * ROW_HEIGHT + 20));
				//System.out.println("Tracker note: " + tracker.getCurrentNote());
				//System.out.println("DAW note: " + notes[i].toString());
				
				if (current.intersects((Rectangle2D) noteOn)) {
					g2d.setColor(Color.GREEN);
					score++;
				} else if (tracker.getCurrentNotePlayed().toString().equals(notes[i].toString())) {
					
					currentX = current.getBounds().x;
					currentWidth = current.getBounds().width;
					center = (currentX + currentWidth) / 2;
					g2d.setColor(Color.BLACK);
					g2d.setColor(Color.WHITE);
					cvt.updateJScrollBarVert(noteOn.getBounds().y - 400);
					cvt.updateJScrollBarHor(noteOn.getBounds().x - 400);
				} else {
					if (notes[i].getVelocityString().length() > 2) {
						g2d.setColor(UtilityMethods.velocityToColor(notes[i].getVelocityString()));
					}
				}
				
				System.out.println("Current note for daw: " + tracker.getCurrentNotePlayed());
				
				if (startFlag == 1) {
					if (notePos[i] != null) {
						notePos[i].setX((int)noteOn.getBounds().x);
						notePos[i].setEnd((int)(noteOn.getBounds().getWidth() + noteOn.getBounds().x));
						notePos[i].setY((int)noteOn.getBounds().y);
					}
				}
				
				g2d.fill(noteOn);
				//System.out.println(notes[i].getValue());
		} 
		
		if (startFlag == 1) {
			startFlag = 0;
		}
		//System.exit(0); 
		Shape ln = new Line2D.Double(cursor.getCursor(), 0, cursor.getCursor(), 88 * ROW_HEIGHT);
		g2d.draw(ln);
		

		cursor.moveCursorTo((int)current.getBounds().getMinX());
		currentMeasure = 0;
		noteStart = 0;
		
	} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void paintWithoutFeedback(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setBackground(Color.WHITE);
		Shape rr = new Rectangle2D.Double(0, 0, WIDTH_CONSTANT, ROW_HEIGHT);
		g2d.setColor(Color.black);
		g2d.fill(rr);
		
		notes = song.getSong();
		if (startFlag == 1) {
			notePos = new NoteArea[notes.length];
		}
		try {
			for (int i = 0; i < NUM_MIDI; i++) {
				g2d.setColor(Color.WHITE);
				//g2d.fill3DRect(xPos[i], yPos[i], (int)(WIDTH_CONSTANT), ROW_HEIGHT, false);
				
				Shape rect = new Rectangle2D.Double(0, i * ROW_HEIGHT, WIDTH_CONSTANT, ROW_HEIGHT);
				Shape line = new Rectangle2D.Double(0, i * ROW_HEIGHT + 5, WIDTH_CONSTANT, 5);

				g2d.fill(rect);
				g2d.setColor(Color.BLACK);
				g2d.fill(line);
				g2d.setColor(Color.BLACK);
				//System.out.println(UtilityMethods.setPitch(i + 21));
				g2d.drawString(UtilityMethods.setPitch((NUM_MIDI + 20) - i), 0, i * ROW_HEIGHT + 20);
				//g2d.drawString(Integer.toString((NUM_MIDI + 20) - i), 0, i * ROW_HEIGHT + 20);
			}
			
			if (!currentNote.toString().equals(tracker.getCurrentNote())) {
				currentNoteStart = (int)cursor.getCursor();
				currentNote.setValue(tracker.getCurrentNote().getValue());
				//System.out.println(currentNote.toString());
			} 
			g2d.setColor(Color.RED);
			Shape current = new Line2D.Double(0, ROW_HEIGHT * 88 - (((currentNote.getValue()) - 11) * ROW_HEIGHT + (ROW_HEIGHT * 2)) - 10, WIDTH_CONSTANT, ROW_HEIGHT * 88 - (((currentNote.getValue()) - 11) * ROW_HEIGHT + (ROW_HEIGHT * 2)) - 10);
			g2d.draw(current);
			
			
		for (int i = 0; i < notes.length; i++) {
				//System.out.println(notes[i].getValue());
				g2d.setColor(Color.GREEN);
				noteLength = notes[i].getDuration() * MEASURE_WIDTH;
				
				Shape noteOn = new Rectangle2D.Double(i * MEASURE_WIDTH + 20, ROW_HEIGHT * 88 - (((notes[i].getValue()) - 21) * ROW_HEIGHT + 20), noteLength, ROW_HEIGHT / 2);
				g2d.drawString(UtilityMethods.setPitch(notes[i].getValue()), i * MEASURE_WIDTH + 20, ROW_HEIGHT * 88 - (((notes[i].getValue()) - 21) * ROW_HEIGHT + 20));
				
				if (current.intersects((Rectangle2D) noteOn)) {
					score++;
				}
				
				if (tracker.getCurrentNotePlayed().toString().equals(notes[i].toString())) {
					g2d.setColor(Color.BLUE);
					cvt.updateJScrollBarVert(noteOn.getBounds().y - 600);
					cvt.updateJScrollBarHor(noteOn.getBounds().x - 600);
				} 
				
				if (startFlag == 1) {
					if (notePos[i] != null) {
						notePos[i].setX((int)noteOn.getBounds().x);
						notePos[i].setEnd((int)(noteOn.getBounds().getWidth() + noteOn.getBounds().x));
						notePos[i].setY((int)noteOn.getBounds().y);
					}
				}
				
				g2d.fill(noteOn);
				//System.out.println(notes[i].getValue());
		} 
		
		if (startFlag == 1) {
			startFlag = 0;
		}
		//System.exit(0); 
		Shape ln = new Line2D.Double(cursor.getCursor(), 0, cursor.getCursor(), 88 * ROW_HEIGHT);
		g2d.draw(ln);
		try {
		if (!currentNote.toString().equals(tracker.getCurrentNote())) {
			currentNoteStart = (int)cursor.getCursor();
			currentNote.setValue(tracker.getCurrentNote().getValue());
			//System.out.println(currentNote.toString());
		} 
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cursor.moveCursor();
		currentMeasure = 0;
		noteStart = 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setColor(int mode) {
		System.out.println("colors set");
		rowColor = new Color[NUM_MIDI];
		int x = 0;
		Color color = Color.WHITE;
		if (mode == 1) {
			for (int i = 0; i < NUM_MIDI; i++) {
				if (x == 10) {
					color = new Color(230, 230, 200);
					
				} else if (x == 9) {
					color = new Color(0, 0, 0);
				} else if (x == 8) {
					color = new Color(49, 79, 79);
				} else if (x == 7) {
					color = new Color(88, 95, 95);
				} else if (x == 6) {
					color = new Color(105, 105, 105);
				} else if (x == 5) {
					color = new Color(112, 138, 144);
				} else if (x == 4) {
					color = new Color(114, 137, 149);
				} else if (x == 3) {
					color = new Color(119, 136, 152);
				} else if (x == 2) {
					color = new Color(150, 150, 155);
				} else if (x == 1) {
					color = new Color(190, 190, 190);
				} else if (x == 0) {
					color = new Color(211, 211, 211);
				} 
				if (i % 10 == 0)
					x++;
				rowColor[i] = color;
			}
		} else {
			color = new Color(231,231,228);
		}
		
	}
	
	public void setSong(List<Note> a) {
		song.newSong(a);
		System.out.println(a.get(0).toString());
		System.out.println("song set");
	}
	
	public void setNewCursor(Cursor c) {
		cursor = c;
	}
	
	public void setNewCursorViewTracker(CursorViewTracker c) {
		cvt = c;
	}

	public void setFeedback(boolean b) {
		feedback = b;
		if (b == true) {
			setColor(1);
		}
	}
	
	public int getScore() {
		return score;
	}
	

}