package midiAlong;

import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfugue.theory.Note;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DAWPanel extends JPanel implements Runnable {
	static final int NUM_MIDI = 88;
	static final int WIDTH_CONSTANT = 120;
	static final int ROW_HEIGHT = 30;
	
	boolean feedback;	
	
	Tracker tracker;
	
	double noteWidth;
	double noteX;
	double noteY;
	
	int [] xPos;
	int [] yPos;
	
	Cursor cursor;
	Song song;
	Note currentNote;
	
	Color [] rowColor; 
	
	//initialize array of x and y positions for midi cells on which notes will be painted
	public DAWPanel(Tracker b) {
		setPreferredSize(new Dimension(500, 500));
		setVisible(true);
		
		tracker = b;
		
		song = new Song();
		
		noteY = 0;
		xPos = new int[NUM_MIDI];
		yPos = new int[NUM_MIDI];
		for (int i = 0; i < NUM_MIDI; i++) {
			xPos[i] = i * WIDTH_CONSTANT;
			yPos[i] = i * ROW_HEIGHT;
		}
	}
	
	public void run() {
		
		while (true) {
			try {
				this.repaint();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
	
	//paints the background and notes of current song from midi file
	@Override
	public void paint(Graphics g) {
		try {
			tracker.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	
		while (song != null && !song.isEmpty()) {
			for (int i = 0; i < NUM_MIDI; i++) {
				//g2d.fill3DRect(xPos[i], yPos[i], (int)(WIDTH_CONSTANT), ROW_HEIGHT, false);
				Shape rect = new Rectangle2D.Double(0, i * ROW_HEIGHT, WIDTH_CONSTANT, ROW_HEIGHT);
				g2d.fill(rect);
			}
			
			Measure measure = song.getNextMeasure();
			while (!measure.isEmpty()) {
				Note temp = measure.getNextNote();
				if (!temp.isRest()) {
					noteY = (int)temp.getValue() - 21;
				} else {
					noteY = 60;
				}
				
				noteWidth = (temp.getDuration() / 1.0) * WIDTH_CONSTANT;
				noteX = noteX + temp.getDuration();
				Shape rect = new Rectangle2D.Double(noteX, noteY, noteWidth, ROW_HEIGHT);
				g2d.fill(rect);
			}
		}
		
	}
	
	public void setColor(int mode) {
		rowColor = new Color[NUM_MIDI];
		int x = 0;
		Color color;
		if (mode == 1) {
			for (int i = 0; i < NUM_MIDI; i++) {
				if (x == 0) {
					color = new Color(230, 230, 230);
					
				} else if (x == 1) {
					color = new Color(231, 231, 228);
				} else if (x == 2) {
					color = new Color(233, 233, 226);
				} else if (x == 3) {
					color = new Color(236, 236, 223);
				} else if (x == 4) {
					color = new Color(238, 238, 221);
				} else if (x == 5) {
					color = new Color(241, 241, 218);
				} else if (x == 6) {
					color = new Color(244, 244, 215);
				} else if (x == 7) {
					color = new Color(246, 246, 213);
				} else if (x == 8) {
					color = new Color(249, 249, 210);
				} else if (x == 9) {
					color = new Color(251, 251, 208);
				} else if (x == 10) {
					color = new Color(252, 252, 207);
				} 
				if (i % 10 == 0)
					x++;
			}
		} else {
			color = new Color(231,231,228);
		}
	}
	
	public void setSong(List<Note> a) {
		song.newSong(a);
	}


}