import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class ScanTest   {
   public static void main(String[] args) throws IOException{
   
      FileWriter writeFile = new FileWriter("test.txt");
      writeFile.write("test");
      writeFile.close();
      
      
   }
}

      
      
      
      
      
   
