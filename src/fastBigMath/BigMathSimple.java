package fastBigMath;

/**
 * This class contains a series of methods capable of performing basic arithmetic operations on integers 
 * of virtually unlimited length. All methods take parameters of type {@code String}, 
 * which represent the operands. The type {@code String} is used, as it permits the representation 
 * of arbitrarily large integers, and can be easily manipulated.
 * 
 * @author Chaitanya Varier
 * @version 05/12/2016
 */

public class BigMathSimple {
	
	/**
	 * Computes the arithmetic sum of two arbitrarily large positive integers
	 * (ie. {@code sum1} + {@code sum2}). At least one integer must be strictly non-zero.
	 * 
	 * @param sum1 the first summand, represented by a {@code String}.
	 * @param sum2 the second summand, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated sum in order.
	 */
	public static int[] add (String sum1, String sum2) {
		
		int[] n1;
		int[] n2;
		int[] sum = {0};
		
		/* 
		 * Set the length of sum[] to the length of the largest operand + 1. This will
		 * ensure that the largest possible sum of any two integers with the same lengths as the
		 * operands can be contained within sum[]. Parse sum1 and sum2 as arrays and
		 * set them to n1[] and n2[] respectively. Pad them with zeroes to ensure they have
		 * the same length as sum[].
		 */
		if (sum1.length() >= sum2.length()) {
			sum = new int[sum1.length()+1];
			n1 = BigMathHelper.toArray(sum1);
			n1 = BigMathHelper.padZeroes(n1, 1);
			n2 = BigMathHelper.toArray(sum2);
			n2 = BigMathHelper.padZeroes(n2, n1.length-n2.length);
		} else {
			sum = new int[sum2.length()+1];
			n2 = BigMathHelper.toArray(sum2);
			n2 = BigMathHelper.padZeroes(n2, 1);
			n1 = BigMathHelper.toArray(sum1);
			n1 = BigMathHelper.padZeroes(n1, n2.length-n1.length);
		}
		
		int sumi = 0, carryOver = 0;
		
		/* 
		 * Compute the sum of n1[] and n2[] into sum[] using the standard carryover approach.
		 * The current sum, sumi, is computed as the sum of the digits in n1[i] and n2[i] and
		 * any carryover as required. The actual sum computed into sum[i] is the last digit of
		 * sumi. sumi can at most be two digits, therefore the carryover will be the first digit of sumi.
		 */
		for (int i=sum.length-1; i>=0; i--) {
			sumi = n1[i]+n2[i]+carryOver;
			sum[i] += sumi%10;
			carryOver = sumi/10;
		}
		
		// Remove the extra zero from the beginning of sum[] as needed.
		sum = BigMathHelper.removePaddedZeroes(sum);
		
		return sum;
		
	}
	
	/**
	 * Computes the arithmetic difference of two arbitrarily large positive integers 
	 * (ie. {@code min} - {@code sub}). Both integers must be zero if either is zero.
	 * 
	 * @param min the minuend, represented by a {@code String}.
	 * @param sub the subtrahend, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated difference in order, along with
	 * a negative sign element at the beginning of the array if the difference is negative. 
	 */
	public static int[] subtract (String min, String sub) {
		
		int[] n1 = BigMathHelper.toArray(min);
		int[] n2 = BigMathHelper.toArray(sub);
		int[] diff = {0};
		boolean addSign = false;
		
		n1 = BigMathHelper.removePaddedZeroes(n1);
		n2 = BigMathHelper.removePaddedZeroes(n2);
		
		// Check if the length of min is greater than the length of sub (ie. if min > sub)
		if (n1.length > n2.length) {
			
			diff = new int[n1.length];
			n2 = BigMathHelper.padZeroes(n2, n1.length-n2.length);
			
		} else if (n1.length < n2.length) {
			
			// sub is greater than min, which implies the difference will be negative
			addSign = true;
			diff = new int[n2.length];
			int[] tmp = n1;
			n1 = n2;
			n2 = tmp;
			n2 = BigMathHelper.padZeroes(n2, n1.length-n2.length);
			
		} else {
			// the lengths of min and sub are equal
			
			// If min = sub, then return {0}
			if (BigMathHelper.checkIsEqual(n1,n2)) {
				int[] diffEquals = {0};
				return diffEquals;
			}
			// check if min is greater than sub
			if (BigMathHelper.checkIsGreaterThan(n1, n2)) {
				diff = new int[n1.length];
			} else {
				// sub is greater than min, which implies the difference will be negative
				addSign = true;
				diff = new int[n2.length];
				int[] tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
		}
		
		int diffi = 0;
		
		/* 
		 * Compute the difference of n1[] and n2[] into diff[] using the standard carryover approach.
		 * At this point, n1[] will always be greater than n2[].
		 * The current difference, diffi, is computed as the difference of the digits in n1[i] and n2[i] and
		 * any carryover as required.
		 */
		
		for (int i=diff.length-1; i>=0; i--) {
			
			if (n1[i] < n2[i]){
				diffi = (10+n1[i])-n2[i];
				if (i > 0) {
					n1[i-1] = n1[i-1]-1;
				} else {
					int j = i;
					// If 
					while (n1[j] == 0) { 
						n1[j] = 9;
						j--;
					}
					n1[j] = n1[j] - 1;
				}
			} else {
				diffi = n1[i]-n2[i];
			}
			diff[i] += diffi;
			
		}

		diff = BigMathHelper.removePaddedZeroes(diff);
		
		// If the difference is negative, then add a -1 flag at the beginning of the array
		if (addSign) {
			diff = BigMathHelper.padZeroes(diff, 1);
			diff[0]=-1;
		}
		
		return diff;
		
	}
	
	/**
	 * Computes the arithmetic product of two positive arbitrarily large integers
	 * (ie. {@code fact1} * {@code fact2}).
	 * 
	 * @param fact1 the first factor, represented by a {@code String}.
	 * @param fact2 the second factor, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated product in order.
	 */
	public static int[] multiply (String fact1, String fact2) {
		
		int[] n1 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(fact1));
		int[] n2 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(fact2));
		
