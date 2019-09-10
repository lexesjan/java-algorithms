import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class ArithTest {
    @Test
    void testValidatePrefixOrder() {
        assertEquals(Arith.validatePrefixOrder(new String[]{"+", "1", "2"}), true);
        assertEquals(Arith.validatePrefixOrder(new String[]{"+", "1", "2", "-", "4"}), false);

    }

    @Test
    void testValidatePostfixOrder() {
        assertEquals(Arith.validatePostfixOrder(new String[]{"1", "2", "+"}), true);
        assertEquals(Arith.validatePostfixOrder(new String[]{}), false);
    }
}
