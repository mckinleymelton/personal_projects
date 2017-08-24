//McKinley Melton
//This program takes a file and compresses it down based on the 
//frequency of the characters by reducing the bits. It also decompresses
//the file by expanding the bits back to their original state

import java.io.*;
import java.util.*;

public class HuffmanTree {
	//holds all the values of frequencies in a tree form
	private HuffmanNode tree;
	
	//pre: accepts frequencies as a parameter with a size greater than 1
	//post: puts all the non-zero frequencies in order and adding an end of file 
	//		frequency to determine the end of the file. it compresses the 
	//		frequencies down into a single tree
	public HuffmanTree(int[] count){
		Queue<HuffmanNode> values = new PriorityQueue<HuffmanNode>();
		for(int i = 0; i < count.length; i++){
			if(count[i] > 0){
				HuffmanNode nextValue = new HuffmanNode(count[i], i);
				values.add(nextValue);
			}
		}
		HuffmanNode endFile = new HuffmanNode(1, 256);
		values.add(endFile);
		values = queueBuilder(values);
		tree = values.remove();
	}
	
	//Pre: accepts the ordered frequencies as a parameter
	//post: compresses the frequencies into a single tree. returns 
	//		a node of all the frequencies.
	private Queue<HuffmanNode> queueBuilder(Queue<HuffmanNode> values){
		HuffmanNode holder;
		while(values.size() > 1){
			HuffmanNode node1 = values.remove();
			HuffmanNode node2 = values.remove();
			if(node1.compareTo(node2) <= 0){
				holder = new HuffmanNode(node1.frequency + node2.frequency, -1, node1, node2);
				values.add(holder);
			}else if(node2.compareTo(node1) <= 0){
				holder = new HuffmanNode(node1.frequency + node2.frequency, -1, node2, node1);
				values.add(holder);
				
			}
		}
		return values;
	}
	
	//pre: accepts a printstream as a parameter containing the name of
	//		the file that the frequencies will be printed to
	//post: writes all the frequencies to the output file as well as the 
	//		route in which those frequencies are stored in the HuffmanNode
	//		based on left or right movements (0's or 1's)
	public void write(PrintStream output){
		String code = "";
		writeHelper(tree, output, code);
	}
	
	//pre: accepts a huffmanNode, the output file, and the representation of the route
	//		as parameters
	//post: writes all the frequencies to the output file as well as the 
	//		route in which those frequencies are stored in the HuffmanNode
	//		based on left or right movements (0's or 1's)
	private void writeHelper(HuffmanNode helper, PrintStream output, String code){
		if(helper.isEndNode()){
			output.println(helper.data);
			output.println(code);
		}else{
			code += "0";
			writeHelper(helper.left, output, code);
			code = code.substring(0, code.length() - 1);
			code += "1";
			writeHelper(helper.right, output, code);
		}
	}
	
	//pre: accepts a scanner containing the file that is to be compressed.
	//		the file does exist
	//post: takes the integer version of the characters and puts them into 
	//		a huffmanNode based on their route of 0's and 1's
	public HuffmanTree(Scanner input){
		HuffmanNode temp = new HuffmanNode(-1, 0);
		while(input.hasNextLine()){
			int number = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			tree = treeHelper(temp, code, number);
		}
	}
	
	//pre: accepts a huffmannode, string, and number representing the character
	//post: compresses down into a single huffmanNode of characters to represent a compressed 
	//file. returns a node of the characters
	private HuffmanNode treeHelper(HuffmanNode temp, String code, int number){
		if(code.charAt(0) == '0' && code.length() < 2){
			temp.left = new HuffmanNode(-1, number);
		}else if(code.charAt(0) == '1' && code.length() < 2){
			temp.right = new HuffmanNode(-1, number);
		}else if(code.charAt(0) == '1'){
			if(temp.right == null){
				temp.right = new HuffmanNode(-1, 0);
			}
			treeHelper(temp.right, code.substring(1), number);
			
		}else{
			if(temp.left == null){
				temp.left = new HuffmanNode(-1, 0);
			}
			treeHelper(temp.left, code.substring(1), number);
		} 
		return temp; 
	}
	
	//pre: takes a BitInputStream, a PrintStream, and an integer representing a end
	//		of file character.the input stream contains legal encoding of characters
	//post: decompresses the bits from the input and prints them to the output file
	//		recreating the original file, stopping the process when the end of file
	//		character has been reached.
	public void decode(BitInputStream input, PrintStream output, int eof){
		HuffmanNode temp = tree;
		int data = 0;
		int current = input.readBit();
		while(data != eof && current != -1){
			if (current == 1){
				temp = temp.right;
			}else{
				temp = temp.left;
			}
			if(temp.isEndNode()){
				data = temp.data;
				temp = tree;
				output.write(data);
			}
			current = input.readBit();
		}
	}
	
}
