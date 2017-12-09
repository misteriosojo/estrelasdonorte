package com.crypted2.estrelasdonorte.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

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
}
