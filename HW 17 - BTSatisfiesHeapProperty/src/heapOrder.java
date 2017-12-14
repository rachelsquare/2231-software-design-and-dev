import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class heapOrder {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private heapOrder() {
    }

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures
     *
     *          <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     *          </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        boolean heapO = true;
        if (t.size() > 1) {
            BinaryTree<Integer> lt = t.newInstance();
            BinaryTree<Integer> rt = t.newInstance();
            int root = t.disassemble(lt, rt);
            if (lt.size() > 0) {
                if (root > lt.root()) {
                    heapO = false;
                } else {
                    satisfiesHeapOrdering(lt);
                }
            }
            if (heapO && rt.size() > 0) {
                if (root > rt.root()) {
                    heapO = false;
                } else {
                    satisfiesHeapOrdering(rt);
                }
            }
            t.assemble(root, lt, rt);
        }
        return heapO;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        BinaryTree<Integer> l = new BinaryTree1<>();
        BinaryTree<Integer> r = new BinaryTree1<>();

        BinaryTree<Integer> ll = new BinaryTree1<>();
        BinaryTree<Integer> lr = new BinaryTree1<>();
        BinaryTree<Integer> rl = new BinaryTree1<>();
        BinaryTree<Integer> rr = new BinaryTree1<>();
        BinaryTree<Integer> t = new BinaryTree1<>();
        l.assemble(4, ll, lr);
        r.assemble(1, rl, rr);
        t.assemble(3, l, r);
        out.println(t);
        out.println(satisfiesHeapOrdering(t));
        out.println(t);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
