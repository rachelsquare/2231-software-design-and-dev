import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Pei-yuan Tang and Shiny Patel
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // Constructor test cases
    @Test
    public void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExp = this.constructorRef();
        assertEquals(mExp, m);

    }

    /*
     * Add test cases
     */
    //add Boundary
    @Test
    public void testAddEmptyMap() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck");
        m.add("OSU", "Buck");
        assertEquals(mExp, m);

    }

    //add Routine
    @Test
    public void testAddNonEmptyMapOnePair() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Cathargie");
        m.add("Rome", "Cathargie");
        assertEquals(mExp, m);
    }

    //add Challenge
    @Test
    public void testAddNonEmptyMapTwoPairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rubick",
                "Cube");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Cathargie", "Rubick", "Cube");
        m.add("Rome", "Cathargie");
        assertEquals(mExp, m);
    }

    //add Challenge
    @Test
    public void testAddNonEmptyMapTenPairs() {
        Map<String, String> m = this.createFromArgsTest("Marcedon", "Greece",
                "Persia", "Assyra", "Egypt", "Barbilon", "India", "China",
                "America", "Mexico", "Peru", "Brazil", "Agentina", "Chile",
                "Sudan", "Tunis", "Arab", "Turkey");
        Map<String, String> mExp = this.createFromArgsRef("Marcedon", "Greece",
                "Persia", "Assyra", "Egypt", "Barbilon", "India", "China",
                "America", "Mexico", "Rome", "Carthage", "Peru", "Brazil",
                "Agentina", "Chile", "Sudan", "Tunis", "Arab", "Turkey");
        m.add("Rome", "Carthage");
        assertEquals(mExp, m);

    }

    //remove Boundary
    @Test
    public void testRemoveOnePair() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthagie");
        Map<String, String> mExp = this.createFromArgsRef();
        Map.Pair<String, String> p = m.remove("Rome");
        assertEquals(mExp, m);
        assertEquals("Rome", p.key());
        assertEquals("Carthagie", p.value());
    }

    //remove challenge
    @Test
    public void testRemoveTwoPairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthagie");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck");
        Map.Pair<String, String> p = m.remove("Rome");
        assertEquals(mExp, m);
        assertEquals("Rome", p.key());
        assertEquals("Carthagie", p.value());
    }

    //remove Routine,
    @Test
    public void testRemoveThreePairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthagie", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck",
                "England", "France");
        Map.Pair<String, String> p = m.remove("Rome");
        assertEquals("Rome", p.key());
        assertEquals("Carthagie", p.value());
        assertEquals(mExp, m);
    }

    // remove Routine,Challenge
    @Test
    public void testRemoveThreePairs2() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthagie", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Carthagie");
        Map.Pair<String, String> p = m.remove("England");
        assertEquals("England", p.key());
        assertEquals("France", p.value());
        assertEquals(mExp, m);
    }

    // remove Routine,Challenge
    @Test
    public void testRemoveTenPairs() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Marcedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "England", "France", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "China", "India");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Marcedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "America", "Mexico", "Peru", "Brazil", "Argentina", "Chile",
                "Sudan", "Tunis", "China", "India");
        Map.Pair<String, String> p = m.remove("England");
        assertEquals("England", p.key());
        assertEquals("France", p.value());
        assertEquals(mExp, m);
    }

    //removeAny challenge
    @Test
    public void testRemoveAnyOnePair() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage");
        Map.Pair<String, String> p = m.removeAny();
        mExp.remove(p.key());
        assertEquals(mExp, m);
    }

    //removeAny challenge
    @Test
    public void testRemoveAnyTwoPairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Carthage");
        Map.Pair<String, String> p = m.removeAny();
        mExp.remove(p.key());
        assertEquals(mExp, m);
    }

    //removeAny challenge
    @Test
    public void testRemoveAnyThreePairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        Map.Pair<String, String> p = m.removeAny();
        mExp.remove(p.key());
        assertEquals(mExp, m);
    }

    //removeAny, challenge
    @Test
    public void testRemoveAnyTenPairs() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map.Pair<String, String> p = m.removeAny();
        mExp.remove(p.key());
        assertEquals(mExp, m);
    }

    /*
     * value
     */

    //boundary

    @Test
    public void testValueOnePair() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage");
        String v = m.value("Rome");
        assertEquals("Carthage", v);
        assertEquals(mExp, m);
    }

    //Routine
    @Test
    public void testValueTwoPairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        String v = m.value("Rome");
        assertEquals("Carthage", v);
        assertEquals(mExp, m);
    }

    //Routine
    @Test
    public void testValueThreePairs() {
        Map<String, String> m = this.createFromArgsTest("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("OSU", "Buck", "Rome",
                "Carthage", "England", "France");
        String v = m.value("Rome");
        assertEquals("Carthage", v);
        assertEquals(mExp, m);
    }

    @Test
    public void testValueTenPairs() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        String v = m.value("Rome");
        assertEquals("Carthage", v);
        assertEquals(mExp, m);
    }

    /*
     * TestCases for hasKey methdod
     */

