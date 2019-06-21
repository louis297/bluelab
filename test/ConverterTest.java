import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    @Test
    public void convert() {
        Converter converter = new Converter();

        assertEquals(converter.Convert(20.0, "Celsius", "Kelvin"), 293.15, 0.000001);
        assertEquals(converter.Convert(22.3, "Celsius", "Fahrenheit"), 72.14, 0.000001);
        assertEquals(converter.Convert(23, "Litre", "Millilitre"), 23000, 0.000001);
        assertEquals(converter.Convert(45345, "Millilitre", "Gallon"), 11.97892, 0.000001);
    }
}