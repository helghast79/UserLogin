package org.academia;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academia.persistence.hibernate.HibernateSessionManager;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadView("LoginWindow");

        HibernateSessionManager.beginTransaction();

    }

    @Override
    public void init() {


    }

    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateSessionManager.close();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
