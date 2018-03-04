package HP5Mtrix;
import homework4Matrix.rowFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
	public static void main(String[] Args) {
		
		//HP--minimum fill-ins
		double[][] fullM = {
				{1,2,0,0,3},
				{4,5,6,0,0},
				{0,7,8,0,9},
				{0,0,0,10,0},
				{11,0,0,0,12}
		};
		
		double[] result = new double[] {5,4,3,2,1};
		double[] valueX = new double[result.length];
		valueX = matrixFunction.SLUSolver(fullM, result);
		System.out.println("the value of X is :"+Arrays.toString(valueX));
		double[][] fullM1= {
				{1,2,0,0,3},
				{4,5,6,0,0},
				{0,7,8,0,9},
				{0,0,0,10,0},
				{11,0,0,0,12}
		};
		//test
		double[] calResult = new double[result.length];
		calResult=rowFunction.fullmatrixProduct(fullM1, valueX, calResult);
		System.out.println("the calculate result is :"+Arrays.toString(calResult));
		double[] trueResult = new double[] {5,4,3,2,1};
		test.secondNorm(calResult,trueResult);
		System.out.println();
		
		//HP--Aone and AInfinite
		//for full matrix
		double[] resultA = new double[2];
		matrixFunction.upperNorm(fullM1, resultA);
		System.out.println("the Aone for full matrix is :"+ resultA[0]);
		System.out.println("the AInfinite for full matrix is :"+ resultA[1]);
		System.out.println();
		//for sparse matrix
		//create a matrix
		
		ArrayList<Double> value = new ArrayList<Double>();
		ArrayList<Integer> rowPtr = new ArrayList<Integer>();
		ArrayList<Integer> colInd = new ArrayList<Integer>();

		Double[] nvalue = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0};
		int[] nrow = {0,3,6,9,10,12};
		int[] ncol = {0,1,4,0,1,2,1,2,4,3,0,4};
		
		rowFunction.createSMtrix(value, rowPtr, colInd, nvalue, nrow, ncol);
		//rowFunction.retriveMethod(value,rowPtr, colInd, 5, 5);
		//System.out.println();
		double[] resultB = new double[2];
		matrixFunction.upperNorm(value, rowPtr, colInd, 5, resultB);
		System.out.println("the Aone for sparse matrix is :"+ resultA[0]);
		System.out.println("the AInfinite for sparse matrix is :"+ resultA[1]);
		
		
		
		}//end of main method
	
		
		
	}
	

