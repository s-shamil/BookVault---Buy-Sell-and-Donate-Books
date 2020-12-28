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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rayhan
 */
public class Booktable {

    public List<List<String>> getAllBooks() {
        String sql = "SELECT BOOK_NAME,ISBN,PUB_ID,RATING FROM BOOK_CATALOG";
        List<List<String>> resultList = new ArrayList<>();
        try {
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("BOOK_NAME"));
                row.add(rs.getString("ISBN"));
                row.add(rs.getString("PUB_ID"));
                row.add(rs.getString("RATING"));
                resultList.add(row);
            }
            pst.close();
            con.close();
        } catch (Exception e) {

        }
        return resultList;
    }
}
