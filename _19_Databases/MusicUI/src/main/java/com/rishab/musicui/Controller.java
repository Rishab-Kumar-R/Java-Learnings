package com.rishab.musicui;

import com.rishab.musicui.model.Artist;
import com.rishab.musicui.model.Datasource;
import com.rishab.musicui.model.Displayable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView<Displayable> artistTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void initialize() {
        artistTable.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void listArtists() {
        Task<ObservableList<Displayable>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist() {
        final Displayable displayable = artistTable.getSelectionModel().getSelectedItem();

        if (displayable != null) {

            Task<ObservableList<Displayable>> task = new Task<>() {
                @Override
                protected ObservableList<Displayable> call() {
                    return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(((Artist) displayable).getId()));
                }
            };
            artistTable.itemsProperty().bind(task.valueProperty());

            new Thread(task).start();
        } else {
            System.out.println("No artist selected");
        }
    }
}

class GetAllArtistsTask extends Task<ObservableList<Displayable>> {
    @Override
    public ObservableList<Displayable> call() {
        return FXCollections.observableArrayList(
            Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}
