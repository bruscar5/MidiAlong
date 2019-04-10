package midiAlong;

public class Cursor {
	double x;
	
	public Cursor() {
		x = 0;
	}
	
	public void moveCursor() {
		x+= 2;
	}
	
	public void moveCursorTo(int x1) {
		x = x1;
	}
	
	public synchronized double getCursor() {
		return x;
	}
}