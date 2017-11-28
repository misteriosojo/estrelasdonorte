package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "music")
public class Music {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField
    private String author;
    @DatabaseField(canBeNull = false)
    private int genre = MusicGenre.UNKNOWN.ordinal();
    @DatabaseField
    private String pdfFileName;

    Music() {

    }

    public Music(String title, String author, int genre, String pdfFileName) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pdfFileName = pdfFileName;
    }

}
