package com.generic.interfaceerror.logger.log4j;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;
import com.generic.interfaceerror.logger.InterfaceerrorError;
import com.generic.interfaceerror.logger.InterfaceerrorErrorManager;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * custom log4j file appender that writes in a file named given loggingEvent message
 *
 * @author capgemini
 */
public class InterfaceerrorLogFileAppender extends InterfaceerrorDirectoryAppender {

    @Override
    public final void append(final LoggingEvent loggingEvent) {
        String origFileName;
        String logFilePath;
        final Map<Integer, InterfaceerrorError> errorLines;
        SortedSet<Integer> keys;
        StringBuilder sb;

        origFileName = (String) loggingEvent.getMessage();
        logFilePath = suffixFileName(origFileName, InterfaceerrorConstants.Logger.LOG_SUFFIX);
        errorLines = InterfaceerrorErrorManager.SINGLETON.getErrorLines(origFileName);
        keys = new TreeSet<>(errorLines.keySet());

        for (Integer lineNb : keys) {
            sb = new StringBuilder();

            InterfaceerrorError errorLine = errorLines.get(lineNb);

            sb.append(InterfaceerrorConstants.log4jLabel.ORIG_FILE_LABEL).append(origFileName)
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.LINE_LABEL).append(lineNb)
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.REJECT_FILE_LABEL).append(super.replaceSlashes(errorLine.getRejectFileName()))
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.LINE_LABEL).append(errorLine.getRejectLineNb())
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.MSG_LABEL).append(errorLine.getMsg());

            super.setFile(logFilePath);
            super.activateOptions();

            LoggingEvent modifiedEvent = modifyMsg(loggingEvent, sb.toString(), this.getClass().getName());

            super.append(modifiedEvent);
        }
    }
}
