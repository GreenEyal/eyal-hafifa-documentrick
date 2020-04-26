package transformers.baseconverter;

import exceptions.InvalidConversionBaseException;
import transformers.baseconverter.strategies.BinaryConverter;
import transformers.baseconverter.strategies.DecimalConverter;
import transformers.baseconverter.strategies.HexConverter;
import transformers.baseconverter.strategies.OctalConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseConverter {
    private ConverterContext converter;
    private String base;

    public BaseConverter(String conversionBase) throws InvalidConversionBaseException {
        switch (conversionBase) {
            case "d":
                converter = new ConverterContext(new DecimalConverter());
                break;
            case "b":
                converter = new ConverterContext(new BinaryConverter());
                break;
            case "o":
                converter = new ConverterContext(new OctalConverter());
                break;
            case "h":
                converter = new ConverterContext(new HexConverter());
                break;
            default:
                throw new InvalidConversionBaseException("Invalid conversion base given: '" + conversionBase + "'. Expected one of d/b/o/h");
        }
        this.base = conversionBase;
    }

    public String convert(String input) {
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, converter.convert(matcher.group(1)));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

}
