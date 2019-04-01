package sample.Admin.TrainerOperations;

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

public class UpdateTrainer implements Initializable{
    Connection conn;
    PreparedStatement pst=null;
    ResultSet rs=null;

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

    public void update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        conn = DataManage.dbConnect();
        String query = "UPDATE trainer SET username='" + username.getText() + "', password='" + password.getText()  + "', email='" + email.getText() + "', phone='"  +phone.getText() + "' WHERE id='" + LoginHelper.id+ "'";
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
            Connection conn = DataManage.dbConnect();
            String query = "SELECT * FROM trainer WHERE id='" + LoginHelper.id + "'";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
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
}
