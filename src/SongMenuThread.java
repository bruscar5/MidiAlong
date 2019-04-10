package midiAlong;

public class SongMenuThread extends Thread {
	Selected selected;
	
	
	public SongMenuThread(Selected sel) {
		this.selected = sel;
	}
	public void run () {
		songMenu menu = new songMenu(this.selected, this);
	}
}
