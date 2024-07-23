package com.tdd.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RenameFileUtil {

    public List<File> getFileList(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        return Arrays.asList(files);
    }

    public List<File> getSortedFileList(String path) {
        File[] files = new File(path).listFiles();
        Arrays.sort(files, new FileComparator());
        return Arrays.asList(files);
    }


    public long getCreationTimeMillis(File file) {
        FileTime creationTime = null;
        try {
            creationTime = (FileTime) Files.getAttribute(file.toPath(), "creationTime");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return creationTime.toMillis();
    }

    public List<File> renameFile(String path) {
        int i = 1;
        for (File file : getSortedFileList(path)) {
            String name = file.getName();
            String renamedPath = path + System.getProperty("file.separator") + i + "-" + name;
            file.renameTo(new File(renamedPath));
            i++;
        }

        return getFileList(path);
    }


    class FileComparator implements Comparator<File> {

        @Override
        public int compare(File o1, File o2) {
            return (int)(getCreationTimeMillis(o1) - getCreationTimeMillis(o2));
        }
    }

}

