// -------------------------------------------------------------------------

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
      if (isNumber(literal)) counter--;
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
      if (isNumber(literal)) counter++;
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
      if (!isNumber(token)) {
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
      if (!isNumber(token)) {
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
    Stack<String> stack = new Stack<>();
    for (int i = prefixLiterals.length - 1; i >= 0; i--) {
      String token = prefixLiterals[i];
      if (!isNumber(token)) {
        String expression1 = stack.pop();
        String expression2 = stack.pop();
        String result = String.format("%s %s %s", expression1, expression2, token);
        stack.push(result);
      } else stack.push(token);
    }
    return stack.pop().split(" ");
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
    // TODO
    return null;
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
    // TODO
    return null;
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
    // TODO
    return null;
  }

  // ~ Helper methods ..........................................................

  /**
   * Checks if a number is an integer or not
   *
   * @param input : a string which can be parsed into a number or not
   * @return true if the string is a digit, else false
   */
  public static boolean isNumber(String input) {
    boolean isNegative = input.length() != 1 && input.charAt(0) == '-';
    for (int i = (isNegative) ? 1 : 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (currentChar < '0' || currentChar > '9') return false;
    }
    return true;
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
