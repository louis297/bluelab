import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ConverterTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(expected = ConverterException.class)
    public void convert() throws ConverterException {
        Converter converter = new Converter();

        assertEquals(converter.Convert(20.0, "Celsius", "Kelvin"), 293.15, 0.000001);
        assertEquals(converter.Convert(22.3, "Celsius", "Fahrenheit"), 72.14, 0.000001);
        assertEquals(converter.Convert(23, "Litre", "Millilitre"), 23000, 0.000001);
        assertEquals(converter.Convert(45345, "Millilitre", "Gallon"), 11.97892, 0.000001);

        converter.Convert(20, "Kelvin", "Millilitre");
        fail("Converting between units in mixing categories");

        exceptionRule.expect(ConverterException.class);
        exceptionRule.expectMessage("The source type Kelvin and target type Millilitre are not belonged to the same category.");
    }



}