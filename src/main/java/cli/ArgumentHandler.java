package cli;

import exceptions.ArgumentHandlingException;
import exceptions.InvalidConversionBaseException;
import filters.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.io.FilenameUtils;
import transformers.FilterableInputStreamTransformer;
import transformers.IFilterableInputStreamTransformer;
import transformers.baseconverter.ConvertBase;
import transformers.characterescaper.EscapeCharacter;
import transformers.encloser.Enclose;

import java.io.*;

public class ArgumentHandler {
    private CommandLine commandLine;

    public ArgumentHandler(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public InputStream getSource() throws FileNotFoundException, ArgumentHandlingException {
        String sourceFilePath = getSourcePath();
        return new FileInputStream(sourceFilePath);
    }

    public OutputStream getDest() throws FileNotFoundException, ArgumentHandlingException {
        String destFilepath = getDestPath();
        return new FileOutputStream((destFilepath));
    }

    public StatefulFilterInputStream getFilter() throws ArgumentHandlingException {
        StatefulFilterInputStream filter = new StatefulFilterInputStream();
        filter = getBaseFilter(filter);
        filter = wrapAdditionalFilters(filter);
        return filter;
    }

    public IFilterableInputStreamTransformer getTransformer() throws InvalidConversionBaseException {
        IFilterableInputStreamTransformer transformer = new FilterableInputStreamTransformer();
        transformer = wrapAdditionalTransformations(transformer);
        return transformer;
    }

    private String getFileExtention() throws ArgumentHandlingException {
        return FilenameUtils.getExtension(getSourcePath());
    }

    private String getSourcePath() throws ArgumentHandlingException {
        if (commandLine.hasOption("source")) {
            return commandLine.getOptionValue("source");
        } else {
            throw new ArgumentHandlingException("Source argument '-source' not given");
        }
    }

    private String getDestPath() throws ArgumentHandlingException {
        if (commandLine.hasOption("save")) {
            return commandLine.getOptionValue("save");
        } else {
            throw new ArgumentHandlingException("Destination argument '-save' not given");
        }
    }

    private StatefulFilterInputStream getBaseFilter(StatefulFilterInputStream filter) throws ArgumentHandlingException {
        switch (getFileExtention()) {
            case "csv":
                filter = new CSVReader(filter);
                break;
            case "xml":
                filter = new XMLReader(filter);
                break;
        }
        return filter;
    }

    private StatefulFilterInputStream wrapAdditionalFilters(StatefulFilterInputStream filter) {
        for (Option option : commandLine.getOptions()) {
            switch (option.getOpt()) {
                case "filterToRow":
                    filter = new RowCountFilter(filter, Integer.parseInt(option.getValue()));
                    break;
                case "filterByElement":
                    filter = new XMLElementFilter(filter, option.getValue());
                    break;
                case "filterByColumn":
                    filter = new CSVColumnFilter(filter, Integer.parseInt(option.getValue()));
                    break;
            }
        }
        return filter;
    }

    private IFilterableInputStreamTransformer wrapAdditionalTransformations(IFilterableInputStreamTransformer transformer) throws InvalidConversionBaseException {
        for (Option option : commandLine.getOptions()) {
            switch (option.getOpt()) {
                case "changeNumBase":
                    transformer = new ConvertBase(transformer, option.getValue());
                    break;
                case "escapeChar":
                    transformer = new EscapeCharacter(transformer, option.getValue());
                    break;
                case "enclose":
                    transformer = new Enclose(transformer, option.getValue(0), option.getValue(1));
                    break;
            }
        }
        return transformer;
    }
}
