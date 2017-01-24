package mathSandbox;

import java.util.*;

import fastBigMath.*;

/**
 * This class demonstrates the use of the fastBigMath arbitrary-precision arithmetic library.
 * 
 * @author Chaitanya Varier
 * @version 05/12/2016
 */

public class MathSandbox {
	
	private static Scanner in;
	
	public static void main (String args[]){
		
		in = new Scanner(System.in);
		
		// Ask the user to input two integers.
		System.out.println("Enter an integer operand of arbitrary length.\n");
		String oper1 = in.nextLine();
		System.out.println();
		System.out.println("Enter a second integer operand of arbitrary length.\n");
		String oper2 = in.nextLine();
		System.out.println();
		
		// Display the sum of the two integers.
		System.out.println("The sum of the two integer operands is: " + 
		BigMathHelper.toString(BigMathSimple.add(oper1, oper2)) + "\n");
		
		// Display the difference of the two integers.
		System.out.println("The difference of the two integer operands is: " + 
		BigMathHelper.toString(BigMathSimple.subtract(oper1, oper2)) + "\n");
		
		// Display the product of the two integers.
		System.out.println("The product of the two integer operands is: " + 
		BigMathHelper.toString(BigMathSimple.multiply(oper1, oper2)) + "\n");
		
		// Display the quotient of the two integers.
		System.out.println("The quotient of the two integer operands is: " + 
		BigMathHelper.toString(BigMathSimple.divide(oper1, oper2)) + "\n");
		
		// Display the division remainder of the two integers.
		if (oper1.length() >= oper2.length()) {
			if (oper1.length() > oper2.length()) {
				System.out.println("The division remainder of the two integer operands is: " + 
				BigMathHelper.toString(BigMathSimple.modulate(oper1, oper2)) + "\n");
			} else if (oper1.equals(oper2)) {
				System.out.println("The division remainder of the two integer operands is: " + 
				BigMathHelper.toString(BigMathSimple.modulate(oper1, oper2)) + "\n");
			} else if (BigMathHelper.checkIsGreaterThan(BigMathHelper.toArray(oper1), BigMathHelper.toArray(oper2))) {
				System.out.println("The division remainder of the two integer operands is: " + 
				BigMathHelper.toString(BigMathSimple.modulate(oper1, oper2)) + "\n");
			}
		}
		
		// Display the exponentiation of the two integers.
		System.out.println("The first integer raised to the power of the second is: " + 
		BigMathHelper.toString(BigMathAdvanced.exponentiate(oper1, Integer.parseInt(oper2))) + "\n");
		
		
	}

}
