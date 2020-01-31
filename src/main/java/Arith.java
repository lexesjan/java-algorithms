// -------------------------------------------------------------------------

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Utility class containing validation/evaluation/conversion operations for prefix and postfix
 * arithmetic expressions.
 *
 * @author Lexes Jan Mantiquilla
 * @version 1/12/15 13:03:48
 */
public class Arith {

  // ~ Validation methods ..........................................................

  /**
   * Validation method for prefix notation.
   *
   * <p>Asymptotic worst-case running time : O(n)
   *
   * <p>Justification: There is a for loop which loops through the prefixLiterals array. Each
   * statement in the for loop is constant. The function isOperator() has a worst case running time
   * complexity of O(1). I chose worst-case running time as it gives a rough estimate of how the
   * program will run at the worst case.
   *
   * @param prefixLiterals : an array containing the string literals hopefully in prefix order. The
   *     method assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a
   *     valid string representation of an integer.
   * @return true if the parameter is indeed in prefix notation, and false otherwise.
   */
  public static boolean validatePrefixOrder(String[] prefixLiterals) {
    int counter = 1;
    for (String literal : prefixLiterals) {
      if (counter <= 0) return false;
      if (!isOperator(literal)) counter--;
      else counter++;
    }
    return counter == 0;
  }

  /**
   * Validation method for postfix notation.
   *
   * <p>Asymptotic worst-case running time : O(n)
   *
   * <p>Justification: There is a for loop which loops through the postfixLiterals array. Each
   * statement in the for loop is constant. The function isOperator() has a worst case running time
   * complexity of O(1). I chose worst-case running time as it gives a rough estimate of how the
   * program will run at the worst case.
   *
   * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
   *     The method assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or
   *     a valid string representation of an integer.
   * @return true if the parameter is indeed in postfix notation, and false otherwise.
   */
  public static boolean validatePostfixOrder(String[] postfixLiterals) {
    int counter = 0;
    for (String literal : postfixLiterals) {
      if (!isOperator(literal)) counter++;
      else counter--;
      if (counter <= 0) return false;
    }
    return true;
  }

  // ~ Evaluation  methods ..........................................................

