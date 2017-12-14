import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
public final class height {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private height() {
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        int height = 0;
        if (t.size() > 0) {
            Sequence<Tree<T>> children = t.newSequenceOfTree();
            T root = t.disassemble(children);
            height++;
            Sequence<Integer> store = new Sequence1L<>();
            for (int l = 0; l < children.length(); l++) {
                Tree<T> tmp = children.remove(l);
                store.add(l, height(tmp));
                children.add(l, tmp);
            }
            int max = 0;
            for (int cmp : store) {
                if (cmp > max) {
                    max = cmp;
                }
            }
            height += max;
            t.assemble(root, children);
        }
        return height;
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
        Tree<Integer> tmp3 = new Tree1<>();
        Tree<Integer> tmp4 = new Tree1<>();
        Sequence<Tree<Integer>> sn2 = tmp2.newSequenceOfTree();
        Sequence<Tree<Integer>> sn4 = tmp4.newSequenceOfTree();
        tmp3.assemble(5, sn4);
        tmp2.assemble(3, sn2);
        sn1.add(0, tmp3);
        sn1.add(1, tmp4);
        sn.add(0, tmp1);
        sn.add(1, tmp2);
        tn.assemble(2, sn);
        out.println(tn);
        out.println(height(tn));
        out.println(tn);
        out.println(tn.height());
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
