package HP5Mtrix;
import java.util.ArrayList;
import java.util.Arrays;

import homework4Matrix.rowFunction;

public class presitioninMatrix {
	
	public static void main(String[] Args) {
		

		Double[] vectornum = {199.00,197.00};
		
		Double x;
		Double y;
		
		
		

		for(Double i = Math.pow(10, -2);i>=Math.pow(10, -9);i=i/10.0) {
			
			x= (19701.0-9801.0/(100.0*i))/9900.0;
			y = 1.0/(100.0*i);
			System.out.println("x is "+x);
			System.out.println("y is "+y);
			Double[] vector = {x,y};
			Double[][] nvalue = {{100.00,99.00},
					{99.00,(98.01-i)}};
			//System.out.println(nvalue[3]);

			Double[] result1= new Double[] {0.0,0.0};//remember to init
			//because the type is Double, it is the subclass of object!!! the default value is null
			//needs to give it a number, or it will throw nullpointer exception
			result1 = rowFunction.fullmatrixProduct(nvalue, vector,result1);

			Double[] result = new Double[result1.length];//result is after suntraction
			rowFunction.subtractionVector(result1, vectornum, result);
			Double sum = 0.0;
			for(int j = 0;j<result.length;j++) {
				sum = sum+ Math.pow(result[j],2);
			}
			sum = Math.pow(sum, 0.5);
			System.out.println("e is " + i +", and the second norm of the residual vector is "+ sum);
			System.out.println();

		}
	}

}






//use pre-defined function to create matix
//but still get wrong answers
//Double[] nvalue = {100.00,99.00,99.00,(98.01-i)};
////System.out.println(nvalue[3]);
//
//rowFunction.createSMtrix(value, rowPtr, colInd, nvalue, nrow, ncol);
//rowFunction.retriveMethod1(value,rowPtr, colInd, 2, 2);//for double, the method is retriveMethod1
//System.out.println();
//Double[] result1= new Double[2];//remember to init
//rowFunction.resultMatrix(value, rowPtr, colInd, vector,result1);
////System.out.println(Arrays.toString(result1));
//Double[] result = new Double[result1.length];//result is after suntraction
//rowFunction.subtractionVector(result1, vectornum, result);
//Double sum = 0.0;
//for(int j = 0;j<result.length;j++) {
//	sum = sum+ Math.pow(result[j],2);
//}
//sum = Math.pow(sum, 0.5);
//System.out.println("e is " + i +", and the second norm of the residual vector is "+ sum);

