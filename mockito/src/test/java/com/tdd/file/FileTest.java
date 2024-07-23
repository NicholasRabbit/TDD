package com.tdd.file;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class FileTest {

    private String path;

    @BeforeEach
    public void setUp() throws Exception {
        path = "D:\\abcxxx.txt";
    }


    @Test
    public void testRenameTo() throws Exception {
        File f = new File(path);
        f.renameTo(new File("D:\\1-abcxxx.txt"));
    }

    @Test
    public void testSubString() throws Exception {
        String dirName = "1-abc";
        String substring = dirName.substring(0, 2);
        assertEquals("1-", substring);
    }

    @Test
    public void regexShouldMatchNumberAndDash() throws  Exception {
        String dirName = "abc1-";
        boolean matches = isMatches(dirName);
        assertFalse(matches);
    }

    private static boolean isMatches(String dirName) {
        String regex = "^\\d.*$";
        Pattern compile = Pattern.compile(regex);
        boolean matches = compile.matcher(dirName).matches();
        return matches;
    }

    @Test
    public void testGetCreateTime() throws Exception {
        File file = new File("D:\\1-abcxxx.txt");
        Date date = getCreationTime(file);
        System.out.println(date);

    }

    private static Date getCreationTime(File file) throws IOException {
        FileTime creationTime = (FileTime) Files.getAttribute(file.toPath(), "creationTime");
        long millis = creationTime.toMillis();
        Date date = new Date(millis);
        return date;
    }

    @Test
    public void testFileSeparator() {
        String separator = System.getProperty("file.separator");
        System.out.println(separator);
    }

}
