import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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

    /**
     * Test cases for constructors
     *
     * @param <T>
     *
     */
    @Test
    public final void testConstructor() {
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        assertEquals(sExpected, s);
    }

    /**
     * Test cases for add
     */

    //boundary
    @Test
    public final void testAdd1E0ement() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("carmen");
        s.add("carmen");
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testAdd1Element() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("OSU");
        s.add("OSU");
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testAdd2Element() {
        Set<String> s = this.createFromArgsTest("band", "go");
        Set<String> sExpected = this.createFromArgsRef("band", "go", "carmen");
        s.add("carmen");
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testAdd4Element() {
        Set<String> s = this.createFromArgsTest("go", "bowl", "march");
        Set<String> sExpected = this.createFromArgsRef("bowl", "go", "carmen",
                "march");
        s.add("carmen");
        assertEquals(sExpected, s);
    }

    //routine challenge
    @Test
    public final void testAdd9Element() {
        Set<String> s = this.createFromArgsTest("go", "buck", "oval", "mirror",
                "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        s.add("carmen");
        assertEquals(sExpected, s);
    }

    //routine challenge
    @Test
    public final void testAdd10Element() {
        Set<String> s = this.createFromArgsTest("go", "buck", "oval", "mirror",
                "rpac", "dreese", "hitchcock", "caldwell", "scott", "bowing");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott", "bowing");
        s.add("carmen");
        assertEquals(sExpected, s);
    }

    /**
     * Test cases for remove method.
     */

    //boundary
    @Test
    public final void testRemove1Element() {
        Set<String> s = this.createFromArgsTest("go");
        Set<String> sExpected = this.createFromArgsRef("go");
        String element = s.remove("go");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("go", element);
        assertEquals(sExpected, s);

    }

    //routine
    @Test
    public final void testRemove2Element() {
        Set<String> s = this.createFromArgsTest("go", "bowl");
        Set<String> sExpected = this.createFromArgsRef("go", "bowl");
        String element = s.remove("go");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("go", element);
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testRemove3Element() {
        Set<String> s = this.createFromArgsTest("go", "carmen", "bowl");
        Set<String> sExpected = this.createFromArgsRef("go", "carmen", "bowl");
        String element = s.remove("carmen");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("carmen", element);
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testRemove4Element() {
        Set<String> s = this.createFromArgsTest("go", "bowl", "carmen",
                "bucks");
        Set<String> sExpected = this.createFromArgsRef("go", "bowl", "carmen",
                "bucks");
        String element = s.remove("go");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("go", element);
        assertEquals(sExpected, s);

    }

    //routine
    @Test
    public final void testRemove10Element() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        String element = s.remove("go");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("go", element);
        assertEquals(sExpected, s);
    }

    //routine, challenge
    @Test
    public final void testRemove10Element2() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        String element = s.remove("rpac");
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals("rpac", element);
        assertEquals(sExpected, s);
    }

    /**
     * Test cases for removeAny method.
     */

    //routine
    @Test
    public final void testRemoveAny1Element() {
        Set<String> s = this.createFromArgsTest("go");
        Set<String> sExpected = this.createFromArgsRef("go");
        String element = s.removeAny();
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testRemoveAny2Element() {
        Set<String> s = this.createFromArgsTest("go", "carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen", "go");
        String element = s.removeAny();
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testRemoveAny3Element() {
        Set<String> s = this.createFromArgsTest("go", "carmen", "somehow");
        Set<String> sExpected = this.createFromArgsRef("go", "carmen",
                "somehow");
        String element = s.removeAny();
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testRemoveAny4Element() {
        Set<String> s = this.createFromArgsTest("go", "carmen", "somehow",
                "band");
        Set<String> sExpected = this.createFromArgsRef("go", "carmen",
                "somehow", "band");
        String element = s.removeAny();
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals(sExpected, s);
    }

    //routine, challenge
    @Test
    public final void testRemoveAny10Element() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        String element = s.removeAny();
        if (sExpected.contains(element)) {
            sExpected.remove(element);
        }
        assertEquals(sExpected, s);
    }

    /**
     * Test cases for contains method.
     */

    //boundary
    @Test
    public final void testContains0() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        boolean b = s.contains("carmen");
        assertFalse(b);
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains1Has() {
        Set<String> s = this.createFromArgsTest("carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen");
        assert (s.contains("carmen"));
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains1NotHas() {
        Set<String> s = this.createFromArgsTest("carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen");
        assert (!s.contains("go"));
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains2Has() {
        Set<String> s = this.createFromArgsTest("carmen", "bucks");
        Set<String> sExpected = this.createFromArgsRef("carmen", "bucks");
        assert (s.contains("bucks"));
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains2NotHas() {
        Set<String> s = this.createFromArgsTest("carmen", "bucks");
        Set<String> sExpected = this.createFromArgsRef("carmen", "bucks");
        assert (!s.contains("go"));
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains3Has() {
        Set<String> s = this.createFromArgsTest("carmen", "bucks", "OSU");
        Set<String> sExpected = this.createFromArgsRef("carmen", "bucks",
                "OSU");
        assert (s.contains("carmen"));
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testContains3NotHas() {
        Set<String> s = this.createFromArgsTest("carmen", "bucks", "OSU");
        Set<String> sExpected = this.createFromArgsRef("carmen", "bucks",
                "OSU");
        assert (!s.contains("go"));
        assertEquals(sExpected, s);
    }

    //challenge, routine
    public final void testContains10Has() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        assert (s.contains("go"));
        assertEquals(sExpected, s);
    }

    //challenge, routine
    public final void testContains10NotHas() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        assert (!s.contains("OSU"));
        assertEquals(sExpected, s);
    }

    /**
     * Test for size method
     */

    //boudary
    @Test
    public final void testSize0() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        assertEquals(sExpected, s);
        assertEquals(sExpected.size(), s.size());
    }

    //routine
    @Test
    public final void testSize1() {
        Set<String> s = this.createFromArgsTest("carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testSize2() {
        Set<String> s = this.createFromArgsTest("go", "carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen", "go");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);
    }

    //routine
    @Test
    public final void testSize3() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen");
        Set<String> sExpected = this.createFromArgsRef("carmen", "go", "buck");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);
    }

    //challenge, routine
    @Test
    public final void testSize10() {
        Set<String> s = this.createFromArgsTest("go", "buck", "carmen", "oval",
                "mirror", "rpac", "dreese", "hitchcock", "caldwell", "scott");
        Set<String> sExpected = this.createFromArgsRef("go", "buck", "carmen",
                "oval", "mirror", "rpac", "dreese", "hitchcock", "caldwell",
                "scott");
        assertEquals(sExpected.size(), s.size());
        assertEquals(sExpected, s);
    }
}
