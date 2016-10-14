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
    /*
    if (this.root == null) return;
    BinaryNode<T> currentNode = this.root;
    while(true) {
      int compare = data.compareTo(currentNode.getData());
    }*/
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
