package midiAlong;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
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
	private Cursor cursor; 
	private JTextArea textArea;
	private JRadioButton rb;
	private Tracker tracker;
	private DAWPanel daw;
	private boolean feedback;


	public MusicContainer() {
		feedback = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1200, 800);
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
		
		rb = new JRadioButton("Feeback");
		rb.addActionListener(this);
		this.add(rb, BorderLayout.NORTH);
		
		tracker = new Tracker();
		PitchDetectorExample p = new PitchDetectorExample();
		p.setTracker(tracker);
		p.setPreferredSize(new Dimension(200, 400));
		this.add(p, BorderLayout.EAST);
		
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
				//tracker = new Tracker();
				player.setTracker(tracker);
				player.start();
				
				cursor = new Cursor();
				
				daw = new DAWPanel(tracker);
				daw.setNewCursor(cursor);
				daw.setFeedback(feedback);
				JScrollPane jsp = new JScrollPane(daw,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				jsp.setVisible(true);
				
				CursorViewTracker view = new CursorViewTracker(jsp.getHorizontalScrollBar(), jsp.getVerticalScrollBar());
				daw.setNewCursorViewTracker(view);
				Thread dawThread = new Thread(daw);
				dawThread.start();
				view.start();
				add(jsp, BorderLayout.CENTER);
				setVisible(true);
				view.start();
				
				
				
				
			}
		} else if (e.getSource() == pause) {
			if (player != null) {
				player.stopSequence();
			}
		} else if (e.getSource() == rb) {
			if (rb.isSelected()) {
				feedback = true;
				System.out.println(feedback);
			} else {
				feedback = false;
			}
		}
	}
}
	
