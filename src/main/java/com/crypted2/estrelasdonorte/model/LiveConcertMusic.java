package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@Data
@DatabaseTable(tableName = "live_concert_music")
public class LiveConcertMusic implements Comparable<LiveConcertMusic> {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int position;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Music music;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private UserProgram userProgram;
    @DatabaseField
    private String singer = Singer.UNKNOWN.toString();
    @DatabaseField
    private int transpose;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private LiveConcertProgram liveConcertProgram;

    public LiveConcertMusic() {

    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(LiveConcertMusic o) {
        return position - o.position;
    }
}
