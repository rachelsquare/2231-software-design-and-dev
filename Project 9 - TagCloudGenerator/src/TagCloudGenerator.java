import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Create a tag cloud generator of input file.
 *
 * @author Shiny Patel
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Counts the number of repetitions of each word and stores it in pairs.
     *
     * @param tokens
     *            to store pair of words and their corresponding count
     * @param inFile
     *            the input stream
     *
     */
    public static void count(SimpleReader inFile, Map<String, Integer> tokens) {
        assert inFile != null : "Violation of: inFile is not null";
        assert tokens != null : "Violation of: pairs is not null";
        assert inFile.isOpen() : "Violation of: inFile.is_open";
        // read one line
        String oneLine = inFile.nextLine();
        // define and add separators to separator set
        Set<Character> separators = new Set1L<>();
        String sep = "' ,.;!?`~-@#&$%^*/+()=<>|:_][{}0123456789";
        separators.add('"');
        for (int i = 0; i < sep.length(); i++) {
            separators.add(sep.charAt(i));
        }
        // start separation of words from index 0
        int position = 0;
        while (position < oneLine.length()) {
            // method call to get strings of either words or separators
            String word = separate(oneLine, position, separators).toLowerCase();
            // if returned string is not a string of separators, add it in qWords
            if (!separators.contains(word.charAt(0))) {
                int count;
                if (tokens.hasKey(word)) {
                    count = tokens.value(word);
                    count++;
                    tokens.replaceValue(word, count);
                } else {
                    count = 1;
                    tokens.add(word, count);
                }
            }
            // update position by adding length of returned string
            position += word.length();
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param oneLine
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
    public static String separate(String oneLine, int position,
            Set<Character> separators) {
        assert oneLine != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < oneLine.length() : "Violation of: position < |text|";
        int i = position;
        // run if character at i is not in separator set to get string of words
        while (i < oneLine.length()
                && !separators.contains(oneLine.charAt(i))) {
            i++;
        }
        String word = oneLine.substring(position, i);
        // run only if s is empty to get string of separators
        if (word.equals("")) {
            while (i < oneLine.length()
                    && separators.contains(oneLine.charAt(i))) {
                i++;
            }
            word = oneLine.substring(position, i);
        }
        return word;
    }

    /**
     * Comparator to sort words in descending order of their counts.
     */
    private static class IntegerLT<String, Integer extends Comparable<Integer>>
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * Comparator to sort words in ascending order.
     */
    private static class StringLT<String extends Comparable<String>, Integer>
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            return o1.key().compareTo(o2.key());
        }
    }

    /**
     * Sorts the words in pairs in descending order based on their count values
     * and extract the top N words into pairs.
     *
     * @param tokens
     *            map containing words and their corresponding count values
     * @param topN
     *            sorting machine to sort the words in descending order based on
     *            their count values
     * @param n
     *            the number of words in tag cloud generator
     *
     * @updates pairs
     *
     */
    public static void topN(Map<String, Integer> tokens,
            SortingMachine<Map.Pair<String, Integer>> topN, int n) {
        assert tokens != null : "Violation of: pairs is not null";
        assert topN != null : "Violation of: topN is not null";
        while (tokens.size() != 0) {
            topN.add(tokens.removeAny());
        }
        topN.changeToExtractionMode();
        while (topN.size() != 0 && tokens.size() < n) {
            Map.Pair<String, Integer> word = topN.removeFirst();
            tokens.add(word.key(), word.value());
        }
    }

    /**
     * Sorts the top N words in pairs in ascending order and prints them in the
     * output file.
     *
     * @param tokens
     *            map containing words and their corresponding count values
     * @param sortTopN
     *            sorting machine to sort the words in ascending order
     * @param outToFile
     *            the out stream
     *
     * @updates outToFile
     * @requires
     *
     *           <pre>
     *           outToFile.is_open
     *           </pre>
     *
     * @ensures
     *
     *          <pre>
     *          outToFile.is_open
     *          </pre>
     */
    public static void print(Map<String, Integer> tokens,
            SortingMachine<Map.Pair<String, Integer>> sortTopN,
            SimpleWriter outToFile) {
        assert outToFile != null : "Violation of: output is not null";
        assert outToFile.isOpen() : "Violation of: output.is_open";
        assert tokens != null : "Violation of: pairs is not null";
        assert sortTopN != null : "Violation of: random is not null";
        int countMax = java.lang.Integer.MIN_VALUE,
                countMin = java.lang.Integer.MAX_VALUE;
        while (tokens.size() != 0) {
            Map.Pair<String, Integer> word = tokens.removeAny();
            int count = word.value();
            if (count > countMax) {
                countMax = count;
            }
            if (count < countMin) {
                countMin = count;
            }
            sortTopN.add(word);
        }
        int diff = countMax - countMin;
        sortTopN.changeToExtractionMode();
        while (sortTopN.size() != 0) {
            Map.Pair<String, Integer> word = sortTopN.removeFirst();
            int count = word.value();
            //  if you do (37 / (countMax - countMin) * count)
            // + (11 - (37 / (countMax - countMin)* countMin)); IT DOESN'T WORK
            // op precedence  matters!
            int fontSize;
            if (diff != 0) {
                fontSize = (37 * count / diff) + (11 - (37 * countMin / diff));
            } else {
                fontSize = (37 * count) + (11 - (37 * countMin));
            }
            outToFile.println("<span style=\"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count: " + count + "\">"
                    + word.key() + "</span>");
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
        //   out.println("Enter the file: ");
        String file = "importance.txt";
        out.println("Enter the number of words in tag cloud generator: ");
        int n = in.nextInteger();
        SimpleReader inFile = new SimpleReader1L(file);
        SimpleWriter outToFile = new SimpleWriter1L(file + ".html");
        outToFile.println("<html><head><title>Top " + n + " words in " + file
                + "</title><h2>Top " + n + " words in " + file
                + "</h2><link href=\"tagcloud.css\" rel=\"stylesheet\""
                + "type=\"text/css\">"
                + "</head><body><hr><div class=\"cdiv\"><p class=\"cbox\">");
        Map<String, Integer> tokens = new Map1L<>();
        Comparator<Map.Pair<String, Integer>> iO = new IntegerLT<String, Integer>();
        SortingMachine<Map.Pair<String, Integer>> topN = new SortingMachine1L<>(
                iO);
        Comparator<Map.Pair<String, Integer>> sO = new StringLT<String, Integer>();
        SortingMachine<Map.Pair<String, Integer>> sortTopN = new SortingMachine1L<>(
                sO);
        while (!inFile.atEOS()) {
            count(inFile, tokens);
        }
        topN(tokens, topN, n);
        print(tokens, sortTopN, outToFile);
        outToFile.println("</p></div></body></html>");
        inFile.close();
        outToFile.close();
        in.close();
        out.close();
    }
}
