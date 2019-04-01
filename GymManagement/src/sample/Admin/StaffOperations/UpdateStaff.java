package sample.Admin.StaffOperations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UpdateStaff implements Initializable{
    Connection conn;
    ResultSet rs=null;
    PreparedStatement pst=null;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conn=DataManage.dbConnect();
            String query = "SELECT * FROM staff WHERE id='" +LoginHelper.id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()){
                name.setText(rs.getString(2));
                surname.setText(rs.getString(3));
                email.setText(rs.getString(7));
                phone.setText(rs.getString(6));
                username.setText(rs.getString(4));
                password.setText(rs.getString(5));

            }

        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        String query = "UPDATE staff SET username='" + username.getText() + "', password='" + password.getText()  + "', email='" + email.getText() + "', phone='"  +phone.getText() + "' WHERE id='" + LoginHelper.id+ "'";
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        if (result == 1) {
            AlertMaker.AlertInformation("Updated");
        }
        stmt.close();
        DataManage.dbDisconnect(conn);
    }
}
