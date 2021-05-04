//--------------------------------------------------------
//Part: 2
//Written by:Alexandra Spyridakos, 40175280
//--------------------------------------------------------

public class ShowList{
	private ShowNode head;
	private int size;
	public int numIterations;

	/**Default Constructor*/
	public ShowList(){
		head = null;
		//		head = new ShowNode();
		size = 0;
	}

	/**Copy Constructor
	 * @param linkedList
	 */	
	public ShowList(ShowList linkedList) {
		if(linkedList.head == null)
			head = null;
		else {
			head = null;	
			ShowNode node1, node2, node3;	
			node1 = linkedList.head;
			node2 = node3 = null;
			while(node1 != null) {
				if (head == null) {
					node2 = new ShowNode(node1.getNodeData(), null);										
					head = node2;
				}
				else {
					node3 = new ShowNode(node1.getNodeData(), null);										
					node2.nextNode = node3;												
					node2 = node3;						
				}
				node1 = node1.nextNode;
			}
			node2 = node3 = null; 				
		}
	}

	/**
	 * Adds node to the start of the linked list
	 * @param TVShow node
	 */
	public void addToStart(TVShow node) {
		numIterations = 0;
		numIterations++;
		head = new ShowNode(node, head);
		size++;
	}

	/**
	 * Inserts node at specific index in the linked list
	 * @param int index
	 * @param TVShow node
	 */
	public void insertAtIndex(int index, TVShow node) {
		numIterations = 0;
		try {
			if (head == null)
				throw new NoSuchElementException("Unable to insert node from an empty linked list.");
			else if (index>size-1 || index<0)
				throw new NoSuchElementException("Unable to insert element at index " + index + " because index is out of bounds.");
			else if (node == null) {
				throw new NullPointerException("Unable to insert null element into linked list.");
			}
			else {
				ShowNode currentNode = head;
				ShowNode previousNode = null;
				if (index==0) {
					addToStart(node);
				}
				else {
					while (currentNode!=null && numIterations!=index) {
						numIterations++;
						previousNode = currentNode;
						currentNode = currentNode.nextNode;
					}
					ShowNode insertedNode = new ShowNode(node, currentNode);
					previousNode.nextNode = insertedNode;
				}
				size++;
			}
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

	/**
	 * Deletes node at specified index in linked list
	 * @param int index
	 */
	public void deleteFromIndex(int index) {	
		numIterations = 0;
		try {
			if (head == null)
				throw new NoSuchElementException("Unable to delete node from an empty linked list.");
			else if (head.nextNode == null) 
				throw new NoSuchElementException("Unable to delete node because it will result in an empty linked list.");
			else if (index>size-1 || index<0)
				throw new NoSuchElementException("Unable to delete element at index " + index + " because index is out of bounds.");
			else {
				ShowNode currentNode = head;
				ShowNode previousNode = null;
				if (index==0) {
					deleteFromStart();
				}
				else {
					while (currentNode!=null && numIterations!=index) {
						numIterations++;
						previousNode = currentNode;
						currentNode = currentNode.nextNode;
					}
					previousNode.setNextNode(currentNode.nextNode);
					previousNode = new ShowNode(previousNode.getNodeData(), currentNode.getNextNode());	
				}
				currentNode = null;
				previousNode = null;
				size--;
			}
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

	}

	/**Deletes node from the start of the linked list
	 *
	 * A privacy leak occurs because even though nextNode is set to private in the 
	 * inner class, ShowNode, the nextNode is still accessible to anyone with access 
	 * to the ShowList class without having to use the designated getters and setters. 
	 * This occurs because inner and outer classes can access even each other's private 
	 * methods and attributes.
	 */
	public void deleteFromStart() {
		numIterations = 0;
		try {
			if (head == null)
				throw new NoSuchElementException("Unable to delete node from an empty linked list.");
			else if (head.nextNode == null) 
				throw new NoSuchElementException("Unable to delete node because it will result in an empty linked list.");
			else {
				numIterations++;
				head = head.getNextNode(); 		//ALTERNATIVE: head = head.nextNode;
				size--;
			}
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Replaces node at specified index
	 * @param int index
	 * @param TVShow newNode
	 * 
	 * A privacy leak occurs because even though nextNode is set to private in the 
	 * inner class, ShowNode, the nextNode is still accessible to anyone with access 
	 * to the ShowList class without having to use the designated getters and setters. 
	 * This occurs because inner and outer classes can access even each other's private 
	 * methods and attributes.
	 */
	public void replaceAtIndex(int index, TVShow newNode) {	
		numIterations = 0;
		try {
			if (head == null)
				throw new NoSuchElementException("Unable to replace node from an empty linked list.");
			else if (index>size-1 || index<0)
				throw new NoSuchElementException("Unable to replace element at index " + index + " because index is out of bounds.");
			else {
				ShowNode currentNode = head;
				ShowNode previousNode = null;
				if (index==0) {
					head.setNodeData(newNode);
					numIterations++;
				}
				else {
					while (currentNode!=null && numIterations!=index) {
						numIterations++;
						previousNode = currentNode;
						currentNode = currentNode.nextNode;		//Alternative: currentNode = currentNode.getNextNode();
					}
					currentNode.setNodeData(newNode);
					previousNode.setNextNode(currentNode);	
					previousNode = new ShowNode(newNode, currentNode);					
					currentNode = null;
					previousNode = null; 	
				}
			}
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			return;
		}
	}


	/**
	 * Method finds and returns a node if it's show id is in the linked list
	 * @param String showID
	 * @return ShowNode node of found element
	 * 
	 * A privacy leak occurs in the second if statement condition because even though 
	 * nodeData is set to private in the inner class, ShowNode, the nodeData is 
	 * still accessible to anyone with access to the ShowList class without having 
	 * to use the designated getters and setters. This occurs because inner and 
	 * outer classes can access even each other's private methods and attributes. The same applies to nextNode.
	 */
	public ShowNode find(String showID)	{
		numIterations = 0;
		try {
			if (head == null)	 
				throw new NoSuchElementException("Unable to find element because no elements exist to search through.");
			ShowNode currentNode = head;
			while(currentNode != null)
			{
				numIterations++;
				if (currentNode.nodeData.getShowID().equals(showID)) {
					ShowNode currentNodeClone = currentNode.clone();
					currentNode = null;
					return currentNodeClone;	
				}
				currentNode = currentNode.nextNode;
			}
			return null;
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}

	/**
	 * Checks if linked list contains node with given show id
	 * @param String showID
	 * @return boolean of whether or not the linked list contains the show id
	 * 
	 * A privacy leak occurs in the second if statement condition because even though 
	 * nodeData is set to private in the inner class, ShowNode, the nodeData is 
	 * still accessible to anyone with access to the ShowList class without having 
	 * to use the designated getters and setters. This occurs because inner and 
	 * outer classes can access even each other's private methods and attributes. The same applies to nextNode.
	 */
	public boolean contains(String showID) {
		numIterations = 0;
		if(find(showID)!=null)
			return true;
		else 
			return false;
	}

	/**
	 * Checks if linked lists are equal
	 * @param ShowList linkedList
	 * @return boolean representing equality of linked lists
	 * 
	 * A privacy leak occurs in the if statement condition because even though 
	 * nodeData is set to private in the inner class, ShowNode, the nodeData is 
	 * still accessible to anyone with access to the ShowList class without having 
	 * to use the designated getters and setters. This occurs because inner and 
	 * outer classes can access even each other's private methods and attributes. The same applies to nextNode.
	 */
	public boolean equals(ShowList linkedList) {	
		numIterations = 0;
		try {
			if (head == null && linkedList.head!=null)
				throw new NoSuchElementException("Unable to check if elements are equal because one element is invalid.");
			else {
				if (head == null && linkedList.head == null)
					return true;
				ShowNode currentNode = head;
				ShowNode node = linkedList.head;
				while (currentNode != null && node != null) {
					numIterations++;
					if (!node.equals(currentNode)) {
						return false;
					}
					currentNode = currentNode.getNextNode();
					node = node.getNextNode();
				}
				currentNode = null;
				node = null;
				return true;
			}
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}	
	}

	/**
	 * Getter for head
	 * @return head
	 */
	public ShowNode getHead() {
		return head;
	}

	/**
	 * Getter for size
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Converts linked list into printable information
	 */
	public String toString() {
		return (head.toString() + "\n\nNumber of Iterations: " + numIterations + "\n");
	}

	
	/**
	 * ShowNode class is used to create and manipulate node values. This class is necessary to be able to make the linked list class
	 */
	public class ShowNode implements Cloneable{
		private ShowNode nextNode;
		private TVShow nodeData;


		/**Default Constructor*/
		private ShowNode() {
			nodeData = null;
			nextNode = null;
		}

		/**
		 * Parameterized Constructor
		 * @param TVShow nodeData
		 * @param ShowNode nextNode
		 */
		private ShowNode(TVShow nodeData, ShowNode nextNode) {
			this.nodeData = nodeData;		
			this.nextNode = nextNode;
		}

		/**
		 * Copy Constructor
		 * @param node : node to  be copied of type ShowNode 
		 */
		private ShowNode(ShowNode node) {
			if (node==null) {
				nodeData = null;
				nextNode = null;
			}
			else {
				nodeData = node.nodeData;
				nextNode = node.nextNode;
			}
		}

		/**
		 * Converts node object to a printable string
		 * @return String of node attributes
		 */
		public String toString() {
			return (getNodeData() +  "\n" + getNextNode());
		}

		/**
		 * Checks if two node objects are equal
		 * @return boolean showing equality
		 */
		public boolean equals(Object someNode) {	
			if (someNode.getClass()!=getClass() || someNode == null)
				return false;
			else {
				ShowNode node = (ShowNode) someNode;
				if (node.getNodeData() == null) {
					return false;
				}
				else if (node.nextNode != null) {
					return (node.getNodeData().equals(getNodeData()) && node.getNextNode().equals(getNextNode()));
				}
				else {
					return (node.getNodeData().equals(getNodeData()) && getNextNode() == null);
				}
			}
		}

		/**
		 * Creates a clone of a given node
		 * @return ShowNode nodeCopy
		 */
		public ShowNode clone() {	
			return new ShowNode(this);
		}

		//Getters
		/**
		 * Getter for object stored in node (some show of type TVShow)
		 * @return TVShow nodeData
		 */
		public TVShow getNodeData() { 
			return nodeData; 
		}
		/**
		 * Getter for object stored in next node (some show of type ShowNode)
		 * @return ShowNode nextNode
		 */
		public ShowNode getNextNode() {
			return nextNode;
		}

		//Setters
		/**
		 * Sets next node to node object from parameter
		 * @param ShowNode nextNode
		 */
		private void setNextNode(ShowNode nextNode) {
			this.nextNode = nextNode;
		}
		/**
		 * Sets current node to node object from parameter
		 * @param TVShow nodeData
		 */
		private void setNodeData(TVShow nodeData) {
			this.nodeData = nodeData;
		}		
	}
}
