package transformers.encloser;

public class Encloser {
    private String encloseString;
    private String encloseByString;

    public Encloser(String encloseString, String encloseByString) {
        this.encloseString = encloseString;
        this.encloseByString = encloseByString;
    }

    public String enclose(String input) {
        return input.replaceAll(encloseString, encloseByString + encloseString + encloseByString);
    }

    public String getEncloseString() {
        return this.encloseString;
    }
}
