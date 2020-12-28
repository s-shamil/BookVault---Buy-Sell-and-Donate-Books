/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author newbit
 */
public class BookForTable {
    private final SimpleStringProperty ISBN;
    private final SimpleStringProperty BOOK_NAME;
    private final SimpleStringProperty AUTHOR;
    private final SimpleStringProperty MARKET_PRICE;
    
    BookForTable(String i, String bn, String a, String mp){
        this.ISBN = new SimpleStringProperty(i);
        this.BOOK_NAME = new SimpleStringProperty(bn);
        this.AUTHOR = new SimpleStringProperty(a);
        this.MARKET_PRICE = new SimpleStringProperty(mp);
    }
    
    public String toString()
    {
        return ISBN + ", "  + BOOK_NAME + ", " + AUTHOR + ", "+ MARKET_PRICE;
    }
    public String getISBN(){
        return ISBN.get();
    }
    public String getBOOK_NAME(){
        return BOOK_NAME.get();
    }
    public String getAUTHOR(){
        return AUTHOR.get();
    }
    public String getMARKET_PRICE(){
        return MARKET_PRICE.get();
    }
    
    public void setISBN(String i){
        ISBN.set(i);
    }
    public void setBOOK_NAME(String bn){
        BOOK_NAME.set(bn);
    }
    public void setAUTHOR(String a){
        AUTHOR.set(a);
    }
    public void setMARKET_PRICE(String mp){
        MARKET_PRICE.set(mp);
    }
}
