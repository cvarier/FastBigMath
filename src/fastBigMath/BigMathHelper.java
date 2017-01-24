package fastBigMath;

/**
 * This class contains a series of helper methods which provide subroutines aiding in the calculation
 * of the operations defined in the classes {@code BigMathSimple} and {@code BigMathAdvanced}.
 * 
 * @author Chaitanya Varier
 * @version 05/12/2016
 */

public class BigMathHelper {
	
	
	/**
	 * Checks if two arrays are equal.
	 * 
	 * @param arr1 the first integer array
	 * @param arr2 the second integer array
	 * @throws IllegalArgumentException If the arrays are of different lengths or are null.
	 * @return {@code true} - If {@code arr1} = {@code arr2} <br> {@code false} - If {@code (arr1} != {@code arr2)}
	 */
	public static boolean checkIsEqual (int[] arr1, int[] arr2) {
		
		if (arr1.length != arr2.length || arr1 == null|| arr2 == null){
			throw new IllegalArgumentException("Both arrays must be of the same length and not be null.");
		}
		
		for (int i=0; i<arr1.length; i++) {
			
			if (arr1[i] != arr2[i])
				return false;
			
		}
		
		return true;
	}
	
	/**
	 * Compares whether one integer array is greater than another of the same length (ie. whether 
	 * {@code arr1} &gt; {@code arr2}). This method assumes {@code arr1} != {@code arr2}.
	 * 
	 * @param arr1 the first integer array
	 * @param arr2 the second integer array
	 * @return {@code true} - If {@code arr1} &gt; {@code arr2} <br> {@code false} - If {@code (arr1} &lt;= {@code arr2)}
	 * @throws IllegalArgumentException If the integer arrays contain more than a 
	 * single digit in each element
	 * @throws IllegalArgumentException If the integer arrays are not of equal length or at least one is {@code null} or contains zero elements
	 */
	public static boolean checkIsGreaterThan (int[] arr1, int[] arr2) {
		
		if (arr1.length != arr2.length || arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
			throw new IllegalArgumentException("The arrays must be of equal length, not null and contain at least one element.");
		}
		
		for (int i=0; i<arr1.length; i++) {
			
			if (!(arr1[i] >= 0 && arr1[i] <= 9 && arr2[i] >= 0 && arr2[i] <= 9))
				throw new IllegalArgumentException("The array must contain only a single digit in each element.");
			
			if (arr1[i] > arr2[i]) {
				return true;
			}
			
			if (arr1[i] < arr2[i]) {
				return false;
			}
			
		}
		
		return true;
	}
	
	/**
	 * Inserts a specified number of single digit zero elements to the beginning of an integer array.
	 * 
	 * @param arr the integer represented by an integer array.
	 * @param numZeroes the number of zeroes to be padded.
	 * @return the processed array padded with the specified number of zeroes. 
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * contains more than a single digit in each element
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * is null or of length 0
	 */
	public static int[] padZeroes (int[] arr, int numZeroes) {
		
		if (arr.length == 0 || arr == null){
			throw new IllegalArgumentException("The array must contain at least one element and not be null.");
		}
		
		int[] arrPadded = new int[arr.length+numZeroes];
		
		for (int i=0; i<arr.length; i++) {
			
			if (!(arr[i] >= 0 && arr[i] <= 9))
				throw new IllegalArgumentException("The array must contain only a single digit in each element.");
			
			arrPadded[i+numZeroes] = arr[i];
			
		}
		
		return arrPadded;
	}
	
	/**
	 * Removes the leading single digit zero elements from an integer array.
	 * 
	 * @param arr the integer represented by an integer array.
	 * @return the processed array stripped of its leading single digit zero elements. 
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * contains more than a single digit in each element
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * is null or of length 0
	 */
	public static int[] removePaddedZeroes (int[] arr) {
		
		if (arr.length == 0 || arr == null){
			throw new IllegalArgumentException("The array must contain at least one element and not be null.");
		}
		
		int numZeroes = 0;
		
		for (int i=0; i<arr.length && arr[i]==0; i++) {
			
			if (!(arr[i] >= 0 && arr[i] <= 9))
				throw new IllegalArgumentException("The array must contain only a single digit in each element.");
			
			numZeroes++;
			
		}
		
		int[] arrStripped = new int[arr.length-numZeroes];
		
		for (int i=arrStripped.length-1; i>=0; i--) {
			
			if (!(arr[i] >= 0 && arr[i] <= 9))
				throw new IllegalArgumentException("The array must contain only a single digit in each element.");
			
			arrStripped[i]=arr[i+numZeroes];
			
		}
		
		return arrStripped;
	}
	
	/**
	 * Converts an arbitrarily large integer represented by a {@code String} to an
	 * integer array.
	 * 
	 * @param str the integer represented by a {@code String}.
	 * @return an integer array containing the separated digits of the integer represented by the {@code String} parameter.
	 * Each element in the array contains a single digit.
	 * @throws IllegalArgumentException If the {@code String} representing the integer parameter
	 * contains anything but the numeric characters from 0-9
	 * @throws IllegalArgumentException If the {@code String} representing the integer parameter
	 * is null or of length 0
	 */
	public static int[] toArray (String str) {
		
		if (str == null || str.length() == 0)
			throw new IllegalArgumentException("The String must not be null or be of length 0.");
		
		int[] arr = new int[str.length()];
		
		for (int i=0; i<str.length(); i++) {
			
			if (!Character.isDigit(str.charAt(i)))
				throw new IllegalArgumentException("The String must contain only numeric characters.");
			
			arr[i] = Character.getNumericValue(str.charAt(i));
			
		}
		
		return arr;
	}
	
	/**
	 * Converts an arbitrarily large integer represented by an integer array to a
	 * {@code String}. Each element in the array must contain a single digit.
	 * 
	 * @param arr the integer represented by an integer array.
	 * @return a {@code String} representing the integer from the integer array parameter. 
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * contains more than a single digit in each element
	 * @throws IllegalArgumentException If the {@code int[]} representing the integer parameter
	 * is null or of length 0
	 */
	public static String toString (int[] arr) {
		
		if (arr.length == 0 || arr == null){
			throw new IllegalArgumentException("The array must contain at least one element and not be null.");
		}
		
		String s = "";
		
		for (int i=0; i<arr.length; i++) {
			
			if (!(arr[i] >= -1 && arr[i] <= 9) )
				throw new IllegalArgumentException("The array must contain only a single digit in each element.");
			
			if (arr[i] != -1) {
				s += Integer.toString(arr[i]);
			} else {
				s += "-";
			}
			
		}
		
		return s;
	}
	
}
