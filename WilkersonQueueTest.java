public class WilkersonQueueTest {
  public static void main(String[] args) {
		int nullSymbol = 0;
		Integer[] seq = {5, 3, 7, 0, 0, 6};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		System.out.println(tree.contains(6));
                

  }
}
