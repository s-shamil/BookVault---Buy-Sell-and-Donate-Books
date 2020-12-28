package sample;

import DB.SearchBookQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MyWishlistController
{

    private Main main;

    @FXML
    private Button remove;
    @FXML
    private TextField isbn;
    @FXML
    private Label removeReport;
    @FXML
    private Button home;
    
    private boolean init = true;
    
    @FXML
    private TableView showWishes;
    @FXML
    private TableColumn<BookForTable, String> isbnCol;
    @FXML
    private TableColumn<BookForTable, String> bookNameCol;
    @FXML
    private TableColumn<BookForTable, String> authorCol;
    @FXML
    private TableColumn<BookForTable, String> marketPriceCol;
    
    private void initializeColumns()
    {
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOK_NAME"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("AUTHOR"));
        marketPriceCol.setCellValueFactory(new PropertyValueFactory<>("MARKET_PRICE"));
        
        //showFound.getColumns().addAll(isbnCol, bookNameCol, authorCol, marketPriceCol);
    }

    
    ObservableList<BookForTable> BooksFound;
    
    public void loadMyWishes()
    {
        if(init)
        {
            initializeColumns();
            init = false;
        }
        
        BooksFound = FXCollections.observableArrayList();
        
        List<List<String>> bookDataList = new SearchBookQuery().getMyWishes(main.LoggedInUserID);
        for(List<String> row : bookDataList)
        {
            BooksFound.add(new BookForTable(row.get(0), row.get(1), row.get(2), row.get(3)));
        }
        showWishes.setEditable(true);
        showWishes.setItems(BooksFound);
    }
    
    
    @FXML
    void removeButtAction(ActionEvent event){
        String ISBN = isbn.getText();
        System.out.println("remove this: " + ISBN);
        BookForTable bft =  (BookForTable) showWishes.getSelectionModel().selectedItemProperty().get();
        if(bft==null){
        } else {
            //wishReport.setText("✓ \"" + bft.getBOOK_NAME() + "\" : Added to wishlist!");
            //wishReport.setTextFill(Color.web("green"));
            removeReport.setText("✓ \"" + bft.getBOOK_NAME() + "\" : Removed from wishlist!");
            removeReport.setTextFill(Color.web("red"));
            new SearchBookQuery().removeFromWishlist(ISBN, main.LoggedInUserID);
            this.loadMyWishes();
        }
        //new SearchBookQuery().insertToWishlist(ISBN, main.LoggedInUserID);
    }
    @FXML
    void homeButtAction(ActionEvent event){
        System.out.println("Home button clicked by " + main.LoggedInUserID);
        try {
            main.showUserHome();
        } catch (Exception ex) {
            Logger.getLogger(WishABookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    showFound.setRowFactory(new Callback<TableView<BookForTable>, TableRow<BookForTable>>(){
//        TableRow<BookForTable> row = new TableRow<>();
//        row.setOnMouseClicked(event -> {
//            if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
//                && event.getClickCount() == 1) {
//
//                BookForTable clickedRow = row.getItem();
//                //printRow(clickedRow);
//                System.out.println(clickedRow.getISBN());
//            }
//        });
//        return row ;
//    });
    @FXML
    void putISBN(MouseEvent event){
        BookForTable bft =  (BookForTable) showWishes.getSelectionModel().selectedItemProperty().get();
        if(bft==null){
        } else {
            System.out.println(bft.getBOOK_NAME());
            isbn.setText(bft.getISBN());
        }
    }
    
    
    void setMain(Main main) {
        this.main = main;
    }
}
