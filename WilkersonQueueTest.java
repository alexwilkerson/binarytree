public class WilkersonQueueTest {
  public static void main(String[] args) {
    WilkersonQueue<Integer> queue = new WilkersonQueue<Integer>();

    MyStack<String> stack = new MyStack<String>();
    System.out.println(stack);
    stack.push("A");
    System.out.println(stack);
    stack.push("B");
    System.out.println(stack);
    stack.push("C");
    System.out.println(stack);
    stack.push("D");
    System.out.println(stack);
    stack.pop();
    System.out.println(stack);
    stack.pop();
    System.out.println(stack);
    stack.pop();
    System.out.println(stack);
    stack.pop();
    System.out.println(stack);
    stack.push("A");
    System.out.println(stack);
    stack.push("B");
    System.out.println(stack);

    String nullSymbol = "0";
    String[] seq = {"A", "B", "C", "D", "0", "0", "G", "H", "I", "0", "0", "0", "0","0", "J"};
    BinaryTree<String> tree = new BinaryTree<String>(seq, nullSymbol);
    System.out.println(tree.inOrderTraverse());

  }
}
