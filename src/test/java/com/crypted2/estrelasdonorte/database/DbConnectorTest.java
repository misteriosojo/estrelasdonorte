package com.crypted2.estrelasdonorte.database;

import com.crypted2.estrelasdonorte.model.*;
import org.junit.jupiter.api.Test;

public class DbConnectorTest {
    // To create an in-memory-db
    // Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");

    @Test
    public void addTestData() {
        ///////////////////////////////////////////////////////////////////////////////////////////
        //        liveConcertProgramObservableList.forEach(lcpo -> {
        //            System.out.println("****** NEW LiveConcertProgram LOADED *****");
        //            System.out.println("Name: " + lcpo.getName());
        //            System.out.println("LiveConcertMusics size: " + lcpo.getLiveConcertMusics().size());
        //            lcpo.getLiveConcertMusics().forEach(a -> System.out.println(" > " + a.getMusic().getTitle()));
        //        });

        ///////////////////////////////////////////////////////////////////////////////////////////
        LiveConcertProgram liveConcertProgram = new LiveConcertProgram();
        liveConcertProgram.setName("Capodanno 2017");

        Music music = new Music("Meu titulo", "Quim Barreiros", MusicGenre.PIMBA.ordinal(), null);

        UserProgram userProgram = new UserProgram("Meu User Program", "R0_AR0", 137, +3, "Some instruments", "");

        Singer singer = new Singer("Jorge");

        LiveConcertMusic liveConcertMusic = new LiveConcertMusic();
        liveConcertMusic.setPosition(1);
        liveConcertMusic.setSinger(singer);
        liveConcertMusic.setTranspose(+3);
        liveConcertMusic.setMusic(music);
        liveConcertMusic.setUserProgram(userProgram);
        liveConcertMusic.setLiveConcertProgram(liveConcertProgram);

        DbConnector.getInstance().createOrUpdate(liveConcertProgram);
        DbConnector.getInstance().createOrUpdate(music);
        DbConnector.getInstance().createOrUpdate(userProgram);
        DbConnector.getInstance().createOrUpdate(singer);
        DbConnector.getInstance().createOrUpdate(liveConcertMusic);

    }
}
