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
public final class TreeToString {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TreeToString() {
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        String tree = "()";
        if (t.size() > 0) {
            BinaryTree<T> l = new BinaryTree1<>();
            BinaryTree<T> r = new BinaryTree1<>();
            T root = t.disassemble(l, r);
            tree = root + "(" + treeToString(l) + treeToString(r) + ")";
            t.assemble(root, l, r);
        }
        return tree;
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
        out.println(tmp.toString());
        out.println(treeToString(tmp));
        out.println(tmp);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
