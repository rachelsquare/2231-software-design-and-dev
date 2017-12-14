import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class UsingClockArithmetic {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private UsingClockArithmetic() {
    }

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures
     *
     *          <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     *          </pre>
     */
    public static int mod(int a, int b) {
        int result = a % b;
        if (result < 0) {
            result += b;
        }
        return result;
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
        out.print(mod(-194, 10));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
