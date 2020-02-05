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
  public static double[] insertionSort(double[] a) {
    assert a != null;
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
  public static double[] selectionSort(double[] a) {
    assert a != null;
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
  public static double[] quickSort(double[] a) {
    assert a != null;
    quickSort(a, 0, a.length - 1);
    return a;
  } // end quicksort

  private static void quickSort(double[] a, int low, int high) {
    if (high <= low) return;
    int partitionIndex = partition(a, low, high);
    quickSort(a, low, partitionIndex - 1);
    quickSort(a, partitionIndex + 1, high);
  }

  private static int partition(double[] a, int low, int high) {
    int i = low;
    int j = high + 1;
    double pivot = a[low];
    while (true) {
      while (a[++i] <= pivot) if (i == high) break;
      while (a[--j] >= pivot) if (j == low) break;
      if (j <= i) break;
      swap(a, i, j);
    }
    swap(a, low, j);
    return j;
  }

  /**
   * Sorts an array of doubles using iterative implementation of Merge Sort. This method is static,
   * thus it can be called as SortComparison.sort(a)
   *
   * @param a: An unsorted array of doubles.
   * @return after the method returns, the array must be in ascending sorted order.
   */
  public static double[] mergeSortIterative(double[] a) {
    assert a != null;
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
  public static double[] mergeSortRecursive(double[] a) {
    assert a != null;
    double[] aux = new double[a.length];
    mergeSortRecursive(a, aux, 0, a.length - 1);
    return a;
  } // end mergeSortRecursive

  private static void mergeSortRecursive(double[] a, double[] aux, int low, int high) {
    if (high <= low) return;
    int mid = low + (high - low) / 2;
    mergeSortRecursive(a, aux, low, mid);
    mergeSortRecursive(a, aux, mid + 1, high);
    merge(aux, a, low, mid, high);
  }

  private static void merge(double[] a, double[] aux, int low, int mid, int high) {
    System.arraycopy(a, low, aux, low, high - low + 1);

    int i = low;
    int j = mid + 1;
    for (int k = low; k <= high; k++) {
      if (i > mid) a[k] = aux[j++];
      else if(j > high) a[k] = aux[i++];
      else if(a[j] < aux[i]) a[k] = aux[j++];
      else a[k] = aux[i++];
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int len = scanner.nextInt();
    double[] nums = new double[len];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = scanner.nextDouble();
    }

    long start = System.currentTimeMillis();
    mergeSortRecursive(nums);
    long end = System.currentTimeMillis();
    System.out.printf("Finished in %d ms \n", end - start);
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
} // end class
