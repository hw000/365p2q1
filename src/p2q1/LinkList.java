package p2q1;

public class LinkList {
	public Node firstNode;
	public int length;
	
	// constructor
	LinkList(){
		firstNode=null;
		length=0;
	}
	
	// returns true if list is empty
	public boolean isEmpty(){
		return firstNode==null;
	}

	
	// returns node that comes before node target
	public Node getPrevious(Node target){
		Node tmp=firstNode;
		while(tmp!=null){
			if(tmp.next==target){
				return tmp;
			}
			tmp=tmp.next;
		}
		return tmp;		//may cause problems
	}
	
	
	// inserts node with no children generally leaf nodes
	public void insert(Node newNode){
		int freq = newNode.getFreq();
		Node insertLoc = find(freq);
		Node tmp;
		//Node newNode = new Node(freq, val);
		Node prevNode = getPrevious(insertLoc);
		if(firstNode==null){
			firstNode=newNode;
		}
		else if(insertLoc.next==null&&insertLoc!=firstNode){
			if (insertLoc.getFreq()>freq){
				newNode.next=insertLoc;
				prevNode.next=newNode;
			}else{
				insertLoc.next=newNode;
			}
		}
		else if(insertLoc==firstNode){
			if(insertLoc.getFreq()>freq){
				
				newNode.next=insertLoc;
				firstNode=newNode;
			}else{
				tmp = insertLoc.next;
				newNode.next=tmp;
				firstNode.next=newNode;
			}
		}else{
			tmp = insertLoc.next;
			prevNode.next=newNode;
			newNode.next=insertLoc;
		}

		
		length++;
	}
	
	// removes and returns firstNode
	public Node removeFirst(){
		Node tmp = firstNode;
		length--;
		if(!isEmpty()){
			firstNode=firstNode.next;
		}else{
			//System.out.println("empty list");
		}
		
		return tmp;
		
	}
	
	// displays value and frequency of nodes
	public void display(){
		Node tmp = firstNode;
		while(tmp!=null){
			
			tmp.display();
			tmp=tmp.next;
		}
	}
	
	// finds location of last node with frequency less than given frequency
	public Node find(int frequency){
		Node theNode = firstNode;
		if(!isEmpty()){
			while(theNode.getFreq()<frequency){
				if(theNode.next==null){
					return theNode;
				}else{
					//returning the wrong node
					theNode = theNode.next;
				}
				
			}
		}else{
			//System.out.println("empty list");
		}
		//System.out.println(theNode.getVal());
		return theNode;
	}
	
	
	// removes node of given value
	// most likely won't be used
	public Node removeNode(int value){
		length--;
		Node currentNode = firstNode;
		Node previousNode = firstNode;
		
		
		while(currentNode.getVal() != value){
			if(currentNode.next==null){
				return null;
			} else {
				previousNode = currentNode;
				
				currentNode= currentNode.next;
			}
		}
		
		if(currentNode == firstNode) {
			firstNode = firstNode.next;
		} else {
			System.out.println("found match");
			System.out.println("currentNode: " + currentNode);
			System.out.println("firstNode: " + firstNode);
			previousNode.next= currentNode.next;
		}
		return currentNode;
	}

	
}
