
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;

/**
 * Program to calculate the running time of merge sort algorithm.
 *
 * @author Christina Liu (czl0144@auburn.edu)
 * @author Jared Whaley
 * @version 4/9/2021
 */
public class ProgrammingAssignment3 {

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
   
   public static void insertSort(int A[])   {
      for (int j = 1; j < A.length; j++)   {
         int key = A[j];
         int i = j - 1;
         
         while (i >= 0 && A[i] > key)  {
            A[i + 1] = A[i];
            i = i - 1;
         }
         A[i + 1] = key;
      }
   }
   
   
   public static void main(String[] args)   {
   

      ArrayList<Long> mergeTotal = new ArrayList<Long>();
      ArrayList<Integer> array1 = new ArrayList<Integer>();
      
      ArrayList<Long> insertTotal = new ArrayList<Long>();
      ArrayList<Integer> array2 = new ArrayList<Integer>();
      
      Random rand = new Random();
      
      int L = 300000;
      int G[] = new int[L];
      
      for (int i = 0; i < L; i++)  {
         G[i] = rand.nextInt();
      }
      
      long total = 0L;
      int n = 1000;
      int A[] = new int[L];
      
      // merge sort
      for (n = 4000; n <= L; n += 1000)  {
         A = Arrays.copyOfRange(G, 0, n);
         long startTime = System.nanoTime();
         mergeSort(A, 0, n - 1);
         long endTime = System.nanoTime();
         total = endTime - startTime;
         array1.add(n);
         mergeTotal.add(total);
      }
      
      int B[] = new int[L];
      
      // insert sort
      for (n = 4000; n <= L; n += 1000)  {
         B = Arrays.copyOfRange(G, 0, n); 
         long startTime = System.nanoTime();
         insertSort(B);
         long endTime = System.nanoTime();
         total = endTime - startTime;
         array2.add(n);
         insertTotal.add(total);
      }
      

      int i;
      File file = new File ("programming3merge.csv");
      File file2 = new File ("programming3insert.csv");      
      
      try   {
         PrintWriter dataFile = new PrintWriter(file);
         dataFile.println("T(n), n, T(n)/n, T(n)/logn, T(n)/nlogn");
         for (i = 0; i < array1.size(); i++) {
            dataFile.print(mergeTotal.get(i) + ",");
            dataFile.print(array1.get(i) + ",");
            dataFile.print(mergeTotal.get(i) / array1.get(i) + ",");
            dataFile.print(mergeTotal.get(i) / log2(array1.get(i)) + ",");
            dataFile.print(mergeTotal.get(i) / (array1.get(i) * log2(array1.get(i))) + "\n");
         }
         dataFile.close();

         PrintWriter dataFile2 = new PrintWriter(file2);
         dataFile2.println("T(n), n, T(n)/n, T(n)/n2, T(n)/n3");
         for (i = 0; i < array2.size(); i++) {
            dataFile2.print(insertTotal.get(i) + ",");
            dataFile2.print(array2.get(i) + ",");
            dataFile2.print(insertTotal.get(i) / array2.get(i) + ",");
            dataFile2.print(insertTotal.get(i) / Math.pow(array2.get(i), 2) + ",");
            dataFile2.print(insertTotal.get(i) / Math.pow(array2.get(i), 3) + "\n");
         }
         dataFile2.close();
         }
      
      catch (IOException e)   {
          System.out.println("An error occurred.");
      }
      
   }

}