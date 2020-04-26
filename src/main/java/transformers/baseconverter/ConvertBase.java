package transformers.baseconverter;

import exceptions.InvalidConversionBaseException;
import transformers.IStringTransformer;
import transformers.StringTransformation;

public class ConvertBase extends StringTransformation {
    private BaseConverter converter;

    public ConvertBase(IStringTransformer transformer, String conversionBase) throws InvalidConversionBaseException {
        super(transformer);
        this.converter = new BaseConverter(conversionBase);
    }

    @Override
    public String transform(String input) {
        return converter.convert(transformer.transform(input));
    }
}
