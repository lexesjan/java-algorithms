import org.junit.Test;

import static org.junit.Assert.*;

public class ArithTest {
  @Test
  public void testValidatePrefixOrder() {
    assertTrue(Arith.validatePrefixOrder(new String[] {"+", "1", "2"}));
    assertTrue(
        Arith.validatePrefixOrder(
            new String[] {"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
    assertFalse(Arith.validatePrefixOrder(new String[] {"+", "1", "2", "-", "4"}));
  }

  @Test
  public void testValidatePostfixOrder() {
    assertTrue(Arith.validatePostfixOrder(new String[] {"1", "2", "+"}));
    assertTrue(
        Arith.validatePostfixOrder(
            new String[] {"3", "3", "1", "-", "*", "3", "6", "/", "3", "+", "10", "-", "+"}));
    assertFalse(Arith.validatePostfixOrder(new String[] {"1", "-", "2", "3", "+"}));
  }
}
