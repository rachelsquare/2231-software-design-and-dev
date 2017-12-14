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
public final class handleExceptions {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private handleExceptions() {
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
        BufferedReader input;
        PrintWriter output;
        try {
            input = new BufferedReader(new FileReader(inFile));
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outFile)));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }
        try {
            String s = input.readLine();
            while (s != null) {
                output.println(s);
                s = input.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading or writing to file");
        }
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }
}
