package sample.Admin.StaffOperations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Admin.AdminData;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminStaff implements Initializable {
    Connection conn;
    PreparedStatement pst=null;

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField id;
    @FXML
    private TextField phone;


    @FXML   private TableView<StaffTable> tableview;
    @FXML   private TableColumn<StaffTable,String> tname;
    @FXML   private TableColumn<StaffTable,String> tsurname;
    @FXML   private TableColumn<StaffTable,String> tid;
    @FXML   private TableColumn<StaffTable,String> tphone;
    @FXML   private TableColumn<StaffTable,String> temail;
    @FXML   private TableColumn<StaffTable,String> tusername;
    @FXML   private TableColumn<StaffTable,String> tpassword;

    ObservableList<StaffTable> list;

    AdminData adminData=new AdminData();

    Controller controller=new Controller();

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();

        String query = "SELECT  * FROM staff ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new StaffTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
        }

        tname.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tname"));
        tsurname.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tsurname"));
        tid.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tid"));
        tphone.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tphone"));
        temail.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("temail"));
        tusername.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tusername"));
        tpassword.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tpassword"));
        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Table();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void add(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn= DataManage.dbConnect();

        if(adminData.isStaffExist(id.getText(),username.getText())){
            AlertMaker.AlertError("You can not add same id or username");
            return;
        }

        if(!name.getText().isEmpty()&&!lastname.getText().isEmpty()&&!username.getText().isEmpty()&&!password.getText().isEmpty()){
            String query="INSERT INTO staff(id,name,surname,username,password,phone,email)"+ "VALUES(?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(query);
            pst.setString(1,id.getText());
            pst.setString(2,name.getText());
            pst.setString(3,lastname.getText());
            pst.setString(4,username.getText());
            pst.setString(5,password.getText());
            pst.setString(6,phone.getText());
            pst.setString(7,email.getText());
            pst.executeUpdate();

            AlertMaker.AlertInformation("Added");

            DataManage.dbDisconnect(conn);

        }else {
            AlertMaker.AlertError("Please fill in required places");
        }
    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        conn=DataManage.dbConnect();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM staff  WHERE id='" + id+"'";
        int result = stmt.executeUpdate(query);
        if(result==1){
            AlertMaker.AlertError("Deleted");
        }
    }

    public void update(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        LoginHelper.id= String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        controller.openNewTab("/sample/Admin/StaffOperations/updatestaff.fxml");

    }

    public void back(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"admin.fxml");
    }


}
