package com.crypted2.estrelasdonorte.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class FileLiveConcertProgramRenamer {

    public static void main(String[] args) {
        String listToParse = "E:\\list_pregassona.txt"; // UTF-8 !!
        String folderPDF = "E:\\ketron_sd9";

        File folder = new File(folderPDF);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("ERROR: No files...");
            return;
        }

        ArrayList<String> parseList = parseList(listToParse);

        for (File currentFile : listOfFiles) {
            String fileName = currentFile.getName();

            Optional<String> found = parseList.stream()
                    .filter(name -> fileName.toLowerCase().contains(name.toLowerCase()))
                    .findFirst();

            if (!found.isPresent()) {
                System.out.println("File " + fileName + " not found in the list.");
                continue;
            }

            System.out.println("Creating: " + String.format("%s\\%03d_%s", folderPDF, parseList.indexOf(found.get()) + 1, fileName));
            File newFile = new File(String.format("%s\\%03d_%s", folderPDF, parseList.indexOf(found.get()) + 1, fileName));
            if (!currentFile.renameTo(newFile)) {
                System.err.println("Impossible to rename the file" + fileName);
            }
        }
    }

    public static ArrayList<String> parseList(String fileToParse) {
        File myFile = new File(fileToParse);

        if (!myFile.exists()) {
            System.err.println("ERROR: file not found --> " + fileToParse);
            return null;
        }

        try {
            FileReader fileReader = new FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            ArrayList<String> listOfSongs = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null)
                listOfSongs.add(line.trim());

            fileReader.close();
            return listOfSongs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
