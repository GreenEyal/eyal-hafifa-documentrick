package transformers;

import filters.StatefulFilterInputStream;

import java.io.IOException;

public class FilterableInputStreamTransformer implements IFilterableInputStreamTransformer {
    private StatefulFilterInputStream filter;

    public FilterableInputStreamTransformer(StatefulFilterInputStream filter) {
        this.filter = filter;
    }

    public FilterableInputStreamTransformer() {
        this.filter = null;
    }

    public void setFilter(StatefulFilterInputStream filter) {
        this.filter = filter;
    }

    @Override
    public int read() throws IOException {
        return filter.read();
    }

    @Override
    public boolean isAccepting() {
        return filter.isAccepting();
    }
}
