import org.junit.Test;

import static org.junit.Assert.*;

public class ArithTest {
  @Test
  public void testValidatePrefixOrder() {
    assertTrue(
        "Validating prefix order with valid input",
        Arith.validatePrefixOrder(new String[] {"+", "1", "2"}));
    assertTrue(
        "Validating prefix order with valid input",
        Arith.validatePrefixOrder(
            new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
    assertFalse(
        "Validating prefix order with invalid input",
        Arith.validatePrefixOrder(new String[] {"+", "1", "2", "-", "4"}));
    assertFalse(
        "Validating prefix order with only a operator",
        Arith.validatePrefixOrder(new String[] {"-"}));
    assertFalse(
        "Validating prefix order with only a operator",
        Arith.validatePrefixOrder(new String[] {"+"}));
  }

  @Test
  public void testValidatePostfixOrder() {
    assertTrue(
        "Validating postfix order with valid input",
        Arith.validatePostfixOrder(new String[] {"1", "2", "+"}));
    assertTrue(
        "Validating postfix order with valid input",
        Arith.validatePostfixOrder(
            new String[] {"3", "3", "1", "-", "*", "3", "6", "/", "3", "+", "10", "-", "+"}));
    assertTrue(
        "Validating postfix order with valid input",
        Arith.validatePostfixOrder(
            new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
    assertFalse(
        "Validating postfix order with invalid input",
        Arith.validatePostfixOrder(new String[] {"1", "-", "2", "3", "+"}));
    assertFalse(
        "Validating postfix order with only a operator",
        Arith.validatePrefixOrder(new String[] {"-"}));
    assertFalse(
        "Validating postfix order with only a operator",
        Arith.validatePrefixOrder(new String[] {"+"}));
  }

  @Test
  public void testEvaluatePrefixOrder() {
    assertEquals(
        "Evaluating postfix order", 3, Arith.evaluatePrefixOrder(new String[] {"+", "1", "2"}));
    assertEquals(
        "Evaluating postfix order", -3, Arith.evaluatePrefixOrder(new String[] {"+", "-1", "-2"}));
    assertEquals(
        "Evaluating postfix order",
        0,
        Arith.evaluatePrefixOrder(new String[] {"-", "+", "1", "2", "3"}));
    assertEquals(
        "Evaluating postfix order",
        0,
        Arith.evaluatePrefixOrder(new String[] {"-", "+", "-1", "-2", "-3"}));
    assertEquals(
        "Evaluating postfix order",
        3,
        Arith.evaluatePrefixOrder(new String[] {"*", "-", "1", "2", "-3"}));
    assertEquals(
        "Evaluating postfix order",
        -3,
        Arith.evaluatePrefixOrder(new String[] {"*", "-", "1", "2", "3"}));
    assertEquals(
        "Evaluating postfix order",
        2,
        Arith.evaluatePrefixOrder(
            new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
  }

  @Test
  public void testEvaluatePostfixOrder() {
    assertEquals(
        "Evaluating postfix order", 3, Arith.evaluatePostfixOrder(new String[] {"1", "2", "+"}));
    assertEquals(
        "Evaluating postfix order", -3, Arith.evaluatePostfixOrder(new String[] {"-1", "-2", "+"}));
    assertEquals(
        "Evaluating postfix order",
        0,
        Arith.evaluatePostfixOrder(new String[] {"1", "2", "+", "3", "-"}));
    assertEquals(
        "Evaluating postfix order",
        0,
        Arith.evaluatePostfixOrder(new String[] {"-1", "-2", "+", "-3", "-"}));
    assertEquals(
        "Evaluating postfix order",
        3,
        Arith.evaluatePostfixOrder(new String[] {"-3", "1", "2", "-", "*"}));
    assertEquals(
        "Evaluating postfix order",
        -3,
        Arith.evaluatePostfixOrder(new String[] {"3", "1", "2", "-", "*"}));
    assertEquals(
        "Evaluating postfix order",
        2,
        Arith.evaluatePostfixOrder(
            new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
  }

  @Test
  public void testConvertPrefixToPostfix() {
    // TODO
  }

  @Test
  public void testConvertPostfixToPrefix() {
    // TODO
  }

  @Test
  public void testConvertPrefixToInfix() {
    // TODO
  }

  @Test
  public void testConvertPostfixToInfix() {
    // TODO
  }
}
