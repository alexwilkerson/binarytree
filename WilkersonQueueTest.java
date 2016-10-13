public class WilkersonQueueTest {
  public static void main(String[] args) {
    WilkersonQueue<Integer> queue = new WilkersonQueue<Integer>();

 
    String[] seq = {"A", "B", "C", "D", "E", "F", "G", "H"};
    BinaryTree<String> tree = new BinaryTree<String>(seq);
    System.out.println("tree height: " + tree.height());

    System.out.println("tree width: " + tree.width());

  }
}
