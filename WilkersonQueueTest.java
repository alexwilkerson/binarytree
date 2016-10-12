public class WilkersonQueueTest {
  public static void main(String[] args) {
    WilkersonQueue<Integer> queue = new WilkersonQueue<Integer>();

    System.out.println(queue);

    queue.enqueue(1);
    queue.enqueue(4);
    queue.enqueue(3);
    queue.enqueue(0);

    System.out.println(queue);

    System.out.println(queue.dequeue());

    System.out.println(queue);

  }
}
