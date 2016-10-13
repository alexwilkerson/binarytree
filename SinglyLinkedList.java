import java.util.*;

/***********************************
  * SinglyLinkedList Class
  * Created by: Alex Wilkerson
  * Homework 1
  * Date: 09.18.2016
  ***********************************/
public class SinglyLinkedList<T> implements Iterable<T> {

  protected Node<T> head; // head is empty
  protected Node<T> tail; // tail is last node
  protected int size;

  // constructor
  public SinglyLinkedList() {
    this.head = new Node<T>(null);
    this.size = 0;
  }

  // add method takes will insert a new node at
  // the end of the linked list
  // if the list is empty, the head will point
  // to the new node.
  public void add(T element) {
    Node<T> node = new Node<T>(element);

    if (this.size == 0) {
      this.head.setNextNode(node);
    } else {
      this.tail.setNextNode(node);
    }
    this.tail = node;
    this.size++;
  } 

  public Node<T> getTail() {
    return this.tail;
  }

  // insertAt iterates through the linked list
  // and inserts the new element at the given index
  public void insertAt(T data, int index) {
    if (index > this.size || index < 0) {
      throw new IndexOutOfBoundsException("cannot insert at this index. out of bounds.");
    }
    Node<T> newNode = new Node<T>(data);
    Node<T> tempNode = this.head;
    for (int i = 0; i < index; i++) {
      tempNode = tempNode.getNextNode();
    }
    newNode.setNextNode(tempNode.getNextNode());
    tempNode.setNextNode(newNode);
    if (index == this.size) this.tail = newNode;
    this.size++;
  }

  // this method iterates through the list and if it 
  // finds a match, it sets the next node of the previous
  // element to the next node of the next node. the
  // matched element will then be removed by the garbage
  // collector.
  public void remove(T data) {
    Node<T> tempNode = this.head;
    for (int i = 0; i < this.size; i++) {
      if (tempNode.getNextNode().getData() == data) {
        tempNode.setNextNode(tempNode.getNextNode().getNextNode());
        this.size--;
        if (i == size) this.tail = tempNode;
        return;
      }
      tempNode = tempNode.getNextNode();
    }
    throw new NoSuchElementException("no element found.");
  }

  // this method removes all pointers to the current linked
  // list so that the garbage collection deletes the list
  // and all variables are reset to their beginning values.
  public void clear() {
    this.head.setNextNode(null);
    this.tail = null;
    size = 0;
  }

  // this method simply returns true if the size of the array
  // is 0 and false if it is more than 0.
  public boolean isEmpty() {
    return (this.size == 0) ? true : false;
  }

  // return size of linked list.
  public int size() {
    return this.size;
  }

  // returns the value of the element at a given 
  // distance from beginning of the linked list.
  // counting starts at 0.
  public T getNthFromFirst(int n) {
    if (n > size - 1 || n < 0) throw new IndexOutOfBoundsException("out of bounds.");
    if (n == size-1) return this.tail.getData(); // added for efficiency
    Node<T> tempNode = this.head;
    for(int i = 0; i < n; i++) {
      tempNode = tempNode.getNextNode();
    }
    return tempNode.getNextNode().getData();
  }

  // returns the value of the element at a given
  // distance from the end of the linked list.
  // counting starts at 0.
  public T getNthFromLast(int n) {
    if (n > size - 1 || n < 0) throw new IndexOutOfBoundsException("out of bounds.");
    if (n == 0) return this.tail.getData(); // added for efficiency
    Node<T> tempNode = this.head;
    for(int i = 0; i < size-1-n; i++) {
      tempNode = tempNode.getNextNode();
    }
    return tempNode.getNextNode().getData();
  }

  // required by the Iterable interface.
  // returns an iterator of the linked list.
  @Override
  public SinglyLinkedListIterator iterator() {
    return new SinglyLinkedListIterator();
  }

  // used an iterator to go over the elements and make
  // a string that looks similar to the java toString from
  // java.util.Arrays
  public String toString() {
    if (size < 1) return "[]";
    String returnString = "[";
    for (T element : this) {
      returnString += element + ", ";
    }
    returnString = returnString.substring(0, returnString.length()-2);
    returnString += "]";
    return returnString;
  }

  /***********************************
   * Node class
   ***********************************/
  // generic node class 
  protected static class Node<T> {
    protected T data;
    protected Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }

    // sets node data
    public void setData(T data) {
      this.data = data;
    }

    // gets node data
    public T getData() {
      return this.data;
    }

    // returns the next linked node
    public Node<T> getNextNode() {
      return next;
    }

    // sets the next linked node
    public void setNextNode(Node<T> next) {
      this.next = next;
    }
  } 

  /***********************************
   * SinglyLinkedListIterator
   ***********************************/
  private class SinglyLinkedListIterator implements Iterator<T> {
    private Node<T> currentNode;

    // constructor. begin the list iterator at head
    public SinglyLinkedListIterator() {
      currentNode = head;
    }

    // hesNext returns true if there is a next element
    // in the list. false otherwise.
    @Override
    public boolean hasNext() {
      return (currentNode.getNextNode() == null) ? false : true;
    }

    // next returns the element from the next node,
    // then makes the currentNode the next node.
    @Override
    public T next() {
      if (currentNode.getNextNode() == null) {
        throw new NoSuchElementException("iterator has reached the end");
      }
      T returnValue = currentNode.getNextNode().getData();
      currentNode = currentNode.getNextNode();
      return returnValue;
    }

    // not supported.
    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove operation is not supported by this iterator");
    }

  }

}
