package transformers.baseconverter;

public interface Converter {
    int DECIMAL_RADIX = 10;
    int BINARY_RADIX = 2;
    int OCTAL_RADIX = 8;
    int HEX_RADIX = 16;

    String convert(String num);
}
