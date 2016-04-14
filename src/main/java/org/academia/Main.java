package org.academia;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main extends Application {

private ApplicationContext applicationContext;




    @Override
    public void start(Stage primaryStage) throws Exception {

        applicationContext = new ClassPathXmlApplicationContext("/spring/spring-config.xml");


        Navigation navigation = applicationContext.getBean(Navigation.class);
        navigation.setStage(primaryStage);
        navigation.loadView("LoginWindow");



       primaryStage.setResizable(false);
/*
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadView("LoginWindow");*/

        //HibernateSessionManager.beginTransaction();//moved to: after init transition beause it slow down the animation

    }

    @Override
    public void init() {


    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //HibernateSessionManager.close();

        applicationContext.getBean(HibernateSessionManager.class).close();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
