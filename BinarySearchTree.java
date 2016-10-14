public class BinarySearchTree<T> extends BinaryTree<T> {
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
  }

}
