package midiAlong;

import java.awt.geom.Point2D;

public class NoteCoordinate extends Point2D {
	double y;
	double x;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setLocation(double x1, double y1) {
		x = x1;
		y = y1;
	}
}
