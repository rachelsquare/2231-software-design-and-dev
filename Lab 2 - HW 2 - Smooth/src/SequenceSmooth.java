import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Shiny Patel
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures
     *
     *          <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     *          </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";
        s2.clear();
        if (s1.length() > 1) {
            int n = s1.remove(1);
            int m = s1.remove(0);
            int a = (m / 2) + (n / 2);
            if (m % 2 != 0 && n % 2 != 0 && m != -n) {
                a++;
            }
            s1.add(0, n);
            smooth(s1, s2);
            s2.add(0, a);
            s1.add(0, m);
        }
    }
//            for (int i = 0; i < s1.length() - 1; i++) {
//                int n = s1.remove(i + 1);
//                int m = s1.remove(i);
//                s1.add(i, m);
//                s1.add(i + 1, n);
//                int a = (m / 2) + (n / 2);
//                if (m % 2 != 0 && n % 2 != 0&&m!=-n) {
//                   a++;
//                }
//                s2.add(i, a);
//            }
//        }
}
