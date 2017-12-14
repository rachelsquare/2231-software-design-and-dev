import java.util.Iterator;

import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.tree.Tree;
import components.tree.Tree1;

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
        Iterator it = t.iterator();
        while (it.hasNext()) {
            it.next();
            size++;
        }
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
        Tree<Statement> tn = new Tree1<>();
        Sequence<Tree<Statement>> sn = tn.newSequenceOfTree();
        Statement s = new Statement1();
        tn.assemble(s, sn);
        out.println(tn);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
