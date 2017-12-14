import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    public final void testNoArgumentConstructor() {
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemove1() {
        Sequence<String> s = this.createFromArgsTest("red");
        Sequence<String> sExpected = this.createFromArgsRef();
        String x = s.remove(0);
        assertEquals(sExpected, s);
        assertEquals("red", x);
    }

    @Test
    public final void testRemove2() {
        Sequence<String> s = this.createFromArgsTest("red", "blue", "green");
        Sequence<String> sExpected = this.createFromArgsRef("red", "green");
        String x = s.remove(1);
        assertEquals(sExpected, s);
        assertEquals("blue", x);
    }

    @Test
    public final void testAdd1() {
        //    SimpleWriter out = new SimpleWriter1L();
        Sequence<String> s = this.createFromArgsTest("red", "green");
        Sequence<String> sExpected = this.createFromArgsRef("red", "blue",
                "green");
        //   out.println(s + " " + sExpected);
        s.add(1, "blue");
        //     out.println(s + " " + sExpected);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLength1() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef();
        int i = s.length();
        assertEquals(0, i);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLength2() {
        Sequence<String> s = this.createFromArgsTest("red", "green");
        Sequence<String> sExpected = this.createFromArgsRef("red", "green");
        int i = s.length();
        assertEquals(2, i);
        assertEquals(sExpected, s);
    }

}
