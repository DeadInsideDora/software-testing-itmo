package first_task;

import org.dallvoro.first_task.Arcsin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArcsinTests {
    @ParameterizedTest
    @ValueSource(doubles = {-1., -0.999998, -0.5, -Double.MIN_VALUE, 0., Double.MIN_VALUE, 0.5, 0.9999799, 1.})
    public void arcsin_ParameterizedTest(double value) {
        assertEquals(new Arcsin(150).calculate(value), Math.asin(value), 0.047);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-98., -1.000001, 1703.1992, 1929., Double.MAX_VALUE, Double.NaN})
    public void arcsin_ThrowsIllegalArgument(double value) {
        assertThrows(IllegalArgumentException.class, () -> new Arcsin(100).calculate(value));
    }
}
