import java.util.*;
import java.io.*;

/**
 * Program to collect runtime of the recursive and greedy algorithm to solve
 * the activity-selection problem.
 * 
 * @author Christina L
 * @author Sam Benefield
 * @version 6/27/2021
 *
 */
public class ActivitySelection {
	
	//////////////////
	///// DRIVER /////
	//////////////////
	public static void main (String[] args) {
		studyOverhead(500, 10);
	}
	
	///////////////////
	///// METHODS /////
	///////////////////
	/**
	 * Recursive algorithm to solve the activity-selection problem.
	 * @param s - array of start times
	 * @param f - array of finish times
	 * @param k - index of the subproblem Sk to solve 
	 * @param n - number of activities in the original problem
	 * @param A - array list for optimal solution
	 * @return the maximum-size set of mutually compatible activities in Sk
	 */
	public static ArrayList<Integer> recursiveActivitySelector(int[] s, int[] f, int k, int n, ArrayList<Integer> A){
		int m = k + 1;	//k is initialized to fictitious activity with f[0] = 0 so start at 1
		
		while (m <= n && s[m] < f[k]) {	//find the first activity in Sk to finish
			m = m + 1;
		}
		
		if (m <= n) {	//run through entire problem set
			A.add(m);	//add a_m to set A
			return recursiveActivitySelector(s, f, m, n, A);
		}
		
		else {
			return null;	//nothing added to set A
		}
	}
	
	/**
	 * Iterative algorithm to solve the activity-selection problem
	 * @param s - array of start times
	 * @param f - array of finish times
	 * @param n - number of activities in the original problem
	 * @return the maximum-size set of mutually compatible activities in Sk
	 */
	public static ArrayList<Integer> greedyActivitySelector(int[] s, int[] f, int n, ArrayList<Integer> A){
		A.add(1);	//activity a1 is always in optimal solution
		
		int k = 1;
		for(int m = 2; m <= n; m++) {
			if (s[m] >= f[k]) {
				A.add(m);	//add a_m to set A
				k = m;
			}
		}
		return A;
	}
	
	/**
	 * Calculate and store the overhead of the function calls
	 * @param numberPoints
	 * @param numberRuns
	 */
	public static void studyOverhead(int numberPoints, int numberRuns) {
		File file = new File("ActivitySelectorData.csv");
		
		try {
			//create file to write to
			PrintWriter dataFile = new PrintWriter(file);
			int[] s = new int[numberPoints];	//initialize start time array
			int[] f = new int[numberPoints];	//initialize finish time array
			
			//create start and finish time arrays
			initializeArrays(s, f, numberPoints);
			
			for (int i = 1; i <= numberPoints; i++) {
				long timeRecursive = 0;
				long timeIterative = 0;
				
				for (int j = 1; j <= numberRuns; j++) {
					ArrayList<Integer> A = new ArrayList<>();
					
					//get overhead of recursive function calls
					long recursiveStart = System.nanoTime();
					recursiveActivitySelector(s, f, 0, i - 1, A);
					long recursiveEnd = System.nanoTime();
					timeRecursive += (recursiveEnd - recursiveStart);
					
					//get overhead of iterative function calls
					long greedyStart = System.nanoTime();
					greedyActivitySelector(s, f, i - 1, A);
					long greedyEnd = System.nanoTime();
					timeIterative += (greedyEnd - greedyStart);
				}
				//record overhead of the function calls ratio
				dataFile.println(i + "," + (double)timeRecursive / (double)timeIterative);
			}
			//close file
			dataFile.close();
		}
		catch(IOException e) {
			System.out.println("An error occurred.");
		}
	}
	
	/**
	 * Create about n/2 mutually compatible activities
	 * @param s - array of start times
	 * @param f - array of finish times
	 * @param n - size of arrays (number of activities)
	 */
	public static void initializeArrays(int[] s, int[] f, int n) {
		s[0] = 0;
		f[0] = 0;
		
		for (int i = 1; i < n; i++) {
			if (i % 2 == 0) {
				s[i] = f[i - 2];
				f[i] = s[i] + 2;
			}
			else {
				s[i] = f[i - 1] - 1;
				f[i] = f[i - 1] + 1;
			}
		}
	}
}
