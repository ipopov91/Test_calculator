import com.it_academy.practice.junit_basics.Calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTest {
    Calculator calculator = new Calculator(10, 5);
    Calculator calculator1 = new Calculator(-10, 5);
    Calculator calculator2 = new Calculator(-10, -5);
    Calculator calculator3 = new Calculator(4, 2);
    Calculator calculator4 = new Calculator(8, 1);
    Calculator calculator5 = new Calculator(8, 0);

    @Test
    void testPositive() {
        assertEquals(5.0, calculator.calculate('-'));
        assertEquals(15.0, calculator.calculate('+'));
        assertEquals(50.0, calculator.calculate('*'));
        assertEquals(2.0, calculator.calculate('/'));
    }

    @Test
    void testOneNegativeNumber() {
        assertEquals(-15.0, calculator1.calculate('-'));
        assertEquals(-5.0, calculator1.calculate('+'));
        assertEquals(-50.0, calculator1.calculate('*'));
        assertEquals(-2.0, calculator1.calculate('/'));
    }

    @Test
    void testTwoNegativeNumbers() {
        assertEquals(-5.0, calculator2.calculate('-'));
        assertEquals(-15.0, calculator2.calculate('+'));
        assertEquals(50.0, calculator2.calculate('*'));
        assertEquals(2.0, calculator2.calculate('/'));
    }

    @Test
    void testException() throws ArithmeticException {
        Exception exception = assertThrows(ArithmeticException.class, () -> new Calculator(8, 0).calculate('/'));
        System.out.println(exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argument")
    void data(float expected, int a, int b, char c) {
        assertEquals(expected, new Calculator(a, b).calculate(c));
    }

    private static Stream<Arguments> argument() {
        return Stream.of(
                Arguments.of(110, 10, 100, '+'),
                Arguments.of(9, 10, 1, '-'),
                Arguments.of(2, 100, 50, '/'),
                Arguments.of(100, 20, 5, '*')
        );
    }

    @Test
    void testDegree() {
        assertEquals(100000.0, calculator.calculate('^'));
    }

    @Test
    void testRoot() {
        assertEquals(2.0, calculator3.calculate('v'));
    }

    @Test
    void testRootNegativeNumber() {
        assertEquals(Float.NaN, calculator1.calculate('v'));
    }

    @Test
    void testDegreeOne() {
        assertEquals(8.0, calculator4.calculate('^'));
    }

    @Test
    void testDegreeNull() {
        assertEquals(1, calculator5.calculate('^'));
    }
}