import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class renameStatement {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private renameStatement() {
    }

    /**
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures
     *
     *          <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     *          </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {
        switch (s.kind()) {
            case BLOCK: {
                for (int l = 0; l < s.lengthOfBlock(); l++) {
                    Statement ns = s.removeFromBlock(l);
                    renameInstruction(ns, oldName, newName);
                    s.addToBlock(l, ns);
                }
                break;
            }
            case IF: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleIf(ns);
                renameInstruction(ns, oldName, newName);
                s.assembleIf(c, ns);
                break;
            }
            case IF_ELSE: {
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition c = s.disassembleIfElse(s1, s2);
                renameInstruction(s1, oldName, newName);
                renameInstruction(s2, oldName, newName);
                s.assembleIfElse(c, s2, s1);
                break;
            }
            case WHILE: {
                Statement ns = s.newInstance();
                Condition c = s.disassembleWhile(ns);
                renameInstruction(ns, oldName, newName);
                s.assembleWhile(c, ns);
                break;
            }
            case CALL: {
                String str = s.disassembleCall();
                if (str.equals(oldName)) {
                    str = newName;
                }
                s.assembleCall(str);
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
