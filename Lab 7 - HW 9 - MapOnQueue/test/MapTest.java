import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
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

    @Test
    public final void testNoArgumentConstructor() {
        Map<String, String> map = this.constructorTest();
        Map<String, String> mapExpected = this.constructorRef();
        assertEquals(mapExpected, map);
    }

    @Test
    public final void testRemoveRoutine() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        Map<String, String> mapExpected = this.createFromArgsRef();
        map.remove("a");
        assertEquals(mapExpected, map);
    }

    @Test
    public final void testAddEmpty() {
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("a", "1");
        map.add("a", "1");
        assertEquals(mapExpected, map);
    }

    @Test
    public final void testAddRoutine() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("a", "1", "b",
                "2");
        map.add("b", "2");
        assertEquals(mapExpected, map);
    }

    @Test
    public final void testSizeEmpty() {
        Map<String, String> map = this.createFromArgsTest();
        int size = map.size();
        assertEquals(0, size);
    }

    @Test
    public final void testSizeRoutine() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        int size = map.size();
        assertEquals(1, size);
    }

    @Test
    public final void testRemoveAnyBoundary() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        Map<String, String> mapExpected = this.createFromArgsRef();
        map.removeAny();
        assertEquals(mapExpected, map);
    }

    @Test
    public final void testhasKeyTrue() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        assert (map.hasKey("a"));
    }

    @Test
    public final void testhasKeyFalse() {
        Map<String, String> map = this.createFromArgsTest();
        assert (!map.hasKey("a"));
    }

    @Test
    public final void testhasValue() {
        Map<String, String> map = this.createFromArgsTest("a", "1");
        String value = map.value("a");
        assertEquals(value, "1");
    }

}
