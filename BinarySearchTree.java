import java.lang.Comparable;

// This class extends the BinaryTree class. Since the methods
// implemented below require comparisons, the generic type
// extends the Comparable type. Otherwise, all constructors
// are simply inherited from the BinaryTree class.
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

  // This insert method traverses the tree using the compareTo()
  // method to determine where on the tree the new node
  // should be inserted so as to preserve the structure
  // of the BST. If a duplicate entry is found, the method
  // returns without doing anything.
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

  // The remove method was broken up into four separate methods
  // because of the complexity of removing a node. The method uses
  // a loop to intelligently traverse the tree until a match is
  // found. When a match is found, a number of things can happen:
  // case 1: the node has no child nodes
  //   * in this case, the parent's connection to the node is made null
  // case 2: the node has one child node
  //   * in this case, the parents' connection to the node is changed
  //   * to the child's connection to its child.
  // case 3: the node has two children
  //   * in this case, a loop iterates through the nodes descendants.
  //   * first, the right subtree is selected and a successor for the
  //   * removed node is found once a node is found with no left children.
  //   * the deleted node is then replaced with the successor node and the
  //   * old successor node is deleted.
  //
  // these methods are listed below as:
  // noChildren(), oneChild(), and twoChildren()
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
          currentNode.setData(twoChildren(currentNode));
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

  // for information about this method, see comment above remove() method.
  public T twoChildren(BinaryNode<T> currentNode) {
    BinaryNode<T> successorNode = currentNode.getRightNode();
    BinaryNode<T> parentOfSuccessorNode = currentNode;
    while (successorNode.getLeftNode() != null) {
      parentOfSuccessorNode = successorNode;
      successorNode = parentOfSuccessorNode.getLeftNode();
    }
    T data = successorNode.getData();
    // case: node has no children
    if (successorNode.isLeaf()) {
      if (parentOfSuccessorNode.getRightNode() == successorNode) parentOfSuccessorNode.setRightNode(null);
      else parentOfSuccessorNode.setLeftNode(null);
    }
    // case: node has one child
    else if (parentOfSuccessorNode.getLeftNode() == successorNode) parentOfSuccessorNode.setLeftNode(successorNode.getRightNode());
    else parentOfSuccessorNode.setRightNode(successorNode.getRightNode());
    return data;
  }

  // for information about this method, see comment above remove() method.
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

  // for information about this method, see comment above remove() method.
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

  // The contains() method traverses the tree in the most efficient
  // way by comparing the data with the current node and travelling
  // in the direction of that node until a match is or is not found.
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

}
