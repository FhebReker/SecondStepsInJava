/**
***********************************************
 @Author: Fardin Abbassi
 @Last Modified: 2023-12-29
 @Description: 	Lets user read in a file to add words into a binary tree, print the read file, 
 				search for a word in the tree, and print the tree in alphabetical order
***********************************************
*/
import java.util.*;
import java.io.*;

public class rootInterface {    
    /**A TreeNode object represents a single node in a binary tree. **/
    private static class TreeNode {
        String item;      // The data in this node.
        int count = 1;
        TreeNode left;    // Pointer to left subtree.
        TreeNode right;   // Pointer to right subtree.
        TreeNode(String str) { // Constructor.
                // Make a node containing the specified string.
                // Note that left and right pointers are initially null.
            item = str;
        }
    }  // end TreeNode class

    static Scanner scan = new Scanner(System.in);
    private static TreeNode root; 	// Pointer to the root node in a binary tree.
                                  	// This tree is used in this program as a binary sort tree.
    								// When the tree is empty, root is null.
    private static String filename;

    public static void main(String[] args) {
        System.out.println("This program stores strings that you enter in a binary sorttree.");
        System.out.println("After each item is inserted, the contents of the tree are displayed.");
        System.out.println("The number of nodes in the tree is also output.");
        System.out.println("Any string you enter will be converted to lower case.");
        System.out.println("Duplicate entries are ignored.\n");
        boolean finishedProgram = true;
        
        while (finishedProgram) {        
        	System.out.println("What would you like to do?");
        	System.out.println("1) Read in a text file (needs touch up)\n2) Print out text file (needs touch up) \n3) Search for occurances of a word \n4) Print out alphabetical list of words in tree, their counts, and the total amount of words \n5) Quit Program");
        	char menuSelect = scan.nextLine().charAt(0);
        	switch(menuSelect) {
	    		case '1': // create binary tree from file
	            	System.out.print("Enter your file's name: ");
	                filename = scan.nextLine(); // The user's input.
	                treeFromFile(filename);
	    			break;
	    			
	    		case '2': // print out text file
    				try(BufferedReader reader = new BufferedReader(new FileReader(filename));) {
						while(reader.ready()) {
							String line = reader.readLine();
							System.out.println(line);
						} // end while
					} catch (Exception e) {
						// TODO Auto-generated catch block
			            System.err.println("Error reading file: " + e.getMessage());
					} // end try/catch
	    			break;
	    			
	    		case '3': // search for word in tree
	    			System.out.print("Enter the word you'd like to search for: ");
	                String wordToFind = scan.nextLine(); // The user's input.
	                if(treeContains(root, wordToFind)) {
	                	System.out.println("Found " + wordToFind + " in the tree.");
	                } // end if
	                else {
	                	System.out.println("Couldn't find" + wordToFind + " in the tree.");
	                } // end else
	                System.out.println();
	    			break;
	    			
	    		case '4': // printing tree
	    			System.out.println("\nContents of tree:");
	                treeList(root);
	                System.out.println("Total number of words: " + countNodes(root));
	                break;
	                
	    		case '5': // quit program
	    			finishedProgram = false;
	    			break;
	    			
	    		default: // just restart if there is a misinput
	    			break;
	    			
        	} // end switch case
        }  // end while
        
        System.out.println("\n\nExiting program.");
        scan.close();
    }  // end main()

