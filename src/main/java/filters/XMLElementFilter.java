package filters;

import java.io.IOException;

public class XMLElementFilter extends FilterBooleanLogic {
    private static final char LESS_THAN_CHARACTER = '<';
    private static final char GREATER_THAN_CHARACTER = '>';
    private static final char SLASH_CHARACTER = '/';
    private String acceptedElement;
    private StringBuilder currentElement;
    private boolean inElementTag;
    private boolean inAcceptedElement;


    public XMLElementFilter(StatefulFilterInputStream filterInputStream, String acceptedElement) {
        super(filterInputStream);
        this.acceptedElement = acceptedElement;
        currentElement = new StringBuilder();
        inElementTag = false;
        inAcceptedElement = false;
    }

    /**
     * The XML event filter read method detects whether the current characters being read are
     * inside of an element tag or not, whilst keeping the state of whether or not the characters
     * are inside of the accepted element given to the filter. Only when the state corresponds
     * to being in the boundries of the accepted tag, and not inside any inner tag, will the filter change
     * its state to accepting.
     */
    public int read() throws IOException {
        int charValue = filterInputStream.read();
        char ch = (char) charValue;
        isAccepting = inAcceptedElement && !inElementTag;
        if ((inElementTag && ch == GREATER_THAN_CHARACTER) || (!inElementTag && ch == LESS_THAN_CHARACTER)) {
            isAccepting = false;
        }
        if (ch == LESS_THAN_CHARACTER) {
            inElementTag = true;
            resetCurrentElement();
        } else if (ch == GREATER_THAN_CHARACTER) {
            inElementTag = false;
            if (currentElement.toString().equals(acceptedElement)) {
                inAcceptedElement = !inAcceptedElement;
            }
        } else if (inElementTag && ch == SLASH_CHARACTER) {
            resetCurrentElement();
        } else if (inElementTag) {
            currentElement.append(ch);
        }
        return charValue;
    }

    private void resetCurrentElement() {
        this.currentElement.setLength(0);
    }
}
