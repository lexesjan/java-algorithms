// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for prefix and postfix arithmetic expressions.
 *
 *  @author  
 *  @version 1/12/15 13:03:48
 */

public class Arith 
{


  //~ Validation methods ..........................................................


  /**
   * Validation method for prefix notation.
   *
   * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return true if the parameter is indeed in prefix notation, and false otherwise.
   **/
  public static boolean validatePrefixOrder(String prefixLiterals[])
  {
    int counter = 1;
    for (int i = 0; i < prefixLiterals.length; i++) {
      String literal = prefixLiterals[i];
      if(isNumber(literal))
        counter--;
      else
        counter++;
      if (counter < 0)
        return false;
      if (counter == 0 && i != prefixLiterals.length - 1)
        return false;
    }
    return true;
  }


  /**
   * Validation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return true if the parameter is indeed in postfix notation, and false otherwise.
   **/
  public static boolean validatePostfixOrder(String postfixLiterals[])
  {
    int counter = 0;
    for (int i = 0; i < postfixLiterals.length; i++) {
      String literal = postfixLiterals[i];
      if(isNumber(literal))
        counter++;
      else
        counter--;
      if (counter <= 0)
        return false;
    }
    return true;
  }


  //~ Evaluation  methods ..........................................................


  /**
   * Evaluation method for prefix notation.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the integer result of evaluating the expression
   **/
  public static int evaluatePrefixOrder(String prefixLiterals[])
  {
    //TODO
    return -1;
  }


  /**
   * Evaluation method for postfix notation.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the integer result of evaluating the expression
   **/
  public static int evaluatePostfixOrder(String postfixLiterals[])
  {
    //TODO
    return -1;
  }


  //~ Conversion  methods ..........................................................


  /**
   * Converts prefix to postfix.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in postfix order.
   **/
  public static String[] convertPrefixToPostfix(String prefixLiterals[])
  {
    //TODO
    return null;
  }


  /**
   * Converts postfix to prefix.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in prefix order.
   **/
  public static String[] convertPostfixToPrefix(String postfixLiterals[])
  {
    //TODO
    return null;
  }

  /**
   * Converts prefix to infix.
   *
   * @param prefixLiterals : an array containing the string literals in prefix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in infix order.
   **/
  public static String[] convertPrefixToInfix(String prefixLiterals[])
  {
    //TODO
    return null;
  }

  /**
   * Converts postfix to infix.
   *
   * @param postfixLiterals : an array containing the string literals in postfix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in infix order.
   **/
  public static String[] convertPostfixToInfix(String postfixLiterals[])
  {
    //TODO
    return null;
  }

  /**
   * Converts postfix to infix.
   *
   * @param input : a string which can be parsed into a number or not
   *
   * @return true if the string is a digit, else false
   **/

  //~ Helper methods ..........................................................

  private static boolean isNumber(String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(0);
      if (currentChar < '0' || currentChar > '9')
        return false;
    }
    return true;
  }

}
