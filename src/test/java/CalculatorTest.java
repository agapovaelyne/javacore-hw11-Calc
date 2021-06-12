import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator testCalc = new Calculator();
    double a = 5;
    double b = 2;
    double c = -2;

    @Test
    void newFormula_test1() {
        assertThat(testCalc.newFormula(), instanceOf(Calculator.Formula.class));
    }

    @Test
    void newFormula_test2() {
        assertNotNull(testCalc.newFormula());
    }

    @Test
    void newFormula_test3() {
        Calculator.Formula testFormula = testCalc.newFormula();
        assertNull(testFormula.a);
        assertNull(testFormula.b);
        assertNull(testFormula.result);
    }

    @Test
    void formula_addOperand_test1() {
        Calculator.Formula testFormula = testCalc.newFormula();
        testFormula.addOperand(a);
        assertEquals(a, testFormula.a);
        assertNull(testFormula.b);
        assertNull(testFormula.result);
    }

    @Test
    void formula_addOperand_test2() {
        Calculator.Formula testFormula = testCalc.newFormula();
        testFormula.addOperand(a);
        testFormula.addOperand(b);
        assertEquals(a, testFormula.a);
        assertEquals(b, testFormula.b);
        assertNull(testFormula.result);
    }

    @Test
    void formula_addThirdOperand_test() {
        Calculator.Formula testFormula = testCalc.newFormula();
        testFormula.addOperand(a).addOperand(b);
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testFormula.addOperand(a)
        );
        assertEquals("Formula is full of operands", result.getMessage());
    }

    @Test
    void formula_calculate_noOperands_test() {
        Calculator.Formula testFormula = testCalc.newFormula();
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testFormula.calculate(Calculator.Operation.SUM)
        );
        assertEquals("Not enough operands!", result.getMessage());
    }

    @Test
    void formula_calculate_oneOperand_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a);
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testFormula.calculate(Calculator.Operation.MULT)
        );
        assertEquals("Not enough operands!", result.getMessage());
    }

    @Test
    void formula_result_test1() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.SUM);
        assertEquals(testFormula.result, testFormula.result());
    }

    @Test
    void formula_result_test2() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b);
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testFormula.result()
        );
        assertEquals("Formula is not computed!", result.getMessage());
    }

    @Test
    void formula_result_test3() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a);
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testFormula.result()
        );
        assertEquals("Formula is not computed!", result.getMessage());
    }

    @Test
    void formula_result_test4() {
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> testCalc.newFormula().result()
        );
        assertEquals("Formula is not computed!", result.getMessage());
    }

    @Test
    void formula_calculateSUM__positive_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.SUM);
        assertEquals(7, testFormula.result());
    }

    @Test
    void formula_calculateSUM__negative_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(c).calculate(Calculator.Operation.SUM);
        assertEquals(3, testFormula.result());
    }

    @Test
    void formula_calculateSUB__positive_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.SUB);
        assertEquals(3, testFormula.result());
    }

    @Test
    void formula_calculateSUB__negative_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(c).calculate(Calculator.Operation.SUB);
        assertEquals(7, testFormula.result());
    }

    @Test
    void formula_calculateMULT__positive_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.MULT);
        assertEquals(10, testFormula.result());
    }

    @Test
    void formula_calculateMULT__negative_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(c).calculate(Calculator.Operation.MULT);
        assertEquals(-10, testFormula.result());
    }

    @Test
    void formula_calculateDIV__positive_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.DIV);
        assertEquals(2.5, testFormula.result());
    }

    @Test
    void formula_calculateDIV__negative_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(c).calculate(Calculator.Operation.DIV);
        assertEquals(-2.5, testFormula.result());
    }

    @Test
    void formula_calculatePOW__positive_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(b).calculate(Calculator.Operation.POW);
        assertEquals(25, testFormula.result());
    }

    @Test
    void formula_calculatePOW__negative_test() {
        Calculator.Formula testFormula = testCalc.newFormula().addOperand(a).addOperand(c).calculate(Calculator.Operation.POW);
        assertEquals(0.04, testFormula.result());
    }
}
