package transformers.baseconverter;

import exceptions.InvalidConversionBaseException;
import transformers.FilterableInputStreamTransformation;
import transformers.IFilterableInputStreamTransformer;

import java.io.IOException;

public class ConvertBase extends FilterableInputStreamTransformation {
    private BaseConverter converter;

    public ConvertBase(IFilterableInputStreamTransformer transformer, String conversionBase) throws InvalidConversionBaseException {
        super(transformer);
        this.converter = new BaseConverter(conversionBase);
    }

    @Override
    public int read() throws IOException {
        if (!buffer.isEmpty()) {
            return buffer.remove();
        }
        int charValue = transformer.read();
        if (Character.isDigit(charValue) && isAccepting()) {
            while (Character.isDigit(charValue) && isAccepting()) {
                addToBuffer(charValue);
                charValue = transformer.read();
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (!buffer.isEmpty()) {
                stringBuilder.append(Character.toChars(buffer.remove()));
            }
            String transformedOutput = converter.convert(stringBuilder.toString());
            for (char ch : transformedOutput.toCharArray()) {
                addToBuffer(ch);
            }
            addToBuffer(charValue); // Add to buffer character which stopped streak of numbers
            return buffer.remove();
        }
        return charValue;
    }
}
