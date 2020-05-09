package transformers.characterescaper;

import transformers.FilterableInputStreamTransformation;
import transformers.IFilterableInputStreamTransformer;

import java.io.IOException;

public class EscapeCharacter extends FilterableInputStreamTransformation {
    private CharacterEscaper escaper;

    public EscapeCharacter(IFilterableInputStreamTransformer transformer, String characters) {
        super(transformer);
        this.escaper = new CharacterEscaper(characters);
    }

    @Override
    public int read() throws IOException {
        if (!buffer.isEmpty()) {
            return buffer.remove();
        }
        int charValue = transformer.read();
        char ch = (char) charValue;
        if (escaper.getCharactersToEscape().contains(Character.toString(ch)) && isAccepting()) {
            String transformedOutput = escaper.escapeCharacter(ch);
            for (char character : transformedOutput.toCharArray()) {
                addToBuffer(character);
            }
            return buffer.remove();
        }
        return charValue;
    }
}
