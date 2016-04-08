package org.academia;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by codecadet on 17/03/16.
 */
public class Navigation {

    //store loaders to be possible to comunicate between them
    private FXMLLoader ctrLogin;
    private FXMLLoader ctrLoginOK;

    // static instance of this class
    private static Navigation instance = null;


    private Stage stage; // reference to the application window


    // private constructor so its not possible to instantiate from outside
    private Navigation() {
    }

    // static method that returns the instance
    public static Navigation getInstance() {

        // the instance is created only the first time this method is called
        if (instance == null) {
            instance = new Navigation();

        }

        // it always return the same instance, there is no way to have another one
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



    private void setScene(Scene scene) {

        // setArgsPassedByGameSetupController the scene
        stage.setScene(scene);

        // show the stage to reload
        stage.show();
    }

    public void loadView(String view) {
        try {

            // Instantiate the view and the controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(Settings.FILES_FXML_FOLDER + view + ".fxml"));
            Parent root = fxmlLoader.load();

            //store the loader because it's necessary to comunicate between org.academiadecodigo.bootcamp.controllers
            if (view.equals("loginWindow")) {
                root.getStylesheets().add("/Login.css");
            } else if (view.equals("loginOKView")) {
                root.getStylesheets().add("/Login.css");
            }

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);


            // Put the scene on the stage
            setScene(scene);


        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }


    }





}
