package sample;

import DB.SearchBookQuery;
import DB.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SIgnUpController
{

    private Main main;

    @FXML
    private TextField userId;

    @FXML
    private TextField fullName;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField email;

    @FXML
    private TextField city;
    
    @FXML
    private TextField zip;
    
    @FXML
    private Label msg;
    
    @FXML
    private Button submit;
    

    
    @FXML
    void signupAction(ActionEvent event) throws Exception{
        String u = userId.getText();
        String f = fullName.getText();
        String p = phoneNo.getText();
        String e = email.getText();
        String c = city.getText();
        String z = zip.getText();
        String ps = Password.getText();
        
        String q = "'" + u + "', '"  + f + "', '"  + p + "', '"  + e + "', '"+  c + "', '" +  z + "', '" +  ps + "'"; 
                //'lelmil', 'Lalman Lelmil', '0101001991', 'lel@bel.com', 'leka', '222', '1234'
        new SearchBookQuery().addUser(q);
        main.showLoginPage();
    }



    void setMain(Main main) {
        this.main = main;
    }

}
