/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author newbit
 */
public class SearchBookQuery {
    private static SearchBookQuery instance;
    
    //gets isbn, book name, author name, price list
    public List<List<String>> getSearchResult(String query)
    {
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("ISBN"));
                row.add(rs.getString("BOOK_NAME"));
                row.add(rs.getString("AUTHOR_NAME"));
                row.add(rs.getString("PRICE_E"));
                //row.add(rs.getString("FULLNAME"));
                resultList.add(row);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        return resultList;
    }
    
    public List<List<String>> getMyWishes(String userid)
    {
        List<List<String>> resultList = new ArrayList<>();
        try{
            String query = "SELECT ISBN,BOOK_NAME,AUTHOR_NAME,PRICE_E FROM D_WISHLIST JOIN BOOK_CATALOG USING(ISBN) JOIN AUTHOR_BOOK USING (ISBN) JOIN AUTHOR USING(AUTHOR_ID) WHERE USER_ID='" + userid + "'";
            System.out.println(query);
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("ISBN"));
                row.add(rs.getString("BOOK_NAME"));
                row.add(rs.getString("AUTHOR_NAME"));
                row.add(rs.getString("PRICE_E"));
                //row.add(rs.getString("FULLNAME"));
                resultList.add(row);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        return resultList;
    }
    
    //inserts into wishlist
    public void insertToWishlist(String ISBN, String USER_ID){
        try {
            String query = "DECLARE BEGIN ADD_TO_WISH( '" + ISBN + "', '" + USER_ID + "'); END;";
            System.out.println(query);
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SearchBookQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeFromWishlist(String ISBN, String USER_ID){
        try {
            //String query = "DECLARE BEGIN ADD_TO_WISH( '" + ISBN + "', '" + USER_ID + "'); END;";
            String query = "DELETE FROM D_WISHLIST WHERE USER_ID='" + USER_ID + "' AND ISBN='" + ISBN + "'";
            System.out.println(query);
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SearchBookQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addUser(String query){
        try {
            //String query = "DECLARE BEGIN ADD_TO_WISH( '" + ISBN + "', '" + USER_ID + "'); END;";
            query = "DECLARE BEGIN ADD_USER(" + query + "); END;";
            System.out.println(query);
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SearchBookQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
