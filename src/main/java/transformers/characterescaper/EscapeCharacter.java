package transformers.characterescaper;

import transformers.IStringTransformer;
import transformers.StringTransformation;

public class EscapeCharacter extends StringTransformation {
    private CharacterEscaper escaper;

    public EscapeCharacter(IStringTransformer transformer, String characters) {
        super(transformer);
        this.escaper = new CharacterEscaper(characters);
    }

    @Override
    public String transform(String input) {
        return escaper.escapeCharacters(transformer.transform(input));
    }
}
