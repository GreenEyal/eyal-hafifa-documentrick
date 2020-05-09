package cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsBuilder {
    private Options options;

    public OptionsBuilder() {
        this.options = buildOptions();
    }

    public Options getOptions() {
        return this.options;
    }

    private Options buildOptions() {
        Options options = new Options();
        options.addOption(Option.builder("source")
                .desc("Choose source filepath")
                .required()
                .hasArg()
                .argName("filePath")
                .build());
        options.addOption(Option.builder("changeNumBase")
                .desc("Base to convert all numbers in file to")
                .hasArg()
                .argName("h/o/b/d")
                .build());
        options.addOption(Option.builder("escapeChar")
                .desc("Escape all entered characters")
                .hasArg()
                .argName("characters")
                .build());
        options.addOption(Option.builder("enclose")
                .desc("Enclose specified string with a different string")
                .numberOfArgs(2)
                .argName("string> <encloseWith")
                .build());
        options.addOption(Option.builder("filterToRow")
                .desc("Choose till what row in the file to transform")
                .hasArg()
                .argName("rowNumber")
                .build());
        options.addOption(Option.builder("filterByElement")
                .desc("Choose which element to allow transforming in")
                .hasArg()
                .argName("element")
                .build());
        options.addOption(Option.builder("FilterByColumn")
                .desc("Choose which column to transform")
                .hasArg()
                .argName("columnNumber")
                .build());
        options.addOption(Option.builder("save")
                .desc("Choose filepath to save result to")
                .required()
                .hasArg()
                .argName("filePath")
                .build());
        return options;
    }

}
