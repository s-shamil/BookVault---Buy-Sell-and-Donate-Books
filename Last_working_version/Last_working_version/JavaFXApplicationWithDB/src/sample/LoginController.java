package sample;

import DB.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LoginController
{

    private Main main;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField Password;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;
    
    @FXML
    private Label errorMessage;

    @FXML
    void loginAction(ActionEvent event)
    {
        //String validUserName = "admin";
        //String validPassword = "123";
        String userName = userId.getText();
        String password = Password.getText();
        System.out.println(userName + password);
        boolean success = new Users().validateLogin(userName, password);
        //boolean success = true;
        if (success)
        {
            main.LoggedInUserID = userName;
            // successful login
            
            try
            {
                //main.showTable();
                main.showUserHome();
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        } else
        {
            // failed login
            /*
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password you provided is not correct.");
            alert.showAndWait();
               */
            errorMessage.setTextFill(Color.web("#B22222"));
            errorMessage.setText("Invalid username/password! Please try again");
            userId.setText(null);
            Password.setText(null);
        }

    }
    @FXML
    void signupAction(ActionEvent event){
        try {
            main.showSignupPage();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userId.setText(null);
        Password.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }

}
