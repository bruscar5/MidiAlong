package midiAlong;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MusicContainer extends JFrame implements ActionListener {
private JButton select;
private JButton play;
private JButton pause;
private Selected selected;
private MidiPlayer player;
private JPanel contentPane;
private JPanel controlPanel;
private JScrollBar scrollBarVert;
private JScrollBar scrollBarHor;
private WorkSpace musicPane;
private JTextArea textArea;
private JTextArea textArea2;
private Tracker tracker;
private DAWPanel daw;


	public MusicContainer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		controlPanel = new JPanel();
		
		select = new JButton("Select Song");
		select.addActionListener(this);
		controlPanel.add(select);
		
		play = new JButton("Play");
		play.addActionListener(this);
		controlPanel.add(play);
		
		pause = new JButton("Pause");
		pause.addActionListener(this);
		controlPanel.add(pause);
		controlPanel.setVisible(true);
		
		JPanel cent = new JPanel();
		
		//textArea = new JTextArea();
		//this.add(textArea, BorderLayout.CENTER);
		
		
		/*PitchDetectorExample p = new PitchDetectorExample();
		this.add(p, BorderLayout.NORTH);
		p.Pitch();*/
		
		daw = new DAWPanel(tracker);
		Thread dawThread = new Thread(daw);
		dawThread.start();
		this.add(daw, BorderLayout.CENTER);
		
		
		this.add(controlPanel, BorderLayout.SOUTH);
		contentPane.setVisible(true);
		getContentPane().setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == select) {
			SwingUtilities.invokeLater(new Runnable()
					{
						public void run() {
							selected = new Selected();
							SongMenuThread menu = new SongMenuThread(selected);
							menu.start();
							try {
							menu.join();
							System.out.println("menu ended:");
							} catch (InterruptedException e) {
								System.out.println("Error with menu thread");
							}
							System.out.println(selected.getSelected());
							
						}
					} );
		} else if (e.getSource() == play) {
			System.out.println("Play pressed");
			if (selected == null || selected.getSelected().equals("error")) {
				System.out.println("No song selected");
				JOptionPane.showMessageDialog(null, "Please select a song first");
			} else {
				player = new MidiPlayer();
				player.setMidiString(selected.getSelected());
				player.setTextArea(textArea);
				tracker = new Tracker();
				player.setTracker(tracker);
				player.start();
				
			}
		} else if (e.getSource() == pause) {
			if (player != null) {
				player.stopSequence();
			}
		}
	}
}
	
