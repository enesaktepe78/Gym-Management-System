package sample.Staff;


import sample.DataManage;
import java.sql.*;


public class ProductData {
    Connection conn;
    PreparedStatement prs=null;
    ResultSet rs=null;
    private int stock;


    public boolean isProductExist(String id) throws SQLException, ClassNotFoundException {
        conn= DataManage.dbConnect();
        String query = "SELECT * FROM productselling";
        prs = conn.prepareStatement(query);
        rs = prs.executeQuery(query);
        while (rs.next()){
            if(rs.getString(1).equals(id))
                return true;
        }
        return false;
    }

    public boolean isProductEnough(int amount,String id) throws SQLException {
        String query2 = "SELECT stock FROM productselling WHERE productid='" +id+"'";
        PreparedStatement prs2 = conn.prepareStatement(query2);
        ResultSet rs2 = prs2.executeQuery(query2);
        while (rs2.next()){
            stock= Integer.parseInt(rs2.getString(1));
        }

        if(stock<amount)
        {
            return true;
        }
        return false;

    }



}
