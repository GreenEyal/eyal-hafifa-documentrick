package filters;

import java.io.IOException;

public class XMLReader extends FilterBooleanLogic {
    protected static final char LESS_THAN_CHARACTER = '<';
    protected static final char GREATER_THAN_CHARACTER = '>';
    protected boolean inElementTag;

    public XMLReader(StatefulFilterInputStream filterInputStream) {
        super(filterInputStream);
        inElementTag = false;
    }

    /**
     * XMLReader's read method detects whether the characters being read are inside of a tag
     * or not and changes the state of its filter accordingly.
     */
    public int read() throws IOException {
        int charValue = filterInputStream.read();
        char ch = (char) charValue;
        isAccepting = !inElementTag;
        if ((inElementTag && ch == GREATER_THAN_CHARACTER) || (!inElementTag && ch == LESS_THAN_CHARACTER)) {
            isAccepting = false;
        }
        if (ch == LESS_THAN_CHARACTER) {
            inElementTag = true;
        } else if (ch == GREATER_THAN_CHARACTER) {
            inElementTag = false;
        }
        return charValue;
    }

}
