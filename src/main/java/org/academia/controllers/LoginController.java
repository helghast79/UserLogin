/**
 * Sample Skeleton for 'LoginWindow.fxml' Controller Class
 */

package org.academia.controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import org.academia.Navigation;

import org.academia.model.User;
import org.academia.service.user.UserService;
import org.academia.service.user.UserServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginController implements Initializable {


    private final double ROTATE_USERNAME = -3.6d;
    private final double ROTATE_PASSWORD = 3.0d;
    private final double ROTATE_EMAIL = -3.4d;
    private final double ROTATE_BTN_SUBMIT = 10.3d;

    UserService userService;
    //UserService userService;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView picture;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label passwordLbl;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passWordField;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLbl;

    @FXML
    private Button btnRegister;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert picture != null : "fx:id=\"picture\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert usernameLbl != null : "fx:id=\"usernameLbl\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passwordLbl != null : "fx:id=\"passwordLbl\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert passWordField != null : "fx:id=\"passWordField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert emailLbl != null : "fx:id=\"emailLbl\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }


    //events -------------------------

    @FXML
    void btnLoginOnClick(MouseEvent event) {
        if (userService.authenticate(usernameField.getText(), passWordField.getText())) {
            transitionLoginAccepted();

        } else {
            //clearForm();
            System.out.println("failed");
            transitionFailed(usernameField);
        }
    }

    @FXML
    void btnRegisterOnClick(MouseEvent event) {
        userService.addUser(new User(usernameField.getText(),
                emailField.getText(),
                passWordField.getText()));


        clearForm();
    }

    @FXML
    void emailChanged(KeyEvent event) {
        if (validateEmail(getEmailFieldText())) {
            emailField.setRotate(0);
            emailLbl.setRotate(0);
        } else {
            emailField.setRotate(ROTATE_EMAIL);
            emailLbl.setRotate(-ROTATE_EMAIL);
        }

        validateButtonSubmit();
    }

    @FXML
    void passwordChanged(KeyEvent event) {
        if (validatePassword(passWordField.getText())) {
            passWordField.setRotate(0);
            passwordLbl.setRotate(0);
        } else {
            passWordField.setRotate(ROTATE_PASSWORD);
            passwordLbl.setRotate(-ROTATE_PASSWORD);
        }
        validateButtonSubmit();
    }

    @FXML
    void usernameChanged(KeyEvent event) {

        if (validateUsername(usernameField.getText())) {
            usernameField.setRotate(0);
            usernameLbl.setRotate(0);


            /*if (userService.findByName(usernameField.getText()) != null) {

                btnSubmit.setText("Login");
                emailField.setVisible(false);
                emailLbl.setVisible(false);
            } else {
                btnSubmit.setText("Register");
                emailField.setVisible(true);
                emailLbl.setVisible(true);
            }
*/
        } else {
            usernameField.setRotate(ROTATE_USERNAME);
            usernameLbl.setRotate(-ROTATE_USERNAME);
        }

        validateButtonSubmit();
    }

    //--------------------------------







    public String getEmailFieldText() {
        return emailField.getText();
    }


    private void clearForm() {
        usernameField.setText("");
        passWordField.setText("");
        emailField.setText("");

        passWordField.setRotate(ROTATE_PASSWORD);
        passwordLbl.setRotate(-ROTATE_PASSWORD);

        emailField.setRotate(ROTATE_EMAIL);
        emailLbl.setRotate(-ROTATE_EMAIL);

        usernameField.setRotate(ROTATE_USERNAME);
        usernameLbl.setRotate(-ROTATE_USERNAME);

        btnLogin.setRotate(ROTATE_BTN_SUBMIT);
        btnLogin.setOpacity(1.0d);
        btnLogin.setDisable(true);

        btnRegister.setRotate(ROTATE_BTN_SUBMIT);
        btnRegister.setOpacity(1.0d);
        btnRegister.setDisable(true);
    }











    private void validateButtonSubmit() {

        boolean allOk = false;


        if (usernameField.getRotate() == 0 && passWordField.getRotate() == 0) {

            if (emailField.getText().equals("") ) {


                btnLogin.setRotate(0);
                btnLogin.setVisible(true);
                btnLogin.setOpacity(1.0d);
                btnLogin.setDisable(false);

                btnRegister.setRotate(ROTATE_BTN_SUBMIT);
                btnRegister.setVisible(false);
                btnRegister.setOpacity(0.6d);
                btnRegister.setDisable(true);

            } else {

                btnLogin.setRotate(ROTATE_BTN_SUBMIT);
                btnLogin.setVisible(false);
                btnLogin.setOpacity(0.6d);
                btnLogin.setDisable(true);

                btnRegister.setVisible(true);

                if(emailField.getRotate() == 0){
                    btnRegister.setRotate(0);
                    btnRegister.setOpacity(1.0d);
                    btnRegister.setDisable(false);
                }else{
                    btnRegister.setRotate(ROTATE_BTN_SUBMIT);
                    btnRegister.setOpacity(0.6d);
                    btnRegister.setDisable(true);
                }

            }


        } else {

            btnLogin.setRotate(ROTATE_BTN_SUBMIT);
            btnLogin.setOpacity(0.6d);
            btnLogin.setDisable(true);

            btnRegister.setRotate(ROTATE_BTN_SUBMIT);
            btnRegister.setOpacity(0.6d);
            btnRegister.setDisable(true);

            if (emailField.getText().equals("") ) {

                btnLogin.setVisible(true);

                btnRegister.setVisible(false);

            }else{

                btnLogin.setVisible(false);

                btnRegister.setVisible(true);

            }


        }


    }

    private void initServices() {
        //this.userService = new JdbcUserService();
        this.userService = new UserServiceImpl();

    }


    private boolean validateEmail(String txtEntered) {

        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(txtEntered);
        return matcher.matches();
    }

    private boolean validateUsername(String txtEntered) {

        return (txtEntered.length() > 1);
    }

    private boolean validatePassword(String txtEntered) {

        return (txtEntered.length() > 1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initServices();
        clearForm();
        initialTransition(picture);


    }

    private void initialTransition(Node o) {


       /* //add a specific action when the transition is finished
        EventHandler initialTransitionFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                transitionLoginAccepted();
            }
        };
*/


        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(1000), o);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.4f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);


        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(2000), o);
        translateTransition.setFromY(0);
        translateTransition.setToY(130);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);


        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(2000), o);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                translateTransition,
                scaleTransition,
                fadeTransition);
        sequentialTransition.setCycleCount(1);
        sequentialTransition.setAutoReverse(true);

        //sequentialTransition.setOnFinished(initialTransitionFinished);
        sequentialTransition.play();

    }

    //Animations
    private void transitionLoginAccepted() {


        //add a specific action when the transition is finished
        EventHandler transitionEnded = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                Navigation.getInstance().loadView("loginOkView");
            }
        };


        RotateTransition r1 = createRotationTransition(2000,usernameField,90,1);
        RotateTransition r2 = createRotationTransition(2000,passWordField,-90,1);
        RotateTransition r3 = createRotationTransition(900,emailField,90,1);
        RotateTransition r4 = createRotationTransition(1500,usernameLbl,-90,1);
        RotateTransition r5 = createRotationTransition(2500,passwordLbl,90,1);
        RotateTransition r6 = createRotationTransition(2000,emailLbl,-90,1);
        RotateTransition r7 = createRotationTransition(1500,btnLogin,90,1);
        RotateTransition r8 = createRotationTransition(1000,btnRegister,90,1);

        ParallelTransition ptr = new ParallelTransition();
        ptr.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7,r8);
        ptr.setCycleCount(1);
        ptr.setAutoReverse(true);



        //translate
        TranslateTransition t1 = createTranslateTransition(2000,usernameField,0,-500,1);
        TranslateTransition t2 = createTranslateTransition(2000,passWordField,0,-500,1);
        TranslateTransition t3 = createTranslateTransition(900,emailField,0,-500,1);
        TranslateTransition t4 = createTranslateTransition(1500,usernameLbl,0,-500,1);
        TranslateTransition t5 = createTranslateTransition(2500,passwordLbl,0,-500,1);
        TranslateTransition t6 = createTranslateTransition(2000,emailLbl,0,-500,1);
        TranslateTransition t7 = createTranslateTransition(1500,btnLogin,0,-500,1);
        TranslateTransition t8 = createTranslateTransition(1000,btnRegister,0,300,1);

        ParallelTransition ptr2 = new ParallelTransition();
        ptr2.getChildren().addAll(t1, t2, t3, t4, t5, t6, t7,t8);
        ptr2.setCycleCount(1);
        ptr2.setAutoReverse(false);

        SequentialTransition sq = new SequentialTransition(ptr,ptr2);
        sq.setCycleCount(1);
        sq.setAutoReverse(false);
        sq.setOnFinished(transitionEnded);
        sq.play();

        //ptr.setOnFinished(transitionEnded);
        //ptr.play();


    }

    private void transitionFailed(Node o) {

//add a specific action when the transition is finished
        EventHandler transitionFailedEnded = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                String us = usernameField.getText();

                clearForm();
                usernameField.setText(us);
                usernameChanged(null);
            }
        };


        RotateTransition r1 = new RotateTransition(Duration.millis(600), usernameField);

        r1.setByAngle(-360);
        r1.setCycleCount(2);


        RotateTransition r2 = new RotateTransition(Duration.millis(800), passWordField);

        r2.setByAngle(360);
        r2.setCycleCount(2);


        RotateTransition r3 = new RotateTransition(Duration.millis(600), emailField);

        r3.setByAngle(-360);
        r3.setCycleCount(2);


        RotateTransition r4 = new RotateTransition(Duration.millis(900), usernameLbl);

        r4.setByAngle(360);
        r4.setCycleCount(2);


        RotateTransition r5 = new RotateTransition(Duration.millis(400), passwordLbl);

        r5.setByAngle(-360);
        r5.setCycleCount(2);


        RotateTransition r6 = new RotateTransition(Duration.millis(700), emailLbl);

        r6.setByAngle(360);
        r6.setCycleCount(2);


        RotateTransition r7 = new RotateTransition(Duration.millis(1000), btnLogin);
        r7.setByAngle(-360);
        r7.setCycleCount(2);

        RotateTransition r8 = new RotateTransition(Duration.millis(1300), btnRegister);
        r8.setByAngle(360);
        r8.setCycleCount(2);


        ParallelTransition ptr = new ParallelTransition();
        ptr.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7,r8);
        ptr.setCycleCount(2);
        ptr.setAutoReverse(true);

        ptr.setOnFinished(transitionFailedEnded);
        ptr.play();

    }


    private TranslateTransition createTranslateTransition(int millis,Node node, int byX, int byY,int cycles){
        TranslateTransition t = new TranslateTransition(Duration.millis(millis),node);
        t.setByX(byX);
        t.setByY(byY);
        t.setCycleCount(cycles);
        return t;

    }

    private RotateTransition createRotationTransition(int millis,Node node, int byAngle, int cycles){
        RotateTransition r = new RotateTransition(Duration.millis(millis),node);
        r.setByAngle(byAngle);
        r.setCycleCount(cycles);
        return r;

    }

}
