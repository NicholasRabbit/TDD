package com.tdd.file;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Rename all the file in a TDD way.
 * */
public class RenameFileUtilTest {


    private RenameFileUtil fileUtil;
    private final static String path = "D:\\audio";
    private List<File> fileList;

    @BeforeEach
    public void setUp() throws Exception{
        fileUtil = new RenameFileUtil();
        fileList = fileUtil.getFileList(path);
    }

    /**
     * The requirement.
     * Rename files with sequential numbers and dashed by the order of created time.
     *
     * 1, TODO  to get the list of files in  a directory.
     * 2, TODO  to sort the list in chronological order.
     * 2, TODO  to get the name of each file.
     * 3, TODO  to rename the name fo each file by adding a sequential number and a dash as prefix.
     *
     * There are still bugs like a file will be still renamed if it starts with a number and a dash.
     * */
    // 1.
    @Test
    public void shouldGetFileListLargerThanZero() throws Exception {
        assertTrue(fileUtil.getFileList(path).size() > 0, "List should not be empty.");
    }

    // 2.
    @Test
    public void listShouldBeSortedInChronologicalOrder() throws Exception {

        List<File> sortedFileList = fileUtil.getSortedFileList(path);
        assertTrue(sortedFileList.size() > 0);

        boolean sortedFlag = true;
        for (int i = 0; i + 1 < sortedFileList.size(); i++) {
            long createdTime1 = fileUtil.getCreationTimeMillis(sortedFileList.get(i));
            long createdTime2 = fileUtil.getCreationTimeMillis(sortedFileList.get(i + 1));
            if (createdTime1 > createdTime2) {
                sortedFlag = false;
                break;
            }
        }
        assertTrue(sortedFlag);
    }

    // 3. Get the names of every file in a directory.
    @Test
    public void shouldHaveName() throws Exception {
        boolean haveName = true;
        for (File f : fileList) {
            if (f.getName().isEmpty()) {
                haveName = false;
                break;
            }
        }

        assertTrue(haveName, "Each file should have a name.");
    }

    // 4. All the names of file should start with a sequential number and dash.
    @Test
    public void fileNamesShouldStartWithNumberAndDash() throws Exception {
        List<File> renamedFiles = fileUtil.renameFile(path);
        boolean matchFlag = true;
        for (File f : renamedFiles) {
            boolean matches = this.isMatches(f.getName());
            if (!matches) {
                matchFlag = false;
                break;
            }
        }
        assertTrue(matchFlag, "All the names of file should start with a sequential number and dash.");
    }



    private boolean isMatches(String fileName) {
        // "1-,2-..."
        String regex = "^\\d.*$";
        Pattern compile = Pattern.compile(regex);
        boolean matches = compile.matcher(fileName).matches();
        return matches;
    }



}
