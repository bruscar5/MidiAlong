package midiAlong;

import java.awt.Color;
import java.util.Scanner;

import org.jfugue.theory.Note;

public class UtilityMethods {

		public static double averageArray(double [] x) {
			double sum = 0;
			for (int i = 0; i < x.length; i++) {
				sum = sum + x[i];
			}
			return sum/x.length;
		}
		
		 public static String setPitch(int midi) {
		        try {
		             if (midi < 0 || midi > 108) {
		            	 System.out.println(midi);
		                throw new IllegalArgumentException();
		            }
		            else if (midi == 21) {
		                return "A0";
		            } else if (midi == 22) {
		            	return "A#0";
		            } else if (midi == 23) {
		                return "B0";
		            } else if (midi == 24) {
		                return "C1";
		            } else if (midi == 25) {
		            	return "C#1";
		            } else if (midi == 26) {
		                return "D1";
		            } else if (midi == 27) {
		            	return "D#1";
		            } else if (midi == 28) {
		                return "E1";
		            } else if (midi == 29) {
		                return "F1";
		            } else if (midi == 30) {
		            	return "F#1";
		            } else if (midi == 31) {
		                return "G1";
		            } else if (midi == 32) {
		            	return "G#1";
		            } else if (midi == 33) {
		                return "A1";
		            } else if (midi == 34) {
		            	return "A#1";
		            } else if (midi == 35) {
		                return "B1";
		            } else if (midi == 36) {
		                return "C2";
		            } else if (midi == 37) {
		            	return "C#2";
		            } else if (midi == 38) {
		                return "D2";
		            } else if (midi == 39) {
		            	return "D#2";
		            } else if (midi == 40) {
		                return "E2";
		            } else if (midi == 41) {
		                return "F2";
		            } else if (midi == 42) {
		            	return "F#2";
		            } else if (midi == 43) {
		                return "G2";
		            } else if (midi == 44) {
		            	return "G#2";
		            } else if (midi == 45) {
		                return "A2";
		            } else if (midi == 46) {
		            	return "A#2";
		            } else if (midi == 47) {
		                return "B2";
		            } else if (midi == 48) {
		                return "C3";
		            } else if (midi == 49) {
		            	return "C#3";
		            } else if (midi == 50) {
		                return "D3";
		            } else if (midi == 51) {
		            	return "D#3";
		            } else if (midi == 52) {
		                return "E3";
		            } else if (midi == 53) {
		                return "F3";
		            } else if (midi == 54) {
		            	return "F#3";
		            } else if (midi == 55) {
		                return "G3";
		            } else if (midi == 56) {
		            	return "G#3";
		            } else if (midi == 57) {
		                return "A3";
		            } else if (midi == 58) {
		            	return "A#3";
		            } else if (midi == 59) {
		                return "B3";
		            } else if (midi == 60) {
		                return "C4";
		            } else if (midi == 61) {
		            	return "C#4";
		            } else if (midi == 62) {
		                return "D4";
		            } else if (midi == 63) {
		            	return "D#4";
		            } else if (midi == 64) {
		                return "E4";
		            } else if (midi == 65) {
		                return "F4";
		            } else if (midi == 66) {
		            	return "F#4";
		            } else if (midi == 67) {
		                return "G4";
		            } else if (midi == 68) {
		            	return "G#4";
		            } else if (midi == 69) {
		                return "A4";
		            } else if (midi == 70) {
		            	return "A#4";
		            } else if (midi == 71) {
		                return "B4";
		            } else if (midi == 72) {
		                return "C5";
		            } else if (midi == 73) {
		            	return "C#5";
		            } else if (midi == 74) {
		                return "D5";
		            } else if (midi == 75) {
		            	return "D#5";
		            } else if (midi == 76) {
		                return "E5";
		            } else if (midi == 77) {
		                return "F5";
		            } else if (midi == 78) {
		            	return "F#5";
		            } else if (midi == 79) {
		                return "G5";
		            } else if (midi == 80) {
		            	return "G#5";
		            } else if (midi == 81) {
		                return "A5";
		            } else if (midi == 82) {
		            	return "A#5";
		            } else if (midi == 83) {
		                return "B5";
		            } else if (midi == 84) {
		                return "C6";
		            } else if (midi == 85) {
		            	return "C#6";
		            } else if (midi == 86) {
		                return "D6";
		            } else if (midi == 87) {
		            	return "D#6";
		            } else if (midi == 88) {
		                return "E6";
		            } else if (midi == 89) {
		                return "F6";
		            } else if (midi == 90) {
		            	return "F#6";
		            } else if (midi == 91) {
		                return "G6";
		            } else if (midi == 92) {
		            	return "G#6";
		            } else if (midi == 93) {
		                return "A6";
		            } else if (midi == 94) {
		            	return "A#6";
		            } else if (midi == 95) {
		                return "B6";
		            } else if (midi == 96) {
		                return "C7";
		            } else if (midi == 97) {
		            	return "C#7";
		            } else if (midi == 98) {
		                return "D7";
		            } else if (midi == 99) {
		            	return "D#7";
		            } else if (midi == 100) {
		                return "E7";
		            } else if (midi == 101) {
		                return "F7";
		            } else if (midi == 102) {
		            	return "F#7";
		            } else if (midi == 103) {
		                return "G7";
		            } else if (midi == 104) {
		            	return "G#7";
		            } else if (midi == 105) {
		                return "A7";
		            } else if (midi == 106) {
		            	return "A#7";
		            } else if (midi == 107) {
		                return "B7";
		            } else if (midi == 108) {   
		                return "C8";
		            } 
		             return "";
		       } catch (IllegalArgumentException e) {
		          // Scanner kb = new Scanner(System.in);
		           System.exit(1);
		           }
		        return "C0";
		          // midi = kb.nextInt();}
				//return null;
		    }
		 
 public static boolean isSharp(Note a) {
	 String note = a.toString();
	 if (note.contains("#")) {
		 return true;
	}
	 return false;
 }
 
 public static Color velocityToColor(String a) {
	 int vel;
	 if (a.length() == 5) {
		 vel = Integer.parseInt(a.substring(1, 3));
	 } else {
		 vel = Integer.parseInt((a.substring(1, 2)));
	 }
	 Color color = Color.GREEN;

	 if (vel < 40) {
		 color = new Color(85, 107, 47);
	 } else if (vel < 60) {
		 color = new Color(3, 192, 60);
	 } else if (vel < 80) {
		 color = new Color(147, 197, 114);
	 } else if (vel < 100) {
		 color = new Color(102, 255, 0);
	 } else if (vel < 120) {
		 color = new Color(178, 236, 93);
	 }
	 return color;
 }
}
