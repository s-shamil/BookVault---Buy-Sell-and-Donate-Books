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

public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        //showLoginPage();
        showBook();
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
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }
    
    public void showTable() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();
        
        // Loading the controller
        TableViewController controller = loader.getController();
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
    
    
        
    

    public static void main(String[] args) {
        launch(args);
    }
}
