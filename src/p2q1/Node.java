package p2q1;

public class Node {
	private Node left_child, right_child;
	private int frequency=0;
	private static int[] codes= new int[256];
	
	// value of -1 represents interior node
	private int value;
	public Node next=null;
	
	// leaf nodes
	public Node(int frequency, int value){
		
		this.frequency=frequency;
		this.value=value;
		left_child=null;
		right_child=null;
	}
	
	// interior nodes
	public Node(Node left_child, Node right_child){
	
		this.left_child=left_child;
		this.right_child=right_child;
		this.frequency=right_child.getFreq()+left_child.getFreq();
		this.value=-1;
	}
	
	// returns value
	public int getVal(){
		return value;
	}
	
	// returns frequency
	public int getFreq(){
		return frequency;
	}
	
	// appends left child
	public void appendLeft(Node child){
		left_child=child;
		frequency = frequency+left_child.getFreq();
	}
	
	// appends right child
	public void appendRight(Node child){
		right_child=child;
		frequency = frequency+right_child.getFreq();
	}
	
	// displays info of node
	public void display(){
		System.out.println("value:	"+value+"	frequency:	" + frequency );
	}
	
	// returns left child
	public Node getLeft(){
		return left_child;
	}
	
	// returns right child
	public Node getRight(){
		return right_child;
	}
	public static int[] getCodes(){
		return codes;
	}
	
	// tree traversal
    public static void postOrder(Node n,String s){
    	if(n!=null){
    		postOrder(n.getLeft(),s+="0");
    		s=s.substring(0, s.length()-1);
    		postOrder(n.getRight(),s+="1");
    		s=s.substring(0, s.length()-1);
    		//System.out.println(n.value);
    		if(n.value>=0){
    			//System.out.println("entered if");
    			System.out.print(n.getVal()+ "	"+s+"				");
    			codes[n.getVal()]=Integer.parseInt(s,2);
    			System.out.println(codes[n.getVal()]);
    		}
    	}
    }
	
	
}
