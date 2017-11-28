package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "live_concert_program")
public class LiveConcertProgram {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @ForeignCollectionField
    ForeignCollection<LiveConcertMusic> liveConcertMusics;

    LiveConcertProgram() {

    }
}
