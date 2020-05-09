package filters;

import java.io.IOException;
import java.io.InputStreamReader;

public class StatefulFilterInputStream {
    protected InputStreamReader reader;
    protected boolean isAccepting;

    public StatefulFilterInputStream(InputStreamReader reader) {
        this.reader = reader;
        isAccepting = true;
    }

    public StatefulFilterInputStream() {
        this.reader = null;
        isAccepting = true;
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    public int read() throws IOException {
        return reader.read();
    }

    public void setReader(InputStreamReader reader) {
        this.reader = reader;
    }
}