		// Let n1 store the larger factor
		if (n1.length < n2.length) {
			int tmp[] = n2;
			n2 = n1;
			n1 = tmp;
		}
		
		/* Calculate the number of digits in the product based on the formula:
		 * numDigits = floor(log_10(n1)+log_10(n2)+1) = ((numDigits in n1 - 1 + numDigits in n2 - 1) + 1)
		 * = (numDigits in n1 + numDigits in n2) -1. Extra digits are added for safety.
		 */
		int numDigits = (n1.length + n2.length) + 1;
		
		// The product will be computed into prod
		int[] prod = new int[numDigits];
		
		// For pointer multiplication
		int digProd = 0;
		int carryOver = 0;
		int i = prod.length - 1;
		
		// For multi-digit multiplication
		int digits = n2.length;
		
		// For adding lines in multi-digit multiplication
		int sumCarry = 0;
		int digSum = 0;
		
		// Pad n1 with zeroes to match the length of prod
		n1 = BigMathHelper.padZeroes(n1, prod.length-n1.length);
		
        /* Execute multi-line multiplication if both factors have
         *  more than one digit.
         */
		if (n1.length > 1 && n2.length > 1) {
			
            /* A two dimensional array such that there is an array for
             * each line in the multiplication. Once these are filled,
             * they will be added together.
             */
			 int[][] lines = new int[digits][prod.length];
			 
            /* A temporary two dimensional array to store the values of
             * the line arrays.
             */
			 int[][] tempArray = new int[digits - 1][prod.length];
			 
            /* Calculate the multiplications and fill the lines
             * according to the standard long multiplication algorithm
             * The loop will run according to the one line per digit
	         * rule.
	         */
			
            for (int z = digits-1; z >= 0; z--) {
 
                {
                    // Check if the index variable has reached the beginning of the array.
                    while (i >= 0) {
 
                        /* Get the product of the current digit in n2 and the digit stored in the current
                         * index of the current line.
                         */
                        digProd = n1[i] * n2[z];
 
                       /* Get the mod 10 of the product and add it to
                        * carryOver. Take the mod 10 of the whole
                        * expression. This will ensure that no
                        * matter how large the product is
                        * (it will always be between 0 - 81), only a
                        * single digit will be stored in each element.
                        */
                        lines[z][i] = ((digProd % 10 + carryOver) % 10);
 
                        /* The carryover will simply be the tens digit of the product plus 
                         * the previous carryover.
                         */
                        carryOver = (digProd + carryOver) / 10;
 
                        // Shift the array index down by one for the next iteration.
                        i--;
                    }
 
                    // Reset values.
                    carryOver = 0;
                    i = prod.length - 1;
                    
                }
 
                // If the current digit is 0, store a value of 0 in all indeces in the corresponding line.
                if (n2[z] == 0) {
 
                    for (int run = 0; run < prod.length; run++) {
                        lines[z][run] = 0;
                    }
 
                }
 
            }
 
           /* Store the value of the lines which need zeroes to be appended 
            * (as per the long multiplication algorithm)
            * in tempArray.
            */
            for (int init = 0; init < prod.length; init++) {
 
                for (int tempInd = 0; tempInd < digits - 1; tempInd++) {
                    tempArray[tempInd][init] = lines[tempInd][init];
                }
 
            }
 
            for (int q = 0; q < digits - 1; q++) {
 
                // Append necessary zeros to each line array in lines at beginning.
                for (int zeroInd = digits - q - 1; zeroInd >= 0; zeroInd--) {
                    lines[q][prod.length - 1 - zeroInd] = 0;
                }
 
               /* Restore the original values to the lines[] two dimensional array with the 
                * appended zeroes.
                */
                for (int index = prod.length - (digits - q); index >= 0; index--) {
                    lines[q][index] = tempArray[q][index + (digits - q - 1)];
                }
 
            }
            
            while (i >= 0) {
            	
            	/* Sum the lines together after multiplying and
                 * store the result in prod.
                 */
                for (int digSumInd = 0; digSumInd < digits; digSumInd++) {
                	
                    // Get the sum across all the lines at each index.
                    digSum += lines[digSumInd][i];
                    
                }

               /* Store the value of the sum modulo 10 plus the
                * sum carryOver value, then take the modulo 10 of
                * the whole expression. Similarly to the multiplication
                * algorithm, this will ensure that a single digit will
                * be stored in each index no matter how large the sum
                * is (between 0 - 18).
                */
                prod[i] = ((digSum % 10 + sumCarry) % 10);

               /* The sum carryover will simply be the tens digit of
                * the sum plus the previous sum carryover.
                */
                sumCarry = (digSum + sumCarry) / 10;
                
                /* Shift the array index down by one for the next
                 *  iteration.
                 */
                i--;
 
                // Reset values.
                digSum = 0;
            }
            
            //Reset values.
            i = prod.length - 1;
			
		} else {
			
	        // Execute single-line multiplication if either factor has only a single digit
            while (i >= 0) {
 
                digProd = n1[i] * n2[0];
 
                prod[i] = ((digProd % 10 + carryOver) % 10);
                carryOver = (digProd + carryOver) / 10;
 
                i--;
            }
 
            carryOver = 0;
            i = prod.length - 1;
		}
		
