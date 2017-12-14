import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Peiyuan Tang and Shiny Patel
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     *
     *          <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     *
     *          <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    //Constructor
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    //Add
    //boundary
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testAddOneElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testAddTwoElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testAddThreeElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testAddFourElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown", "black");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //challenge
    @Test
    public final void testAddNineElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown", "black", "white", "scarlet",
                "purple", "yellow", "orange");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //challenge
    @Test
    public final void testAddTenElementsDuplicate() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown", "black", "white", "scarlet",
                "purple", "yellow", "orange", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    //changeToExtractionMode
    //boundary
    @Test
    public final void testChangeToExtractionMode0Element() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testChangeToExtractionMode1Element() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testChangeToExtractionMode2Elements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green");
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testChangeToExtractionMode3Elements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "green", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue");
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testChangeToExtractionMode4Elements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "green", "blue", "silver");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue", "silver");
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //routine,challenge
    @Test
    public final void testChangeToExtractionMode10Elements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "green", "blue", "silver", "gold", "brown", "black", "white",
                "grey", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue", "silver", "gold", "brown", "black",
                "white", "grey", "orange");
        m.changeToExtractionMode();
        assertEquals(m, mExpected);
    }

    //removeFirst

    //boundary
    @Test
    public final void testRemoveFirstOneElment() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String str = m.removeFirst();
        assertEquals("green", str);
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testRemoveFirstTwoElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        String str = m.removeFirst();
        assertEquals("blue", str);
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testRemoveFirstThreeElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "yellow");
        String str = m.removeFirst();
        assertEquals("blue", str);
        assertEquals(m, mExpected);
    }

    //routine
    @Test
    public final void testRemoveFirstFourElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow", "silver");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "yellow", "silver");
        String str = m.removeFirst();
        assertEquals("blue", str);
        assertEquals(m, mExpected);
    }

    //routine,challenge
    @Test
    public final void testRemoveFirstTenElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red", "blue", "brown", "white", "scarlet", "purple",
                "yellow", "orange");
        String str = m.removeFirst();
        assertEquals("black", str);
        assertEquals(m, mExpected);
    }

    //isInInsertionMode
    //boundary
    @Test
    public final void testIsInInsertionModeEmpty1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        boolean mode = m.isInInsertionMode();
        assertTrue(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        boolean mode = m.isInInsertionMode();
        assertFalse(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeOneElements1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red");
        boolean mode = m.isInInsertionMode();
        assertTrue(mode);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testIsInInsertionModeOneElements2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red");
        boolean mode = m.isInInsertionMode();
        assertFalse(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeTwoElements1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "red");
        boolean mode = m.isInInsertionMode();
        assertTrue(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeTwoElements2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "yellow");
        boolean mode = m.isInInsertionMode();
        assertFalse(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeThreeElements1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "green", "red");
        boolean mode = m.isInInsertionMode();
        assertTrue(mode);
        assertEquals(mExpected, m);
    }

    //routine
    @Test
    public final void testIsInInsertionModeThreeElements2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "yellow", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "yellow", "green");
        boolean mode = m.isInInsertionMode();
        assertFalse(mode);
        assertEquals(mExpected, m);
    }

    //challenge
    @Test
    public final void testIsInInsertionModeTenElements1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "red", "brown", "black", "white", "scarlet", "purple",
                "yellow", "orange", "green");
        boolean mode = m.isInInsertionMode();
        assertTrue(mode);
        assertEquals(mExpected, m);
    }

    //challenge
    @Test
    public final void testIsInInsertionModeTenElements2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "red", "brown", "black", "white", "scarlet", "purple",
                "yellow", "orange", "green");
        boolean mode = m.isInInsertionMode();
        assertFalse(mode);
        assertEquals(mExpected, m);
    }

    //order
    //boundary

    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    //boundary challenge
    @Test
    public final void testOrderEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    //routine
    @Test
    public final void testOrderOneElement() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    //routine
    @Test
    public final void testOrderTwoElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    //routine, challenge
    @Test
    public final void testOrderThreeElementsExtration() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "orange");
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    @Test
    public final void testOrderTenElementsExtration() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown", "black", "white", "scarlet",
                "purple", "yellow", "orange");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(10, size);
        Comparator<String> order = m.order();
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);

    }

    //size
    //boundary
    @Test
    public final void testSize0() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }

    //routine
    @Test
    public final void testSize1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    //routine
    @Test
    public final void testSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(2, size);
    }

    //routine
    @Test
    public final void testSize3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "orange");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(3, size);
    }

    //challenge
    @Test
    public final void testSize3ExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "orange");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(3, size);
    }

    //challenge
    @Test
    public final void testSize10() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "red", "brown", "black", "white", "scarlet", "purple", "yellow",
                "orange", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "brown", "black", "white", "scarlet",
                "purple", "yellow", "orange");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(10, size);
    }

    //challenge
    @Test
    public final void testSize11Duplicate() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "green", "blue", "silver", "gold", "brown", "black",
                "white", "grey", "orange", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue", "silver", "gold", "brown", "black",
                "white", "grey", "orange", "red");
        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(11, size);
    }

}
