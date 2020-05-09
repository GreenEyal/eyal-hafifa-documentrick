package processors;

import filters.StatefulFilterInputStream;
import transformers.IFilterableInputStreamTransformer;

import java.io.*;

public class Processor {
    private InputStream source;
    private OutputStream dest;
    private IFilterableInputStreamTransformer transformer;
    private StatefulFilterInputStream filter;

    public Processor(InputStream source, OutputStream dest, IFilterableInputStreamTransformer transformer, StatefulFilterInputStream filter) {
        this.source = source;
        this.dest = dest;
        this.transformer = transformer;
        this.filter = filter;
    }

    public void process() throws IOException {
        filter.setReader(new InputStreamReader(source));
        transformer.setFilter(filter);
        OutputStreamWriter writer = new OutputStreamWriter(dest);
        int charValue;
        while ((charValue = transformer.read()) != -1) {
//            System.out.print((char) charValue);
            writer.write((char) charValue);
        }
        writer.close();

    }
}
