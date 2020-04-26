package transformers.baseconverter.strategies;

import transformers.baseconverter.Converter;

public class DecimalConverter implements Converter {

    @Override
    public String convert(String num) {
        return Integer.toString(Integer.parseInt(num), DECIMAL_RADIX);
    }
}
