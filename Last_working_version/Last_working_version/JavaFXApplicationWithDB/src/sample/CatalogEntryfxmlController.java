/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import DB.OracleDBMS;
import DB.Users;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author newbit
 */
public class CatalogEntryfxmlController {

    /**
     * Initializes the controller class.
     */
    private Main main;
    
    @FXML
    private TextField isbn;
    
    @FXML
    private TextField pub_id;
    
    @FXML
    private TextField book_name;
    
    @FXML
    private TextField author_code;
    
    @FXML
    private TextField genre_code;
    
    @FXML
    private TextField p_min;
    
    @FXML
    private TextField p_max;
    
    @FXML
    private TextField rating;
    
    @FXML
    private Button butt;
    @FXML
    void showAction(ActionEvent event) throws IOException
    {
        main.showBook();
    }
    
    @FXML
    void buttAction(ActionEvent event)
    {
           
            System.out.println("dhukse");
            String isbns = isbn.getText();
            String p_id = pub_id.getText();
            String b_name = book_name.getText();
            String au_code = author_code.getText();
            String gn_code = genre_code.getText();
            String pmin = p_min.getText();
            String pmax = p_max.getText();
            String r = rating.getText();
            System.out.print(isbns + "\n" + p_id + "\n" + b_name + "\n" + au_code + "\n" + gn_code + "\n" + pmin + "\n" + pmax + r );
            
            try ( //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin", "123");
                Connection con = new OracleDBMS().getConnection()) {
                String sql = "INSERT INTO BOOK_CATALOG VALUES (?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, isbns);
                pst.setString(2, null);
                pst.setString(3, b_name);
                pst.setString(4, pmin);
                pst.setString(5, pmax);
                pst.setString(6, r);
                
                pst.executeUpdate();
                //pst.executeU();
                pst.close();
                con.close();
                
                
                //set text fields null
                isbn.setText(null);
                pub_id.setText(null);
                book_name.setText(null);
                author_code.setText(null);
                genre_code.setText(null);
                p_min.setText(null);
                p_max.setText(null);
                rating.setText(null);
                
            }
            catch (SQLException e)
            {
                System.out.println("Connection Failed! Check it from console");
                e.printStackTrace();
            }
    }
    
    void setMain(Main main) {
        this.main = main;
    }
}
