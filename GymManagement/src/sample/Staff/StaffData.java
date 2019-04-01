package sample.Staff;

import sample.DataManage;

import java.sql.*;

public class StaffData {

    Connection conn;
    PreparedStatement pst=null;
    ResultSet resultSet = null;
    Statement s=null;

    public boolean isExist(String id,String username) throws SQLException, ClassNotFoundException {

        conn = DataManage.dbConnect();
        String query = "SELECT * FROM member";
        pst = conn.prepareStatement(query);
        resultSet = pst.executeQuery(query);
        while (resultSet.next()){
            if(resultSet.getString(1).equals(id)||resultSet.getString(7).equals(username))
               return true;
        }
        return false;
    }
    public boolean isPaymentExist(String memberid) throws SQLException, ClassNotFoundException {

        conn = DataManage.dbConnect();
        String query = "SELECT * FROM membership";
        pst = conn.prepareStatement(query);
        resultSet = pst.executeQuery(query);
        while (resultSet.next()){
            if(resultSet.getString(1).equals(memberid))
                return true;
        }
        return false;
    }
    public int numberofMember() throws SQLException, ClassNotFoundException {

        conn=DataManage.dbConnect();
        s=conn.createStatement();
        resultSet=s.executeQuery("SELECT COUNT(*) AS memberid FROM member");
        resultSet.next();
        int count=resultSet.getInt("memberid");
        resultSet.close();

        return count;
    }
    public int numberofMale() throws SQLException, ClassNotFoundException {

        conn=DataManage.dbConnect();
        s=conn.createStatement();
        resultSet=s.executeQuery("SELECT COUNT(*) AS memberid FROM member WHERE gender='" + "Male"+"'");
        resultSet.next();
        int count=resultSet.getInt("memberid");
        resultSet.close();

        return count;
    }

    public boolean addMember(){

        return false;
    }
}
