package com.crypted2.estrelasdonorte.controller;


import com.crypted2.estrelasdonorte.database.DbConnector;
import com.crypted2.estrelasdonorte.model.LiveConcertMusic;
import com.crypted2.estrelasdonorte.model.LiveConcertProgram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private final ObservableList<LiveConcertProgram> liveConcertProgramObservableList = FXCollections.observableArrayList();
    private final ObservableList<LiveConcertMusic> liveConcertMusicObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<LiveConcertProgram> lvLiveConcertProgram;
    @FXML
    private ListView<LiveConcertMusic> lvLiveConcertMusic;

    @FXML
    private void initialize() {
        initFromDatabase(LiveConcertProgram.class, liveConcertProgramObservableList);

        lvLiveConcertProgram.setItems(liveConcertProgramObservableList);
        lvLiveConcertProgram.setCellFactory(new Callback<ListView<LiveConcertProgram>, ListCell<LiveConcertProgram>>() {

            @Override
            public ListCell<LiveConcertProgram> call(ListView<LiveConcertProgram> p) {
                return new ListCell<LiveConcertProgram>() {

                    @Override
                    protected void updateItem(LiveConcertProgram t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null)
                            setText(t.getName());
                    }

                };
            }
        });

        lvLiveConcertProgram.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + lvLiveConcertProgram.getSelectionModel().getSelectedItem());
                liveConcertMusicObservableList.setAll(lvLiveConcertProgram.getSelectionModel().getSelectedItem().getLiveConcertMusics());
                lvLiveConcertMusic.setItems(liveConcertMusicObservableList);
            }
        });


        lvLiveConcertMusic.setCellFactory(new Callback<ListView<LiveConcertMusic>, ListCell<LiveConcertMusic>>() {

            @Override
            public ListCell<LiveConcertMusic> call(ListView<LiveConcertMusic> p) {
                return new ListCell<LiveConcertMusic>() {

                    @Override
                    protected void updateItem(LiveConcertMusic t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null)
                            setText(t.getMusic().getTitle());
                    }

                };
            }
        });
    }

    /**
     * Read items from database
     *
     * @param clazz
     * @param observableList
     */
    private <T> void initFromDatabase(Class<T> clazz, ObservableList<T> observableList) {
        List<T> resultList = DbConnector.getInstance().readAll(clazz);
        if (resultList != null) {
            logger.debug("Fetched {} items from class {}", resultList.size(), clazz.getSimpleName());
            observableList.addAll(resultList);
        } else
            logger.error("Impossible to retrieve items of class: {}", clazz.getSimpleName());
    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        System.out.println("clicked on " + lvLiveConcertProgram.getSelectionModel().getSelectedItem());
    }

}

/////////////////////////////////////////////////////////////////////////////////////////////
//        liveConcertProgramObservableList.forEach(lcpo -> {
//        System.out.println("****** NEW LiveConcertProgram LOADED *****");
//        System.out.println("Name: " + lcpo.getName());
//        System.out.println("LiveConcertMusics size: " + lcpo.getLiveConcertMusics().size());
//        lcpo.getLiveConcertMusics().forEach(a -> System.out.println(" > " + a.getMusic().getTitle()));
//        });

/////////////////////////////////////////////////////////////////////////////////////////////
//        LiveConcertProgram liveConcertProgram = new LiveConcertProgram();
//        liveConcertProgram.setName("Capodanno 2017");
//
//        Music music = new Music("Meu titulo", "Quim Barreiros", MusicGenre.PIMBA.ordinal(), null);
//
//        UserProgram userProgram = new UserProgram("Meu User Program", "R0_AR0", 137, +3, "Some instruments", "");
//
//        LiveConcertMusic liveConcertMusics = new LiveConcertMusic();
//        liveConcertMusics.setSinger("Jorge");
//        liveConcertMusics.setTranspose(+3);
//        liveConcertMusics.setMusic(music);
//        liveConcertMusics.setUserProgram(userProgram);
//        liveConcertMusics.setLiveConcertProgram(liveConcertProgram);
//
//        DbConnector.getInstance().create(liveConcertProgram);
//        DbConnector.getInstance().create(music);
//        DbConnector.getInstance().create(userProgram);
//        DbConnector.getInstance().create(liveConcertMusics);
