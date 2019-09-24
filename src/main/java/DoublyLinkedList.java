import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 * This class contains the methods of Doubly Linked List.
 *
 * @author Lexes Jan Mantiquilla
 * @version 09/10/18 11:13:22
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 *
 * @param <T> This is a type parameter. T is used as a class name in the definition of this class.
 *     <p>When creating a new DoublyLinkedList, T should be instantiated with an actual class name
 *     that extends the class Comparable. Such classes include String and Integer.
 *     <p>For example to create a new DoublyLinkedList class containing String data:
 *     DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *     <p>The class offers a toString() method which returns a comma-separated sting of all elements
 *     in the data structure.
 *     <p>This is a bare minimum class you would need to completely implement. You can add
 *     additional methods to support your code. Each method will need to be tested by your jUnit
 *     tests -- for simplicity in jUnit testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

  /** private class DLLNode: implements a *generic* Doubly Linked List node. */
  private class DLLNode {
    public final T data; // this field should never be updated. It gets its
    // value once from the constructor DLLNode.
    public DLLNode next;
    public DLLNode prev;

    /**
     * Constructor
     *
     * @param theData : data of type T, to be stored in the node
     * @param prevNode : the previous Node in the Doubly Linked List
     * @param nextNode : the next Node in the Doubly Linked List
     * @return DLLNode
     */
    public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
      data = theData;
      prev = prevNode;
      next = nextNode;
    }
  }

  // Fields head and tail point to the first and last nodes of the list.
  private DLLNode head, tail;
  private int size;

  /**
   * Constructor of an empty DLL
   *
   * @return DoublyLinkedList
   */
  public DoublyLinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Tests if the doubly linked list is empty
   *
   * @return true if list is empty, and false otherwise
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public boolean isEmpty() {
    if (head == null && tail == null) return true;
    return false;
  }

  /**
   * Inserts an element in the doubly linked list
   *
   * @param pos : The integer location at which the new data should be inserted in the list. We
   *     assume that the first position in the list is 0 (zero). If pos is less than 0 then add to
   *     the head of the list. If pos is greater or equal to the size of the list then add the
   *     element at the end of the list.
   * @param data : The new data of class T that needs to be added to the list
   * @return none
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public void insertBefore(int pos, T data) {
    if (!isEmpty()) {
      if (pos <= 0) {
        head.prev = new DLLNode(data, null, head);
        head = head.prev;
      } else if (pos >= size) {
        tail.next = new DLLNode(data, tail, null);
        tail = tail.next;
      } else {
        DLLNode current = head;
        for (int i = 0; i < pos; i++) {
          current = current.next;
        }
        DLLNode previous = current.prev;
        current.prev = new DLLNode(data, previous, current);
        previous.next = current.prev;
      }
    } else {
      head = new DLLNode(data, null, null);
      tail = head;
    }
    size++;
  }

  /**
   * Returns the data stored at a particular position
   *
   * @param pos : the position
   * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public T get(int pos) {
    if (!isEmpty() && isValidPos(pos)) {
      DLLNode current = head;
      for (int i = 0; i < pos; i++) {
        current = current.next;
      }
      return current.data;
    }
    return null;
  }

  /**
   * Deletes the element of the list at position pos. First element in the list has position 0. If
   * pos points outside the elements of the list then no modification happens to the list.
   *
   * @param pos : the position to delete in the list.
   * @return true : on successful deletion, false : list has not been modified.
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public boolean deleteAt(int pos) {
    if (!isEmpty() && isValidPos(pos)) {
      if (size == 1) {
        head = null;
        tail = null;
      } else if (pos == 0) {
        head = head.next;
        head.prev = null;
      } else if (pos == size - 1) {
        tail = tail.prev;
        tail.next = null;
      } else {
        DLLNode current = head;
        for (int i = 0; i < pos; i++) {
          current = current.next;
        }
        DLLNode previous = current.prev;
        previous.next = current.next;
        current.next.prev = previous;
      }
      size--;
      return true;
    }
    return false;
  }

  /**
   * Reverses the list. If the list contains "A", "B", "C", "D" before the method is called Then it
   * should contain "D", "C", "B", "A" after it returns.
   *
   * <p>Worst-case asymptotic running time cost: TODO
   *
   * <p>Justification: TODO
   */
  public void reverse() {
    if (!isEmpty()) {
      DLLNode current = head;
      head = tail;
      tail = current;
      for (int i = 0; i < size; i++) {
        DLLNode temp = current.next;
        current.next = current.prev;
        current.prev = temp;
        current = current.prev;
      }
    }
  }

  /**
   * Removes all duplicate elements from the list. The method should remove the _least_number_ of
   * elements to make all elements uniqueue. If the list contains "A", "B", "C", "B", "D", "A"
   * before the method is called Then it should contain "A", "B", "C", "D" after it returns. The
   * relative order of elements in the resulting list should be the same as the starting list.
   *
   * <p>Worst-case asymptotic running time cost: TODO
   *
   * <p>Justification: TODO
   */
  public void makeUnique() {
    if (!isEmpty()) {
      DoublyLinkedList<T> seen = new DoublyLinkedList<T>();
      DLLNode current = head;
      for (int i = 0; i < size; i++) {
        T data = current.data;
        if (!seen.contains(data)) seen.push(data);
        current = current.next;
      }
      head = seen.head;
      tail = seen.tail;
      size = seen.size;
    }
  }

  /*----------------------- STACK API
   * If only the push and pop methods are called the data structure should behave like a stack.
   */

  /**
   * This method adds an element to the data structure. How exactly this will be represented in the
   * Doubly Linked List is up to the programmer. The tail is the top of the stack.
   *
   * @param item : the item to push on the stack
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public void push(T item) {
    insertBefore(size, item);
  }

  /**
   * This method returns and removes the element that was most recently added by the push method.
   *
   * @return the last item inserted with a push; or null when the list is empty.
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public T pop() {
    int topIndex = size - 1;
    T top = get(topIndex);
    if(deleteAt(topIndex)) return top;
    return null;
  }

  /*----------------------- QUEUE API
   * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
   */

  /**
   * This method adds an element to the data structure. How exactly this will be represented in the
   * Doubly Linked List is up to the programmer. The head is the start of the queue.
   *
   * @param item : the item to be enqueued to the stack
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public void enqueue(T item) {
    push(item);
  }

  /**
   * This method returns and removes the element that was least recently added by the enqueue
   * method.
   *
   * @return the earliest item inserted with an equeue; or null when the list is empty.
   *     <p>Worst-case asymptotic running time cost: TODO
   *     <p>Justification: TODO
   */
  public T dequeue() {
    T start = get(0);
    deleteAt(0);
    return start;
  }

  /**
   * @return a string with the elements of the list as a comma-separated list, from beginning to end
   *     <p>Worst-case asymptotic running time cost: Theta(n)
   *     <p>Justification: We know from the Java documentation that StringBuilder's append() method
   *     runs in Theta(1) asymptotic time. We assume all other method calls here (e.g., the iterator
   *     methods above, and the toString method) will execute in Theta(1) time. Thus, every one
   *     iteration of the for-loop will have cost Theta(1). Suppose the doubly-linked list has 'n'
   *     elements. The for-loop will always iterate over all n elements of the list, and therefore
   *     the total cost of this method will be n*Theta(1) = Theta(n).
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    boolean isFirst = true;

    // iterate over the list, starting from the head
    for (DLLNode iter = head; iter != null; iter = iter.next) {
      if (!isFirst) s.append(",");
      else isFirst = false;
      s.append(iter.data.toString());
    }

    return s.toString();
  }

  /**
   * This method returns the size of the doubly linked list.
   *
   * @return the doubly linked list size.
   *     <p>Worst-case asymptotic running time cost: Θ(1)
   *     <p>Justification: TODO
   */
  public int size() {
    return size;
  }

  /**
   * This method returns if the position exists in the doubly linked list.
   *
   * @return true if position is valid: false if position is invalid.
   *     <p>Worst-case asymptotic running time cost: Θ(1)
   *     <p>Justification: TODO
   */
  private boolean isValidPos(int pos) {
    return (pos >= 0 && pos < size);
  }

  /**
   * This method checks if an element is contained in the doubly linked list
   *
   * @return true if element is found: false if element is not found.
   *     <p>Worst-case asymptotic running time cost: Θ(1)
   *     <p>Justification: TODO
   */
  public boolean contains(T data) {
    if (!isEmpty()) {
      DLLNode current = head;
      for (int i = 0; i < size; i++) {
        if (current.data.equals(data)) return true;
        current = current.next;
      }
    }
    return false;
  }
}
