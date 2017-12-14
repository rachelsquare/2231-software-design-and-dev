import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [<"IF"> is a proper prefix of tokens]
     * @ensures
     *
     *          <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     *          </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";
        String sIF = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isKeyword(sIF),
                "Invalid keyword");
        String cString = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(cString),
                "The condition is not valid.");
        Condition c = parseCondition(cString);
        Reporter.assertElseFatalError(tokens.dequeue().equals("THEN"),
                "Invalid token");
        Statement ifBlock = s.newInstance();
        ifBlock.parseBlock(tokens);
        if (tokens.front().equals("ELSE")) {
            Reporter.assertElseFatalError(tokens.dequeue().equals("ELSE"),
                    "Invalid token");
            Statement elseBlock = s.newInstance();
            elseBlock.parseBlock(tokens);
            s.assembleIfElse(c, ifBlock, elseBlock);
        } else {
            s.assembleIf(c, ifBlock);
        }
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Invalid token");
        String eIF = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isKeyword(eIF),
                "Invalid keyword");
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [<"WHILE"> is a proper prefix of tokens]
     * @ensures
     *
     *          <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     *          </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";
        String sWHILE = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isKeyword(sWHILE),
                "Invalid keyword");
        String cString = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(cString),
                "The condition is not valid.");
        Condition c = parseCondition(cString);
        Reporter.assertElseFatalError(tokens.dequeue().equals("DO"),
                "Invalid token");
        Statement block = s.newInstance();
        block.parseBlock(tokens);
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Invalid token");
        String eWHILE = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isKeyword(eWHILE),
                "Invalid keyword");
        s.assembleWhile(c, block);
        //System.out.println(s);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures
     *
     *          <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     *          </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";
        String instr = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(instr),
                "Invalid identifier");
        s.assembleCall(instr);
        //System.out.println(s);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        String kind = tokens.front();
        Reporter.assertElseFatalError(
                Tokenizer.isIdentifier(kind) || Tokenizer.isKeyword(kind),
                "Invalid identifier or keyword");
        if (kind.equals("IF")) {
            parseIf(tokens, this);
        } else if (kind.equals("WHILE")) {
            parseWhile(tokens, this);
        } else {
            parseCall(tokens, this);
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        // cannot do this.addToBlock(this.lengthOfBlock(),this)
        // cannot add this to this, it creates an empty block
        Statement block = this.newInstance();
        // System.out.println(this);
        while (!tokens.front().equals("END")) {
            this.parse(tokens);
            // System.out.println(block);
            block.addToBlock(block.lengthOfBlock(), this);
            //   System.out.println(block);
        }
        // System.out.println(this);
        this.transferFrom(block);
        // System.out.println(this);
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
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
