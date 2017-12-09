package com.crypted2.estrelasdonorte;

public class EstrelasConfig {
    public static class Database {
        public static final String URL = "jdbc:sqlite:database/EstrelasDoNorte.db"; // "jdbc:sqlite:sample.db"
    }

    public static class Pattern {
        public static final String PDF_PATTERN = "%3d-[%s]_%s"; // Number - [Singer] _ SongName
        public static final String USER_PROGRAM_PATTERN = "%3d-[%s]_%s"; // Number - [Sex] _ UserProgramName
    }
}
