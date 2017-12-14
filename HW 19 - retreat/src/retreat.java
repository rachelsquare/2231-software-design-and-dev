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
public final class retreat {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private retreat() {
    }

    /**
     * Retreats the position in {@code this} by one.
     *
     * @updates this
     * @requires this.left /= <>
     * @ensures
     *
     *          <pre>
     * this.left * this.right = #this.left * #this.right  and
     * |this.left| = |#this.left| - 1
     *          </pre>
     */
//    public void retreat() {
//        int ll = this.leftLength();
//        this.moveToStart();
//        while (this.leftLength() != ll - 1) {
//            this.advance();
//        }
//    }

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
