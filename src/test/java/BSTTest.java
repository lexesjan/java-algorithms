import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// -------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @version 3.1 09/11/15 11:32:15
 * @author Lexes Jan Mantiquilla
 */
@RunWith(JUnit4.class)
public class BSTTest {
  /** Test {@link BST#prettyPrintKeys()}. */
  @Test
  public void testPrettyPrint() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());

    //  -7
    //   |-3
    //   | |-1
    //   | | |-null
    bst.put(7, 7); //   | |  -2
    bst.put(8, 8); //   | |   |-null
    bst.put(3, 3); //   | |    -null
    bst.put(1, 1); //   |  -6
    bst.put(2, 2); //   |   |-4
    bst.put(6, 6); //   |   | |-null
    bst.put(4, 4); //   |   |  -5
    bst.put(5, 5); //   |   |   |-null
    //   |   |    -null
    //   |    -null
    //    -8
    //     |-null
    //      -null

    String result =
        "-7\n"
            + " |-3\n"
            + " | |-1\n"
            + " | | |-null\n"
            + " | |  -2\n"
            + " | |   |-null\n"
            + " | |    -null\n"
            + " |  -6\n"
            + " |   |-4\n"
            + " |   | |-null\n"
            + " |   |  -5\n"
            + " |   |   |-null\n"
            + " |   |    -null\n"
            + " |    -null\n"
            + "  -8\n"
            + "   |-null\n"
            + "    -null\n";
    assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
  }

  /** Test {@link BST#delete(Comparable)}. */
  @Test
  public void testDelete() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    bst.delete(1);
    assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

    bst.put(7, 7);
    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */

    assertEquals(
        "Checking order of constructed tree",
        "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
        bst.printKeysInOrder());

    bst.delete(9);
    assertEquals(
        "Deleting non-existent key",
        "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
        bst.printKeysInOrder());

    bst.delete(8);
    assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

    bst.delete(6);
    assertEquals(
        "Deleting node with single child",
        "(((()1(()2()))3(()4(()5())))7())",
        bst.printKeysInOrder());

    bst.delete(3);
    assertEquals(
        "Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
  }

  @Test
  public void testHeight() {
    BST<Integer, Integer> bst = new BST<>();

    assertEquals("Getting height of empty tree", -1, bst.height());

    bst.put(7, 7);

    /*
    7
    */

    assertEquals("Getting height of tree with one node", 0, bst.height());

    bst.put(8, 8);
    bst.put(3, 3);

    /*
       _7_
     /     \
    3       8
    */

    assertEquals("Getting height of tree of 1", 1, bst.height());

    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);

    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */

    assertEquals("Getting height of 4", 4, bst.height());
  }

  @Test
  public void testPrintInOrder() {
    BST<Character, Character> bst = new BST<>();

    assertEquals("Printing in order empty tree", "()", bst.printKeysInOrder());

    bst.put('A', 'A');

    /*
    A
    */

    assertEquals("Printing in order with tree of A", "(()A())", bst.printKeysInOrder());

    bst = new BST<>();

    bst.put('B', 'B');
    bst.put('A', 'A');
    bst.put('C', 'C');
    bst.put('D', 'D');

    /*
      B
     / \
    A   C
         \
          D
    */

    bst = new BST<>();

    bst.put('S', 'S');
    bst.put('X', 'X');
    bst.put('E', 'E');
    bst.put('A', 'A');
    bst.put('R', 'R');
    bst.put('C', 'C');
    bst.put('H', 'H');
    bst.put('M', 'M');

    /*
              _S_
            /     \
          _E_      X
        /     \
       A       R
        \     /
         C   H
              \
               M
    */

    assertEquals(
        "Printing in order with tree of A",
        "(((()A(()C()))E((()H(()M()))R()))S(()X()))",
        bst.printKeysInOrder());
  }

  @Test
  public void testRank() {
    BST<Character, Character> bst = new BST<>();

    assertEquals("Getting rank of empty list", 0, bst.rank('?'));

    bst.put('S', 'S');
    bst.put('X', 'X');
    bst.put('E', 'E');
    bst.put('A', 'A');
    bst.put('R', 'R');
    bst.put('C', 'C');
    bst.put('H', 'H');
    bst.put('M', 'M');

    /*
              _S_
            /     \
          _E_      X
        /     \
       A       R
        \     /
         C   H
              \
               M
    */

    assertEquals("Getting rank of A", 0, bst.rank('A'));
    assertEquals("Getting rank of S", 6, bst.rank('S'));
    assertEquals("Getting rank of X", 7, bst.rank('X'));
    assertEquals("Getting rank of X", 5, bst.rank('R'));
  }

  @Test
  public void testSelect() {
    BST<Character, Character> bst = new BST<>();

    assertNull("Selecting key in an empty tree", bst.select(0));

    bst.put('S', 'S');
    bst.put('X', 'X');
    bst.put('E', 'E');
    bst.put('A', 'A');
    bst.put('R', 'R');
    bst.put('C', 'C');
    bst.put('H', 'H');
    bst.put('M', 'M');

    /*
              _S_
            /     \
          _E_      X
        /     \
       A       R
        \     /
         C   H
              \
               M
    */

    assertNull("Selecting key of rank -1", bst.select(-1));
    assertEquals("Selecting key of rank 2", new Character('E'), bst.select(2));
    assertEquals("Selecting key of rank 6", new Character('R'), bst.select(5));
    assertEquals("Selecting key of rank 6", new Character('S'), bst.select(6));
    assertEquals("Selecting key of rank 7", new Character('X'), bst.select(7));
    assertNull("Selecting key of rank 8", bst.select(8));
  }

  @Test
  public void testMedian() {
    BST<Character, Character> bst = new BST<>();

    assertNull("Getting median of empty list", bst.median());

    bst.put('S', 'S');
    bst.put('X', 'X');
    bst.put('E', 'E');
    bst.put('A', 'A');
    bst.put('R', 'R');
    bst.put('C', 'C');
    bst.put('H', 'H');
    bst.put('M', 'M');

    /*
              _S_
            /     \
          _E_      X
        /     \
       A       R
        \     /
         C   H
              \
               M
    */

    assertEquals("Getting median of tree", new Character('H'), bst.median());

    bst = new BST<>();

    bst.put('S', 'S');
    bst.put('E', 'E');
    bst.put('A', 'A');
    bst.put('R', 'R');
    bst.put('C', 'C');
    bst.put('H', 'H');
    bst.put('M', 'M');

    /*
             S
            /
          _E_
        /     \
       A       R
        \     /
         C   H
              \
               M
    */

    assertEquals("Getting median of tree", new Character('H'), bst.median());
  }

  @Test
  public void testMax() {
    BST<Integer, Integer> bst = new BST<>();

    assertNull("Getting max of empty tree", bst.max());

    bst.put(7, 7);

    assertEquals("Getting max of tree with max 7", new Integer(7), bst.max());

    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */

    assertEquals("Getting max of tree with max 8", new Integer(8), bst.max());
  }

  @Test
  public void testDeleteMax() {
    BST<Integer, Integer> bst = new BST<>();

    bst.deleteMax();
    assertEquals("Deleting max of an empty tree", "()", bst.printKeysInOrder());

    bst.put(7, 7);

    bst.deleteMax();
    assertEquals("Deleting max of a tree with one node", "()", bst.printKeysInOrder());

    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
          8
         /
       _3_
     /     \
    1       6
     \     /
      2   4
           \
            5
    */
    bst.deleteMax();
    assertEquals(
        "Deleting max of a tree", "((()1(()2()))3((()4(()5()))6()))", bst.printKeysInOrder());
  }

  @Test
  public void testContains() {
    BST<Integer, Integer> bst = new BST<>();

    assertFalse("Test contains on empty tree", bst.contains(0));

    bst.put(7, 7);
    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */
    for (int i = 1; i <= 8; i++) assertTrue("Checking if tree contains " + i, bst.contains(i));
    assertFalse("Check if contains with int not in tree", bst.contains(0));
  }

  @Test
  public void testPut() {
    BST<Integer, Integer> bst = new BST<>();

    bst.put(7, 7);
    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */

    assertEquals(
        "Testing put", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

    bst.put(7, 7);
    assertEquals(
        "Testing put with the same key and value",
        "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
        bst.printKeysInOrder());

    bst.put(7, 8);
    assertEquals("Changing value with put", bst.get(7), new Integer(8));

    /*
           _6_
         /     \
       _3_      8
     /     \
    1       4
     \       \
      2       5
    */

    bst.put(7, null);
    assertEquals(
        "Putting null value", "(((()1(()2()))3(()4(()5())))6(()8()))", bst.printKeysInOrder());
  }

  @Test
  public void testGet() {
    BST<Integer, Integer> bst = new BST<>();

    assertNull("Testing get empty tree", bst.get(0));

    bst.put(7, 7);
    bst.put(8, 8);
    bst.put(3, 3);
    bst.put(1, 1);
    bst.put(2, 2);
    bst.put(6, 6);
    bst.put(4, 4);
    bst.put(5, 5);
    /*
           _7_
         /     \
       _3_      8
     /     \
    1       6
     \     /
      2   4
           \
            5
    */

    for (int i = 1; i <= 8; i++)
      assertEquals("Testing get with the key " + i, bst.get(i), new Integer(i));
    assertNull("Testing get with key not in tree", bst.get(0));
  }
}
