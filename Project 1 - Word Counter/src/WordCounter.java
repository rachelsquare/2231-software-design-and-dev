import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Create a word counter of input file.
 *
 * @author Shiny Patel
 *
 */
public final class WordCounter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounter() {
    }

    /**
     * Gets one line from input file and makes a queue of all the words in each
     * line.
     *
     * @param input
     *            the input stream
     * @param random
     *            store all the words in the input file
     * @updates input
     * @updates random
     * @requires
     *
     *           <pre>
     *           input.is_open
     *           </pre>
     *
     * @ensures
     *
     *          <pre>
     *          input.is_open
     *          </pre>
     */
    public static void getOneLine(SimpleReader input, Queue<String> random) {
        assert input != null : "Violation of: input is not null";
        assert input.isOpen() : "Violation of: input.is_open";
        // read one line
        String oneLine = input.nextLine();
        /*
         * makeQueue returns a queue containing words in that line; all of which
         * are added into random
         */
        for (String word : makeQueue(oneLine)) {
            random.enqueue(word);
        }
    }

    /**
     * create a queue of words from the string containing one line of the input
     * file.
     *
     * @param oneLine
     *            a single line from input file
     *
     * @return queue of words from that line
     */
    public static Queue<String> makeQueue(String oneLine) {
        assert oneLine != null : "Violation of: line is not empty";
        // define and add separators to separator set
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add(';');
        separators.add('!');
        separators.add('?');
        separators.add('`');
        separators.add('~');
        separators.add('-');
        separators.add('"');
        separators.add('@');
        separators.add('#');
        separators.add('&');
        separators.add('$');
        separators.add('%');
        separators.add('^');
        separators.add('*');
        separators.add('/');
        separators.add('+');
        separators.add('(');
        separators.add(')');
        separators.add('=');
        separators.add('<');
        separators.add('>');
        separators.add('|');
        separators.add(':');
        separators.add('_');
        separators.add('}');
        separators.add('{');
        separators.add(']');
        separators.add('[');
        separators.add('0');
        separators.add('1');
        separators.add('2');
        separators.add('3');
        separators.add('4');
        separators.add('5');
        separators.add('6');
        separators.add('7');
        separators.add('8');
        separators.add('9');
        // start separation of words from index 0
        int position = 0;
        // store separated words in qWords
        Queue<String> qWords = new Queue1L<>();
        while (position < oneLine.length()) {
            // method call to get strings of either words or separators
            String word = separate(oneLine, position, separators);
            // if returned string is not a string of separators, add it in qWords
            if (!separators.contains(word.charAt(0))) {
                qWords.enqueue(word);
            }
            // update position by adding length of returned string
            position += word.length();
        }
        return qWords;
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
     * sort words in lexicographical order and count their number of repetitions
     * to store in pairs.
     *
     * @param pairs
     *            to store pair of words and their corresponding count
     * @param random
     *            contains all the words in random order (with duplicate
     *            entries)
     * @updates pairs and random
     */
    public static void sortInOrder(Queue<String> random,
            Map<String, Integer> pairs) {
        assert random != null : "Violation of: random is not null";
        // define comparator for sorting words.
        Comparator<String> lexo = new StringLT();
        // sort all the words in random
        random.sort(lexo);
        /**
         * declare set noDuplicate to factor out all the duplicate words while
         * adding them in pairs
         */
        Set<String> noDuplicate = new Set1L<>();
        int count = 0;
        /**
         * for each word in random, add it in noDuplicate only if it is not
         * already present.
         *
         * increment the count when adding it the first time, and increment it
         * thereafter each time, the word is repeated
         */
        for (String word : random) {
            if (!noDuplicate.contains(word)) {
                count = 0;
                noDuplicate.add(word);
                count++;
            } else {
                count++;
            }
            if (!pairs.hasKey(word)) {
                /**
                 * add the word in map and its corresponding count value
                 */
                pairs.add(word, count);
            } else {
                /**
                 * replace the value of count if the word is already present
                 */
                pairs.replaceValue(word, count);
            }
        }
    }

    /**
     * print a table of words and their corresponding count in lexicographical
     * order.
     *
     * @param output
     *            the output stream
     * @param pairs
     *            contains pair of words and their corresponding count
     * @param random
     *            contains all the words in random order (with duplicate
     *            entries)
     *
     * @updates output
     * @requires
     *
     *           <pre>
     *           output.is_open and pairs and random are not empty
     *           </pre>
     *
     * @ensures
     *
     *          <pre>
     *          output.is_open
     *          </pre>
     */
    public static void print(Queue<String> random, Map<String, Integer> pairs,
            SimpleWriter output) {
        assert output != null : "Violation of: output is not null";
        assert output.isOpen() : "Violation of: output.is_open";
        assert pairs != null : "Violation of: pairs is not null";
        assert random != null : "Violation of: random is not null";
        Set<String> filterDuplicate = new Set1L<>();
        // use words in random as they are sorted in lexicographical order
        for (String word : random) {
            /**
             * execute only if word is not present in filterDuplicate
             */
            if (!filterDuplicate.contains(word)) {
                filterDuplicate.add(word);
                output.println("<tr><td>" + word + "</td>");
                // print the value of count corresponding to each word
                output.println("<td>" + pairs.value(word) + "</td></tr>");
            }
        }
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
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
        out.println("Enter the name of input file: ");
        String filename = in.nextLine();
        SimpleReader input = new SimpleReader1L(filename);
        SimpleWriter output = new SimpleWriter1L("data/" + filename + ".html");
        // print header
        output.println(
                "<html><head><title>WordCounter</title><h2>WordCounter for data/"
                        + filename + "</h2>"
                        + "</head><body><table border=2><tr><th>Words</th><th>"
                        + "Counts</th></tr>");
        // declare pairs to store words and their corresponding count values
        Map<String, Integer> pairs = new Map1L<>();
        // queue to store all the words in the input file
        Queue<String> random = new Queue1L<>();
        while (!input.atEOS()) {
            getOneLine(input, random);
        }
        // call methods to print words and their count values in lexicographical order
        sortInOrder(random, pairs);
        print(random, pairs, output);
        out.print(pairs);
        // close html tags
        output.println("</table></body></html>");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        input.close();
        output.close();
    }
}
