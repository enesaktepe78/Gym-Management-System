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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.AlertMaker;
import sample.DataManage;
import sample.Staff.StaffData;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PaymentOperations implements Initializable{

    Connection conn;
    PreparedStatement pst=null;
    ResultSet rs=null;

    @FXML
    private ComboBox memberid;
    @FXML
    private TextField totalpayment;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker finishdate;
    @FXML
    private TextField duration;
    @FXML
    private TextField amount;
    @FXML
    private ComboBox memberid2;
    @FXML
    private DatePicker paymentdate;

    private int remain,total;

    @FXML   private TableView<PaymentOperationsTable> tableview;
    @FXML   private TableColumn<PaymentOperationsTable,String> tmemberid;
    @FXML   private TableColumn<PaymentOperationsTable,String> ttotalpayment;
    @FXML   private TableColumn<PaymentOperationsTable,String> tpaid;
    @FXML   private TableColumn<PaymentOperationsTable,String> tremain;
    @FXML   private TableColumn<PaymentOperationsTable,String> tpaydate;

    ObservableList<PaymentOperationsTable> list;

    StaffData staffData=new StaffData();
   

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();

        String query = "SELECT  * FROM membership LEFT  JOIN payment ON membership.memberid=payment.memberid";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new PaymentOperationsTable(rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(8)));
        }
        tmemberid.setCellValueFactory(new PropertyValueFactory<PaymentOperationsTable, String>("tmemberid"));
        ttotalpayment.setCellValueFactory(new PropertyValueFactory<PaymentOperationsTable, String>("ttotalpayment"));
        tpaid.setCellValueFactory(new PropertyValueFactory<PaymentOperationsTable, String>("tpaid"));
        tpaydate.setCellValueFactory(new PropertyValueFactory<PaymentOperationsTable, String>("tpaydate"));

        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Tablomuzu dolduruyoruz
        try {
            Table();
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        //comboboxları dolduruyoruz
        try {
            conn= DataManage.dbConnect();
            String query = "SELECT * FROM member";
            pst = (PreparedStatement) conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()){
                memberid.getItems().add(rs.getString(1));
            }
            String query2 = "SELECT * FROM membership";
            pst = (PreparedStatement) conn.prepareStatement(query2);
            rs = pst.executeQuery(query2);
            while (rs.next()){
                memberid2.getItems().add(rs.getString(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void submit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        conn = DataManage.dbConnect();

        startdate.setOnAction((ActionEvent event) ->{
            startdate.getValue(); });

        finishdate.setOnAction((ActionEvent event) ->{
            finishdate.getValue(); });

        if(staffData.isPaymentExist((String) memberid.getValue())){
            AlertMaker.AlertError("You can not add same id");
            return;
        }

        if (!duration.getText().isEmpty()) {
            String query = "INSERT INTO membership" + "(memberid,totalpayment,startdate,finishdate,duration) VALUES (?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(query);

            pst.setString(1, (String) memberid.getValue());
            pst.setString(2, totalpayment.getText());
            pst.setDate(3, java.sql.Date.valueOf(startdate.getValue()));
            pst.setDate(4,java.sql.Date.valueOf(finishdate.getValue()));
            pst.setString(5, duration.getText());
            pst.executeUpdate();

            AlertMaker.AlertInformation("Added");

            DataManage.dbDisconnect(conn);

        } else {
            AlertMaker.AlertError("Please fill required places");
        }
    }

    public void pay(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        paymentdate.setOnAction((ActionEvent event) ->{
            paymentdate.getValue(); });

         conn=DataManage.dbConnect();

         String query2="SELECT totalpayment FROM membership WHERE memberid='" + memberid2.getValue() +"'";
         pst= (PreparedStatement) conn.prepareStatement(query2);
         rs=pst.executeQuery(query2);
         while (rs.next()){
             remain=rs.getInt(1);
         }

         if(Integer.parseInt(amount.getText())>remain){
             AlertMaker.AlertError("You exceed totalpayment");
             return;
         }

        String query3="SELECT SUM(amount),memberid  FROM payment  GROUP  BY memberid HAVING memberid='" + memberid2.getValue() +"'";
        pst= (PreparedStatement) conn.prepareStatement(query3);
        rs=pst.executeQuery(query3);
        while (rs.next()){
            total=Integer.parseInt(rs.getString(1));
        }

        if((Integer.parseInt(amount.getText())+total)>remain){
            AlertMaker.AlertError("No remain payment");
            return;
        }

        if (!amount.getText().isEmpty()) {

            String query = "INSERT INTO payment" + "(memberid,amount,date,staffid,payid) VALUES (?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, (String) memberid2.getValue());
            pst.setString(2, amount.getText());
            pst.setDate(3,java.sql.Date.valueOf(paymentdate.getValue()) );
            pst.setString(4, "");
            pst.setString(5, "");
            pst.executeUpdate();

            AlertMaker.AlertInformation("Paid");

            DataManage.dbDisconnect(conn);

        } else {
            AlertMaker.AlertError("Please fill required places");
        }

    }

    public void back(ActionEvent actionEvent) throws IOException {

        Parent home_page_parent= FXMLLoader.load(getClass().getResource("staff.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void showDetails(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String name=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTmemberid());
        conn=DataManage.dbConnect();

        String query2="SELECT totalpayment FROM membership WHERE memberid='" + name +"'";
        pst= (PreparedStatement) conn.prepareStatement(query2);
        rs=pst.executeQuery(query2);
        while (rs.next()){
            remain=rs.getInt(1);
        }

        String query3="SELECT SUM(amount),memberid  FROM payment  GROUP  BY memberid HAVING memberid='" + name +"'";
        pst= (PreparedStatement) conn.prepareStatement(query3);
        rs=pst.executeQuery(query3);
        while (rs.next()){
            total=Integer.parseInt(rs.getString(1));
        }

        if(mouseEvent.getClickCount()==2){
            AlertMaker.AlertInformation("Remain Payment: " + String.valueOf(remain-total));
        }
    }
}
