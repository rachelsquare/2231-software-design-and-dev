import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class throwException {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private throwException() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String inFile = args[0];
        String outFile = args[1];
        BufferedReader input = new BufferedReader(new FileReader(inFile));
        PrintWriter output = new PrintWriter(
                new BufferedWriter(new FileWriter(outFile)));
        String s = input.readLine();
        while (s != null) {
            output.println(s);
            s = input.readLine();
        }
        input.close();
        output.close();
    }
}
