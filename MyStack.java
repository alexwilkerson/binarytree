/***********************************
  * MyStack Class
  * Created by: Alex Wilkerson
  * Homework 1
  * Date: 09.18.2016
  ***********************************/
// this class extends the SinglyLinkedList
// and adds two additional functions. the
// homework required implementation of the
// isEmpty class, but it is not implemented
// here because it is redundant.
public class MyStack<T> extends SinglyLinkedList<T> {

  public MyStack() {
    super();
  }

  // pop iterates through the list and when the
  // function reaches the second to last node
  // returns the value of the last node, then
  // removes the reference.
  public T pop() {
    if (!this.isEmpty()) {
      T returnValue = this.tail.getData();
      Node<T> tempNode = this.head;
      for (int i = 0; i < this.size-1; i++) {
        tempNode = tempNode.getNextNode();
      }
      tempNode.setNextNode(null);
      this.tail = tempNode;
      this.size--;
      return returnValue;
    } else {
      throw new NullPointerException("array empty. nothing to pop.");
    }
  }

  public T peek() {
    return this.getTail().getData();
  }

  // push is simply a different name for add.
  public void push (T value) {
    this.add(value);
  }

  // isEmpty() is not implemented here because it
  // is inherited from the SinglyLinkedList class.

}
