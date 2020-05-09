package transformers.encloser;

import transformers.FilterableInputStreamTransformation;
import transformers.IFilterableInputStreamTransformer;

import java.io.IOException;

public class Enclose extends FilterableInputStreamTransformation {
    private Encloser encloser;

    public Enclose(IFilterableInputStreamTransformer transformer, String encloseString, String encloseByString) {
        super(transformer);
        this.encloser = new Encloser(encloseString, encloseByString);
    }

    @Override
    public int read() throws IOException {
        if (!buffer.isEmpty()) {
            return buffer.remove();
        }
        int charValue = transformer.read();
        char ch = (char) charValue;
        if (isMatchingStringToBeEnclosed(String.valueOf(ch)) && isAccepting()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ch);
            addToBuffer(charValue);
            while (isMatchingStringToBeEnclosed(stringBuilder.toString()) && isAccepting()) {
                if (equalsStringToBeEnclosed(stringBuilder.toString())) {
                    String transformedOutput = encloser.enclose(stringBuilder.toString());
                    buffer.clear();
                    addToBuffer(transformedOutput);
                    return buffer.remove();
                }
                charValue = transformer.read();
                stringBuilder.append((char) charValue);
                addToBuffer(charValue);
            }
            return buffer.remove();
        }
        return charValue;
    }

    /**
     * Method to receive a string input and return whether that input matches
     * the start of the class' string to be enclosed.
     */
    private boolean isMatchingStringToBeEnclosed(String input) {
        return (encloser.getEncloseString().indexOf(input) == 0);
    }

    /**
     * Method to return whether an input is exactly matching the class' string to be enclosed
     */
    private boolean equalsStringToBeEnclosed(String input) {
        return input.equals(encloser.getEncloseString());
    }
}
