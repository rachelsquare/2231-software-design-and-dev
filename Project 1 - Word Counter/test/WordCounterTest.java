import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * test sets for methods of WordCounter not including the print methods.
 **/
public class WordCounterTest {

    /*
     * Test of getOneLine, checks if all the words from input file are stored in
     * queue.
     */

    @Test
    public void getOneLine1() {
        SimpleReader input = new SimpleReader1L("getOneLine1.txt");
        Queue<String> random = new Queue1L<>();
        Queue<String> randomExpected = new Queue1L<>();
        while (!input.atEOS()) {
            WordCounter.getOneLine(input, random);
        }
        assertEquals(randomExpected, random);
        input.close();
    }

    /*
     * Test of getOneLine, checks if all the words from input file are stored in
     * queue.
     */

    @Test
    public void getOneLine2() {
        SimpleReader input = new SimpleReader1L("getOneLine2.txt");
        Queue<String> random = new Queue1L<>();
        Queue<String> randomExpected = new Queue1L<>();
        randomExpected.enqueue("hey");
        randomExpected.enqueue("CSE");
        while (!input.atEOS()) {
            WordCounter.getOneLine(input, random);
        }
        assertEquals(randomExpected, random);
        input.close();
    }

    /*
     * Test of getOneLine, checks if all the words from input file are stored in
     * queue.
     */

    @Test
    public void getOneLine3() {
        SimpleReader input = new SimpleReader1L("getOneLine3.txt");
        Queue<String> random = new Queue1L<>();
        Queue<String> randomExpected = new Queue1L<>();
        randomExpected.enqueue("yatta");
        randomExpected.enqueue("wOw");
        while (!input.atEOS()) {
            WordCounter.getOneLine(input, random);
        }
        assertEquals(randomExpected, random);
        input.close();
    }

    /*
     * Tests of makeQueue, checks if queue of words is made from strings
     * containing single lines
     */

    @Test
    public void makeQueue1() {
        SimpleReader input = new SimpleReader1L("makeQueue1.txt");
        String oneLine = input.nextLine();
        Queue<String> qWords = new Queue1L<>();
        qWords.transferFrom(WordCounter.makeQueue(oneLine));
        Queue<String> expectedQWords = new Queue1L<>();
        expectedQWords.enqueue("go");
        expectedQWords.enqueue("Bucks");
        assertEquals(expectedQWords, qWords);
        input.close();
    }

    /*
     * Tests of makeQueue, checks if queue of words is made from strings
     * containing single lines
     */

    @Test
    public void makeQueue2() {
        SimpleReader input = new SimpleReader1L("makeQueue2.txt");
        String oneLine = input.nextLine();
        Queue<String> qWords = new Queue1L<>();
        qWords.transferFrom(WordCounter.makeQueue(oneLine));
        Queue<String> expectedQWords = new Queue1L<>();
        assertEquals(expectedQWords, qWords);
        input.close();
    }

    /*
     * Tests of makeQueue, checks if queue of words is made from strings
     * containing single lines
     */

    @Test
    public void makeQueue3() {
        SimpleReader input = new SimpleReader1L("makeQueue3.txt");
        String oneLine = input.nextLine();
        Queue<String> qWords = new Queue1L<>();
        qWords.transferFrom(WordCounter.makeQueue(oneLine));
        Queue<String> expectedQWords = new Queue1L<>();
        expectedQWords.enqueue("hackers");
        expectedQWords.enqueue("rock");
        expectedQWords.enqueue("Hackers");
        expectedQWords.enqueue("rock");
        assertEquals(expectedQWords, qWords);
        input.close();
    }

    /*
     * Test of sortInOrder, checks if queues containing duplicate entries are
     * added in map with their count values.
     */

