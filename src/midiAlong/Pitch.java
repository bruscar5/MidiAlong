package midiAlong;

import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine.Info;
import java.util.*;

import com.darkprograms.speech.microphone.MicrophoneAnalyzer;

public class Pitch implements Runnable{
	
	public void run() {
		byte[] targetData;
		try {
			AudioFormat format = new AudioFormat(44100, 1024, 2, true, true);

			DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
			DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);

			try {
				TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
				targetLine.open(format);
				targetLine.start();
				
				SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
				sourceLine.open(format);
				sourceLine.start();
				
				MicrophoneAnalyzer ma = new MicrophoneAnalyzer(AudioFileFormat.Type.WAVE);

				int numBytesRead;
				if (targetLine.getBufferSize() % 2 != 0) {
					targetData = new byte[128];	
				} else {
					targetData = new byte[128];
				}

				while (true) {
					numBytesRead = targetLine.read(targetData, 0, targetData.length);

					if (numBytesRead == -1)	break;
					System.out.println(ma.getFrequency(numBytesRead));

					sourceLine.write(targetData, 0, numBytesRead);
				}
			}
			catch (Exception e) {
				System.err.println(e);
			}
	} catch (Exception e) {System.exit(1);}
	}
	
	public static void main (String [] args) {
		Pitch pitch = new Pitch();
		Thread thread = new Thread(pitch);
		thread.start();
	}
}
