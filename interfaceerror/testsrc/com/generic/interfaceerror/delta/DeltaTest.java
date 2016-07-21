package com.generic.interfaceerror.delta;

import junit.framework.TestCase;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * delta diff test that fetch all csv in the DIFF_CSV_DIR_REL_PATH directory and compute diff with BASE_CSV_FILE_REL_PATH
 *      diff results are made with the file name of files
 * @author capgemini on 11/04/2014.
 */
@RunWith(Parameterized.class)
public class DeltaTest extends TestCase
{


    public static final String DIFF_CSV_DIR_REL_PATH = "csv/diff/";
    public static final String BASE_CSV_FILE_REL_PATH = "csv/base.csv";
    public static final String DIFF_CSV_FILE_REL_PATH = "csv/diff.csv";
    public static final String REMOVE_CSV_FILE_REL_PATH = "csv/remove.csv";

    public static final String DIFF_FILE_SPLIT = "-";
    public static final String DIFF_FILE_RESULT = "_";

    private URL baseCsv;
    private URL diffCsv;
    private URL removeCsv;

    private URL fileToTest;

    /**
     * constructor called by junit for each file found in the DIFF_CSV_DIR_REL_PATH directory
     * @param fileToTest
     */
    public DeltaTest(final URL fileToTest)
    {
        this.fileToTest = fileToTest;
    }

    /**
     *  get all files in the DIFF_CSV_DIR_REL_PATH directory
     * @return
     *      files found
     * @throws IOException
     */
    @Parameters
    public static Collection<URL[]> getTestsFiles()
            throws IOException
    {
        final List<String> strings = parseFolder(DIFF_CSV_DIR_REL_PATH);
        Collection<URL[]> rtn = new ArrayList<>();
        for (String s : strings)
        {
            rtn.add(new URL[]{DeltaTest.class.getResource(DIFF_CSV_DIR_REL_PATH + s)});
        }
        return rtn;
    }

    private static List<String> parseFolder(final String folder) throws IOException {
        return IOUtils.readLines(DeltaTest.class.getResourceAsStream(folder));
    }

    /**
     * load base file and diff file
     */
    @Before
    public final void setUp()
    {
        baseCsv = DeltaTest.class.getResource(BASE_CSV_FILE_REL_PATH);
        assertNotNull("Can not read " + BASE_CSV_FILE_REL_PATH, baseCsv);
        diffCsv = DeltaTest.class.getResource(DIFF_CSV_FILE_REL_PATH);
        assertNotNull("Can not read " + DIFF_CSV_FILE_REL_PATH, diffCsv);
        removeCsv = DeltaTest.class.getResource(REMOVE_CSV_FILE_REL_PATH);
        assertNotNull("Can not read " + REMOVE_CSV_FILE_REL_PATH, removeCsv);
    }

    /**
     * compute delta on a file and check if results match the file name
     *  i.e. the file named : updateCount_4 must have 4 updates with the base file
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public final void testDeltaWithoutRemove()
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        DiffTool.DiffResult diff = DiffTool.diff(baseCsv.getPath(), fileToTest.getPath(), diffCsv.getPath(),null);
        final String fileNameFromURL = getFileNameFromURL(fileToTest);
        String[] parts = fileNameFromURL.split(DIFF_FILE_SPLIT);
        for (String part : parts)
        {
            String[] res = part.split(DIFF_FILE_RESULT);
            Long property = (Long) PropertyUtils.getProperty(diff, res[0]);
            assertEquals("the "+res[0]+" is wrong for "+fileNameFromURL, Long.decode(res[1]), property);
        }
    }

    /**
     * compute delta on a file and check if results match the file name
     *  i.e. the file named : updateCount_4 must have 4 updates with the base file
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public final void testDeltaWithLineCount()
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        DiffTool.DiffResult diff = DiffTool.diff(baseCsv.getPath(), fileToTest.getPath(), diffCsv.getPath(),removeCsv.getPath());
        final String fileNameFromURL = getFileNameFromURL(fileToTest);
        String[] parts = fileNameFromURL.split(DIFF_FILE_SPLIT);
        int nbRemove = 0;
        int nbInsUp = 0;
        for (String part : parts)
        {
            String[] res = part.split(DIFF_FILE_RESULT);
            if(res[0].equals("deleteCount")) {
                nbRemove = Integer.parseInt(res[1]);
            }else{
                nbInsUp += Integer.parseInt(res[1]);
            }
        }
        assertEquals(countLines(removeCsv.getPath()),nbRemove);
        assertEquals(countLines(diffCsv.getPath()),nbInsUp);
    }

    /**
     * get the file name of the given url between the last '/' and '.'
     * @param url
     *      the url to get the filename
     * @return
     *      the file name
     */
    private String getFileNameFromURL(final URL url)
    {
        String str = url.toString();
        return str.substring(str.lastIndexOf('/') + 1, str.lastIndexOf('.'));
    }

    /**
     * count line of a file
     * @param fileName
     *      the path of the file
     * @return
     *      the number of lines
     */
    private int countLines(final String fileName)
    {
        BufferedReader reader = null;
        int lines = 0;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
