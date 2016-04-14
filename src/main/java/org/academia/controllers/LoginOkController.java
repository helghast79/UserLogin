package org.academia.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginOkController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView picture;

    @FXML
    private Label messageLbl;

    @FXML
    void initialize() {
        assert picture != null : "fx:id=\"picture\" was not injected: check your FXML file 'loginOkView.fxml'.";
        assert messageLbl != null : "fx:id=\"messageLbl\" was not injected: check your FXML file 'loginOkView.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
