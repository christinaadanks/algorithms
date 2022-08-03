
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;

/**
 * Program to calculate the running time of merge sort algorithm.
 *
 * @author Christina L (czl0144@auburn.edu)
 * @author Jared Whaley
 * @version 3/24/2021
 */
public class ProgrammingAssignment2 {

   public static double log2(int N) {
      double result = (Math.log(N) / Math.log(2));
      
      return result;
   }

   public static void mergeSort(int A[], int p, int r) {
      if (p < r)  {
         int q = p + (r - p)/2;
         
         mergeSort(A, p, q);
         mergeSort(A, q + 1, r);
         merge(A, p, q, r);
      }
   }
   
   public static void merge(int A[], int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		
		for (int i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = A[q + j + 1];
		}
		
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		
		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}

   }
   
   
   public static void main(String[] args)   {
   

      ArrayList<Long> arrTotal = new ArrayList<Long>();
      ArrayList<Integer> nArray = new ArrayList<Integer>();
      
      Random rand = new Random();
      
      int L = 5000000;
      int G[] = new int[L];
      
      for (int i = 0; i < L; i++)  {
         G[i] = rand.nextInt();
      }
      
      long total = 0L;
      int n = 1000;
      int A[] = new int[L];
      for (n = 1000; n <= L; n += 1000)  {
         for (int m = n - 1000; m < n; m++)  {
            A[m] = G[m];
         }
         long startTime = System.nanoTime();
         mergeSort(A, 0, n - 1);
         long endTime = System.nanoTime();
         total = endTime - startTime;
         nArray.add(n);
         arrTotal.add(total);
      }

      int i;
      File file = new File ("programming2.csv");
      try   {
         PrintWriter dataFile = new PrintWriter(file);
         dataFile.println("n, T(n)/n, T(n)/log2n, T(n)/nlogn");
         for (i = 0; i < nArray.size(); i++) {
            dataFile.print(nArray.get(i) + ",");
            dataFile.print(arrTotal.get(i) / nArray.get(i) + ",");
            dataFile.print(arrTotal.get(i) / log2(nArray.get(i)) + ",");
            dataFile.print(arrTotal.get(i) / ((nArray.get(i) * log2(nArray.get(i)))) + "\n");
            
         }
         dataFile.close();
         }
      
      catch (IOException e)   {
          System.out.println("An error occurred.");
      }
      
   }

}


   
   static Node newNode (int n) {
      Node temp =  new Node();
      temp.key = n;
      temp.left = null;
      temp.right = null;
      
      return temp;
   }
