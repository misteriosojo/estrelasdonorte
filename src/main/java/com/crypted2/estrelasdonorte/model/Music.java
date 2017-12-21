package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Music music = (Music) o;
        return getGenre() == music.getGenre() &&
                Objects.equals(getTitle(), music.getTitle()) &&
                Objects.equals(getAuthor(), music.getAuthor());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getTitle(), getAuthor(), getGenre());
    }
}
