/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author Rayhan
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sample.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nayeem
 */
public class query
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException
    {
        // TODO code application logic here
        String sql = "SELECT * FROM USERSTABLE";

        try
        {
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            //pst.setString(1, "1505001");
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getString("USERNAME") + " , " + rs.getString(2) + " , " + rs.getString(3));
            }
            pst.close();
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
//        try
//        {
//            Connection con = new OracleDBUtil().getConnection();
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, "1505002");
//            ResultSet rs = pst.executeQuery();
//            while (rs.next())
//            {
//                System.out.println(rs.getString(1) + " , " + rs.getString(2) + " , " + rs.getString(3));
//            }
//            pst.close();
//            con.close();
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
    }

}
