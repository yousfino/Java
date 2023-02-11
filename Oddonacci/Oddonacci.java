import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Oddonacci {

	//Like a binary recursion, the trinary version returns the sum of the three past recursive calls
    public static long TriOdd(int x){

        if(x==0) {
            return 0;
        }
        else if(x==1 || x==2 || x==3) {
            return 1;
        }

        return TriOdd(x-1) + TriOdd(x-2) + TriOdd(x-3);
    }

    public static long LinOdd2(int x, int n, long i, long j, long k) {
    	
    	if(x<1) return 0;
    	else if (x<4) return 1;
    	
    	if(n==x) 
    		return k;
    	else
    		return LinOdd2(x,n+1,j,k,i+j+k);
    }
    
    
    public static long[] LinOdd(int x) {

        long[] A = new long[3];
        long i = 0, j = 1, k = 2;
        long z = 0, w = 0, y = 0;

        if(x == 0) {
            System.out.print(0 + " ");
            return A;
        }
        if(x == 1) {
            A[0] = 1;
            System.out.print(1 + " ");
            return A;
        }
        if(x == 2) {
            A[0] = 1; A[1] = 1;
            System.out.print(1 + " " + 1 + " ");
            return A;
        }
        if(x == 3) {
            A[0] = 1; A[1] = 1; A[2] = 1;
            System.out.print(1 + " " + 1 + " " + 1 + " ");
            return A;
        }

        A = LinOdd(x-1);
        z = A[0]; w = A[1]; y = A[2];
        System.out.print((z+w+y) + " ");
        A[0] = z+w+y; A[1] = z; A[2] = w;
        return A;


    }

    //Wrapper function for the trinary oddonacci
    public static void TrinaryOddonacci(int n){

        System.out.print("The Oddonacci sequence of " + n + " numbers using trinary recursion is: ");

        for (int i=0; i<n; i++)
            System.out.print(TriOdd(i+1) + " ");

        System.out.println();
    }

    //Wrapper function for the linear oddonacci
    public static void LinearOddonacci(int n){

        System.out.print("The Oddonacci sequence of " + n + " numbers using linear recursion is: ");

        LinOdd(n);
        System.out.println();
    }
    
    public static void LinearOddonacciV2(int n){

        System.out.print("The Oddonacci sequence of " + n + " numbers using linear recursion is: ");

        for (int i=0; i<n; i++)
            System.out.print( LinOdd2(i+1,3,1,1,1) + " ");
       ;
        System.out.println();
    }

    public static void main(String[] args) {
    	try {
    		long[] triArr = new long[20];
    		long[] linArr = new long[20];
    		
    		PrintWriter out = new PrintWriter("OddoOut.txt");
    		out.println("Output trace of Trinary Oddonacci:\n");
    		for(int i=0; i<5; i++) {
    			long startTime = System.nanoTime();
    			long temp = TriOdd(5*(i+1));
    			long endTime = System.nanoTime();
    			triArr[i] = endTime - startTime;
    			
    			out.println(String.format("%-50s %s","Trinary Oddonacci of " + (i+1) + " is: " + temp, "(" + triArr[i] + " ns)"));
    		}
    		out.println();
    		
    		out.println("Output trace of Linear Oddonacci:\n");
    		for(int i=0; i<20; i++) {
    			long startTime = System.nanoTime();
    			long temp = LinOdd2(5*(i+1),3,1,1,1);
    			long endTime = System.nanoTime();
    			linArr[i] = endTime - startTime;
    			
    			out.println(String.format("%-50s %s","Linear Oddonacci of " + (5*(i+1)) + " is: " + temp,"(" + linArr[i] + " ns)"));    			
    		}
    		out.println();
    		
    		out.println("Table of trinary and linear call times for comparison (in nanoseconds):");
    		for(int i=0; i<20; i++)
    			out.println(String.format("%-4d %-20s %s",5*(i+1),triArr[i],linArr[i]));
    		
    		out.close();
    	} catch (IOException e) {
    		System.out.println("An error has occured");
    		e.printStackTrace();
    	}
    		
    	TrinaryOddonacci(7);
        LinearOddonacci(7);
        LinearOddonacciV2(7);
        System.out.println(LinOdd2(7,3,1,1,1));

    }	
}
