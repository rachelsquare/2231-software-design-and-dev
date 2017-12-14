import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel;
import components.statement.StatementKernel.Condition;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class negateIF_ELSE {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private negateIF_ELSE() {
    }

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures
     *
     *          <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     *          </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {
                for (int l = 0; l < s.lengthOfBlock(); l++) {
                    Statement ns = s.removeFromBlock(l);
                    simplifyIfElse(ns);
                    s.addToBlock(l, ns);
                }
                break;
            }
            case IF: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleIf(ns);
                simplifyIfElse(ns);
                s.assembleIf(c, ns);
                break;
            }
            case IF_ELSE: {
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                boolean negate = false;
                Condition c = s.disassembleIfElse(s1, s2);
                if (c.equals(Condition.NEXT_IS_NOT_EMPTY)) {
                    c = StatementKernel.Condition.valueOf("NEXT_IS_EMPTY");
                    negate = true;
                } else if (c.equals(Condition.NEXT_IS_NOT_ENEMY)) {
                    c = StatementKernel.Condition.valueOf("NEXT_IS_ENEMY");
                    negate = true;
                } else if (c.equals(Condition.NEXT_IS_NOT_FRIEND)) {
                    c = StatementKernel.Condition.valueOf("NEXT_IS_FRIEND");
                    negate = true;
                } else if (c.equals(Condition.NEXT_IS_NOT_WALL)) {
                    c = StatementKernel.Condition.valueOf("NEXT_IS_WALL");
                    negate = true;
                }
                simplifyIfElse(s2);
                simplifyIfElse(s1);
                if (negate) {
                    s.assembleIfElse(c, s2, s1);
                } else {
                    s.assembleIfElse(c, s1, s2);
                }
                break;
            }
            case WHILE: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleWhile(ns);
                simplifyIfElse(ns);
                s.assembleWhile(c, ns);
                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
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
