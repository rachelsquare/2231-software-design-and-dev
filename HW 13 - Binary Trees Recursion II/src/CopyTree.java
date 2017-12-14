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
public final class CopyTree {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyTree() {
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> tCopy = new BinaryTree1<>();
        if (t.size() != 0) {
            BinaryTree<Integer> l = new BinaryTree1<>();
            BinaryTree<Integer> r = new BinaryTree1<>();
            int root = t.disassemble(l, r);
            tCopy.assemble(root, copy(l), copy(r));
            t.assemble(root, l, r);
        }
        return tCopy;
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
        BinaryTree<Integer> rr = new BinaryTree1<>();
        BinaryTree<Integer> tmp = new BinaryTree1<>();
        l.assemble(3, ll, rr);
        tmp.assemble(4, l, r);
        out.println(tmp);
        out.println(copy(tmp));
        out.println(tmp);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
