/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Rayhan
 */
public class Book {
    private final SimpleStringProperty BOOK_NAME;
    private final SimpleStringProperty ISBN;
    //private final SimpleStringProperty pub_id;
    private final SimpleStringProperty PUB_ID;
    private final SimpleStringProperty RATING;
    //private final SimpleStringProperty writer;

    Book(String fnm, String isbn, String PUB_ID, String rating) {
        this.BOOK_NAME = new SimpleStringProperty(fnm);
        this.ISBN = new SimpleStringProperty(isbn);
        this.PUB_ID = new SimpleStringProperty(PUB_ID);
        this.RATING = new SimpleStringProperty(rating);
    }
    
    public String toString()
    {
        return BOOK_NAME+ ", " + ISBN +", " + PUB_ID + ", " + RATING;
    }

    public String getBOOK_NAME() {
        return BOOK_NAME.get();
    }
    public void setBOOK_NAME(String uName)
    {
        BOOK_NAME.set(uName);
    }
    public String getISBN() {
        return ISBN.get();
    }
    public void setISBN(String uName)
    {
        ISBN.set(uName);
    }

    public String getPUB_ID() {
        return PUB_ID.get();
    }
    public void setPUB_ID(String uName)
    {
        PUB_ID.set(uName);
    }
    public String getRATING() {
        return RATING.get();
    }
    public void setRATING(String uName)
    {
        RATING.set(uName);
    }
    
}
