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

import org.academia.model.dao.RoleDao;
import org.academia.model.dao.UserDao;
import org.academia.model.dao.hibernate.HibernateRoleDao;
import org.academia.model.dao.hibernate.HibernateUserDao;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.academia.persistence.hibernate.HibernateTransactionManager;
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
    private final double ROTATE_ROLE = -30.3;

    UserService userService;
    //UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
    @FXML
    private TextField role;

    @FXML
    private Label roleLbl;


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


        userService.addUserRole(usernameField.getText(),role.getText() );

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

        } else {
            usernameField.setRotate(ROTATE_USERNAME);
            usernameLbl.setRotate(-ROTATE_USERNAME);
        }

        validateButtonSubmit();
    }

    @FXML
    void roleFieldChanged(KeyEvent event) {
        if (validateRole(role.getText())) {
            role.setRotate(0);
            roleLbl.setRotate(0);
        } else {
            role.setRotate(ROTATE_ROLE);
            roleLbl.setRotate(-ROTATE_ROLE);
        }
        validateButtonSubmit();
    }


    //--------------------------------

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
        assert role != null : "fx:id=\"role\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert roleLbl != null : "fx:id=\"roleLbl\" was not injected: check your FXML file 'LoginWindow.fxml'.";


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initServices();
        clearForm();
        initialTransition(picture);


    }

    private void initServices() {
        //this.userService = new JdbcUserService();
        //this.userService = new UserServiceImpl(new HibernateUserDao(), new HibernateRoleDao(), new HibernateTransactionManager());
    }



    public String getEmailFieldText() {
        return emailField.getText();
    }

    private void validateButtonSubmit() {

        boolean allOk = false;


        if (usernameField.getRotate() == 0 && passWordField.getRotate() == 0 && role.getRotate()==0) {

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

    private boolean validateRole(String txtEntered) {

        return (txtEntered.length() > 1);
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





    //Animations
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


        RotateTransition r1 = createRotationTransition(600,usernameField,-360,2);
        RotateTransition r2 = createRotationTransition(800,passWordField,360,2);
        RotateTransition r3 = createRotationTransition(600,emailField,-360,2);
        RotateTransition r4 = createRotationTransition(900,usernameLbl,360,2);
        RotateTransition r5 = createRotationTransition(400,passwordLbl,-360,2);
        RotateTransition r6 = createRotationTransition(700,emailLbl,360,2);
        RotateTransition r7 = createRotationTransition(1000,btnLogin,-360,2);
        RotateTransition r8 = createRotationTransition(1300,btnRegister,360,2);


        ParallelTransition ptr = new ParallelTransition();
        ptr.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7,r8);
        ptr.setCycleCount(1);
        ptr.setAutoReverse(false);

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
