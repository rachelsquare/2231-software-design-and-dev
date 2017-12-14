import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.tree.Tree;
import components.tree.Tree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class size {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private size() {
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {
        int size = 0;
        T root = t.root();
        Sequence<Tree<T>> children = t.newSequenceOfTree();
        if (t.height() > 0) {
            root = t.disassemble(children);
            size++;
            for (int l = 0; l < children.length(); l++) {
                Tree<T> tmp = children.remove(l);
                size += size(tmp);
                children.add(l, tmp);
            }
        }
        t.assemble(root, children);
        return size;
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
        Tree<Integer> tn = new Tree1<>();
        Sequence<Tree<Integer>> sn = tn.newSequenceOfTree();
        Tree<Integer> tmp1 = new Tree1<>();
        Sequence<Tree<Integer>> sn1 = tmp1.newSequenceOfTree();
        tmp1.assemble(3, sn1);
        Tree<Integer> tmp2 = new Tree1<>();
        Sequence<Tree<Integer>> sn2 = tmp1.newSequenceOfTree();
        tmp2.assemble(3, sn2);
        sn.add(0, tmp1);
        sn.add(1, tmp2);
        tn.assemble(2, sn);
        out.println(tn);
        out.println(size(tn));
        out.println(tn);
        out.println(tn.size());
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
