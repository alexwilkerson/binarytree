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
    // stubbed
  }

  public void remove(T data) {
    // stubbed
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
