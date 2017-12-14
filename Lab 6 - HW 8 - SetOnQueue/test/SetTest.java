import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testNoArgumentConstructor() {
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        assertEquals(sExpected, s);
    }

    // remove string "2" from s
    @Test
    public final void testRemoveRoutine() {
        Set<String> s = this.createFromArgsTest("1", "2", "3");
        Set<String> sExpected = this.createFromArgsRef("1", "3");
        String x = s.remove("2");
        assertEquals(sExpected, s);
        assertEquals("2", x);
    }

    // remove string "2" from s
    @Test
    public final void testRemoveBoundary() {
        Set<String> s = this.createFromArgsTest("2");
        Set<String> sExpected = this.createFromArgsRef();
        String x = s.remove("2");
        assertEquals(sExpected, s);
        assertEquals("2", x);
    }

    // add string "1" in s
    @Test
    public final void testAddRoutine() {
        Set<String> s = this.createFromArgsTest("2", "3");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3");
        s.add("1");
        assertEquals(sExpected, s);
    }

    // add string "1" in s
    @Test
    public final void testAddBoundary() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("1");
        s.add("1");
        assertEquals(sExpected, s);
    }

    // remove any string in s
    @Test
    public final void testRemoveAnyRoutine() {
        Set<String> s = this.createFromArgsTest("2", "3");
        Set<String> sCheck = this.createFromArgsRef("2", "3");
        String x = s.removeAny();
        if (sCheck.contains(x)) {
            sCheck.remove(x);
        }
        assertEquals(sCheck, s);
    }

    // remove any string in s
    @Test
    public final void testRemoveAnyBoundary() {
        Set<String> s = this.createFromArgsTest("2");
        Set<String> sCheck = this.createFromArgsRef("2");
        String x = s.removeAny();
        if (sCheck.contains(x)) {
            sCheck.remove(x);
        }
        assertEquals(sCheck, s);
    }

    // find length of s
    @Test
    public final void testLengthBoundary() {
        Set<String> s = this.createFromArgsTest();
        assert s.size() == 0;
    }

    // find length of s
    @Test
    public final void testLengthRoutine() {
        Set<String> s = this.createFromArgsTest("1", "2");
        assert s.size() == 2;
    }

    // check if true when string present in s
    @Test
    public final void testContainsTrue() {
        Set<String> s = this.createFromArgsTest("1");
        assert s.contains("1");
    }

    // check if true when string absent in s
    @Test
    public final void testContainsFalse() {
        Set<String> s = this.createFromArgsTest();
        assert !s.contains("1");
    }

}
