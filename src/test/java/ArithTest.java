import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class ArithTest {
    @Test
    void testValidatePrefixOrder() {
        assertTrue(Arith.validatePrefixOrder(new String[]{"+", "1", "2"}));
        assertTrue(Arith.validatePrefixOrder(new String[]{"+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3"}));
        assertFalse(Arith.validatePrefixOrder(new String[]{"+", "1", "2", "-", "4"}));

    }

    @Test
    void testValidatePostfixOrder() {
        assertTrue(Arith.validatePostfixOrder(new String[]{"1", "2", "+"}));
        assertTrue(Arith.validatePostfixOrder(new String[]{"3", "3", "1", "-", "*", "3", "6", "/", "3", "+", "10", "-", "+"}));
        assertFalse(Arith.validatePostfixOrder(new String[]{"1", "-", "2", "3", "+"}));
    }
}
