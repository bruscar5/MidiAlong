package midiAlong;

public class SongMenuThread extends Thread {
	Selected selected;
	
	
	public SongMenuThread(Selected sel) {
		this.selected = sel;
	}
	public void run () {
		SongMenu menu = new SongMenu(this.selected, this);
	}
}
