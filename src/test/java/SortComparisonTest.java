import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// -------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Lexes Jan Mantiquilla
 * @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
  // ~ Constructor ........................................................
  @Test
  public void testConstructor() {
    new SortComparison();
  }

  // ~ Public Methods ........................................................

  // ----------------------------------------------------------
  /** Check that the methods work for empty arrays */
  @Test
  public void testEmpty() {}

  // ----------------------------------------------------------
  /**
   * Main Method. Use this main method to create the experiments needed to answer the experimental
   * performance questions of this assignment.
   */
  public static void main(String[] args) {
    // TODO: implement this method
  }

  // ~ Helper Methods ........................................................
  /**
   * Checks of the array is sorted
   *
   * @param arr input array
   * @return true of the array is sorted else false
   */
  private static boolean isSorted(double[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1]) return false;
    }
    return true;
  }
}
