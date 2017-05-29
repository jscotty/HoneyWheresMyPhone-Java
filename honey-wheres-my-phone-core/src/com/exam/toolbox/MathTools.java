package com.exam.toolbox;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MathTools {

	/**
	 * dot product calculations of multiple doubles
	 * @param a double array
	 * @param b double array
	 * @return dot product of two double arrays
	 */
	public static double dotProduct(double[] a, double[] b) {
		double sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i] * b[i];
		}

		return sum;
	}

	/**
	 * Dot product calculation of 2 vectors
	 * @param a
	 * @param b
	 * @return Dot product of 2 vectors
	 */
	public static double dotProduct(Vector2 a, Vector2 b) {
		double sum = 0;
		sum += a.x * b.x;
		sum += a.y * b.y;
		return sum;
	}

	/**
	 * magnitude calculation of a Vector2
	 * @param vector2 to calculate magnitude of
	 * @return square root of (v.x * v.x) + (v.y * v.y)
	 */
	public static double magnitude(Vector2 v) {
		return Math.sqrt((v.x * v.x) + (v.y * v.y));
	}
}
