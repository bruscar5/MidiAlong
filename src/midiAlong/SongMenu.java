package midiAlong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class SongMenu extends JFrame implements ActionListener {
	private List<String> fileNames = new ArrayList<>();
	private JComboBox fileList;
	private JFrame frame;
	private JLabel lblText;
	private JButton choose;
	private Selected selected;
	private Thread songThread;
	int numFN;
	
	public SongMenu(Selected sel, Thread thread) {
		//set frame for window listener so window disposes on song selection
		//set private instance variables to reference those of other thread (selected is synchronized)
		this.songThread = thread;
		this.selected = sel;
		this.numFN = this.getFileNames();
		//instantiate JFrame and generate dropdown list of song files
		frame = new JFrame("Choose a song");
		fileList = new JComboBox(fileNames.toArray());
		//add selection button and actionlistener
		choose = new JButton("Select");
		choose.addActionListener(this);
		frame.add(choose);
		//centerFrame(fileList);
		frame.setLayout(new FlowLayout());
		frame.setSize(400, 300);
		frame.setVisible(true);
		fileList.setVisible(true);
		frame.setTitle("Choose a Song");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		for (int i = 0; i < numFN; i++) {
			lblText = new JLabel(fileNames.get(i));
			fileList.setSelectedIndex(i);
			fileList.addActionListener(this);
			frame.add(fileList);	
	}
	
	}
	
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == fileList) {
				JComboBox fl = (JComboBox)e.getSource();
				String file = (String)fl.getSelectedItem();
				for (int i = 0; i < numFN; i++) {
					if (fileNames.get(i).equals(file)) {
						selected.setSelected(file);
						return;
					}
			}
			this.selected.setSelected("error");
		} else if (e.getSource() == choose) {
			songThread.interrupt();
			frame.dispose();
		}
	}
	
	
	public int getFileNames() {
		File dir = new File("./Songs/");
		File [] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".mid");
			}
		});
		
		int x = 0;
		for (File path: files) {
			fileNames.add(path.getName());
			x++;
		}
		return x;
	}
}
