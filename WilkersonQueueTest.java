public class WilkersonQueueTest {
  public static void main(String[] args) {
    WilkersonQueue<Integer> queue = new WilkersonQueue<Integer>();

 
    String nullSymbol = "0";
    String[] seq = {"A", "B", "C", "D", "0", "0", "G", "H", "I", "0", "0", "0", "0","0", "J"};
    BinaryTree<String> tree = new BinaryTree<String>(seq, nullSymbol);
    System.out.println(tree.inOrderTraverse());

  }
}
