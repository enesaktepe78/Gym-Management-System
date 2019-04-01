package sample.Admin;

import sample.DataManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminData {
    Connection conn;
    PreparedStatement psr=null;
    ResultSet rs=null;

    public boolean isStaffExist(String id,String username) throws SQLException, ClassNotFoundException {
        conn= DataManage.dbConnect();

        String query = "SELECT * FROM staff";
        psr = conn.prepareStatement(query);
        rs = psr.executeQuery(query);
        while (rs.next()){
            if(rs.getString(1).equals(id)||rs.getString(4).equals(username))
                return true;
        }
        DataManage.dbDisconnect(conn);
        return false;

    }
    public boolean isTrainerExist(String id,String username) throws SQLException, ClassNotFoundException {
        conn=DataManage.dbConnect();
        String query="SELECT * FROM trainer";
        psr=conn.prepareStatement(query);
        rs=psr.executeQuery();
        while (rs.next()){
            if(rs.getString(1).equals(id)||rs.getString(4).equals(username))
                return true;
        }
        return false;
    }
}
