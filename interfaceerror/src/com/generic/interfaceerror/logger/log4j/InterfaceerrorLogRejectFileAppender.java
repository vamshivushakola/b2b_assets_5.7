package com.generic.interfaceerror.logger.log4j;

import com.generic.interfaceerror.constants.InterfaceerrorConstants;
import com.generic.interfaceerror.logger.InterfaceerrorError;
import com.generic.interfaceerror.logger.InterfaceerrorErrorManager;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Custom log4j appender that routes the log msg to the correct reject or log file.
 * splits the message sent by InterfaceerrorCleanupHelper
 * the format is : dirName_;_fileName_;_content
 *
 * @author Capgemini
 * @see {@link org.apache.log4j.FileAppender}
 */
public class InterfaceerrorLogRejectFileAppender extends InterfaceerrorDirectoryAppender {

    @Override
    public final void append(final LoggingEvent loggingEvent) {

        String origFileName;
        final Map<Integer, InterfaceerrorError> errorLines;
        SortedSet<Integer> keys;
        int i;
        StringBuilder sb;

        origFileName = (String) loggingEvent.getMessage();
        errorLines = InterfaceerrorErrorManager.SINGLETON.getErrorLines(origFileName);
        keys = new TreeSet<>(errorLines.keySet());
        i = 1;

        for (Integer lineNb : keys) {
            sb = new StringBuilder();

            InterfaceerrorError errorLine = errorLines.get(lineNb);
            errorLine.setRejectLineNb(i);
            sb.append(InterfaceerrorConstants.log4jLabel.ORIG_FILE_LABEL).append(origFileName)
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.LINE_LABEL).append(lineNb)
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.MSG_LABEL).append(errorLine.getMsg())
                    .append(InterfaceerrorConstants.Impex.CSV_FIELD_SEPARATOR);
            sb.append(InterfaceerrorConstants.log4jLabel.ORIG_LINE_LABEL).append(errorLine.getOrigLine());

            LoggingEvent modifiedEvent = modifyMsg(loggingEvent, sb.toString(), this.getClass().getName());

            super.append(modifiedEvent);
        }
    }
}
