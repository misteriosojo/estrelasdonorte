package com.crypted2.estrelasdonorte.model;

import lombok.Data;

@Data
public class Music {
    private String title;
    private String author;
    private MusicGenre genre = MusicGenre.UNKNOWN;

    private short speed;
    private short transpose;
}
