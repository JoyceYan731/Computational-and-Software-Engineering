package HP5Mtrix;
import java.util.Arrays;

import homework4Matrix.rowFunction;

public class test {
	//test if the X is the right answer
	//second term
	
	public static void secondNorm(double[] calResult, double[] trueResult) {
		double[] termResult = new double[trueResult.length];
		for(int i = 0;i<trueResult.length;i++) {
			termResult[i] = calResult[i] - trueResult[i];
		}
		double sum = 0.0;
		//System.out.println(Arrays.toString(termResult));
		for(int i = 0;i<trueResult.length;i++) {
			sum = sum+ Math.pow(termResult[i],2);
			//System.out.println(sum);
		}
		sum = Math.pow(sum, 0.5);
		System.out.println("Test, the second norm of calculate X is "+ sum);
		System.out.println();
	}
	
	
	

}

