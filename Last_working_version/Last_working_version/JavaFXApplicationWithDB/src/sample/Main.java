package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import javafx.stage.Modality;

public class Main extends Application {

    Stage stage;
    String LoggedInUserID;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
        //showCatPage();
    }
    public void showCatPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("catalogEntry.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CatalogEntryfxmlController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("New Entry for Book Catalog");
        

        stage.setScene(new Scene(root, 600, 650));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }
    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("The Book Vault - Login");
        stage.setScene(new Scene(root, 600, 650));
        stage.show();
    }
    public void showSignupPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SIgnUp.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SIgnUpController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("The Book Vault - Sign Up");
        stage.setScene(new Scene(root, 600, 650));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }
    public void showUserHome() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        UserHomeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        

        stage.setScene(new Scene(root, 600, 650));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }
    
    public void showWishABook() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WishABook.fxml"));
        Parent root = loader.load();

        // Loading the controller
        WishABookController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Wish A Book");
        

        stage.setScene(new Scene(root, 800, 650));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }
    
    public void showMyWishlist() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyWishlist.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MyWishlistController controller = loader.getController();
        controller.setMain(this);
        controller.loadMyWishes();

        // Set the primary stage
        stage.setTitle("My Wishlist");
        

        stage.setScene(new Scene(root, 800, 650));
        stage.show();
        stage.resizableProperty().setValue(Boolean.FALSE);
    }
    
    
    //rayhan
    public void showBook() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowBooks.fxml"));
        Parent root = loader.load();
        
        // Loading the controller
        ShowBooksController controller = loader.getController();
        controller.load();
        controller.setMain(this);
        
        // Set the primary stage
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void showDonate() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Donate.fxml"));
        Parent root=loader.load();
        DonateController controller=loader.getController();
        //controller.load();
        controller.setMain(this);
        stage.setTitle("Donate A BOOK");
        stage.setScene(new Scene(root ));
        stage.show();
        
    }
        
    

    public static void main(String[] args) {
        //System.out.println("hello \"world\"");
        launch(args);
    }
}
