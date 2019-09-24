import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// -------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
  // ~ Constructor ........................................................
  @Test
  public void testConstructor() {
    new DoublyLinkedList<Integer>();
  }

  // ~ Public Methods ........................................................

  // ----------------------------------------------------------
  /** Check if the insertBefore works */
  @Test
  public void testInsertBefore() {
    // test non-empty list
    DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.insertBefore(1, 2);
    testDLL.insertBefore(2, 3);

    testDLL.insertBefore(0, 4);
    assertEquals(
        "Checking insertBefore to a list containing 3 elements at position 0",
        "4,1,2,3",
        testDLL.toString());
    testDLL.insertBefore(1, 5);
    assertEquals(
        "Checking insertBefore to a list containing 4 elements at position 1",
        "4,5,1,2,3",
        testDLL.toString());
    testDLL.insertBefore(2, 6);
    assertEquals(
        "Checking insertBefore to a list containing 5 elements at position 2",
        "4,5,6,1,2,3",
        testDLL.toString());
    testDLL.insertBefore(-1, 7);
    assertEquals(
        "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
        "7,4,5,6,1,2,3",
        testDLL.toString());
    testDLL.insertBefore(7, 8);
    assertEquals(
        "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
        "7,4,5,6,1,2,3,8",
        testDLL.toString());
    testDLL.insertBefore(700, 9);
    assertEquals(
        "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
        "7,4,5,6,1,2,3,8,9",
        testDLL.toString());

    // test empty list
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    assertEquals(
        "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
        "1",
        testDLL.toString());
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(10, 1);
    assertEquals(
        "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
        "1",
        testDLL.toString());
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(-10, 1);
    assertEquals(
        "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
        "1",
        testDLL.toString());
  }

  @Test
  public void testGet() {
    // test non-empty list
    DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.insertBefore(1, 2);
    testDLL.insertBefore(2, 3);
    assertEquals("Checking get to a non-empty list at position 0", (Integer) 1, testDLL.get(0));
    assertEquals("Checking get to a non-empty list at position 1", (Integer) 2, testDLL.get(1));
    assertEquals("Checking get to a non-empty list at position 2", (Integer) 3, testDLL.get(2));
    assertNull("Checking get to an empty list as position -1", testDLL.get(-1));
    assertNull("Checking get to an empty list as position -10", testDLL.get(-10));
    assertNull("Checking get to an empty list as position 3", testDLL.get(3));
    assertNull("Checking get to an empty list as position 10", testDLL.get(10));

    // test list of size 1
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    assertEquals("Checking get to a non-empty list at position 0", (Integer) 1, testDLL.get(0));
    assertNull("Checking get to an empty list as position -1", testDLL.get(-1));
    assertNull("Checking get to an empty list as position -10", testDLL.get(-10));
    assertNull("Checking get to an empty list as position 3", testDLL.get(3));
    assertNull("Checking get to an empty list as position 10", testDLL.get(10));

    // test empty list
    testDLL = new DoublyLinkedList<Integer>();
    assertNull("Checking get to an empty list as position 0", testDLL.get(0));
    assertNull("Checking get to an empty list as position 1", testDLL.get(1));
    assertNull("Checking get to an empty list as position 10", testDLL.get(10));
    assertNull("Checking get to an empty list as position -1", testDLL.get(-1));
    assertNull("Checking get to an empty list as position -10", testDLL.get(-10));
  }

  @Test
  public void testDeleteAt() {
    // test non-empty list
    DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.insertBefore(1, 2);
    testDLL.insertBefore(2, 3);
    testDLL.insertBefore(3, 4);
    testDLL.insertBefore(4, 5);
    assertTrue("Checking deleteAt to a non-empty list at position 0", testDLL.deleteAt(0));
    assertEquals("Checking deleteAt to a non-empty list at position 0", "2,3,4,5", testDLL.toString());
    assertTrue("Checking deleteAt to a non-empty list at position 1", testDLL.deleteAt(1));
    assertEquals("Checking deleteAt to a non-empty list at position 1", "2,4,5", testDLL.toString());
    assertTrue("Checking deleteAt to a non-empty list at position 2", testDLL.deleteAt(2));
    assertEquals("Checking deleteAt to a non-empty list at position 2", "2,4", testDLL.toString());
    assertFalse("Checking deleteAt to an empty list as position -1", testDLL.deleteAt(-1));
    assertFalse("Checking deleteAt to an empty list as position -10", testDLL.deleteAt(-10));
    assertFalse("Checking deleteAt to an empty list as position 3", testDLL.deleteAt(3));
    assertFalse("Checking deleteAt to an empty list as position 10", testDLL.deleteAt(10));
    assertTrue("Checking deleteAt to a non-empty list at position 0", testDLL.deleteAt(0));
    assertEquals("Checking deleteAt to a non-empty list at position 0", "4", testDLL.toString());
    assertTrue("Checking deleteAt to a non-empty list at position 0", testDLL.deleteAt(0));
    assertEquals("Checking deleteAt to a non-empty list at position 0", "", testDLL.toString());


    // test empty list
    testDLL = new DoublyLinkedList<Integer>();
    assertFalse("Checking deleteAt to an empty list as position 0", testDLL.deleteAt(0));
    assertFalse("Checking deleteAt to an empty list as position 1", testDLL.deleteAt(1));
    assertFalse("Checking deleteAt to an empty list as position 10", testDLL.deleteAt(10));
    assertFalse("Checking deleteAt to an empty list as position -1", testDLL.deleteAt(-1));
    assertFalse("Checking deleteAt to an empty list as position -10", testDLL.deleteAt(-10));
  }

  @Test
  public void testReverse() {
    // test odd non-empty list
    DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.insertBefore(1, 2);
    testDLL.insertBefore(2, 3);
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "3,2,1", testDLL.toString());
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "1,2,3", testDLL.toString());

    // test even sized list
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.insertBefore(1, 2);
    testDLL.insertBefore(2, 3);
    testDLL.insertBefore(3, 4);
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "4,3,2,1", testDLL.toString());
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "1,2,3,4", testDLL.toString());

    // test list of size 1
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.insertBefore(0, 1);
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "1", testDLL.toString());
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 3", "1", testDLL.toString());


    // test empty list
    testDLL = new DoublyLinkedList<Integer>();
    testDLL.reverse();
    assertEquals("Checking reverse to a list with the size of 0", "", testDLL.toString());
  }

  @Test
  public void testMakeUnique() {
    // test odd non-empty list
    DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    testDLL.push(1);
    testDLL.push(1);
    testDLL.push(2);
    testDLL.push(2);
    testDLL.push(3);
    testDLL.push(3);
    testDLL.makeUnique();
    assertEquals("Checking makeUnique to a list with the size of 6", "1,2,3", testDLL.toString());
    testDLL.makeUnique();
    assertEquals("Checking makeUnique to a list with the size of 3", "1,2,3", testDLL.toString());

    testDLL = new DoublyLinkedList<Integer>();
    for (int i = 0; i < 1000; i++) {
      testDLL.push(1);
    }
    testDLL.makeUnique();
    assertEquals("Checking makeUnique to a list with the size of 1000", "1", testDLL.toString());

    // test empty list
    testDLL = new DoublyLinkedList<Integer>();
    assertEquals("Checking makeUnique to a list with the size of 0", "", testDLL.toString());
  }
  // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
  // be executed at least once from at least one test.
}
