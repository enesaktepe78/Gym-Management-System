package sample.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberLogin {
    Connection conn;
    PreparedStatement pst=null;
    ResultSet resultSet=null;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    Controller controller=new Controller();

    public void loginButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String Username=username.getText();
        String Password=password.getText();
        conn = DataManage.dbConnect();
        String query = "SELECT * FROM member WHERE username=? AND password=?";
        pst = conn.prepareStatement(query);
        pst.setString(1, Username);
        pst.setString(2, Password);
        resultSet = pst.executeQuery();

        if (resultSet.next()) {
            LoginHelper.username=resultSet.getString(1);
            controller.buton(actionEvent,"/sample/Member/member.fxml");
        } else {
            AlertMaker.AlertError("Username or password wrong");
        }

        pst.close();
        resultSet.close();
        DataManage.dbDisconnect(conn);
    }
}

