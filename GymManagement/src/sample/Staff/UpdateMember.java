package sample.Staff;

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

public class UpdateMember implements Initializable{
    Connection conn;
    PreparedStatement prs=null;
    ResultSet rs=null;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        conn = DataManage.dbConnect();
        String query = "UPDATE member SET username='" + username.getText() + "', password='" + password.getText()  + "', email='" + email.getText() + "', phone='"  +phone.getText() + "' WHERE memberid='" + LoginHelper.id+ "'";
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        if (result == 1) {
            AlertMaker.AlertInformation("Updated");
        }
        stmt.close();
        DataManage.dbDisconnect(conn);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conn=DataManage.dbConnect();
            String query = "SELECT * FROM member WHERE memberid='" +LoginHelper.id + "'";
            prs = conn.prepareStatement(query);
            rs = prs.executeQuery(query);
            while (rs.next()){
                name.setText(rs.getString(2));
                surname.setText(rs.getString(3));
                email.setText(rs.getString(6));
                phone.setText(rs.getString(5));
                username.setText(rs.getString(7));
                password.setText(rs.getString(8));

            }

        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
