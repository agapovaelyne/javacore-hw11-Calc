import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class IntsCalculatorTest {
    private Ints intsCalc = new IntsCalculator();
    private int a = 5;
    private int b = 2;
    private int c = -2;

    @Test
    void sum_positive_test() {
        assertEquals(intsCalc.sum(a, b), 7);
    }

    @Test
    void sum_negative_test() {
        assertEquals(intsCalc.sum(a, c), 3);
    }

    @Test
    void mult_positive_test() {
        assertEquals(intsCalc.mult(a, b), 10);
    }

    @Test
    void mult_negative_test() {
        assertEquals(intsCalc.mult(a, c), -10);
    }

    @Test
    void pow_positive_test() {
        assertEquals(intsCalc.pow(a, b), 25);
    }

    @Test
    void pow_negative_test() {
        assertEquals(intsCalc.pow(a, c), 0);
    }
}
