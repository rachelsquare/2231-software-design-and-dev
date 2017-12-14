import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Peiyuan Tang and Shiny Patel
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for constructor,non-argument.
     */

    @Test
    public final void testNoArgumentConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(nExpected, n);
    }

    /*
     * Constructor taking integers.
     */

    // boundary
    @Test
    public final void testIntArgumentConsturctor0() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testIntArgumentConsturctor9() {
        NaturalNumber n = this.constructorTest(9);
        NaturalNumber nExpected = this.constructorRef(9);
        assertEquals(nExpected, n);
    }

    // routine, challenge
    @Test
    public final void testIntArgumentConsturctor10() {
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(10);
        assertEquals(nExpected, n);
    }

    // routine
    @Test
    public final void testIntArgumentConsturctor67() {
        NaturalNumber n = this.constructorTest(67);
        NaturalNumber nExpected = this.constructorRef(67);
        assertEquals(nExpected, n);
    }

    // routine
    @Test
    public final void testIntArgumentConsturctor607() {
        NaturalNumber n = this.constructorTest(607);
        NaturalNumber nExpected = this.constructorRef(607);
        assertEquals(nExpected, n);
    }

    // routine
    @Test
    public final void testIntArgumentConsturctor3450() {
        NaturalNumber n = this.constructorTest(3450);
        NaturalNumber nExpected = this.constructorRef(3450);
        assertEquals(nExpected, n);
    }

    // routine
    @Test
    public final void testIntArgumentConsturctor83456() {
        NaturalNumber n = this.constructorTest(83456);
        NaturalNumber nExpected = this.constructorRef(83456);
        assertEquals(nExpected, n);
    }

    // routine, challenge
    @Test
    public final void testIntArgumentConsturctorIntegerMax_Value() {
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        assertEquals(nExpected, n);
    }

    /*
     * Constructor taking string,
     */

    //boundary
    @Test
    public final void testStringArgumentConstructor0() {
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testStringArgumentConstructor6() {
        NaturalNumber n = this.constructorTest("6");
        NaturalNumber nExpected = this.constructorRef("6");
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testStringArgumentConstructor67() {
        NaturalNumber n = this.constructorTest("67");
        NaturalNumber nExpected = this.constructorRef("67");
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testStringArgumentConstructor1567() {
        NaturalNumber n = this.constructorTest("1567");
        NaturalNumber nExpected = this.constructorRef("1567");
        assertEquals(nExpected, n);
    }

    //routine, challenge
    @Test
    public final void testStringArgumentConstructor4000() {
        NaturalNumber n = this.constructorTest("4000");
        NaturalNumber nExpected = this.constructorRef("4000");
        assertEquals(nExpected, n);
    }

    //boundary
    @Test
    public final void testStringArgumentConstructor156708() {
        NaturalNumber n = this.constructorTest("156708");
        NaturalNumber nExpected = this.constructorRef("156708");
        assertEquals(nExpected, n);
    }

    /*
     * challenge, big integer
     */
    @Test
    public final void testStringArgumentConstructor99999999999() {
        NaturalNumber n = this.constructorTest("99999999999");
        NaturalNumber nExpected = this.constructorRef("99999999999");
        assertEquals(nExpected, n);
    }

    /*
     * challenge, big integer
     */
    @Test
    public final void testStringArgumentConstructor900000000000() {
        NaturalNumber n = this.constructorTest("900000000000");
        NaturalNumber nExpected = this.constructorRef("900000000000");
        assertEquals(nExpected, n);
    }

    /*
     * constructor taking NaturalNumber
     */

    //boundary
    @Test
    public final void testNaturalNumberArgumentConstructor0() {
        NaturalNumber num = this.constructorRef();
        NaturalNumber nExpected = this.constructorRef(num);
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //boundary
    @Test
    public final void testNaturalNumberArgumentConstructorSecond0() {
        NaturalNumber num = this.constructorRef();
        NaturalNumber nExpected = this.constructorRef(0);
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //boundary, routine
    @Test
    public final void testNaturalNumberArgumentConstructor1() {
        NaturalNumber num = this.constructorRef(1);
        NaturalNumber nExpected = this.constructorRef(1);
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //routine 12
    public final void testNaturalNumberArgumentConstructor12() {
        NaturalNumber num = this.constructorRef(12);
        NaturalNumber nExpected = this.constructorRef(12);
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //routine 1245
    public final void testNaturalNumberArgumentConstructor1245() {
        NaturalNumber num = this.constructorRef("1245");
        NaturalNumber nExpected = this.constructorRef("1245");
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //routine 10101, challenge
    public final void testNaturalNumberArgumentConstructor10101() {
        NaturalNumber num = this.constructorRef(10101);
        NaturalNumber nExpected = this.constructorRef(10101);
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    //boundary and challenge, large number
    public final void testNaturalNumberArgumentConstructor12345678910() {
        NaturalNumber num = this.constructorRef("12345678910");
        NaturalNumber nExpected = this.constructorRef("12345678910");
        NaturalNumber n = this.constructorTest(num);
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for multiplyby10.
     */

    //boundary
    @Test
    public final void testMultiplyBy10Boundary0() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        n.multiplyBy10(0);
        assertEquals(nExpected, n);
    }

    /*
     * routine 1, challenge
     */
    @Test
    public final void testMultiplyBy10Routine1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef("7");
        n.multiplyBy10(7);
        assertEquals(nExpected, n);
    }

    /*
     * routine 2, challenge
     */
    @Test
    public final void testMultiplyBy10Routine2() {
        NaturalNumber n = this.constructorTest(3);
        NaturalNumber nExpected = this.constructorRef("30");
        int digit = 0;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 3
     */
    @Test
    public final void testMultiplyBy10Routine3() {
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef("101");
        int digit = 1;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 4
     */
    @Test
    public final void testMultiplyBy10Routine4() {
        NaturalNumber n = this.constructorTest(45);
        NaturalNumber nExpected = this.constructorRef("457");
        int digit = 7;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 5
     */
    @Test
    public final void testMultiplyBy10Routine5() {
        NaturalNumber n = this.constructorTest(335);
        NaturalNumber nExpected = this.constructorRef("3357");
        int digit = 7;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 6 challenge
     */
    @Test
    public final void testMultiplyBy10Routine6() {
        NaturalNumber n = this.constructorTest(335000);
        NaturalNumber nExpected = this.constructorRef("3350000");
        int digit = 0;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 7 challenge
     */
    @Test
    public final void testMultiplyBy10Routine7() {
        NaturalNumber n = this.constructorTest(3350000);
        NaturalNumber nExpected = this.constructorRef("33500009");
        int digit = 9;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * challenge. boundary
     */
    @Test
    public final void testMultiplyBy10LargeNumber1() {
        NaturalNumber n = this.constructorTest("12345678900");
        NaturalNumber nExpected = this.constructorRef("123456789005");
        int digit = 5;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * routine 8, boundary
     */
    @Test
    public final void testMultiplyBy10LargeNumber2() {
        NaturalNumber n = this.constructorTest("12345678999");
        NaturalNumber nExpected = this.constructorRef("123456789995");
        int digit = 5;
        n.multiplyBy10(digit);
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for divide by 10.
     */

    //boundary
    @Test
    public final void testDivideBy10boudary() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int digitExpected = 0;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    //routine 1
    @Test
    public final void testDivideBy10Routine1() {
        NaturalNumber n = this.constructorTest("20");
        NaturalNumber nExpected = this.constructorRef("2");
        int digitExpected = 0;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    //boundary, routine 2
    @Test
    public final void testDivideBy10Routine2() {
        NaturalNumber n = this.constructorTest("7");
        NaturalNumber nExpected = this.constructorRef();
        int digitExpected = 7;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine 3
    @Test
    public final void testDivideBy10Routine3() {
        NaturalNumber n = this.constructorTest("36");
        NaturalNumber nExpected = this.constructorRef(3);
        int digitExpected = 6;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine 4, challenge
    @Test
    public final void testDivideBy10Routine4() {
        NaturalNumber n = this.constructorTest(9007);
        NaturalNumber nExpected = this.constructorRef(900);
        int digitExpected = 7;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine 5
    @Test
    public final void testDivideBy10Routine5() {
        NaturalNumber n = this.constructorTest(8753);
        NaturalNumber nExpected = this.constructorRef(875);
        int digitExpected = 3;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine 6, challenge
    @Test
    public final void testDivideBy10Routine6() {
        NaturalNumber n = this.constructorTest(90000);
        NaturalNumber nExpected = this.constructorRef(9000);
        int digitExpected = 0;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine 7
    @Test
    public final void testDivideBy10Routine7() {
        NaturalNumber n = this.constructorTest(9795826);
        NaturalNumber nExpected = this.constructorRef(979582);
        int digitExpected = 6;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // challenge large number 1
    @Test
    public final void testDivideBy10LargeNumbers1() {
        NaturalNumber n = this.constructorTest("12345678990");
        NaturalNumber nExpected = this.constructorRef("1234567899");
        int digitExpected = 0;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    //challenge large number 2
    @Test
    public final void testDivideBy10LargeNumbers2() {
        NaturalNumber n = this.constructorTest("1234567899009");
        NaturalNumber nExpected = this.constructorRef("123456789900");
        int digitExpected = 9;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // challenge large number 3
    @Test
    public final void testDivideBy10LargeNumbers3() {
        NaturalNumber n = this.constructorTest("12345678990");
        NaturalNumber nExpected = this.constructorRef("1234567899");
        int digitExpected = 0;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    // routine large number 4
    @Test
    public final void testDivideBy10LargeNumbers4() {
        NaturalNumber n = this.constructorTest("1234567899876");
        NaturalNumber nExpected = this.constructorRef("123456789987");
        int digitExpected = 6;
        int digit = n.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(digitExpected, digit);
    }

    /*
     * Test cases for isZero
     */

    //boundary
    @Test
    public final void testIsZero0() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        boolean zero = n.isZero();
        assertTrue(zero);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testIsZero8() {
        NaturalNumber n = this.constructorTest(8);
        NaturalNumber nExpected = this.constructorRef(8);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testIsZero43() {
        NaturalNumber n = this.constructorTest(43);
        NaturalNumber nExpected = this.constructorRef(43);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //challenge
    @Test
    public final void testIsZero403() {
        NaturalNumber n = this.constructorTest(403);
        NaturalNumber nExpected = this.constructorRef(403);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //challenge
    @Test
    public final void testIsZero1000() {
        NaturalNumber n = this.constructorTest(1000);
        NaturalNumber nExpected = this.constructorRef(1000);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testIsZero734() {
        NaturalNumber n = this.constructorTest(734);
        NaturalNumber nExpected = this.constructorRef(734);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //routine
    @Test
    public final void testIsZero1345() {
        NaturalNumber n = this.constructorTest(1345);
        NaturalNumber nExpected = this.constructorRef(1345);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //challenge
    @Test
    public final void testIsZero10000007() {
        NaturalNumber n = this.constructorTest(10000007);
        NaturalNumber nExpected = this.constructorRef(10000007);
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //challenge routine
    @Test
    public final void testIsZeroLargeNumbers1() {
        NaturalNumber n = this.constructorTest("1234567897156");
        NaturalNumber nExpected = this.constructorRef("1234567897156");
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

    //challenge routine
    @Test
    public final void testIsZeroLargeNumbers2() {
        NaturalNumber n = this.constructorTest("1234567890100");
        NaturalNumber nExpected = this.constructorRef("1234567890100");
        boolean zero = n.isZero();
        assertFalse(zero);
        assertEquals(nExpected, n);
    }

}
