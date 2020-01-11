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
}
