// -------------------------------------------------------------------------

import java.util.Scanner;

/**
 * This class contains static methods that implementing sorting of an array of numbers using
 * different sort algorithms.
 *
 * @author Lexes Jan Mantiquilla
 * @version HT 2020
 */
class SortComparison {

  /**
   * Sorts an array of doubles using InsertionSort. This method is static, thus it can be called as
   * SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return array sorted in ascending order.
   */
  static double[] insertionSort(double[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (a[j] < a[j - 1]) swap(a, j - 1, j);
        else break;
      }
    }
    return a;
  } // end insertionsort

  /**
   * Sorts an array of doubles using Selection Sort. This method is static, thus it can be called as
   * SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return array sorted in ascending order
   */
  static double[] selectionSort(double[] a) {

    // todo: implement the sort
    return null;
  } // end selectionsort

  /**
   * Sorts an array of doubles using Quick Sort. This method is static, thus it can be called as
   * SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return array sorted in ascending order
   */
  static double[] quickSort(double[] a) {

    // todo: implement the sort
    return null;
  } // end quicksort

  /**
   * Sorts an array of doubles using iterative implementation of Merge Sort. This method is static,
   * thus it can be called as SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return after the method returns, the array must be in ascending sorted order.
   */
  static double[] mergeSortIterative(double[] a) {

    // todo: implement the sort
    return null;
  } // end mergesortIterative

  /**
   * Sorts an array of doubles using recursive implementation of Merge Sort. This method is static,
   * thus it can be called as SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return after the method returns, the array must be in ascending sorted order.
   */
  static double[] mergeSortRecursive(double[] a) {

    // todo: implement the sort
    return null;
  } // end mergeSortRecursive

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int len = scanner.nextInt();
    double[] nums = new double[len];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = scanner.nextDouble();
    }

    long start = System.currentTimeMillis();
    insertionSort(nums);
    long end = System.currentTimeMillis();
    System.out.printf("Finished in %d ms \n", end - start);
    System.out.println(isSorted(nums));
    scanner.close();
    // todo: do experiments as per assignment instructions
  }

  // ~ Helper Methods ........................................................
  private static void swap(double[] arr, int index1, int index2) {
    double temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

  private static boolean isSorted(double[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1]) return false;
    }
    return true;
  }
} // end class
