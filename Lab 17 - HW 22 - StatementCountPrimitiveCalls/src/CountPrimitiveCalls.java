import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures
     *
     *          <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     *          </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                for (int l = 0; l < s.lengthOfBlock(); l++) {
                    Statement ns = s.removeFromBlock(l);
                    count += countOfPrimitiveCalls(ns);
                    s.addToBlock(l, ns);
                }
                break;
            }
            case IF: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleIf(ns);
                count += countOfPrimitiveCalls(ns);
                s.assembleIf(c, ns);
                break;
            }
            case IF_ELSE: {
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition c = s.disassembleIfElse(s1, s2);
                count += countOfPrimitiveCalls(s1) + countOfPrimitiveCalls(s2);
                s.assembleIfElse(c, s1, s2);
                break;
            }
            case WHILE: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleWhile(ns);
                count += countOfPrimitiveCalls(ns);
                s.assembleWhile(c, ns);
                break;
            }
            case CALL: {
                String str = s.disassembleCall();
                if (str.equals("turnleft") || str.equals("turnright")
                        || str.equals("skip") || str.equals("infect")
                        || str.equals("move")) {
                    count++;
                }
                s.assembleCall(str);
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }
}
