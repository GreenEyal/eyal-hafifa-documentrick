package filters;

import java.io.IOException;

public class XMLElementFilter extends XMLReader {
    private static final char SLASH_CHARACTER = '/';
    private String acceptedElement;
    private StringBuilder currentElement;
    private boolean inAcceptedElement;


    public XMLElementFilter(StatefulFilterInputStream filterInputStream, String acceptedElement) {
        super(filterInputStream);
        this.acceptedElement = acceptedElement;
        currentElement = new StringBuilder();
        inAcceptedElement = false;
    }

    /**
     * XMLElementFilter's read method detects whether the current characters being read are
     * inside the accepted element tag given to the object. Only when the state corresponds
     * to being in the boundries of the accepted tag, and not inside any inner tag, will the filter change
     * its state to accepting.
     */
    public int read() throws IOException {
        int charValue = super.read();
        char ch = (char) charValue;
        isAccepting = inAcceptedElement && !inElementTag;
        if (ch == LESS_THAN_CHARACTER) {
            resetCurrentElement();
        } else if (ch == GREATER_THAN_CHARACTER && currentElement.toString().equals(acceptedElement)) {
            inAcceptedElement = !inAcceptedElement;
        } else if (inElementTag && ch != SLASH_CHARACTER) {
            currentElement.append(ch);
        }
        return charValue;
    }

    private void resetCurrentElement() {
        this.currentElement.setLength(0);
    }
}
