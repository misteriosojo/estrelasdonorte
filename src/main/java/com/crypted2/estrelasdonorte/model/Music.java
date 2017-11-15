package com.crypted2.estrelasdonorte.model;

import com.crypted2.estrelasdonorte.database.modelsupport.SimpleIntegerPropertyPersister;
import com.crypted2.estrelasdonorte.database.modelsupport.SimpleStringPropertyPersister;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
@DatabaseTable(tableName = "music")
public class Music {
    @DatabaseField(id = true, persisterClass = SimpleStringPropertyPersister.class)
    private StringProperty title;
    @DatabaseField(persisterClass = SimpleStringPropertyPersister.class)
    private StringProperty author;
    @DatabaseField(persisterClass = SimpleStringPropertyPersister.class)
    private StringProperty singer;
    @DatabaseField(persisterClass = SimpleStringPropertyPersister.class)
    private StringProperty genre;
    //    private MusicGenre genre = MusicGenre.UNKNOWN;
    @DatabaseField(persisterClass = SimpleIntegerPropertyPersister.class)
    private IntegerProperty speed;
    @DatabaseField(persisterClass = SimpleIntegerPropertyPersister.class)
    private IntegerProperty transpose;
    @DatabaseField(persisterClass = SimpleStringPropertyPersister.class)
    private StringProperty pdfFileName;

    Music() {

    }

    public Music(StringProperty title, StringProperty author, StringProperty singer, StringProperty genre, IntegerProperty speed, IntegerProperty transpose, StringProperty pdfFileName) {
        this.title = title;
        this.author = author;
        this.singer = singer;
        this.genre = genre;
        this.speed = speed;
        this.transpose = transpose;
        this.pdfFileName = pdfFileName;
    }

}
