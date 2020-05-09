package transformers;

import java.io.IOException;

public interface InputStreamTransformer {
    int read() throws IOException;
}
