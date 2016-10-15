import java.lang.Comparable;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {
  public BinarySearchTree() {
    super();
  }

  public BinarySearchTree(T[] seq){
    super(seq);
  }

  public BinarySearchTree(T[] seq, T nullSymbol){
    super(seq, nullSymbol);
  }

  public void insert(T data) {
    BinaryNode<T> newNode = new BinaryNode<T>(data);
    if (this.root == null) {
      root = newNode;
    }
    BinaryNode<T> currentNode = this.root;
    while(true) {
      int compare = data.compareTo(currentNode.getData());
      if (compare == 0) return;
      if (compare < 0) {
        if (currentNode.getLeftNode() == null) {
          currentNode.setLeftNode(newNode);
          return;
        }
        currentNode = currentNode.getLeftNode();
      } else if (compare > 0) {
        if (currentNode.getRightNode() == null) {
          currentNode.setRightNode(newNode);
          return;
        }
        currentNode = currentNode.getRightNode();
      }
    }
  }

  public void remove(T data) {
    if (this.root == null || data == null) return;
    BinaryNode<T> parentNode = null;
    BinaryNode<T> currentNode = this.root;
    while(currentNode != null) {
      int compare = data.compareTo(currentNode.getData());
      if (compare == 0) { // match found
        // case: no children
        if (currentNode.isLeaf()) { 
          parentNode = noChildren(parentNode, currentNode);
          return;
        }
        // case: one child
        else if (currentNode.getLeftNode() == null || currentNode.getRightNode() == null) {
          parentNode = oneChild(parentNode, currentNode);
          return;
        }
        // case: two children
        else {
          return;
        }
      } else if (compare < 0) {
        parentNode = currentNode;
        currentNode = parentNode.getLeftNode();
      } else if (compare > 0) {
        parentNode = currentNode;
        currentNode = parentNode.getRightNode();
      } else return;
    } // end while loop
  }

  public BinaryNode<T> noChildren(BinaryNode<T> parentNode, BinaryNode<T> currentNode) {
    if (parentNode == null) { // case: root node
      this.root = null;
      return null;
    } else {
      if (parentNode.getLeftNode() == currentNode) parentNode.setLeftNode(null);
      else parentNode.setRightNode(null);
      return parentNode;
    }
  }

  public BinaryNode<T> oneChild(BinaryNode<T> parentNode, BinaryNode<T> currentNode) {
    // case: node has left child
    if (currentNode.getLeftNode() != null) { 
      if (parentNode == null) { // case: root node
        this.root = currentNode.getLeftNode();
        return null;
      } else {
        if (parentNode.getLeftNode() == currentNode) parentNode.setLeftNode(currentNode.getLeftNode());
        else parentNode.setRightNode(currentNode.getLeftNode());
        return parentNode;
      }
    // case: node has right child
    } else {
      if (parentNode == null) { // case: root node
        this.root = currentNode.getRightNode();
        return null;
      } else {
        if (parentNode.getLeftNode() == currentNode) parentNode.setLeftNode(currentNode.getRightNode());
        else parentNode.setRightNode(currentNode.getRightNode());
        return parentNode;
      }
    }
  }

  public boolean contains(T data) {
    BinaryNode<T> currentNode = root;
    while(true) {
      int compare = data.compareTo(currentNode.getData());
      if (compare == 0) return true;
      else if (compare < 0 && currentNode.getLeftNode() != null) {
        currentNode = currentNode.getLeftNode();
      } else if (compare > 0 && currentNode.getRightNode() != null) {
        currentNode = currentNode.getRightNode();
      } else return false;
    }
  }

  /*
  public boolean contains(T data){        
    MyStack<BinaryNode> stack = new MyStack<BinaryNode>();
    BinaryNode currentNode = root;
    while (!stack.isEmpty() || currentNode != null) {
      while (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.getLeftNode();
      }
      currentNode = stack.pop();
      if (data.equals(currentNode.getData())) return true;
      currentNode = currentNode.getRightNode();
    }
    return false;
  } */

  

}
