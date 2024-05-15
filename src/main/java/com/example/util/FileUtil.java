package com.example.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtil {
    public static List<String> getSubFilePathList(String path) {
        List<String> subFilePathList = new ArrayList<>();
        File[] subFileList = new File(path).listFiles();
        for (File file : subFileList) {
            subFilePathList.add(file.getPath());
        }
        return subFilePathList;
    }

    public static List<String> filterFilePathBySuffix(Collection<String> filePathCollection, final String suffix) {
        // the suffix must be final so that
        // the anonymous class can access the parameter.
        List<String> filteredPathList = filePathCollection.stream().filter(new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return s.endsWith(suffix);
            }

        }).collect(Collectors.toList());
        return filteredPathList;
    }
}
