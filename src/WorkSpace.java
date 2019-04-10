package midiAlong;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Scrollbar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import java.awt.event.ItemListener;

import MusicNote.MusicNote;

public class WorkSpace extends JPanel {
private JPanel canvasFrame;
private JScrollPane scrollPane;
private int height;
private int width;
private boolean init;
private int rowHeight;
private int noteRange;
private int TRACK_HEIGHT = 2200;


	
	public WorkSpace(int canvasHeight, int canvasWidth) {
		init = false;
		height = canvasHeight;
		width = canvasWidth;
		//set size and background of canvas
		this.setPreferredSize(new Dimension(rowHeight * 88, width * 3));
		this.setVisible(true);
		//create JPanel to add canvas to
		//scrollPane = new JScrollPane(canvasFrame);
		//scrollPane.setPreferredSize(new Dimension(500, 500));
		
	}
	
	public void paintComponent(Graphics g) {
		if (!init) {
			initPaint(g);
		} else {
			
		}
	}
	
	public void repaint(Graphics g) {
		initPaint(g);
	}
	
	public void initPaint(Graphics g) {
		int midiStart = 21;
		MusicNote note = new MusicNote();
		note.setPitch(midiStart);
		rowHeight = 10;
		noteRange = height / rowHeight;
		for (int i = 0; i < 87; i++) {
			//fill rectangle for a single note
			g.setColor(Color.GRAY);
			g.fillRect(0, i * rowHeight, width, rowHeight);
			//fill a border rectangle between notes
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, i * rowHeight - 5, width, 5);
			//label track
			g.setColor(Color.WHITE);
			g.drawString(note.getPitchSymbol(), 0, i * rowHeight - 5);
			
			midiStart = midiStart + 1; //increment midi and set new pitch
			note.setPitch(midiStart);
		}
		//init = true;
	}
	
	public JPanel getCanvasFrame() {
		return this;
	}
	
	public void drawCanvas(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(10, 10, 200, 200);
	}
	
}
