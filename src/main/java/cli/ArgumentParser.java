package cli;

import org.apache.commons.cli.*;

public class ArgumentParser {
    private CommandLine commandLine;

    public ArgumentParser(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            this.commandLine = parser.parse(options, args);

        } catch (ParseException e) {
            System.err.println("Argument parsing failed. Reason: " + e.getMessage());
        }
    }

    public CommandLine getCommandLine() {
        return this.commandLine;
    }
}
