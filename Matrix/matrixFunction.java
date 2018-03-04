package HP5Mtrix;

import java.util.ArrayList;
import java.util.Arrays;

import homework4Matrix.rowFunction;

public class matrixFunction {
	
	public static double[] SLUSolver(double[][] fullM, double[] result) {

	//the number of rows/columns in this matrix
	int rowSize = fullM.length;
	int columnSize = fullM[0].length;
	int[] pivotRecord = new int[rowSize];
	
	
	//the first loop is for this matrix
	//test part,only for the first row -- 
	//for (int i = 0;i<2;i++) {
	for (int i = 0;i<rowSize;i++) {
		//loop rowi.1: row permutation-- find the most sparse row in a loop
		//calcute the number of nonzero element in this row
		int maxCount = 0;
		for(int column =0;column<columnSize;column++) {
			if(fullM[i][column]==0) maxCount++;
		}
		//start from the next row after i
		//rowP is a row with maximum sparsity
		int rowP = i;
		for(int j=i+1;j<rowSize;j++) {
			int count =0;
			for(int column =0;column<columnSize;column++) {
				if(fullM[j][column]==0) count++;
			}
			if(count>maxCount){
				maxCount = count;
				rowP = j;
			}	
		}
		//test: pass -- System.out.println(rowP);
		//change the row and vector 
		if(rowP != i) {
			fullM= rowFunction.fullrowPermute(fullM, i, rowP);
			//change the number in result vector
			rowFunction.changeVector(result, i,rowP);
			//test:pass -- System.out.println(Arrays.toString(result));
		}
		
		//loop rowi.2
		//after changing the row, go through every element in this row
		//there is a loop for every element
		//using minFill to record the pivot with minimum fillins

		int pivotC =0;
		int minFill = Integer.MAX_VALUE;
		//set the max value to initialize the minimum fillin
		//and it can be replaced when meets smaller integer
		
		//this loop is for changing different pivot
		for(int c=0;c<columnSize;c++) {
			if(fullM[i][c]==0) continue;
			//this loop is to calculate fill in
			int tempFillin = 0;
			for(int loopC=0;loopC<columnSize;loopC++) {
				
				if(loopC == c) continue;//loops in other column
				//find which column will be polluted -- full[i][~] is not zero
				if(fullM[i][loopC]==0) continue;
				//find which row will be polluted--fullM[~][c] is  zero
				for(int temR = i+1;temR<rowSize;temR++) {
					if(fullM[temR][loopC]==0) tempFillin++;
				}
			}
			
			
			//if the tempFillin less than minfillin, change pivot
			if(tempFillin<minFill) {
				pivotC = c;
				minFill=tempFillin;
			}
			
		}
		//test:pass -- System.out.println(i+"haha"+pivotC);
		//System.out.println(minFill);
		
		//after determining the pivot
		//start to change the matrix and vector
		
		//for full matrix
		//scaling:add row2 to row1
		for(int changeR=i+1;changeR<rowSize;changeR++) {
			double a = -fullM[changeR][pivotC]/fullM[i][pivotC];
			fullM=rowFunction.fullrowscaling(fullM, changeR, i,a);
			result[changeR]+=result[i]*a;
		}
		
		
		
		
		pivotRecord[i] = pivotC;
		
		
		
	}//this is the outer loop for pivot in every row
	//test:pass--System.out.println(Arrays.toString(pivotRecord));
	//test:pass --System.out.println(Arrays.toString(result));

	
	//gtest:pass--rowFunction.printFullMatrix(fullM);
	
	//System.out.println();
	//change the matrix to an upper triangular matrix
	
	for(int rowX=0;rowX<rowSize;rowX++) {
		if(fullM[rowX][rowX]!=0) continue;
		for(int searchRow = rowX+1;searchRow<rowSize;searchRow++) {
			if(fullM[searchRow][rowX]!=0) {
				//row permutation
				fullM= rowFunction.fullrowPermute(fullM, rowX,searchRow );
				//change the number in result vector
				rowFunction.changeVector(result, rowX,searchRow);
				//rowFunction.printFullMatrix(fullM);
				break;
				
			}
		}
	}//END FOR
	
	//test:pass--rowFunction.printFullMatrix(fullM);
	//System.out.println(Arrays.toString(result));
	
	//get X for AX=b -- backward substitution
	double[] valueX= new double[result.length];
	//start from the last row
	for(int nowX = valueX.length-1;nowX >=0;nowX--) {
		double sum =0;
		for(int nowC = columnSize-1; nowC>nowX;nowC--) {
		sum += fullM[nowX][nowC]*valueX[nowC];
		}
		valueX[nowX] = (result[nowX]-sum)/fullM[nowX][nowX];
	}
	//test:pass --System.out.println(Arrays.toString(valueX));
	return valueX;


	}//end of a method
	
	//for full matrix
	public static void upperNorm(double[][] fullM, double[] result) {
		int rowSize = fullM.length;
		int colSize = fullM[0].length;
		//for Aone
		double max = 0;
		for(int column=0;column<colSize;column++) {
			double temp = 0;
			for(int row=0;row<rowSize;row++) {
			temp += fullM[row][column];
			}
			if(temp>max) max = temp;
		}
		//Aone = max;
		result[0] = max;
		//test:pass -- 
		//System.out.println(Aone);
		
		//for AInfinite
		double max1 = 0;
		for(int row=0;row<rowSize;row++) {
			double temp = 0;
			for(int column=0;column<colSize;column++) {
			temp += fullM[row][column];
			}
			if(temp>max1) max1 = temp;
		}
		//AInfinite = max1;
		result[1] = max1;
		//test:pass -- 
		//System.out.println(AInfinite);
		
	}//end of full-upper
	
	//for sparse matrix
		public static void upperNorm(ArrayList<Double> value, ArrayList<Integer> rowPtr, ArrayList<Integer> colInd, int colSize, double[] result) {
			int rowSize = rowPtr.size()-1;
			//for Aone
			//retrive elements and then calculate
			double max =0;
			for(int column=0;column<colSize;column++) {
				double temp = 0;
				for(int row=0;row<rowSize;row++) {
				temp += Math.abs(rowFunction.retriveMethodHelp1(value, rowPtr, colInd, row, column));
				}
				if(temp>max) max = temp;
			}
			result[0] = max;
			

			//for AInfinite
			double max1 = 0;
			for(int row=0;row<rowSize;row++) {
				double temp = 0;
				for(int column=0;column<colSize;column++) {
				temp += Math.abs(rowFunction.retriveMethodHelp1(value, rowPtr, colInd, row, column));
				}
				if(temp>max1) max1 = temp;
			}
			//AInfinite = max1;
			result[1] = max1;
			

	
		}
	
}


