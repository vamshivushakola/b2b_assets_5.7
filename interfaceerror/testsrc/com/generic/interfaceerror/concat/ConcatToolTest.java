package com.generic.interfaceerror.concat;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author capgemini on 16/04/2014.
 */
public class ConcatToolTest extends TestCase
{

    /**
     * log4j logger
     */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * the dir containing all files to concatenate
     */
    static final String CONCAT_DIR = "D:\\Users\\bdurantD\\Documents\\data\\price\\parts";
    /**
     * the concatenated result file
     */
    static final String CONCAT_FILE = "D:\\Users\\bdurantD\\Documents\\data\\price\\concatTest.csv";

    /**
     * test ConcatTool with directory
     */
    @Test
    public final void testConcatDir()
    {
        long timer = System.currentTimeMillis();
        ConcatTool.concat(CONCAT_DIR, CONCAT_FILE);
        long duration = (System.currentTimeMillis() - timer);
        logger.info("concat computed in " + duration + " ms");

        File baseDir = new File(CONCAT_DIR);
        File[] files = baseDir.listFiles();
        long concatLinesFromDir = 1;

        for (File file : files)
        {
            concatLinesFromDir += countLines(file.getAbsolutePath());
        }
        assertEquals("Line number must be the same!", concatLinesFromDir, countLines(CONCAT_FILE));
    }
    /**
     * test ConcatTool array of file
     */
    @Test
    public final void testConcatFiles()
    {

        File baseDir = new File(CONCAT_DIR);
        File[] files = baseDir.listFiles();

        long timer = System.currentTimeMillis();
        ConcatTool.concat(files, CONCAT_FILE);
        long duration = (System.currentTimeMillis() - timer);
        logger.info("concat computed in " + duration + " ms");

        long concatLinesFromDir = 1;

        for (File file : files)
        {
            concatLinesFromDir += countLines(file.getAbsolutePath());
        }
        assertEquals("Line number must be the same!", concatLinesFromDir, countLines(CONCAT_FILE));
    }

    /**
     * loop over lines of the given file to return the number of lines
     * @param fileName
     *      the absolute file path
     * @return
     *      the line number
     */
    private int countLines(final String fileName)
    {
        File file = new File(fileName);
        if (file.exists())
        {
            int linenumber = 1;
            try (
                FileReader fr = new FileReader(file);
            )
            {
                LineNumberReader lnr = new LineNumberReader(fr);
                while (lnr.readLine() != null)
                {
                    linenumber++;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return linenumber;
        }
        else
        {
            logger.debug("File " + fileName + " does not exists!");
            return 0;
        }
    }
}
