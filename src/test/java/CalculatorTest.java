import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class CalculatorTest {
    private Calculator calculator;
    private Calculator mockobject;
    private Calculator spycal;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();

    }
    @BeforeEach
    public void setUpSpy() {
        spycal = spy(new Calculator());

    }

    @AfterEach
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdd() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
    @Test
    public void testSubtract() {
        int result = calculator.subtract(2, 3);
        assertEquals(-1, result);
    }
    @Test
    public void testMultiply() {
        int result = calculator.multiply(2, 3);
        assertEquals(6, result);
    }
    @Test
    public void testDivide() {
        int result = calculator.divide(6, 3);
        assertEquals(2, result);
    }

    @Test
    public void testAddWithNegativeNumbers() {
        int result = calculator.add(-5, -10);
        assertEquals(-15, result);
    }

    @Test
    public void testAddWithZero() {
        int result = calculator.add(5, 0);
        assertEquals(5, result);
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testIsPositive(int number) {
        assertTrue(number > 0);
    }

    @Test
    @Disabled
    public void testDisabled() {
        // This test will be disabled and not executed
    }
    @BeforeEach
    public void setUpMock() {
        mockobject = mock(Calculator.class);
    }
    @Test
    public void testAddWithMock() {
        when(mockobject.add(1, 2)).thenReturn(3);
        assertEquals(3, mockobject.add(1, 2));
        verify(mockobject).add(1,2);
}
    @Test
    public void testMulWithSpy() {
        doReturn(10).when(spycal).multiply(2, 5);
        int result = spycal.multiply(2, 5);
        assertEquals(10, result);
        verify(spycal).multiply(2, 5);
    }
    @Test
    public void testWithSpy() {
        int result = spycal.multiply(2, 5);
        assertEquals(10, result);

        verify(spycal).multiply(2, 5);
    }
    @Test
    public void testWithExcept() {
        doThrow(new RuntimeException("Cannot be Implemented")).when(mockobject).divide(6, 3);
        try {
            mockobject.divide(6, 3);

            fail("Expected exception not thrown");
        } catch (RuntimeException e) {
            assertEquals("Cannot be Implemented",e.getMessage());
        }


    }
}