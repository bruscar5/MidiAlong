package midiAlong;
import java.awt.Graphics;

import javax.swing.JComponent;
import java.awt.Color;

public class Corner extends JComponent {
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(164, 59, 59));
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 65, 65);
	}
}