  /**
   * Evaluation method for prefix notation.
   *
   * <p>Asymptotic amortized running time : O(n)
   *
   * <p>Justification: There is one for loop which loops through the prefixLiterals array. All the
   * methods and statements run in constant time. The function isOperator(), pop() has a worst case
   * running time complexity of O(1). The function push() has an amortized running time complexity
   * of O(1). I chose amortized running time as it gives a rough estimate of how the program will
   * run at the worst case. Since the push function is amortized due to a resizing array
   * implementation, the running time used is amortized.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the integer result of evaluating the expression
   */
  public static int evaluatePrefixOrder(String[] prefixLiterals) {
    if (!validatePrefixOrder(prefixLiterals)) throw new IllegalArgumentException();
    Stack<Integer> stack = new Stack<>();
    for (int i = prefixLiterals.length - 1; i >= 0; i--) {
      String token = prefixLiterals[i];
      if (isOperator(token)) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        int result = applyOperator(num1, num2, token);
        stack.push(result);
      } else stack.push(Integer.parseInt(token));
    }
    return stack.pop();
  }

  /**
   * Evaluation method for postfix notation.
   *
   * <p>Asymptotic amortized running time : O(n)
   *
   * <p>Justification: There is one for loop which loops through the postfixLiterals array. All the
   * methods and statements run in constant time. The function isOperator(), pop() has a worst case
   * running time complexity of O(1). The function push() has an amortized running time complexity
   * of O(1). I chose amortized running time as it gives a rough estimate of how the program will
   * run at the worst case. Since the push function is amortized due to a resizing array
   * implementation, the running time used is amortized.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the integer result of evaluating the expression
   */
  public static int evaluatePostfixOrder(String[] postfixLiterals) {
    if (!validatePostfixOrder(postfixLiterals)) throw new IllegalArgumentException();
    Stack<Integer> stack = new Stack<>();
    for (String token : postfixLiterals) {
      if (isOperator(token)) {
        int num2 = stack.pop();
        int num1 = stack.pop();
        int result = applyOperator(num1, num2, token);
        stack.push(result);
      } else stack.push(Integer.parseInt(token));
    }
    return stack.pop();
  }

  // ~ Conversion  methods ..........................................................

  /**
   * Converts prefix to postfix.
   *
   * <p>Asymptotic amortized running time : O(n^2)
   *
   * <p>Justification: There is a for loop which loops through all the prefixLiterals. Within the
   * for loop all the statements have a worst case or amortized running time complexity of O(1)
   * except for the new LinkedList<>(Collection<? extends E> c) and addAll(Collection<? extends E>
   * c) function which have a worst case running time complexity of O(sizeof(c)). Since sizeof(c)
   * can be n, the final amortized running time complexity is O(n^2). I chose amortized running time
   * as it gives a rough estimate of how the program will run at the worst case. Since the push
   * function is amortized due to a resizing array implementation, the running time used is
   * amortized.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the expression in postfix order.
   */
  public static String[] convertPrefixToPostfix(String[] prefixLiterals) {
    if (!validatePrefixOrder(prefixLiterals)) throw new IllegalArgumentException();
    Stack<List<String>> stack = new Stack<>();
    for (int i = prefixLiterals.length - 1; i >= 0; i--) {
      String token = prefixLiterals[i];
      if (isOperator(token)) {
        List<String> expression1 = stack.pop();
        List<String> expression2 = stack.pop();
        List<String> result = new LinkedList<>(expression1);
        result.addAll(expression2);
        result.add(token);
        stack.push(result);
      } else {
        List<String> literalList = new LinkedList<>();
        literalList.add(token);
        stack.push(literalList);
      }
    }
    return stack.pop().toArray(new String[] {});
  }

  /**
   * Converts postfix to prefix.
   *
   * <p>Asymptotic amortized running time : O(n^2)
   *
   * <p>Justification: There is a for loop which loops through all the postfixLiterals. Within the
   * for loop all the statements have a worst case or amortized running time complexity of O(1)
   * except for the new LinkedList<>(Collection<? extends E> c) and addAll(Collection<? extends E>
   * c) function which have a worst case running time complexity of O(sizeof(c)). Since sizeof(c)
   * can be n, the final amortized running time complexity is O(n^2). I chose amortized running time
   * as it gives a rough estimate of how the program will run at the worst case. Since the push
   * function is amortized due to a resizing array implementation, the running time used is
   * amortized.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the expression in prefix order.
   */
  public static String[] convertPostfixToPrefix(String[] postfixLiterals) {
    Stack<List<String>> stack = new Stack<>();
    for (String token : postfixLiterals) {
      if (isOperator(token)) {
        List<String> expression2 = stack.pop();
        List<String> expression1 = stack.pop();
        List<String> result = new LinkedList<>();
        result.add(token);
        result.addAll(expression1);
        result.addAll(expression2);
        stack.push(result);
      } else {
        List<String> operand = new LinkedList<>();
        operand.add(token);
        stack.push(operand);
      }
    }
    return stack.pop().toArray(new String[] {});
  }

  /**
   * Converts prefix to infix.
   *
   * <p>Asymptotic amortized running time : O(n^2)
   *
   * <p>Justification: There is a for loop which loops through all the prefixLiterals. Within the
   * for loop all the statements have a worst case or amortized running time complexity of O(1)
   * except for the new LinkedList<>(Collection<? extends E> c) and addAll(Collection<? extends E>
   * c) function which have a worst case running time complexity of O(sizeof(c)). Since sizeof(c)
   * can be n, the final amortized running time complexity is O(n^2). I chose amortized running time
   * as it gives a rough estimate of how the program will run at the worst case. Since the push
   * function is amortized due to a resizing array implementation, the running time used is
   * amortized.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the expression in infix order.
   */
  public static String[] convertPrefixToInfix(String[] prefixLiterals) {
    if (!validatePrefixOrder(prefixLiterals)) throw new IllegalArgumentException();
    Stack<List<String>> stack = new Stack<>();
    for (int i = prefixLiterals.length - 1; i >= 0; i--) {
      String token = prefixLiterals[i];
      if (isOperator(token)) {
        List<String> expression1 = stack.pop();
        List<String> expression2 = stack.pop();
        List<String> result = new LinkedList<>();
        result.add("(");
        result.addAll(expression1);
        result.add(token);
        result.addAll(expression2);
        result.add(")");
        stack.push(result);
      } else {
        List<String> operand = new LinkedList<>();
        operand.add(token);
        stack.push(operand);
      }
    }
    return stack.pop().toArray(new String[] {});
  }

  /**
   * Converts postfix to infix.
   *
   * <p>Asymptotic amortized running time : O(n^2)
   *
   * <p>Justification: There is a for loop which loops through all the postfixLiterals. Within the
   * for loop all the statements have a worst case or amortized running time complexity of O(1)
   * except for the new LinkedList<>(Collection<? extends E> c) and addAll(Collection<? extends E>
   * c) function which have a worst case running time complexity of O(sizeof(c)). Since sizeof(c)
   * can be n, the final amortized running time complexity is O(n^2). I chose amortized running time
   * as it gives a rough estimate of how the program will run at the worst case. Since the push
   * function is amortized due to a resizing array implementation, the running time used is
   * amortized.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the expression in infix order.
   */
  public static String[] convertPostfixToInfix(String[] postfixLiterals) {
    if (!validatePostfixOrder(postfixLiterals)) throw new IllegalArgumentException();
    Stack<List<String>> stack = new Stack<>();
    for (String token : postfixLiterals) {
      if (isOperator(token)) {
        List<String> expression2 = stack.pop();
        List<String> expression1 = stack.pop();
        List<String> result = new LinkedList<>();
        result.add("(");
        result.addAll(expression1);
        result.add(token);
        result.addAll(expression2);
        result.add(")");
        stack.push(result);
      } else {
        List<String> operand = new LinkedList<>();
        operand.add(token);
        stack.push(operand);
      }
    }
    return stack.pop().toArray(new String[] {});
  }

  // ~ Helper methods ..........................................................

  /**
   * Checks if a string is an operator or not
   *
   * <p>Asymptotic worst-case running time : O(1)
   *
   * <p>Justification: switch statements have a worst run time complexity of O(1)
   *
   * @param input the input string
   * @return true if the string is an operator, false if it's not
   */
  private static boolean isOperator(String input) {
    switch (input) {
      case "+":
      case "-":
      case "/":
      case "*":
        return true;
    }
    return false;
  }

  /**
   * Applies the operator on num1 and num2
   *
   * <p>Asymptotic worst-case running time : O(1)
   *
   * <p>Justification: switch statements have a worst run time complexity of O(1)
   *
   * @param num1 the first number
   * @param num2 the second number
   * @param operator the operator to apply the first and second number
   * @return the result of the operator being applied to the two operands
   */
  private static int applyOperator(int num1, int num2, String operator) {
    switch (operator) {
      case "+":
        return num1 + num2;
      case "-":
        return num1 - num2;
      case "/":
        return num1 / num2;
      case "*":
        return num1 * num2;
    }
    throw new IllegalArgumentException();
  }
}
// ~ Research ...................................................................

/*
 *  Stack data structure:
 *    Worst run time complexities:
 *      * public synchronized E pop() - O(1)
 *      * public Stack() - O(1)
 *    Amortized run time complexities:
 *      * public E push(E item) - O(1)
 *
 *  LinkedList data structure:
 *    Worst run time complexities:
 *      * public LinkedList() - O(1)
 *      * public LinkedList(Collection<? extends E> c) - O(sizeof(c))
 *      * public boolean addAll(Collection<? extends E> c) - O(sizeof(c))
 *      * public void add(int index, E element) O(1)
 */

// ~ Sources ....................................................................

/*
 * http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/util/Stack.java
 * http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/util/LinkedList.java
 */
