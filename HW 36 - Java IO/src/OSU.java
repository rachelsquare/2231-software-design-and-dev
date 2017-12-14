import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class OSU {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private OSU() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        String inFile = args[0];
        String outFile = args[1];
        SimpleReader in = new SimpleReader1L(inFile);
        SimpleWriter out = new SimpleWriter1L(outFile);
        while (in.atEOS()) {
            String oneLine = in.nextLine();
            out.println(oneLine);
        }
        in.close();
        out.close();
    }

}
