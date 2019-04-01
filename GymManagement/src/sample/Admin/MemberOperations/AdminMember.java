package sample.Admin.MemberOperations;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;
import sample.Staff.StaffData;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminMember implements Initializable {
    Connection conn;
    PreparedStatement pst=null;

    StaffData staffData=new StaffData();

    Controller controller=new Controller();
    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private ComboBox gender;
    @FXML
    private TextField id;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML   private TableView<MemberTable> tableview;
    @FXML   private TableColumn<MemberTable, String> tid;
    @FXML   private TableColumn<MemberTable, String> tname;
    @FXML   private TableColumn<MemberTable, String> tsurname;
    @FXML   private TableColumn<MemberTable, String> temail;
    @FXML   private TableColumn<MemberTable, String> tphone;
    @FXML   private TableColumn<MemberTable, String> tusername;
    @FXML   private TableColumn<MemberTable, String> tpassword;

    ObservableList<MemberTable> list;

    public void Table() throws SQLException, ClassNotFoundException {
        list= FXCollections.observableArrayList();
        conn = (Connection) DataManage.dbConnect();

        String query = "SELECT * FROM member ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            list.add(new MemberTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getString(5),rs.getString(7),rs.getString(8)));
        }
        tid.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tid"));
        tname.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tname"));
        tsurname.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tsurname"));
        temail.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("temail"));
        tphone.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tphone"));
        tusername.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tusername"));
        tpassword.setCellValueFactory(new PropertyValueFactory<MemberTable, String>("tpassword"));
        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }

    public void add(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn = (Connection) DataManage.dbConnect();

        if(staffData.isExist(id.getText(),username.getText())){
            AlertMaker.AlertError("You can not add same id or username");
            return;
        }

        if (!id.getText().isEmpty()&&!name.getText().isEmpty()&&!lastname.getText().isEmpty()&&!email.getText().isEmpty()&&!username.getText().isEmpty()&&!password.getText().isEmpty()) {

            String query = "INSERT INTO member" + "(memberid, name, surname, gender,phone,email,username,password,trainerid,staffid,adminid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, id.getText());
            pst.setString(2, name.getText());
            pst.setString(3, lastname.getText());
            pst.setString(4, (String) gender.getValue());
            pst.setString(5, phone.getText());
            pst.setString(6, email.getText());
            pst.setString(7, username.getText());
            pst.setString(8, password.getText());
            pst.setString(9, "");
            pst.setString(10, "");
            pst.setString(11, "");
            pst.executeUpdate();

            AlertMaker.AlertInformation("Added");
            DataManage.dbDisconnect(conn);

        } else {
            AlertMaker.AlertError("Please fill required places");
        }
    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        java.sql.Connection conn = DataManage.dbConnect();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM member  WHERE memberid='" + id +"'";
        int result = stmt.executeUpdate(query);
        if(result==1)
            AlertMaker.AlertInformation("Deleted");
    }

    public void update(ActionEvent actionEvent) throws IOException {
        LoginHelper.id=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        controller.openNewTab("/sample/Admin/MemberOperations/updatemember.fxml");
    }

    public void back(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"admin.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().add("Male");
        gender.getItems().add("Famale");
        try {
            Table();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