// hasKey boundary
    @Test
    public void testhasKeyEmptyMap() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExp = this.createFromArgsRef();
        boolean result = m.hasKey("OSU");
        assertFalse(result);
        assertEquals(mExp, m);
    }

    //hasKey routine
    @Test
    public void testhasKeyOnePair() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage");
        boolean result = m.hasKey("OSU");
        assertFalse(result);
        assertEquals(mExp, m);

    }

    //hasKey routine
    @Test
    public void testhasKeyOnePair2() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage");
        boolean result = m.hasKey("Rome");
        assertTrue(result);
        assertEquals(mExp, m);
    }

    //hasKey routine
    @Test
    public void testhasKeyTwoPairs() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Germany", "Prussia");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Germany", "Prussia");
        boolean result = m.hasKey("Germany");
        assertTrue(result);
        assertEquals(mExp, m);
    }

    //hasKey routine
    @Test
    public void testhasKeyTwoPairs2() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Germany", "Prussia");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Germany", "Prussia");
        boolean result = m.hasKey("Burgundy");
        assertFalse(result);
        assertEquals(mExp, m);
    }

    // hasKey challenge
    @Test
    public void testhasKeyThreePairs1() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persia", "Assyria");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persia", "Assyria");
        boolean result1 = m.hasKey("Rome");
        assertTrue(result1);
        assertEquals(mExp, m);
    }

    // hasKey challenge
    @Test
    public void testhasKeyThreePairs2() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persia", "Assyria");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persia", "Assyria");
        boolean result = m.hasKey("Greece");
        assertFalse(result);
        assertEquals(mExp, m);

    }

    //hasKey challenge
    @Test
    public void testhasKeyTenPairs1() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "China", "India", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "China", "India", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        boolean result = m.hasKey("China");
        assertTrue(result);
        assertEquals(mExp, m);

    }

    //hasKey challenge, check whether it distinguishes the value from the key.
    @Test
    public void testhasKeyTenPairs2() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        boolean result = m.hasKey("Chile");
        assertFalse(result);
        assertEquals(mExp, m);

    }
    /*
     * Test cases for size
     */
    //size boundary

    @Test
    public void testSize0() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExp = this.createFromArgsRef();
        int ls = m.size();
        assertEquals(0, ls);
        assertEquals(mExp, m);
    }

//size routine

    @Test
    public void testSize1() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage");
        int ls = m.size();
        assertEquals(1, ls);
        assertEquals(mExp, m);
    }

    //size routine
    @Test
    public void testSize2() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Marcedon", "Greece");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Marcedon", "Greece");
        int ls = m.size();
        assertEquals(2, ls);
        assertEquals(mExp, m);
    }

    //size challenge
    @Test
    public void testSize3() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Marcedon", "Greece", "Persia", "Assyra");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Marcedon", "Greece", "Persia", "Assyra");
        int ls = m.size();
        assertEquals(3, ls);
        assertEquals(mExp, m);
    }

    //size challenge
    @Test
    public void testSize10() {
        Map<String, String> m = this.createFromArgsTest("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        Map<String, String> mExp = this.createFromArgsRef("Rome", "Carthage",
                "Macedon", "Greece", "Persian", "Assyria", "Egypt", "Barbylon",
                "India", "China", "America", "Mexico", "Peru", "Brazil",
                "Argentina", "Chile", "Sudan", "Tunis", "England", "France");
        int ls = m.size();
        assertEquals(10, ls);
        assertEquals(mExp, m);
    }

}
