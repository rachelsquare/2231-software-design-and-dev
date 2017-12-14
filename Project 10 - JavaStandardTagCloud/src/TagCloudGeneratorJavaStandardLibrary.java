import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Put a short phrase describing the program here.
 *
 * @author Pei-yuan Tang and Shiny Patel
 *
 */
public final class TagCloudGeneratorJavaStandardLibrary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGeneratorJavaStandardLibrary() {
    }

    /**
     * The code from software I Generates the set of characters in the given
     * {@code String} into the given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            strSet.add(ch);
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures
     *
     *          <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     *          </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        char ch = text.charAt(position);
        int endingPos = position;
        if (separators.contains(ch)) {
            while (endingPos < text.length()
                    && separators.contains(text.charAt(endingPos))) {
                endingPos++;
            }
        } else {
            while (endingPos < text.length()
                    && !separators.contains(text.charAt(endingPos))) {
                endingPos++;
            }
        }
        String result = text.substring(position, endingPos);
        return result;

    }

    /**
     *
     * @param line
     *            single line of the input file
     * @param wordCounter
     *            the map stores (word, counts) pair
     * @param separatorSet
     *            the set that stores all word separator.
     *
     *            This method is set to store all words and its counts in a
     *            single line of the input file.
     */
    private static void processOneLine(String line,
            Map<String, Integer> wordCounter, Set<Character> separatorSet) {

        int pos = 0; // from the the start of the line.
        while (pos < line.length()) {
            String token = nextWordOrSeparator(line, pos, separatorSet);
            if (!separatorSet.contains(token.charAt(0))) {
                String word = token.toLowerCase(); // transfer the word to lower case.
                if (wordCounter.containsKey(word)) {
                    int count = wordCounter.remove(word);
                    count++; // add the count by one if the word has already existed.

                    wordCounter.put(word, count);
                } else {

                    int count = 1; // store the new key(the word)
                    wordCounter.put(word, count);
                }
            }
            pos += token.length();
        }

    }

    /**
     * This method is used to store the word and its count of every line from
     * the input file into the map.
     *
     * @param wordCounter
     *            the map stores (word, count) pair
     * @param separatorSet
     *            the set that stores all word separators.
     * @param input
     *            the scanner of the input file
     * @throws IOException
     */
    private static void processEveryline(Map<String, Integer> wordCounter,
            Set<Character> separatorSet, BufferedReader input) {
        String line;
        try {
            line = input.readLine();
            while (line != null) {
                processOneLine(line, wordCounter, separatorSet);
                line = input.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the the input file");
        }

    }

    /**
     * Comparator to sort word pairs (word, count) in descending order of their
     * counts. If the counts of word pairs are the same, we deal with the word
     * in alphabetical order.
     */
    private static class MapPairComparator1
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            int result = o2.getValue().compareTo(o1.getValue());
            if (result == 0) {
                // In ascending order
                result = (o1.getKey().compareTo(o2.getKey()));
            }
            return result;
        }
    }

    /**
     * Comparator to sort word pairs (word, count) in ascending alphabetic
     * order. If they have the same alphabetical order, we deal with them in the
     * descending order based on the count
     *
     */
    private static class MapPairComparator2
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            int result = o1.getKey().compareTo(o2.getKey());
            if (result == 0) {
                // count is from large to small.
                return o2.getValue().compareTo(o1.getValue());
            }
            return result;

        }
    }

    /**
     * output the compatible font size.
     *
     * @param count
     *            The input count of the word
     *
     * @param maxCount
     *            The input max count of the input word list.
     *
     * @param minCount
     *            The input min count of the input word list.
     * @return fontSize The font size of the word based on the count.
     *
     */
    public static int fontSize(int count, int maxCount, int minCount) {
        int fontSize;
        final int maxFont = 48;
        final int minFont = 11;
        int difference = maxCount - minCount;
        if (difference != 0) {
            fontSize = (int) Math
                    .round((((maxFont - minFont) / (double) difference))
                            * (count - minCount) + minFont);
        } else {
            fontSize = (maxFont + minFont) / 2;
        }
        return fontSize;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        /*
         * Create a string to store all separators, and then put every entry
         * into a set. Generated from Software I.
         */
        final String separatorStr = " \t, _.<>{}[]():;/?!%^-~`''\"&*#|+=";
        Set<Character> separatorSet = new HashSet<Character>();
        generateElements(separatorStr, separatorSet);
        /*
         * Use map and queue to store the word and its counts.
         */

        Map<String, Integer> wordCounter = new HashMap<String, Integer>();

        //read the file name from scanner.
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the name of the input file: ");
        String fileName = in.nextLine();

        // ask for the number of the words in the resulted file.
        // read the input file
        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(fileName));
            processEveryline(wordCounter, separatorSet, input);

        } catch (IOException e) {
            System.err.println("Error opening the input file");
            return;
        }

        try {
            input.close();
        } catch (IOException e1) {
            System.err.println("Error closing the input file");
        }
        // ask for the number of the words in the resulted file.
        System.out.print("Please enter the number of words generated: ");
        int numberN = in.nextInt();
        in.nextLine(); // java bug

        // sorting all the entries
        Comparator<Map.Entry<String, Integer>> order1 = new MapPairComparator1();

        // Create a sorted set to store according to the count of the mapping.
        Set<Entry<String, Integer>> entryset = wordCounter.entrySet();
        List<Entry<String, Integer>> ss1 = new ArrayList<Entry<String, Integer>>();
        Iterator<Entry<String, Integer>> it = entryset.iterator();

        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            ss1.add(entry);
        }

        ss1.sort(order1);
        int maxCount = ss1.get(0).getValue(); // the max count
        int minCount = ss1.get(ss1.size() - 1).getValue(); // the min count
        //sort the list of mappings according to the alphabeticall order.

        Comparator<Map.Entry<String, Integer>> order2 = new MapPairComparator2();

        // if numberN>size, then let numberN be the size.
        if (numberN > ss1.size()) {
            numberN = ss1.size();
        }

        // factor out top 100 counts
        while (ss1.size() > numberN) {
            ss1.remove(ss1.size() - 1);
        }
        ss1.sort(order2);
        // if N is larger than the size of the sets, change the amount of generated words to be the size of the set.

        // output the file
        System.out.print("Please enter the name of the output file: ");
        String outputFile = in.nextLine();
        PrintWriter output;
        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException e) {
            System.err.print("Error opening the output file");
            return;
        }

        output.println("<html><head><title>");
        output.print("Top " + numberN + " words in " + fileName);
        output.print("</title>");
        output.print("</html>");
        output.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        output.print("</head><body><h2>");
        output.print("Top " + numberN + " words in " + fileName);
        output.println("<hr><div class=\"cdiv\"><p class=\"cbox\">");

        /*
         * print the word list.
         */

        for (int i = 0; i < numberN; i++) {
            Map.Entry<String, Integer> pr = ss1.remove(0);
            String word = pr.getKey();
            int count = pr.getValue();
            int font = fontSize(count, maxCount, minCount);
            output.println("<span style=\"cursor:default\" class=\"f" + font
                    + "\" title=\"count: " + count + "\">" + word + "</span>");
        }

        output.println("</p></div></body></html>");
        in.close(); //close the scanner
        output.close(); // close the output stream.

    }

}
