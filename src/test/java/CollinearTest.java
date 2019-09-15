import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author Lexes Jan Mantiquilla
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    /**
     * Check for no false positives in an even sized array
     */

    @Test
    public void testBinarySearchEven() {
        int[] a = IntStream.range(0, 10).map(x -> 2  * x).toArray();

        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 2));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 8));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 10));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 14));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 18));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 7));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 100));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, -2));
    }

    /**
     * Check for no false positives in an odd sized array
     */

    @Test
    public void testBinarySearchOdd() {
        int[] a = IntStream.range(0, 9).map(x -> 2  * x).toArray();

        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 2));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 8));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 10));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 14));
        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 16));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 7));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 100));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, -2));
    }

    /**
     * Check for no false positives in an array of size 1
     */

    @Test
    public void testBinarySearchSingle() {
        int[] a = {10};

        assertTrue(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, 10));
        assertFalse(String.format("binarySearch(%s)", Arrays.toString(a)), Collinear.binarySearch(a, -2));
    }
}
