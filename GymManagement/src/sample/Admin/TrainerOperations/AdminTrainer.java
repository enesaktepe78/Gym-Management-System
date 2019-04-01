package sample.Admin.TrainerOperations;

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
import sample.Admin.StaffOperations.StaffTable;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminTrainer implements Initializable{
    Connection conn;
    PreparedStatement pst=null;
    @FXML
    private TextField id1;
    @FXML
    private TextField name1;
    @FXML
    private TextField phone1;
    @FXML
    private TextField email1;
    @FXML
    private TextField lastname1;
    @FXML
    private TextField username1;
    @FXML
    private TextField password1;

    @FXML   private TableView<StaffTable> tableview;
    @FXML   private TableColumn<StaffTable,String> tname;
    @FXML   private TableColumn<StaffTable,String> tsurname;
    @FXML   private TableColumn<StaffTable,String> tid;
    @FXML   private TableColumn<StaffTable,String> tphone;
    @FXML   private TableColumn<StaffTable,String> temail;
    @FXML   private TableColumn<StaffTable,String> tusername;
    @FXML   private TableColumn<StaffTable,String> tpassword;

    ObservableList<StaffTable> list;

    Controller controller=new Controller();

    AdminData adminData=new AdminData();

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();

        String query = "SELECT  * FROM trainer ";
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

    public void add(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn= DataManage.dbConnect();

        if(adminData.isTrainerExist(id1.getText(),username1.getText())){
            AlertMaker.AlertError("You can not add same id or username");
            return;
        }

        if(!username1.getText().isEmpty()){
            String query="INSERT INTO trainer(id,name,surname,username,password,phone,email,adminid)"+ "VALUES(?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(query);
            pst.setString(1,id1.getText());
            pst.setString(2,name1.getText());
            pst.setString(3,lastname1.getText());
            pst.setString(4,username1.getText());
            pst.setString(5,password1.getText());
            pst.setString(6,phone1.getText());
            pst.setString(7,email1.getText());
            pst.setString(8,"");
            pst.executeUpdate();

            AlertMaker.AlertInformation("Added");

        }else {
            AlertMaker.AlertError("Please fill in required places");
        }
        DataManage.dbDisconnect(conn);

    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        conn=DataManage.dbConnect();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM trainer  WHERE id='" + id+"'";
        int result = stmt.executeUpdate(query);
        if(result==1){
            AlertMaker.AlertError("Deleted");
        }
    }

    public void update(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        LoginHelper.id= String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        controller.openNewTab("/sample/Admin/TrainerOperations/updatetrainer.fxml");

    }

    public void back(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"admin.fxml");
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
}