    @Test
    public void sortInOrder1() {
        Map<String, Integer> pairs = new Map1L<String, Integer>();
        Map<String, Integer> pairsExpected = new Map1L<String, Integer>();
        Queue<String> random = new Queue1L<>();
        random.enqueue("anger");
        random.enqueue("anger");
        Queue<String> randomSorted = new Queue1L<>();
        randomSorted.enqueue("anger");
        randomSorted.enqueue("anger");
        WordCounter.sortInOrder(random, pairs);
        pairsExpected.add("anger", 2);
        assertEquals(pairsExpected, pairs);
        assertEquals(randomSorted, random);
    }

    /*
     * Test of sortInOrder, checks if queues containing duplicate entries are
     * added in map with their count values.
     */

    @Test
    public void sortInOrder2() {
        Map<String, Integer> pairs = new Map1L<String, Integer>();
        Map<String, Integer> pairsExpected = new Map1L<String, Integer>();
        Queue<String> random = new Queue1L<>();
        random.enqueue("anger");
        random.enqueue("AnGer");
        Queue<String> randomSorted = new Queue1L<>();
        randomSorted.enqueue("AnGer");
        randomSorted.enqueue("anger");
        WordCounter.sortInOrder(random, pairs);
        pairsExpected.add("anger", 1);
        pairsExpected.add("AnGer", 1);
        assertEquals(pairsExpected, pairs);
        assertEquals(randomSorted, random);
    }

    /*
     * Test of sortInOrder, checks if queues containing duplicate entries are
     * added in map with their count values.
     */

    @Test
    public void sortInOrder3() {
        Map<String, Integer> pairs = new Map1L<String, Integer>();
        Map<String, Integer> pairsExpected = new Map1L<String, Integer>();
        Queue<String> random = new Queue1L<>();
        Queue<String> randomSorted = new Queue1L<>();
        WordCounter.sortInOrder(random, pairs);
        assertEquals(pairsExpected, pairs);
        assertEquals(randomSorted, random);
    }

    /*
     * Test of sortInOrder, checks if queues containing duplicate entries are
     * added in map with their count values.
     */

    @Test
    public void sortInOrder4() {
        Map<String, Integer> pairs = new Map1L<String, Integer>();
        Map<String, Integer> pairsExpected = new Map1L<String, Integer>();
        Queue<String> random = new Queue1L<>();
        Queue<String> randomSorted = new Queue1L<>();
        random.enqueue("Boy");
        random.enqueue("anger");
        randomSorted.enqueue("anger");
        randomSorted.enqueue("Boy");
        WordCounter.sortInOrder(random, pairs);
        pairsExpected.add("anger", 1);
        pairsExpected.add("Boy", 1);
        assertEquals(pairsExpected, pairs);
        assertEquals(randomSorted, random);
    }

    /*
     * Test of separate, checks if a string of words and separators is separated
     * into either a word or a string of separator. Returns the first word or
     * separator string found in {@code text} starting at index {@code position}
     */

    @Test
    public void separate1() {
        String oneLine = ", 12hjb/ll  .!@ hey BuCK@!";
        int position = 0;
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
        String checkExpected = WordCounter.separate(oneLine, position,
                separators);
        String check = ", 12";
        assertEquals(checkExpected, check);
    }

    /*
     * Test of separate, checks if a string of words and separators is separated
     * into either a word or a string of separator. Returns the first word or
     * separator string found in {@code text} starting at index {@code position}
     */

    @Test
    public void separate2() {
        String oneLine = "BbuckSSbux !@  jbjhjc11323/-3";
        int position = 0;
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
        String checkExpected = WordCounter.separate(oneLine, position,
                separators);
        String check = "BbuckSSbux";
        assertEquals(checkExpected, check);
    }

    /*
     * Test of separate, checks if a string of words and separators is separated
     * into either a word or a string of separator. Returns the first word or
     * separator string found in {@code text} starting at index {@code position}
     */

    @Test
    public void separate3() {
        String oneLine = "";
        int position = 0;
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
        String checkExpected = WordCounter.separate(oneLine, position,
                separators);
        String check = "";
        assertEquals(checkExpected, check);
    }

}
