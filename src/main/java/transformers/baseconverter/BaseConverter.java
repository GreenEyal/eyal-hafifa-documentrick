package transformers.baseconverter;

import exceptions.InvalidConversionBaseException;
import transformers.baseconverter.strategies.BinaryConverter;
import transformers.baseconverter.strategies.DecimalConverter;
import transformers.baseconverter.strategies.HexConverter;
import transformers.baseconverter.strategies.OctalConverter;

public class BaseConverter {
    private ConverterContext converter;
    private String baseNotation;

    public BaseConverter(String conversionBase) throws InvalidConversionBaseException {
        switch (conversionBase) {
            case "d":
                converter = new ConverterContext(new DecimalConverter());
                baseNotation = "";
                break;
            case "b":
                converter = new ConverterContext(new BinaryConverter());
                baseNotation = "0b";
                break;
            case "o":
                converter = new ConverterContext(new OctalConverter());
                baseNotation = "0";
                break;
            case "h":
                converter = new ConverterContext(new HexConverter());
                baseNotation = "0x";
                break;
            default:
                throw new InvalidConversionBaseException("Invalid conversion base given: '" + conversionBase + "'. Expected one of d/b/o/h");
        }
    }

    public String convert(String input) {
        return baseNotation + converter.convert(input);
    }

}
