package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "live_concert_music")
public class LiveConcertMusic {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Music music;
    @DatabaseField(canBeNull = false, foreign = true)
    private UserProgram userProgram;
    @DatabaseField
    private String singer = Singer.UNKNOWN.toString();
    @DatabaseField
    private int transpose;
    @DatabaseField(foreign = true)
    private LiveConcertProgram liveConcertProgram;

    LiveConcertMusic() {

    }
}
