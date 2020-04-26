package transformers.baseconverter.strategies;

import transformers.baseconverter.Converter;

public class OctalConverter implements Converter {
    @Override
    public String convert(String num) {
        return Integer.toString(Integer.parseInt(num), OCTAL_RADIX);
    }
}
