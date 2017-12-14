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
public final class isInTree {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private isInTree() {
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        boolean result = false;
        if (t.size() > 0) {
            BinaryTree<T> l = new BinaryTree1<>();
            BinaryTree<T> r = new BinaryTree1<>();
            T root = t.disassemble(l, r);
            if (root.compareTo(x) == 0) {
                result = true;
            } else if (root.compareTo(x) > 0) {
                result = isInTree(l, x);
            } else {
                result = isInTree(r, x);
            }
            t.assemble(root, l, r);
        }
        return result;
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
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
