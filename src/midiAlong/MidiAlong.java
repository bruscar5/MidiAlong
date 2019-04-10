package midiAlong;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MidiAlong {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		MusicContainer a = new MusicContainer();
		a.setVisible(true);
		/*
		PitchDetectorExample example = new PitchDetectorExample();

			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						//ignore failure to set default look en feel;
					}
					JFrame frame = new PitchDetectorExample();
					frame.pack();
					frame.setVisible(true);
				}
			}); */
	} 

}
