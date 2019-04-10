package midiAlong;

public class UtilityMethods {

		public static double averageArray(double [] x) {
			double sum = 0;
			for (int i = 0; i < x.length; i++) {
				sum = sum + x[i];
			}
			return sum/x.length;
		}
}
