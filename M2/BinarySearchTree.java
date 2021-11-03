import java.util.Scanner;

/**
 * Program to implement the Search and Tree-Insert operations on a BST.
 *
 * @author Christina Liu
 * @author Sam Benefield
 * @version 6/4/2021
 */

public class BinarySearchTree {

   /////////////////////////////////
   //         NODE CLASS          //
   /////////////////////////////////
   public static class Node {
      int key;
      Node left, right;
      
      public Node()  {
         key = 0;
         left = null;
         right = null;
      }
      
      public Node (int n)  {
         this.key = n;
         left = null;
         right = null;
      }
   }

   /////////////////////////////////////////
   //         TREE SEARCH METHOD          //
   /////////////////////////////////////////
   public static boolean treeSearch(Node root, int key)   {
      Node x = root;
      
      if (x == null )   {  // tree is empty or entire tree is scanned (key not found)
         return false;
      }      
      
      if (key == x.key)   {   // key is found on tree and cannot be inserted
         return true;
      }
      
      if (key < x.key)  {  // recursive call on left
         return treeSearch(x.left, key);
      }
      
      else  {  // recursive call on right
         return treeSearch(x.right, key);
      }
   }
   
   /////////////////////////////////////////
   //       INSERT INTO TREE METHOD       //
   /////////////////////////////////////////
   public static Node treeInsert(Node root, int key)   {
      Node z = new Node(key); // number we are trying to insert
      Node parent = null;  // parent
      
      Node y = null; // trailing pointer
      Node x = root;

      while (x != null) { // keep looping until x is null (NIL)
         y = x;
         if (z.key < x.key)   {  // move left
            x = x.left;
         }
         else  {  // move right
            x = x.right;
         }
      }
      
      // set parent once x is null (this is the value we will be returning)
      parent = y;
      
      if (y == null) {  // tree was empty
         y = z;
      }
      
      else if (z.key < y.key)   {   // insert key on left side
         y.left = z;
      }
      
      else {   // insert key on right side
         y.right = z;
      }
      
      return parent;
   }
   
   
   /////////////////////////////////////////
   //       FIND TREE HEIGHT METHOD       //
   /////////////////////////////////////////
   public static int treeHeight(Node root) {
      Node x = root;
      
      if (x == null) { // the tree is empty
         return 0;
      }
      
      else  { // get the height of each subtree
         int left = treeHeight(x.left);
         int right = treeHeight(x.right);
         
         // use the subtree (left or right) with the max depth
         if (left > right) {
            return (left + 1);
         }
         else  {
            return (right + 1);
         }
      }
   }
   
   
   /////////////////////////////
   //       DRIVER CODE       //
   /////////////////////////////
   public static void main(String[] args) {
      Node root = null;      
      
      // while the user doesn't enter -1, loop keeps running
      while (true)   {
      
         System.out.print("Enter a number: ");
         Scanner scan = new Scanner(System.in);
         int n = scan.nextInt();
         
         if (n == -1)   {  // break out of loop
            break;
         }
         
         else if (root == null) {
            root = new Node(n);  // set the root value
            System.out.println("Parent: NIL. Node is ROOT.");
            continue;
         }

         if (treeSearch(root, n) == true)   {   // n is already in the tree
            System.out.println(n + " is already in the tree, please enter another number.");
            continue;
         }
         
         
         // find parent of n (most recent user submission)
         Node parent = treeInsert(root, n);
         
         if (n < parent.key)  {  // n must go on the left side of the parent
            System.out.println("Parent: " + parent.key + ", " + n + " is LEFT child.");
         }
         else  {  // n must go on the right side of parent (n > parent.key)
            System.out.println("Parent: " + parent.key + ", " + n + " is RIGHT child.");
         }
         
      }
      
      // print tree height when session ends (user enters -1)
      // -1 because height starts counting from 0
      int height = treeHeight(root) - 1;
      
      if (height < 0)   {
         System.out.println("No tree was created, program ended. Please start over.");
      }
      
      else  {
         System.out.println("The HEIGHT of the tree is " + height + ".");

      }
   
   }




}

