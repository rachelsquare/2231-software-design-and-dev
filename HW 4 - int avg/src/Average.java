import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Shiny Patel
 *
 */
public final class Average {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Average() {
    }

    /**
     * @param x
     *            first integer
     * @param y
     *            second integer
     * @return integer average of x and y
     **/
    private static int average(int x, int y) {
        int avg = (x / 2) + (y / 2);
        if (x % 2 != 0 && y % 2 != 0 && x != -y) {
            // what if x=y=-1
            avg++;
        }
        return avg;
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
        out.println(average(-1, -1));
        in.close();
        out.close();
    }

}
