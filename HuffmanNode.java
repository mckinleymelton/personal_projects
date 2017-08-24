//McKinley Melton



public class HuffmanNode implements Comparable<HuffmanNode>{

	public int frequency;
	public int data;
	public HuffmanNode left;
	public HuffmanNode right;
	
	//constructs the HuffmanNode class by accepting two ints
    //as a parameter
	public HuffmanNode(int frequency, int data){
		this(frequency, data, null, null);
	}
	
	//constructs the HuffmanNode class by accepting 2 ints and a left and right
    //HuffmanNode as a parameter
	public HuffmanNode(int frequency, int data, HuffmanNode left, HuffmanNode right){
		this.frequency = frequency;
		this.data = data;
		this.left = left;
		this.right = right;
		
	}
	
	//returns true if the node is a leaf and false if it is not
	public boolean isEndNode(){
		return left == null && right == null;
	}
	
	//returns the difference bewteen the frequencies of 2 nodes
	public int compareTo(HuffmanNode other){
		return this.frequency - other.frequency;
	}
}
