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
      double key = a[i];
      int j = i - 1;
      while (j >= 0 && key <= a[j]) {
        a[j] = a[j + 1];
        j--;
      }
      a[j + 1] = key;
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
    for (int i = 0; i < a.length - 1; i++) {
      int minIndex = findMin(a, i);
      swap(a, minIndex, i);
    }
    return a;
  } // end selectionsort

  /**
   * Finds the index of the smallest element in the array
   *
   * @param arr the input array
   * @param start starting index of the search
   * @return the index of the smallest element
   */
  private static int findMin(double[] arr, int start) {
    int minIndex = start;
    double min = arr[start];
    for (int i = start; i < arr.length; i++) {
      double curr = arr[i];
      if (curr < min) {
        min = arr[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

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

  /**
   * Swaps the contents of index1 and index2 in arr
   *
   * @param arr the array to complete the swap on
   * @param index1 first index to swap with
   * @param index2 second index to swap with
   */
  private static void swap(double[] arr, int index1, int index2) {
    double temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

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
} // end class