    /* Method Name: treeInsert 
	 * Author: Kyle McKay
	 * Creation Date: ???
	 * Modified Date: November 23, 2023
	 * Description: Adds an item to the binary sort tree that the global TreeNode variable root refers to
	 * @Parameters: String newItem
	 * @Return Value: void
	 * Data Type: n/a 
	 * Dependencies: TreeNode
	 * Throws/Exceptions: n/a
	 */
    private static void treeInsert(String newItem) {
        if ( root == null ) {
                // The tree is empty.  Set root to point to a new node containing
                // the new item.  This becomes the only node in the tree.
            root = new TreeNode(newItem);
            return;
        }
        
        TreeNode runner;  // Runs down the tree to find a place for newItem.
        runner = root;   // Start at the root.
        
        while (true) {
            // Since the new item is less than the item in runner, it belongs in the left subtree of runner.
            if ( newItem.compareTo(runner.item) < 0 ) {
            		// If there is an open space at runner.left, add a new node there.
                    // Otherwise, advance runner down one level to the left.
                if ( runner.left == null ) {
                    runner.left = new TreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    runner = runner.left;
            }

            // Since the new item is greater than or equal to the item in runner it belongs in the right subtree of runner.  
            else {
            		// If there is an open space at runner.right, add a new node there.
                    // Otherwise, advance runner down one level to the right.
                if ( runner.right == null ) {
                    runner.right = new TreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    runner = runner.right;
            }
        } // end while
    }  // end treeInsert()
    /* Method Name: treeContains 
	 * Author: Kyle McKay
	 * Creation Date: ???
	 * Modified Date: November 23, 2023
	 * Description: Checks if the binary tree contains a node or not
	 * @Parameters: TreeNode root, String item
	 * @Return Value: boolean
	 * Data Type: boolean
	 * Dependencies: TreeNode
	 * Throws/Exceptions: n/a
	 */
    static boolean treeContains( TreeNode root, String item ) {
        // Tree is empty, so it certainly doesn't contain item.
        if ( root == null )
            return false;
        
        // Yes, the item has been found in the root node.
        else if ( item.equals(root.item) ) {
        	root.count++;
            return true;
        }
        
        // If the item occurs, it could be in the left subtree.
        else if ( item.compareTo(root.item) < 0 )
            return treeContains( root.left, item );

        // If the item occurs, it could be in the right subtree.
        else
            return treeContains( root.right, item );
    }  // end treeContains()   
    /* Method Name: treeList 
	 * Author: Kyle McKay
	 * Creation Date: ???
	 * Modified Date: November 23, 2023
	 * Description: Print the items in the tree, inorder, one item to a line. 
	 * 				Since the tree is a sort tree, the output will be in increasing order.
	 * @Parameters: TreeNode node
	 * @Return Value: void
	 * Data Type: n/a
	 * Dependencies: TreeNode
	 * Throws/Exceptions: n/a
	 */
    private static void treeList(TreeNode node) {
        if ( node != null ) {
            treeList(node.left);             									// Print items in left subtree.
            System.out.print(node.item);										// Print item of node
            for (int i = node.item.length(); i<15; i++) System.out.print(" "); 	// Add spacing
            System.out.println(node.count);										// Print count of the node.
            treeList(node.right);            									// Print items in the right subtree.
        }
    } // end treeList()
    /* Method Name: countNodes 
	 * Author: Kyle McKay
	 * Creation Date: ???
	 * Modified Date: November 23, 2023
	 * Description: Count the nodes in the binary tree.
	 * @Parameters: TreeNode node
	 * @Return Value: int
	 * Data Type: int
	 * Dependencies: TreeNode
	 * Throws/Exceptions: n/a
	 */
    private static int countNodes(TreeNode node) {
        if ( node == null ) {
                // Tree is empty, so it contains no nodes.
            return 0;
        }
        else {
                // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes( node.left );
            int rightCount = countNodes( node.right );
            return  1 + leftCount + rightCount;  
        }
    } // end countNodes()

    /* Method Name: treeFromFile 
	 * Author: Fardin Abbassi 
	 * Creation Date: December 27, 2023
	 * Modified Date: December 29, 2023
	 * Description: takes a file, then reads all words and adds them to the program's binary tree
	 * @Parameters: String filename
	 * @Return Value: void
	 * Data Type: n/a 
	 * Dependencies: BufferedReader, FileReader, IOException
	 * Throws/Exceptions: IOException
	 */
    public static void treeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int c;
            boolean inWord = false;
            String word = "";
            
            while ((c = reader.read()) != -1) { // while there are characters in the file
                char character = (char) c;
                
                /** NEEDS A DOUBLE CHECK: Is it only letters and digits, or can include other types of characters**/
                if (!Character.isWhitespace(character)) { // if it's not a space, add to word
                    inWord = true;
                    word += character;
                } // end if
                
                else if (inWord) { // if it is a space, check if the word is already there, then add word to tree
                    inWord = false;
                    word = word.toLowerCase();                    
                    
                    if(!Character.isLetterOrDigit(word.charAt(0))) // if first character isn't a part of the word, remove it
                    	word = word.substring(1);
                    
                    if(word.length() > 1 &&!Character.isLetterOrDigit(word.charAt(word.length()-1)))// if last character isn't a part of the word, remove it
                    	word = word.substring(0, word.length() - 1);
                    
                    if(word == "") // if word has now become nothing after the above shortenings, move on
                    	word = "";
                    
                    else if (!treeContains(root, word)) { // otherwise, if the tree doesn't have the word, add to tree
                    	if (word.length() > 15) // keep word length to a maximum 15 character length
                        	word = word.substring(0, 14);
                        treeInsert(word);
                    } // end else if
                    word = "";
                } // end else if
            } // end while
            
            if (inWord) { // last check whether to add word to tree or not
                word = word.toLowerCase();   
                
                if(!Character.isLetterOrDigit(word.charAt(0))) // if first character isn't a part of the word, remove it
                	word = word.substring(1);
                
                if(word.length() > 1 &&!Character.isLetterOrDigit(word.charAt(word.length()-1)))// if last character isn't a part of the word, remove it
                	word = word.substring(0, word.length() - 1);
                
                if(word == "") // if word has now become nothing after the above shortenings, move on
                	word = "";
                
                else if (!treeContains(root, word)) { // otherwise, if the tree doesn't have the word, add to tree
                	if (word.length() > 15) // keep word length to a maximum 15 character length
                    	word = word.substring(0, 14);
                    treeInsert(word);
                } // end else if
            } // end if
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } // end try/catch
    } // end treeFromFile 
} // end class SortTreeDemo