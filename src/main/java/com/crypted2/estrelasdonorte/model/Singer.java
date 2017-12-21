package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Objects;

@Data
@DatabaseTable(tableName = "singer")
public class Singer {
    /*
    UNKNOWN,
    JORGE,
    ZE,
    LUIS,
    RICARDO,
    INES,
    LEO
     */
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String notes;

    public Singer() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Singer singer = (Singer) o;
        return Objects.equals(getName(), singer.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getName());
    }

    public Singer(String name) {

        this.name = name;
    }


}
