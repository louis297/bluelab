import java.util.HashMap;
import java.util.Map;

/**
 * The Converter class is for the coding test for BlueLab
 *
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
public class Converter {
    private Map<String, String> types = new HashMap<>();


    Converter(){
        // add new type and category here

        types.put("Kelvin", "temperature");
        types.put("Celsius", "temperature");
        types.put("Fahrenheit", "temperature");

        types.put("Litre", "volume");
        types.put("Millilitre", "volume");
        types.put("Gallon", "volume");
    }
    /**
     * The Convert function checks the types and call the actual converter functions
     *
     * @param value     The source value
     * @param source    The type of source value
     * @param target    The type of target value
     * @return          Target value after conversion
     */
    public double Convert(double value, String source, String target){
        try{
            if(!types.containsKey(source)){
                throw new ConverterException(String.format("The source type %s cannot be recognised.", source));
            }
            if(!types.containsKey(target)){
                throw new ConverterException(String.format("The target type %s cannot be recognised.", target));
            }
        } catch(ConverterException e){
            e.printStackTrace();
            System.exit(-1);
        }
        try{
            if(!types.get(source).equals(types.get(target))){
                throw new ConverterException(String.format("The source type %s and target type %s are not belonged to the same category.", source, target));
            }
        } catch(ConverterException e){
            e.printStackTrace();
            System.exit(-1);
        }
        // the types are double-checked by the actual converter for safety
        // add new case for new categories here
        try{
            switch(types.get(source)){
                case "temperature":
                    return ConvertTemperature(value, source, target);
                case "volume":
                    return ConvertVolume(value, source, target);
                default:
                    throw new ConverterException(String.format("The category %s cannot be recognised.", types.get(source)));
            }
        } catch(ConverterException e){
            e.printStackTrace();
            System.exit(-1);
        }

        // the programme should not reach the placeholder return value
        return 0.0;
    }

    /**
     * The source value is converted to a medium value (Celsius is chosen), then to the target type
     * This strategy may save coding amount when adding more types to the converter
     *
     * @param value     The source value
     * @param source    The type of source value
     * @param target    The type of target value
     * @return          Target value after conversion
     */
    private double ConvertTemperature(double value, String source, String target) throws ConverterException {
        double celValue = 0.0;
        double targetValue = 0.0;

        // add new case for new source type for temperature
        switch (source) {
            case "Kelvin": {
                celValue = value - 273.15;
                break;
            }
            case "Fahrenheit": {
                celValue = (value - 32) * 5 / 9;
                break;
            }
            case "Celsius": {
                celValue = value;
                break;
            }
            default:
                throw new ConverterException(String.format("The input type %s cannot be converted.", source));
        }

        // add new case for new source type for temperature
        switch (target) {
            case "Kelvin": {
                targetValue = celValue + 273.15;
                break;
            }
            case "Fahrenheit": {
                targetValue = celValue * 9 / 5 + 32;
                break;
            }
            case "Celsius": {
                targetValue = celValue;
                break;
            }
            default:
                throw new ConverterException(String.format("The output type %s cannot be converted.", target));
        }

        return targetValue;
    }

    /**
     * The source value is converted to a medium value (Litre is chosen), then to the target type
     * This strategy may save coding amount when adding more types to the converter
     *
     * @param value     The source value
     * @param source    The type of source value
     * @param target    The type of target value
     * @return          Target value after conversion
     */
    private double ConvertVolume(double value, String source, String target) throws ConverterException {
        double litreValue = 0.0;
        double targetValue = 0.0;

        // add new case for new source type for volume
        switch (source) {
            case "Millilitre": {
                litreValue = value / 1000;
                break;
            }
            case "Gallon": {
                litreValue = value * 3.7854;
                break;
            }
            case "Litre": {
                litreValue = value;
                break;
            }
            default:
                throw new ConverterException(String.format("The input type %s cannot be converted.", source));
        }

        // add new case for new source type for volume
        switch (target) {
            case "Litre": {
                targetValue = litreValue;
                break;
            }
            case "Millilitre": {
                targetValue = litreValue * 1000;
                break;
            }
            case "Gallon": {
                targetValue = litreValue / 3.7854;
                break;
            }
            default:
                throw new ConverterException(String.format("The output type %s cannot be converted.", target));
        }

        return targetValue;
    }

}

// TODO: Use Spring IoC to isolate the code and type/category data
// then adding new types will not interfere the source code
// Sorry it is not do-able as a 1 hour project