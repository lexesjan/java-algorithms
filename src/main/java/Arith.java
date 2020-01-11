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
