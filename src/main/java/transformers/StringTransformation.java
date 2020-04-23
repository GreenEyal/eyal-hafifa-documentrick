package transformers;

public abstract class StringTransformation implements IStringTransformer {
    protected IStringTransformer transformer;

    public StringTransformation(IStringTransformer transformer) {
        this.transformer = transformer;
    }

    public abstract String transform(String input);
}
