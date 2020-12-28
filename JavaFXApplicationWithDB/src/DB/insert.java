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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nayeem
 */
public class insert
{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)
    {
        // TODO code application logic here

        try
        {
            try ( //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "admin", "123");
                Connection con = new OracleDBMS().getConnection()) {
                String sql = "INSERT INTO USERSTABLE VALUES (99912,1233,'KKKFHF')";
                PreparedStatement pst = con.prepareStatement(sql);
                //pst.setString(1, "1505006");
                //pst.setString(2, "123");
                //pst.setString(3, " 'Abcd' ");
                pst.executeUpdate();
                //pst.executeU();
                pst.close();
                con.close();
            }
        }catch (SQLException e)
        {
            System.out.println("Connection Failed! Check it from console");
            e.printStackTrace();
        }
     
    }

}

