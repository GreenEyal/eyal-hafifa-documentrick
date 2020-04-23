package transformers.baseconverter.strategies;

import transformers.baseconverter.Converter;

public class BinaryConverter implements Converter {
    @Override
    public String convert(String num) {
        return Integer.toString(Integer.parseInt(num, BINARY_RADIX));
    }
}
