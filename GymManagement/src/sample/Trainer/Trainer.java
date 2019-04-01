package sample.Trainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.DataManage;
import sample.Staff.ProductTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Trainer implements Initializable{

    Connection conn;
    PreparedStatement pst,pst2=null;
    Statement stmt=null;
    ResultSet rs=null;

    @FXML
    private ChoiceBox memberid;
    @FXML
    private TextField height;
    @FXML
    private TextField fat;
    @FXML
    private TextField age;
    @FXML
    private TextField weight;
    @FXML
    private TextField biceps;
    @FXML
    private TextField waistlane;
    @FXML
    private TextField leg;
    @FXML
    private TextField shoulder;
    @FXML
    private TextField chest;
    @FXML
    private DatePicker date;

    @FXML   private TableView<TrainerTable> tableview;
    @FXML   private TableColumn<TrainerTable,String> tmemberid;
    @FXML   private TableColumn<TrainerTable,String> theight;
    @FXML   private TableColumn<TrainerTable,String> tfat;
    @FXML   private TableColumn<TrainerTable,String> tweight;
    @FXML   private TableColumn<TrainerTable,String> tbiceps;
    @FXML   private TableColumn<TrainerTable,String> twaistlane;
    @FXML   private TableColumn<TrainerTable,String> tleg;
    @FXML   private TableColumn<TrainerTable,String> tshoulder;
    @FXML   private TableColumn<TrainerTable,String> tchest;
    @FXML   private TableColumn<TrainerTable,String> tage;

    ObservableList<TrainerTable> list;

    Controller controller=new Controller();

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();
        String query = "SELECT  * FROM memberinfos  ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new TrainerTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9),rs.getString(10),rs.getString(4)));
        }
        tmemberid.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tmemberid"));
        theight.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("theight"));
        tfat.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tfat"));
        tweight.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tweight"));
        tbiceps.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tbiceps"));
        twaistlane.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("twaistlane"));
        tleg.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tleg"));
        tshoulder.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tshoulder"));
        tchest.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tchest"));
        tage.setCellValueFactory(new PropertyValueFactory<TrainerTable, String>("tage"));

        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }

    public void submit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        conn= DataManage.dbConnect();

        date.setOnAction((ActionEvent event) ->{
            date.getValue(); });

        if (!height.getText().isEmpty()) {
            String query = "INSERT INTO memberinfos" + "(memberid,height,fat,date,age,biceps,leg,shoulder,chest,waistlane,weight) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(query);

            pst.setString(1, (String) memberid.getValue());
            pst.setString(2, height.getText());
            pst.setString(3,fat.getText());
            pst.setDate(4,java.sql.Date.valueOf(date.getValue()));
            pst.setString(5, age.getText());
            pst.setString(6, biceps.getText());
            pst.setString(7,leg.getText());
            pst.setString(8,shoulder.getText());
            pst.setString(9, chest.getText());
            pst.setString(10,waistlane.getText());
            pst.setString(11, weight.getText());

            pst.executeUpdate();

            AlertMaker.AlertInformation("Added");

            DataManage.dbDisconnect(conn);

            controller.buton(actionEvent,"/sample/Trainer/trainer.fxml");


        } else {
            AlertMaker.AlertError("Please fill required places");
        }
    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn=DataManage.dbConnect();
        String date=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTage());
        String query = "DELETE FROM memberinfos WHERE memberid='"+String.valueOf(tableview.getSelectionModel().getSelectedItem().getTmemberid())+"'" + "AND date='" + date + "'";
        pst = conn.prepareStatement(query);
        int result=pst.executeUpdate();
        if(result==1){
            AlertMaker.AlertInformation("deleted");
        }
        DataManage.dbDisconnect(conn);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Table();
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn= DataManage.dbConnect();
            String query = "SELECT * FROM member";
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            while (rs.next()){
                memberid.getItems().add(rs.getString(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    String x;
    public void click(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if(mouseEvent.getClickCount()==2){
            conn=DataManage.dbConnect();
            String name=String.valueOf(tableview.getSelectionModel().getSelectedItem().getTmemberid());
            String query = "SELECT name FROM member WHERE memberid='"+name+"'" ;
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery(query);
            while (rs.next()){
                x=rs.getString(1);
            }

            AlertMaker.AlertInformation(x);
        }
    }
}
