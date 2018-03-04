package HP5Mtrix;
import homework4Matrix.rowFunction;

public class test {
	//test if the X is the right answer
	public static boolean testResult(double[][] matrix, double[] valueX, double[] result, double[] trueResult){
		
	if(trueResult == rowFunction.fullmatrixProduct(matrix, valueX, result))return true;
	return false;
	
	}
}

