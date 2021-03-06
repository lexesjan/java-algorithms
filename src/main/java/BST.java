/**
 * *********************************************************************** Binary Search Tree class.
 * Adapted from Sedgewick and Wayne.
 *
 * @version 3.0 1/11/15 16:49:42
 * @author Lexes Jan Mantiquilla
 *     <p>***********************************************************************
 */
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
  private Node root; // root of BST

  /** Private node class. */
  private class Node {
    private Key key; // sorted by key
    private Value val; // associated data
    private Node left, right; // left and right subtrees
    private int N; // number of nodes in subtree

    public Node(Key key, Value val, int N) {
      this.key = key;
      this.val = val;
      this.N = N;
    }
  }

  // is the symbol table empty?
  public boolean isEmpty() {
    return size() == 0;
  }

  // return number of key-value pairs in BST
  public int size() {
    return size(root);
  }

  // return number of key-value pairs in BST rooted at x
  private int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }

  /**
   * Search BST for given key. Does there exist a key-value pair with given key?
   *
   * @param key the search key
   * @return true if key is found and false otherwise
   */
  public boolean contains(Key key) {
    return get(key) != null;
  }

  /**
   * Search BST for given key. What is the value associated with given key?
   *
   * @param key the search key
   * @return value associated with the given key if found, or null if no such key exists.
   */
  public Value get(Key key) {
    return get(root, key);
  }

  private Value get(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else return x.val;
  }

  /**
   * Insert key-value pair into BST. If key already exists, update with new value.
   *
   * @param key the key to insert
   * @param val the value associated with key
   */
  public void put(Key key, Value val) {
    if (val == null) {
      delete(key);
      return;
    }
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    x.N = 1 + size(x.left) + size(x.right);
    return x;
  }

  /**
   * Tree height.
   *
   * <p>Asymptotic worst-case running time using Theta notation: Θ(N)
   *
   * Justification: The method is called recursively by each node i.e. it traverses whole tree.
   *
   * @return the number of links from the root to the deepest leaf.
   *     <p>Example 1: for an empty tree this should return -1. Example 2: for a tree with only one
   *     node it should return 0. Example 3: for the following tree it should return 2. B / \ A C \
   *     D
   */
  public int height() {
    return height(root);
  }

  private int height(Node node) {
    if (node == null) return -1;
    int leftHeight = height(node.left);
    int rightHeight = height(node.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  /**
   * Median key. If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key is the
   * element at position (N+1)/2 (where "/" here is integer division)
   *
   * @return the median key, or null if the tree is empty.
   */
  public Key median() {
    if (isEmpty()) return null;
    int middleKeyPos = (size(root) - 1) / 2;
    return select(middleKeyPos);
  }

  /**
   * Returns the rank of the node which matches the key
   *
   * @param key the key of the node you want to get the rank of
   * @return the rank of the node
   */
  public int rank(Key key) {
    return rank(root, key);
  }

  private int rank(Node node, Key key) {
    if (node == null) return 0;
    int cmp = key.compareTo(node.key);
    if (cmp == 0) return size(node.left);
    else if (cmp < 0) return rank(node.left, key);
    return 1 + size(node.left) + rank(node.right, key);
  }

  /**
   * Returns the key of the node which matches the rank inputted
   *
   * @param rank the rank of the node to be searched
   * @return the key of the node which matches the rank inputted
   */
  public Key select(int rank) {
    if (rank < 0 || rank >= size(root)) return null;
    return select(root, rank).key;
  }

  private Node select(Node node, int rank) {
    if (node == null) return null;
    int temp = size(node.left);
    if (rank < temp) return select(node.left, rank);
    else if (rank > temp) return select(node.right, rank - temp - 1);
    return node;
  }

  /**
   * Print all keys of the tree in a sequence, in-order. That is, for each node, the keys in the
   * left subtree should appear before the key in the node. Also, for each node, the keys in the
   * right subtree should appear before the key in the node. For each subtree, its keys should
   * appear within a parenthesis.
   *
   * <p>Example 1: Empty tree -- output: "()" Example 2: Tree containing only "A" -- output:
   * "(()A())" Example 3: Tree: B / \ A C \ D
   *
   * <p>output: "((()A())B(()C(()D())))"
   *
   * <p>output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
   *
   * @return a String with all keys in the tree, in order, parenthesized.
   */
  public String printKeysInOrder() {
    if (isEmpty()) return "()";
    return "(" + printKeysInOrder(root) + ")";
  }

  private String printKeysInOrder(Node node) {
    if (node == null) return "";
    String leftSide = printKeysInOrder(node.left);
    String rightSide = printKeysInOrder(node.right);
    return "(" + leftSide + ")" + node.key + "(" + rightSide + ")";
  }

  /**
   * Pretty Printing the tree. Each node is on one line -- see assignment for details.
   *
   * @return a multi-line string with the pretty ascii picture of the tree.
   */
  public String prettyPrintKeys() {
    return prettyPrint(root, "");
  }

  private String prettyPrint(Node node, String prefix) {
    if (node == null) return prefix + "-null\n";
    String root = prefix + "-" + node.key + "\n";
    String left = prettyPrint(node.left, prefix + " |");
    String right = prettyPrint(node.right, prefix + "  ");
    return root + left + right;
  }

  /**
   * Deletes a key from a tree (if the key is in the tree). Note that this method works
   * symmetrically from the Hibbard deletion: If the node to be deleted has two child nodes, then it
   * needs to be replaced with its predecessor (not its successor) node.
   *
   * @param key the key to delete
   */
  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node delete(Node node, Key key) {
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) node.left = delete(node.left, key);
    else if (cmp > 0) node.right = delete(node.right, key);
    else {
      if (node.right == null) return node.left;
      if (node.left == null) return node.right;

      Node temp = node;
      node = max(temp.left);
      node.left = deleteMax(temp.left);
      node.right = temp.right;
    }
    node.N = size(node.left) + size(node.right) + 1;
    return node;
  }

  /** Deletes the biggest value in the tree */
  public void deleteMax() {
    root = deleteMax(root);
  }

  private Node deleteMax(Node node) {
    if (node == null) return null;
    if (node.right == null) return node.left;
    node.right = deleteMax(node.right);
    node.N = size(node.right) + size(node.left) + 1;
    return node;
  }

  /**
   * Retrieves the maximum value of the tree
   *
   * @return the maximum value in the tree
   */
  public Value max() {
    if (isEmpty()) return null;
    return max(root).val;
  }

  private Node max(Node node) {
    if (node.right != null) return max(node.right);
    return node;
  }
}
