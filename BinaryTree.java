import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T>{
	BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes = new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	// This method will handle the null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
						int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1; // finding the left child index from formula 
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2; // finding the right child index from formula
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	// This method will handle the non-null nodes in the iteration of nodes.get(i) in initFromBfsSequence method.
	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){ //need to check if the index falls outdise of the list index
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		return root.height();
	}

        public int width(){
          if (this.root == null) return 0;
          WilkersonQueue<BinaryNode> queue = new WilkersonQueue<BinaryNode>();
          queue.enqueue(this.root);
          queue.enqueue(null);
          int width = 1;
          while (!queue.isEmpty()) {
            BinaryNode node = (BinaryNode) queue.dequeue();
            if (node == null) {
              if (queue.getSize() > 0) {
                if (queue.getSize() > width) width = queue.getSize();
                queue.enqueue(null);
              }
            } else {
              if (node.getLeftNode() != null) queue.enqueue(node.getLeftNode());
              if (node.getRightNode() != null) queue.enqueue(node.getRightNode());
            }
          }
          return width;
        }

        public String breadthFirstTraverse(){
          if (this.root == null) return null;
          WilkersonQueue<BinaryNode> queue = new WilkersonQueue<BinaryNode>();
          String returnString = "";
          queue.enqueue(this.root);
          while (!queue.isEmpty()) {
            BinaryNode node = (BinaryNode) queue.dequeue();
            returnString += node.getData() + " ";
            if (node.getLeftNode() != null) queue.enqueue(node.getLeftNode());
            if (node.getRightNode() != null) queue.enqueue(node.getRightNode());
          }
          return returnString.trim();
        }

	public String preOrderTraverse(){
          if (this.root == null) return null;
          return root.preOrderTraverse().trim();				
        }

        public String postOrderTraverse(){
          if (this.root == null) return null;
          return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
          return root.inOrderTraverse().trim();
        }
        
        protected class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}

		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}


		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		public String postOrderTraverse(){			
                  String returnString = "";
                  MyStack<BinaryNode> stack1 = new MyStack<BinaryNode>();
                  MyStack<BinaryNode> stack2 = new MyStack<BinaryNode>();
                  stack1.push(root);
                  BinaryNode currentNode;
                  while(!stack1.isEmpty()) {
                    currentNode = stack1.pop();
                    if (currentNode.getLeftNode() != null) stack1.push(currentNode.getLeftNode());
                    if (currentNode.getRightNode() != null) stack1.push(currentNode.getRightNode());
                    stack2.push(currentNode);
                  }
                  while(!stack2.isEmpty()) {
                    returnString += stack2.pop().getData() + " ";
                  }
                  return returnString;
		}

		public String inOrderTraverse(){	
                  String returnString = "";
                  MyStack<BinaryNode> stack = new MyStack<BinaryNode>();
                  BinaryNode currentNode = root;
                  while (!stack.isEmpty() || currentNode != null) {
                    while (currentNode != null) {
                      stack.push(currentNode);
                      currentNode = currentNode.getLeftNode();
                    }
                    
                    currentNode = stack.pop();
                    returnString += currentNode.getData() + " ";
                    currentNode = currentNode.getRightNode();
                  }
                  return returnString;
		}
	}
}
