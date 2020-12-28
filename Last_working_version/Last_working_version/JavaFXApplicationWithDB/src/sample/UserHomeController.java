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

public class UserHomeController
{

    private Main main;

    @FXML
    private Button wish;
    @FXML
    private Button donate;
    @FXML
    private Button myWishes;
    @FXML
    private Button noti;
    @FXML
    private Button reqDelivery;
    @FXML
    private Button history;
    @FXML
    private Button logout;
    
    @FXML
    void wishButtAction(ActionEvent event)
    {
        System.out.println("Wish Clicked");
        try {
            main.showWishABook();
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void donateButtAction(ActionEvent event)
    {
        System.out.println("Donate Clicked");
    }
    @FXML
    void myWishesButtAction(ActionEvent event)
    {
        System.out.println("Wishlist Clicked");
        try {
            main.showMyWishlist();
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void notiButtAction(ActionEvent event)
    {
        System.out.println("notifications Clicked");
    }
    @FXML
    void reqDeliveryButtAction(ActionEvent event)
    {
        System.out.println("Req Del Clicked");
        //System.out.println("amar user_id : "  +  main.LoggedInUserID);
    }
    @FXML
    void historyButtAction(ActionEvent event)
    {
        System.out.println("History Clicked");
    }
    @FXML
    void logoutButtAction(ActionEvent event)
    {
        System.out.println("Logout Clicked");
        try {
            main.showLoginPage();
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    void setMain(Main main) {
        this.main = main;
    }
}
