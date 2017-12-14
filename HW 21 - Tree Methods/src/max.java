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
public final class max {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private max() {
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures
     *
     *          <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     *          </pre>
     */
    public static int max(Tree<Integer> t) {
        int max = t.root();
        Sequence<Tree<Integer>> children = t.newSequenceOfTree();
        int root = t.disassemble(children);
        for (int l = 0; l < children.length(); l++) {
            Tree<Integer> tmp = children.remove(l);
            if (tmp.size() > 0) {
                int cmp = max(tmp);
                if (max < cmp) {
                    max = cmp;
                }
            }
            children.add(l, tmp);
        }
        t.assemble(root, children);
        return max;
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
        sn.add(0, tmp1);
        sn.add(1, tmp2);
        tn.assemble(5, sn);
        out.println(tn);
        out.println(max(tn));
        out.println(tn);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
