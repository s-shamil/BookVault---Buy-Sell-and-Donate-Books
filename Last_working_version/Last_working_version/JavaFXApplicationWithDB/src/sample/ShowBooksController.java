package sample;

import DB.Booktable;
import DB.Users;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rayhan
 */
public class ShowBooksController {
    private Main main;
    @FXML
    private TableView tableView;

    ObservableList<Book> data;

    @FXML
    private Button button;

    private boolean init = true;

    private void initializeColumns()
    {
        TableColumn<Book, String> bookNameCol = new TableColumn<>("BOOK_NAME");
        bookNameCol.setMinWidth(200);
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("BOOK_NAME"));
        //firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
//        firstNameCol.setOnEditCommit(
//                (TableColumn.CellEditEvent<User, String> t) ->
//                t.getTableView().getItems().get(t.getTablePosition().getRow()).setUserName(t.getNewValue())
//        );

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setMinWidth(100);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        //lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*lastNameCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastName(t.getNewValue())
         );*/

        TableColumn<Book, String> authColumn = new TableColumn<>("PUB_ID");
        authColumn.setMinWidth(200);
        authColumn.setCellValueFactory(new PropertyValueFactory<>("PUB_ID"));
        //emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*emailCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue())
         );*/
        TableColumn<Book, String> genreColumn = new TableColumn<>("RATING");
        genreColumn.setMinWidth(200);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("RATING"));
        tableView.getColumns().addAll(bookNameCol,isbnColumn, authColumn, genreColumn);
    }

    public void load()
    {
        if (init)
        {
            initializeColumns();
            init = false;
        }

        data = FXCollections.observableArrayList();

        List<List<String>> userDataList = new Booktable().getAllBooks();
        for (List<String> row : userDataList)
        {
            data.add(new Book(row.get(0), row.get(1), row.get(2),row.get(3)));
            System.out.println("HERE"+ row.get(0));
            System.out.println("HERE"+ row.get(1));
            System.out.println("HERE"+ row.get(2));
            System.out.println("HERE"+ row.get(3));
        }
        //tableView.

//        data = FXCollections.observableArrayList(
//                new User("Jacob", "2312", "Jacob Smith"),
//                new User("Isabella", "546t5", "Isabella Johnson"),
//                new User("Ethan", "fg565", "Ethan Williams"),
//                new User("Emma", "56564", "Emma Jones"),
//                new User("Michael", "gh5456", "Michael Brown")
//        );
        tableView.setEditable(true);
        tableView.setItems(data);
        //data.get(0).setFirstName("Jacob2");

    }

    @FXML
    void buttonAction(ActionEvent event)
    {
        try
        {
            //main.showTable;
            main.showCatPage();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    void setMain(Main main)
    {
        this.main = main;
    }

    public void refreshTable()
    {
        for (int i = 0; i < tableView.getColumns().size(); i++)
        {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }
}
