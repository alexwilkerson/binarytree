import java.util.NoSuchElementException;

public class WilkersonQueue<T> {
  private WilkersonQueueNode<T> bottom = null;
  private WilkersonQueueNode<T> top = null;
  private int size = 0;

  public WilkersonQueue() {

  }

  public void enqueue(T data) {
    WilkersonQueueNode<T> newNode = new WilkersonQueueNode<T>(data);
    if (size == 0) {
      this.bottom = newNode;
      this.top = newNode;
    } else if (size == 1) {
      this.top = newNode;
      this.bottom.setPreviousNode(this.top);
      this.top.setNextNode(this.bottom);
    } else {
      this.top.setPreviousNode(newNode);
      newNode.setNextNode(this.top);
      this.top = newNode;
    }
    this.size++;
  }

  public T dequeue() {
    if (size == 0) throw new NoSuchElementException("no element found.");
    T data = this.bottom.getData();
    if (size == 1) {
      this.top = null;
      this.bottom = null;
    } else {
      this.bottom = this.bottom.getPreviousNode();
      this.bottom.setNextNode(null);
    }
    this.size--;
    return data;
  }

  public int getSize() {
    return this.size;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  @Override
  public String toString() {
    if (size == 0) return "[]";
    else {
      WilkersonQueueNode<T> tempNode = this.top;
      String returnString = "[";
      for (int i = 0; i < size; i++) {
        returnString += tempNode.getData() + " ";
        tempNode = tempNode.getNextNode();
      }
      return returnString.trim() + "]";
    }
  }

  protected static class WilkersonQueueNode<T> {
    private T data = null;
    private WilkersonQueueNode<T> previousNode = null;
    private WilkersonQueueNode<T> nextNode = null;

    public WilkersonQueueNode(T data) {
      this.data = data;
    }

    public void setNextNode(WilkersonQueueNode<T> node) {
      this.nextNode = node;
    }

    public WilkersonQueueNode<T> getNextNode() {
      return this.nextNode;
    }

    public void setPreviousNode(WilkersonQueueNode<T> node) {
      this.previousNode = node;
    }

    public WilkersonQueueNode<T> getPreviousNode() {
      return this.previousNode;
    }

    public T getData() {
      return this.data;
    }
  }
}
