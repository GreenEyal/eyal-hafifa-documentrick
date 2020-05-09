package transformers.characterescaper;

public class CharacterEscaper {
    private String charactersToEscape;

    public CharacterEscaper(String characters) {
        this.charactersToEscape = characters;
    }

    public String escapeCharacter(Character input) {
        return "\\" + input;
    }
    public String getCharactersToEscape() {
        return this.charactersToEscape;
    }
}
