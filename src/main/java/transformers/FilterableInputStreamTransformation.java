package transformers;

import filters.StatefulFilterInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public abstract class FilterableInputStreamTransformation implements IFilterableInputStreamTransformer {
    protected IFilterableInputStreamTransformer transformer;
    protected Queue<Integer> buffer;

    public FilterableInputStreamTransformation(IFilterableInputStreamTransformer transformer) {
        this.transformer = transformer;
        this.buffer = new LinkedList<>();
    }

    public void addToBuffer(int charValue) {
        buffer.add(charValue);
    }

    public void addToBuffer(char ch) {
        buffer.add((int) ch);
    }

    public void addToBuffer(String input) {
        for (char ch : input.toCharArray()) {
            buffer.add((int) ch);
        }
    }

    public abstract int read() throws IOException;

    @Override
    public boolean isAccepting() {
        return transformer.isAccepting();
    }

    @Override
    public void setFilter(StatefulFilterInputStream filter) {
        transformer.setFilter(filter);

    }
}
