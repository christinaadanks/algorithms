import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Christina L
 */

public class CollectData   {

   
   public static double ComputeSumPowers(double x, int n) {
      double sum = 0;
      for (int i = 0; i <= n; i++)   {
         double prod = 1;
         for (int j = 0; j <= i; j++)  {
            prod = prod * x;
         }
         sum = sum + prod;
      }
      return sum;
   }
   
   public static void main (String[] args) {
      ArrayList<Long> arrTotal = new ArrayList<Long>();
      ArrayList<Integer> nArray = new ArrayList<Integer>();
      long L = 100000L;
      long total = 0L;
      int n = 100;
      for (n = 100; n < L; n += 100)   {
         long startTime = System.nanoTime();
         ComputeSumPowers(0.5, n);
         long endTime = System.nanoTime();
         total = endTime - startTime;
         nArray.add(n);
         arrTotal.add(total);
      }
      
      int i;
      File file = new File ("collectData.csv");
      try   {
         PrintWriter dataFile = new PrintWriter(file);
         for (i = 0; i < nArray.size(); i++) {
            dataFile.println("n = " + nArray.get(i));
            dataFile.println("Tn = " + arrTotal.get(i) / nArray.get(i));
            dataFile.println("T2 = " + arrTotal.get(i) / Math.pow(nArray.get(i), 2));
            dataFile.println("T3 = " + arrTotal.get(i) / Math.pow(nArray.get(i), 3) + "\n");
            
         }
         dataFile.close();
      }
      
      catch (IOException e)   {
          System.out.println("An error occurred.");
      }
   
   }
}





