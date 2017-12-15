package com.crypted2.estrelasdonorte.controller;


import com.crypted2.estrelasdonorte.database.DbConnector;
import com.crypted2.estrelasdonorte.model.LiveConcertMusic;
import com.crypted2.estrelasdonorte.model.LiveConcertProgram;
import com.crypted2.estrelasdonorte.model.MusicGenre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Drag & Drop in a List View: http://blog.ngopal.com.np/2012/05/06/javafx-drag-and-drop-cell-in-listview/
 */
public class MainController {
    private final ObservableList<LiveConcertProgram> liveConcertProgramObservableList = FXCollections.observableArrayList();
    private final ObservableList<LiveConcertMusic> liveConcertMusicObservableList = FXCollections.observableArrayList();
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    @FXML
    private ListView<LiveConcertProgram> lvLiveConcertProgram;
    @FXML
    private ListView<LiveConcertMusic> lvLiveConcertMusic;

    /* General info */
    @FXML
    private Text tGeneralMusicTitle;
    @FXML
    private Text tGeneralSinger;
    @FXML
    private Text tGeneralSingerHelper;
    @FXML
    private Text tGeneralTranspose;
    @FXML
    private Text tGeneralNotes;

    /* User Program info */
    @FXML
    private Text tUserProgramName;
    @FXML
    private Text tUserProgramStyle;
    @FXML
    private Text tUserProgramTempo;
    @FXML
    private Text tUserProgramTranspose;
    @FXML
    private Text tUserProgramInstruments;
    @FXML
    private Text tUserProgramNotes;

    /* Others */
    @FXML
    private Button bOpenPDF;

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
            if(lvLiveConcertProgram.getSelectionModel().getSelectedItem() == null)
                return;
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

        lvLiveConcertMusic.setOnMouseClicked(event -> {
            LiveConcertMusic liveConcertMusic = lvLiveConcertMusic.getSelectionModel().getSelectedItem();
            if(liveConcertMusic == null)
                return;

            tGeneralMusicTitle.setText(liveConcertMusic.getMusic().getTitle());
            tGeneralSinger.setText(liveConcertMusic.getSinger());
            //ToDo:            tGeneralSingerHelper.setText();
            tGeneralTranspose.setText("" + liveConcertMusic.getTranspose());

            tUserProgramName.setText(liveConcertMusic.getUserProgram().getName());
            tUserProgramStyle.setText(liveConcertMusic.getUserProgram().getStyle());
            tUserProgramTempo.setText("" + liveConcertMusic.getUserProgram().getTempo());
            tUserProgramTranspose.setText((liveConcertMusic.getUserProgram().getTranspose() > 0 ? "+" : "") + liveConcertMusic.getUserProgram().getTranspose());
            tUserProgramInstruments.setText(liveConcertMusic.getUserProgram().getInstruments());
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


}

