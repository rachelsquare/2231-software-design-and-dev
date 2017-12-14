import java.util.Iterator;

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
public final class sizeIterative {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private sizeIterative() {
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {
        int count = 0;
        Iterator it = t.iterator();
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count;
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
        out.println(size(tmp));
        out.println(tmp.size());
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
