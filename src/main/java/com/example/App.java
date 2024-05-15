package com.example;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.example.core.Converter;
import com.example.util.FileUtil;
import com.example.util.JsonReader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String inputDirPath = null, outputDirPath = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("InputDir");
            inputDirPath = scanner.nextLine();
            System.out.println("OutputDir");
            outputDirPath = scanner.nextLine();
        }
        if (inputDirPath != null && outputDirPath != null
                && new File(inputDirPath).exists() && new File(outputDirPath).exists()) {
            List<String> subDirPathList = FileUtil.getSubFilePathList(inputDirPath);
            for (String subDirPath : subDirPathList) {
                String[] subDirPathToken=subDirPath.split(File.separator);
                String subDirName=subDirPathToken[subDirPathToken.length-1];
                List<String> subFilePathList = FileUtil.getSubFilePathList(subDirPath);
                List<String> mediaFilePathList = FileUtil.filterFilePathBySuffix(subFilePathList, ".m4s");
                List<String> jsonFilePathList = FileUtil.filterFilePathBySuffix(subFilePathList, ".json");
                Map<String, Object> resultMap = JsonReader.readJsonFile(jsonFilePathList.get(0));
                String videoName = (String) resultMap.get("tabName");
                System.out.println("Converting: " + videoName);
                for (String mediaFilePath : mediaFilePathList) {
                    String inputMediaFilePath = mediaFilePath;
                    String outputMediaFilePath = inputMediaFilePath
                            .replace(inputDirPath, outputDirPath)
                            .replaceFirst(subDirName, videoName);
                    String outputMediaDirPath = "";
                    String[] outputPathToken = outputMediaFilePath.split(File.separator);
                    for (int i = 0; i < outputPathToken.length - 1; ++i) {
                        outputMediaDirPath += outputPathToken[i];
                        outputMediaDirPath += File.separator;
                    }
                    if (!new File(outputMediaDirPath).exists()) {
                        new File(outputMediaDirPath).mkdirs();
                    }
                    Converter.convert(inputMediaFilePath, outputMediaFilePath);
                }
            }
        }
    }
}
