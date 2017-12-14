import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Shiny Patel
 *
 */
public final class SmoothModify2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SmoothModify2() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @requires |s1| >= 1
     * @ensures
     * @return s2 smoothed sequence
     *
     *         <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     *         </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";
        Sequence<Integer> s2 = new Sequence1L<>();
        if (s1.length() > 1) {
            int i = s1.remove(0);
            int j = s1.remove(0);
            s2.add(s2.length(), (i + j) / 2);
            s1.add(0, j);
            s2.append(smooth(s1));
            s1.add(0, i);
        }
        return s2;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        Sequence<Integer> s1 = new Sequence1L<>();
        s1.add(0, 2);
        s1.add(0, 23);
        s1.add(0, 7);
        out.println(s1);
        out.println(smooth(s1));
        out.print(s1);
        out.close();
    }

}