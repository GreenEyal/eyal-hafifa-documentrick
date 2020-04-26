package transformers.baseconverter.strategies;

import transformers.baseconverter.Converter;

public class HexConverter implements Converter {
    @Override
    public String convert(String num) {
        return Integer.toString(Integer.parseInt(num), HEX_RADIX);
    }
}
