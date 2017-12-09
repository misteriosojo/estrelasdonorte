package com.crypted2.estrelasdonorte.controller;


import com.crypted2.estrelasdonorte.database.DbConnector;
import com.crypted2.estrelasdonorte.model.LiveConcertMusic;
import com.crypted2.estrelasdonorte.model.LiveConcertProgram;
import com.crypted2.estrelasdonorte.model.MusicGenre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class MainController {
    private final ObservableList<LiveConcertProgram> liveConcertProgramObservableList = FXCollections.observableArrayList();
    private final ObservableList<LiveConcertMusic> liveConcertMusicObservableList = FXCollections.observableArrayList();
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    @FXML
    private ListView<LiveConcertProgram> lvLiveConcertProgram;
    @FXML
    private ListView<LiveConcertMusic> lvLiveConcertMusic;

    /**
     * Init operations
     */
    @FXML
    private void initialize() {
        initFromDatabase(LiveConcertProgram.class, liveConcertProgramObservableList);
        initializeLiveConcertProgram();
        initializeLiveConcertMusic();
    }

    /**
     * Init Live Concert Program
     */
    private void initializeLiveConcertProgram() {
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

        lvLiveConcertProgram.setOnMouseClicked(event -> {
            System.out.println("clicked on " + lvLiveConcertProgram.getSelectionModel().getSelectedItem());
            liveConcertMusicObservableList.setAll(lvLiveConcertProgram.getSelectionModel().getSelectedItem().getLiveConcertMusics());
            lvLiveConcertMusic.setItems(liveConcertMusicObservableList);
        });
    }

    /**
     * Init Live Concert Music
     */
    private void initializeLiveConcertMusic() {
        lvLiveConcertMusic.setCellFactory(new Callback<ListView<LiveConcertMusic>, ListCell<LiveConcertMusic>>() {
            @Override
            public ListCell<LiveConcertMusic> call(ListView<LiveConcertMusic> p) {
                return new ListCell<LiveConcertMusic>() {
                    @Override
                    protected void updateItem(LiveConcertMusic t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null)
                            setText(String.format("%3d - %s [%s]", t.getPosition(), t.getMusic().getTitle(), MusicGenre.values()[t.getMusic().getGenre()])); // ToDo: Check again array out of bounds exception
                    }
                };
            }
        });

//        lvLiveConcertMusic.setOnMouseClicked(event -> {
//            System.out.println("clicked on " + lvLiveConcertMusic.getSelectionModel().getSelectedItem());
//            liveConcertMusicObservableList.setAll(lvLiveConcertMusic.getSelectionModel().getSelectedItem().getLiveConcertMusics());
//            lvLiveConcertMusic.setItems(liveConcertMusicObservableList);
//        });
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


}

