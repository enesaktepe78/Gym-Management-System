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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLogin {

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    Controller controller=new Controller();

    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {

        if (username.getText().equals("enesaktepe")&&password.getText().equals("12345")) {
            controller.buton(actionEvent,"/sample/Admin/admin.fxml");
        } else {
            AlertMaker.AlertError("Username or password wrong");
        }
    }


}


