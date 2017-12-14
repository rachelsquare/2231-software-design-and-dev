import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Put your name here
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires [<"INSTRUCTION"> is a proper prefix of tokens]
     * @ensures
     *
     *          <pre>
     * if [an instruction string is a proper prefix of #tokens] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     *          </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";
        Reporter.assertElseFatalError(tokens.dequeue().equals("INSTRUCTION"),
                "Invalid token");
        String sName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(sName),
                "Invalid identifier");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "Invalid token");
        body.parseBlock(tokens);
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Invalid token");
        String eName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(eName),
                "Invalid identifier");
        Reporter.assertElseFatalError(sName.equals(eName),
                "More than one identifier used as instruction name.");
        return eName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        Reporter.assertElseFatalError(tokens.dequeue().equals("PROGRAM"),
                "Invalid token");
        String sName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(sName),
                "Invalid identifier");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "Invalid token");
        Map<String, Statement> ctxt = this.newContext();
        while (!tokens.front().equals("BEGIN")) {
            Statement body = this.newBody();
            String instr = parseInstruction(tokens, body);
            Reporter.assertElseFatalError(!Tokenizer.isKeyword(instr),
                    "Identifier cannot be a keyword of the language.");
            Reporter.assertElseFatalError(!ctxt.hasKey(instr),
                    "Duplicate identifier used as instruction name.");
            ctxt.add(instr, body);
        }
        Reporter.assertElseFatalError(tokens.dequeue().equals("BEGIN"),
                "Invalid token");
        Statement block = this.newBody();
        block.parseBlock(tokens);
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Invalid token");
        String eName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(eName),
                "Invalid identifier");
        Reporter.assertElseFatalError(sName.equals(eName),
                "More than one identifier used as program name.");
        String endOfInput = tokens.dequeue();
        Reporter.assertElseFatalError(endOfInput.equals(Tokenizer.END_OF_INPUT),
                "Program does not terminate properly.");
        this.replaceName(sName);
        this.replaceContext(ctxt);
        this.replaceBody(block);
    }

    /*
     * Main test method -------------------------------------------------------
     */

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
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
