import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

// -------------------------------------------------------------------------
/**
 * Test class for Arith
 *
 * @version 1.0 31/01/2020 20:51:11
 * @author Lexes Jan Mantiquilla
 */
@RunWith(JUnit4.class)
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
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"1", "2", "+"},
        Arith.convertPrefixToPostfix(new String[] {"+", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"-1", "-2", "+"},
        Arith.convertPrefixToPostfix(new String[] {"+", "-1", "-2"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"1", "2", "+", "3", "-"},
        Arith.convertPrefixToPostfix(new String[] {"-", "+", "1", "2", "3"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"-1", "-2", "+", "-3", "-"},
        Arith.convertPrefixToPostfix(new String[] {"-", "+", "-1", "-2", "-3"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"-3", "1", "2", "-", "*"},
        Arith.convertPrefixToPostfix(new String[] {"*", "-3", "-", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"3", "1", "2", "-", "*"},
        Arith.convertPrefixToPostfix(new String[] {"*", "3", "-", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"},
        Arith.convertPrefixToPostfix(
            new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
  }

  @Test
  public void testConvertPostfixToPrefix() {
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"+", "1", "2"},
        Arith.convertPostfixToPrefix(new String[] {"1", "2", "+"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"+", "-1", "-2"},
        Arith.convertPostfixToPrefix(new String[] {"-1", "-2", "+"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"-", "+", "1", "2", "3"},
        Arith.convertPostfixToPrefix(new String[] {"1", "2", "+", "3", "-"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"-", "+", "-1", "-2", "-3"},
        Arith.convertPostfixToPrefix(new String[] {"-1", "-2", "+", "-3", "-"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"*", "-3", "-", "1", "2"},
        Arith.convertPostfixToPrefix(new String[] {"-3", "1", "2", "-", "*"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"*", "3", "-", "1", "2"},
        Arith.convertPostfixToPrefix(new String[] {"3", "1", "2", "-", "*"}));
    assertArrayEquals(
        "Converting prefix to postfix",
        new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"},
        Arith.convertPostfixToPrefix(
            new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
  }

  @Test
  public void testConvertPrefixToInfix() {
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "1", "+", "2", ")"},
        Arith.convertPrefixToInfix(new String[] {"+", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "-1", "+", "-2", ")"},
        Arith.convertPrefixToInfix(new String[] {"+", "-1", "-2"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "(", "1", "+", "2", ")", "-", "3", ")"},
        Arith.convertPrefixToInfix(new String[] {"-", "+", "1", "2", "3"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "(", "-1", "+", "-2", ")", "-", "-3", ")"},
        Arith.convertPrefixToInfix(new String[] {"-", "+", "-1", "-2", "-3"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "3", "*", "(", "1", "-", "2", ")", ")"},
        Arith.convertPrefixToInfix(new String[] {"*", "3", "-", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {"(", "-3", "*", "(", "1", "-", "2", ")", ")"},
        Arith.convertPrefixToInfix(new String[] {"*", "-3", "-", "1", "2"}));
    assertArrayEquals(
        "Converting prefix to infix",
        new String[] {
          "(", "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(",
          "6", "/", "3", ")", ")", ")", ")"
        },
        Arith.convertPrefixToInfix(
            new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
  }

  @Test
  public void testConvertPostfixToInfix() {
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "1", "+", "2", ")"},
        Arith.convertPostfixToInfix(new String[] {"1", "2", "+"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "-1", "+", "-2", ")"},
        Arith.convertPostfixToInfix(new String[] {"-1", "-2", "+"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "(", "1", "+", "2", ")", "-", "3", ")"},
        Arith.convertPostfixToInfix(new String[] {"1", "2", "+", "3", "-"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "(", "-1", "+", "-2", ")", "-", "-3", ")"},
        Arith.convertPostfixToInfix(new String[] {"-1", "-2", "+", "-3", "-"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "-3", "*", "(", "1", "-", "2", ")", ")"},
        Arith.convertPostfixToInfix(new String[] {"-3", "1", "2", "-", "*"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {"(", "3", "*", "(", "1", "-", "2", ")", ")"},
        Arith.convertPostfixToInfix(new String[] {"3", "1", "2", "-", "*"}));
    assertArrayEquals(
        "Converting postfix to infix",
        new String[] {
          "(", "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(",
          "6", "/", "3", ")", ")", ")", ")"
        },
        Arith.convertPostfixToInfix(
            new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
  }
}
