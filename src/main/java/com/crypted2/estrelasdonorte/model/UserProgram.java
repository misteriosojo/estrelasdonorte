package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

import java.util.Objects;

@Data
@DatabaseTable(tableName = "user_program")
public class UserProgram {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String style;
    @DatabaseField
    private int tempo;
    @DatabaseField
    private int transpose;
    @DatabaseField
    private String instruments;
    @DatabaseField
    private String otherNotes;

    public UserProgram() {

    }

    public UserProgram(String name, String style, int tempo, int transpose, String instruments, String otherNotes) {
        this.name = name;
        this.style = style;
        this.tempo = tempo;
        this.transpose = transpose;
        this.instruments = instruments;
        this.otherNotes = otherNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserProgram that = (UserProgram) o;
        return getTempo() == that.getTempo() &&
                getTranspose() == that.getTranspose() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStyle(), that.getStyle()) &&
                Objects.equals(getInstruments(), that.getInstruments());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getName(), getStyle(), getTempo(), getTranspose(), getInstruments());
    }
}