		return BigMathHelper.removePaddedZeroes(prod);
		
	}
	
	/**
	 * Computes the arithmetic quotient of two arbitrarily large positive integers 
	 * (ie. {@code divid} / {@code div}).
	 * The quotient is returned as a normal integer division in Java 
	 * (ie. the fractional component is truncated).
	 * 
	 * @param divid the dividend, represented by a {@code String}.
	 * @param div the divisor, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated quotient in order.
	 */
	public static int[] divide (String divid, String div) {
		
		int[] n1 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(divid));
		int[] n2 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(div));
		
		/* Calculate the number of digits in the quotient based on the formula:
		 * numDigits = floor(log_10(n1)-log_10(n2)+1) = ((numDigits in n1 - 1 - numDigits in n2 + 1) + 1)
		 * = (numDigits in n1 - numDigits in n2) + 1.
		 */
		
		int numDigits = (n1.length - n2.length) + 1;
		
		// Check if the length of div is greater than the length of divid (ie. if div > divid)
		if (n2.length > n1.length) {
			
			int[] quotn = {0};
			return quotn;
			
		} else if (n1.length == n2.length) {
			
			// the lengths of min and sub are equal
			
			// If div = divid, return 1
			if (BigMathHelper.checkIsEqual(n1,n2)) {
				int[] quotn = {1};
				return quotn;
			}
			
			// check if div is greater than divid
			if (BigMathHelper.checkIsGreaterThan(n2, n1)) {
				
				int[] quotn = {0};
				return quotn;
				
			} 
			
		}
		
		// The quotient will be computed into quot
		int[] quot = new int[numDigits];
		int[] divMult = BigMathHelper.padZeroes(n2, n1.length-n2.length);
		String quotStr = BigMathHelper.toString(quot);
		
		/*
		 * Carry out the division by finding the greatest multiple of div that fits into divid
		 */
		while (!BigMathHelper.checkIsGreaterThan(divMult, n1) || BigMathHelper.checkIsEqual
				(divMult, n1)) {
			
			// Increment quot by 1
			quot = add(quotStr, "1");
			quotStr = BigMathHelper.toString(quot);
			
			// Multiply div by quot
			divMult = multiply(BigMathHelper.toString(n2), quotStr);
			divMult = BigMathHelper.padZeroes(divMult, n1.length-divMult.length);
			
		}
		
		return subtract(BigMathHelper.toString(quot), "1");
		
	}
	
	/**
	 * Computes the arithmetic modulus of two arbitrarily large positive integers
	 * (ie. {@code divid} % {@code div}). Neither parameter may be zero, and {@code divid} 
	 * must be greater than or equal to {@code div}.
	 * 
	 * @param divid the dividend, represented by a {@code String}.
	 * @param div the divisor, represented by a {@code String}.
	 * @return an integer array containing the digits of the calculated division remainder in order.
	 */
	public static int[] modulate (String divid, String div) {
		
		int[] n1 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(divid));
		int[] n2 = BigMathHelper.removePaddedZeroes(BigMathHelper.toArray(div));
		
		int[] mod = multiply(BigMathHelper.toString(divide(BigMathHelper.toString(n1), 
				BigMathHelper.toString(n2))),div);
		
		return subtract(divid, BigMathHelper.toString(mod));
		
	}

}
