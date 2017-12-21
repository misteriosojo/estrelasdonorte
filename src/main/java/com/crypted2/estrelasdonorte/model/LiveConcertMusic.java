package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Objects;

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
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Singer singer;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Singer singerHelper;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiveConcertMusic that = (LiveConcertMusic) o;
        return getTranspose() == that.getTranspose() &&
                Objects.equals(getMusic(), that.getMusic()) &&
                Objects.equals(getUserProgram(), that.getUserProgram()) &&
                Objects.equals(getSinger(), that.getSinger()) &&
                Objects.equals(getSingerHelper(), that.getSingerHelper());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getMusic(), getUserProgram(), getSinger(), getSingerHelper(), getTranspose());
    }
}
