package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Objects;

@Data
@DatabaseTable(tableName = "live_concert_program")
public class LiveConcertProgram {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @ForeignCollectionField
    private ForeignCollection<LiveConcertMusic> liveConcertMusics;

    public LiveConcertProgram() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiveConcertProgram that = (LiveConcertProgram) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getName());
    }
}
