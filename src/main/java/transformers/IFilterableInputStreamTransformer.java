package transformers;

import filters.StatefulFilterInputStream;

public interface IFilterableInputStreamTransformer extends InputStreamTransformer {
    boolean isAccepting();
    void setFilter(StatefulFilterInputStream filter);
}
