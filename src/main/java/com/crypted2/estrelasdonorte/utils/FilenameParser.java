package com.crypted2.estrelasdonorte.utils;

import java.io.File;

public class FilenameParser {

    public static void main(String[] args) {
        String path = "D:\\Dropbox\\EstrelasDoNorte\\Testi_iPad\\Testi_KetronSD9\\docx";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("ERROR: No files...");
            return;
        }

        for (File listOfFile : listOfFiles) {
            String fileName = listOfFile.getName();
            String[] nameSplitted = fileName.split("-");

            String title = nameSplitted[nameSplitted.length <= 1 ? 0 : 1].split("\\.")[0].trim();
            String author = nameSplitted.length <= 1 ? "" : nameSplitted[0].trim();

            System.out.println(title + "\t" + author);
        }
    }

}
