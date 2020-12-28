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

public class WishABookController
{

    private Main main;

    @FXML
    private TextField searchText;
    @FXML
    private Button search;
    @FXML
    private Button wish;
    @FXML
    private TextField isbn;
    @FXML
    private Label wishReport;
    @FXML
    private Button home;
    
    private boolean init = true;
    
    @FXML
    private TableView showFound;
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
    
    public void loadSearchResult(String query)
    {
        if(init)
        {
            initializeColumns();
            init = false;
        }
        
        BooksFound = FXCollections.observableArrayList();
        
        List<List<String>> bookDataList = new SearchBookQuery().getSearchResult(query);
        for(List<String> row : bookDataList)
        {
            BooksFound.add(new BookForTable(row.get(0), row.get(1), row.get(2), row.get(3)));
        }
        showFound.setEditable(true);
        showFound.setItems(BooksFound);
    }
    
    @FXML
    void searchButtAction(ActionEvent event){
        String Searched = searchText.getText();
        System.out.println("Searched for: " + Searched);
        String[] keywords = Searched.split("\\s");
        
        for (int i = 0; i<keywords.length; i++) {
            keywords[i] = keywords[i].toUpperCase();
            System.out.println(keywords[i]);
        }
        
        String query = "SELECT ISBN, BC.BOOK_NAME, AU.AUTHOR_NAME, BC.PRICE_E FROM BOOK_CATALOG BC JOIN (AUTHOR_BOOK AB JOIN AUTHOR AU USING (AUTHOR_ID)) USING (ISBN) WHERE ";
        for (int i = 0; i<keywords.length; i++) {
            query += "(UPPER(BC.BOOK_NAME) LIKE '%";
            query += keywords[i];
            query += "%' ) ";
            if(i<(keywords.length-1)) {
                query += "AND";
            }
        }
        System.out.println(query);
        init = true;
        this.loadSearchResult(query);
        
    }
    @FXML
    void wishButtAction(ActionEvent event){
        String ISBN = isbn.getText();
        System.out.println("Wished for: " + ISBN);
        BookForTable bft =  (BookForTable) showFound.getSelectionModel().selectedItemProperty().get();
        if(bft==null){
        } else {
            wishReport.setText("✓ \"" + bft.getBOOK_NAME() + "\" : Added to wishlist!");
            wishReport.setTextFill(Color.web("green"));
            new SearchBookQuery().insertToWishlist(ISBN, main.LoggedInUserID);
        }
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
        BookForTable bft =  (BookForTable) showFound.getSelectionModel().selectedItemProperty().get();
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
