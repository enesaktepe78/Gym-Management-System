package sample.Staff;

import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.Charts;
import sample.DataManage;
import sample.LoginHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Staff implements Initializable {

    PreparedStatement pst = null;
    Connection conn;

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox gender;
    @FXML
    private ComboBox trainer;
    @FXML
    private Label number;
    @FXML
    private PieChart chart;

    StaffData staffData=new StaffData();
    Controller controller=new Controller();

    @FXML   private TableView<StaffTable> tableview;
    @FXML   private TableColumn<StaffTable, String> tid;
    @FXML   private TableColumn<StaffTable, String> tname;
    @FXML   private TableColumn<StaffTable, String> tsurname;
    @FXML   private TableColumn<StaffTable, String> tgender;
    @FXML   private TableColumn<StaffTable, String> temail;
    @FXML   private TableColumn<StaffTable, String> tphone;
    @FXML   private TableColumn<StaffTable, String> tusername;
    @FXML   private TableColumn<StaffTable, String> tpassword;
    @FXML   private TableColumn<StaffTable, String> tduration;
    @FXML   private TableColumn<StaffTable, String> tstartdate;
    @FXML   private TableColumn<StaffTable, String> tfinishdate;
    @FXML   private TableColumn<StaffTable, String> ttrainer;

    ObservableList<StaffTable> list;

    public void Table() throws SQLException, ClassNotFoundException {
        list= FXCollections.observableArrayList();
        conn = DataManage.dbConnect();

        String query = "SELECT * FROM member LEFT JOIN membership ON member.memberid=membership.memberid ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
           list.add(new StaffTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(16),rs.getString(15),rs.getString(14),""));
        }
        tid.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tid"));
        tname.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tname"));
        tsurname.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tsurname"));
        tgender.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tgender"));
        temail.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("temail"));
        tphone.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tphone"));
        tusername.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tusername"));
        tpassword.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tpassword"));
        tduration.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tduration"));
        tstartdate.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tstartdate"));
        tfinishdate.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("tfinishdate"));
        ttrainer.setCellValueFactory(new PropertyValueFactory<StaffTable, String>("ttrainer"));
        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gender.getItems().add("Male");
        gender.getItems().add("Famale");

        try {
            Table();
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            number.setText(String.valueOf(staffData.numberofMember()));
            Charts.pieChart(chart,staffData.numberofMale(),staffData.numberofMember()-staffData.numberofMale());
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
         conn = DataManage.dbConnect();

        if(staffData.isExist(id.getText(),username.getText())){
            AlertMaker.AlertError("You can not add same id or username");
            return;
        }

        if (!id.getText().isEmpty()&&!name.getText().isEmpty()&&!surname.getText().isEmpty()&&!email.getText().isEmpty()&&!username.getText().isEmpty()&&!password.getText().isEmpty()) {
            String query = "INSERT INTO member" + "(memberid, name, surname, gender,phone,email,username,password,trainerid,staffid,adminid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, id.getText());
            pst.setString(2, name.getText());
            pst.setString(3, surname.getText());
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
        Connection conn = DataManage.dbConnect();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM member  WHERE memberid='" +id +"'";
        int result = stmt.executeUpdate(query);
        if(result==1) {
            AlertMaker.AlertInformation("Deleted");
        }

    }

    public void update(ActionEvent actionEvent) throws IOException {
        LoginHelper.id= String.valueOf(tableview.getSelectionModel().getSelectedItem().getTid());
        controller.openNewTab("/sample/Staff/updatemember.fxml");

    }

    public void paymentoperations(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"/sample/Staff/PaymentOperations.fxml");

    }

    public void products(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"/sample/Staff/product.fxml");
    }

}
