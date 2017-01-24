package fastBigMath;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a series of methods capable of performing advanced arithmetic operations on integers 
 * of virtually unlimited length. All methods take parameters of type {@code String}, 
 * which represent the operands. The type {@code String} is used, as it permits the representation 
 * of arbitrarily large integers, and can be easily manipulated.
 * 
 * @author Chaitanya Varier
 * @version 05/12/2016
 */

public class BigMathAdvanced {
	
	/**
	 * Computes the exponentiation of a positive integer base raised to a positive integer exponent. 
	 * The base may be arbitrarily large, however the exponent is restricted to a max value of 
	 * 2<sup>31</sup>-1.
	 * 
	 * @param base The base, represented by a {@code String}.
	 * @param pow The exponent, represented by an {@code int}.
	 * @return an integer array containing the digits of the calculated exponentiation in order.
	 */
	public static int[] exponentiate (String base, int pow) {
		
	   /* The power is represented by an int to avoid exceeding the max size for an array,
		* which is determined by the max value of an int.
		*/
		
		/* Calculate the number of digits in the exponentiation based on the formula:
		 * numDigits = floor(pow * Math.log10(base) + 1) = (pow * (number of digits in base) + 1).
		 * Extra digits are added for safety.
		 */
		
		int numDigits = pow*base.length() + 3;
		
		int[] exp = BigMathHelper.padZeroes(BigMathHelper.toArray(base), numDigits - base.length());
		
		for (int i=1; i<pow; i++) {
			
			exp = BigMathSimple.multiply(BigMathHelper.toString(exp), base);
			
		}
		
		return BigMathHelper.removePaddedZeroes(exp);
		
	}
	
	/**
	 * Computes the factorial of an arbitrarily large integer.
	 * 
	 * @param n The integer operand of the factorial, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated factorial in order.
	 */
	public static int[] factorialize (String n) {
		
		return null;
		
	}
	
	/**
	 * Computes the prime factorization of an arbitrarily large integer.
	 * 
	 * @param n The integer to be prime factorized, represented by a {@code String}.
	 * @return a two dimensional {@code ArrayList} of type {@code String} with 
	 * dimensions 2*(the number of distinct prime factors of <i>n</i>)
	 *  containing the prime factors of <i>n</i> and their corresponding quantities.
	 */
	public static List<ArrayList<String>> primeFactorize (String n) {
		
		return null;
		
	}

}
