package filters;

import java.io.IOException;

public class XMLElementFilter extends FilterBooleanLogic {
    private String acceptedElement;
    private StringBuilder currentElement;
    private boolean inElementTag;
    private boolean inStartElement;

    public XMLElementFilter(StatefulFilterInputStream filterInputStream, String acceptedElement) {
        super(filterInputStream);
        this.acceptedElement = acceptedElement;
        currentElement = new StringBuilder();
        inElementTag = false;
    }
    // Does not handle inner elements yet
    public int read() throws IOException {
        int charvalue = filterInputStream.read();
        char ch = (char) charvalue;
        if (ch == '<') {
            inElementTag = true;
            currentElement.setLength(0);
        } else if (ch == '>') {
            inElementTag = false;
        } else if (inElementTag && ch != '/') {
            currentElement.append(ch);
        } else if (inElementTag) { // Enters when in element tag and character is slash
            currentElement.setLength(0);
        }
        isAccepting = (currentElement.toString().equals(acceptedElement));
        if (!inElementTag && ch != '>')
            isAccepting = false; // take care of this
        return charvalue;
    }
}
