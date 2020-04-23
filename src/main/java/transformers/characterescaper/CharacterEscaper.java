package transformers.characterescaper;

public class CharacterEscaper {
    private String characters;

    public CharacterEscaper(String characters) {
        this.characters = characters;
    }

    public String escapeCharacters(String input) {
        for (char character : characters.toCharArray()) {
            input = input.replaceAll(String.valueOf(character), "\\\\" + character);
        }
        return input;
    }
}
