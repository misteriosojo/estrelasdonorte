package com.crypted2.estrelasdonorte.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        File folder = new File(RolandManagerConfig.FOLDER);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            System.out.println("ERROR: No files...");
            return;
        }

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && listOfFile.getName().endsWith(RolandManagerConfig.USER_PROGRAM_EXTENSION)) {
                getParameters(listOfFile);
            } else if (listOfFile.isDirectory()) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Directory " + listOfFile.getName());
            }
        }
    }

    public static void getParameters(File file) throws IOException {
        byte[] buffer = Files.asByteSource(file).read();

        int transpose = buffer[RolandManagerConfig.OFFSET_TRANSPOSE];
        int tempo = buffer[RolandManagerConfig.OFFSET_TEMPO] & 0xff;

        StringBuilder sb = new StringBuilder();
        for (int i = RolandManagerConfig.OFFSET_STYLE; i < (RolandManagerConfig.OFFSET_STYLE + RolandManagerConfig.MAX_STYLE_LENGTH); i++) {
            if (buffer[i] == 0)
                break;
            sb.append((char) buffer[i]);
        }
        String style = sb.toString();

        if (RolandManagerConfig.SHOW_COMPACT) {
            System.out.println(String.format("%s%s%d%s%s%s%d",  // %22s%s%2d%s%17s%s%3d
                    file.getName(),
                    RolandManagerConfig.SEPARATOR,
                    transpose,
                    RolandManagerConfig.SEPARATOR,
                    style,
                    RolandManagerConfig.SEPARATOR,
                    tempo));
        } else {
            System.out.println("*******" + file.getName() + "*******");
            System.out.println("\t> Transpose: " + transpose);
            System.out.println("\t> Style: " + style);
            System.out.println("\t> Tempo: " + tempo);
        }
    }
}
