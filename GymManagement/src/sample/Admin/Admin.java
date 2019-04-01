package sample.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Admin {

    public Controller controller=new Controller();

    public void staff(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("StaffOperations/adminstaff.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void trainer(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("TrainerOperations/admintrainer.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void member(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("MemberOperations/adminmember.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

}
