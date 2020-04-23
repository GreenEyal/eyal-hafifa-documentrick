package transformers.baseconverter;

public class ConverterContext {
    private Converter converter;

    public ConverterContext(Converter converter) {
        this.converter = converter;
    }

    public String convert(String num) {
        return converter.convert(num);
    }
}
