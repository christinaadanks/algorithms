import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;


public class MergeTest {

   public static void minHeap(int[] A, int i)   {
      
      int smallest = 0;
      int l = 2*i;
      int r = 2*i + 1;
      
      if (l <= A.length && A[l] < A[i]){
         smallest = l;
      }
      else{
         smallest = i;
      }
      
      if (r <= A.length && A[r] < A[smallest]){
         smallest = r;
      }
      
      if (smallest != i)   {
         int temp = A[i];
         A[i] = A[smallest];
         A[smallest] = temp;
         minHeap(A, smallest);
      }
   
   
   
   }
   
   public static void main(String[] args)   {
   
      int B[] = {0, 36, 30, 27, 19, 26, 23, 14, 18, 20, 25} ;

      minHeap(B, 1);
      

   }

}