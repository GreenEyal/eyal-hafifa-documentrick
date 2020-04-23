package transformers.encloser;

import transformers.IStringTransformer;
import transformers.StringTransformation;

public class Enclose extends StringTransformation {
    private IStringTransformer transformer;
    private Encloser encloser;

    public Enclose(IStringTransformer transformer, String encloseString, String encloseByString) {
        super(transformer);
        this.encloser = new Encloser(encloseString, encloseByString);
    }

    public String transform(String input) {
        return encloser.enclose(transformer.transform(input));
    }
}
