package midiAlong;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;

import javax.swing.JScrollBar;
import javax.swing.JViewport;

import org.jfugue.theory.Note;

public class CursorViewTracker extends Thread {
	private JScrollBar hbar;
	private JScrollBar vbar;
	private int x;
	private int prevX;
	private int y;
	private int prevY;
	
	public CursorViewTracker(JScrollBar h, JScrollBar v) {
		hbar = h;
		vbar = v;
		prevY = -1000;
		prevX = -1000;
	}
	
	public void run() {
		while (true) {
			if (x < prevX - 700 || x > prevX + 700) {
				hbar.setValue(x);
			}
			if (y < prevY - 1000 || y > prevY + 1000) {
				vbar.setValue(y);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public void updateJScrollBarHor(int x1) {
		x = x1;
	}
	
	public void updateJScrollBarVert(int y1) {
		y = y1;
	}
	
	public int getX() {
		return x;
	}


}
