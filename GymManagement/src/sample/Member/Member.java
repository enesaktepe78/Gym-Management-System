package sample.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.DataManage;
import sample.LoginHelper;
import sample.Trainer.TrainerTable;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Member implements Initializable {

    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;

    @FXML
    private Label height;
    @FXML
    private Label fat;
    @FXML
    private Label leg;
    @FXML
    private Label shoulder;
    @FXML
    private Label chest;
    @FXML
    private Label waistlane;
    @FXML
    private Label age;
    @FXML
    private Label biceps;
    @FXML
    private Label lastdate;
    String name = LoginHelper.username;

    @FXML   private TableView<InformationTable> tableview;
    @FXML   private TableColumn<InformationTable,String> tmemberid;
    @FXML   private TableColumn<InformationTable,String> theight;
    @FXML   private TableColumn<InformationTable,String> tfat;
    @FXML   private TableColumn<InformationTable,String> tweight;
    @FXML   private TableColumn<InformationTable,String> tbiceps;
    @FXML   private TableColumn<InformationTable,String> twaistlane;
    @FXML   private TableColumn<InformationTable,String> tleg;
    @FXML   private TableColumn<InformationTable,String> tshoulder;
    @FXML   private TableColumn<InformationTable,String> tchest;
    @FXML   private TableColumn<InformationTable,String> tage;

    ObservableList<InformationTable> list;

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();
        String query = "SELECT  * FROM memberinfos  WHERE memberid='" + LoginHelper.username + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new InformationTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9),rs.getString(10),rs.getString(4)));
        }
        tmemberid.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tmemberid"));
        theight.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("theight"));
        tfat.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tfat"));
        tweight.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tweight"));
        tbiceps.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tbiceps"));
        twaistlane.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("twaistlane"));
        tleg.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tleg"));
        tshoulder.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tshoulder"));
        tchest.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tchest"));
        tage.setCellValueFactory(new PropertyValueFactory<InformationTable, String>("tage"));

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
        try {
            conn = DataManage.dbConnect();
            String query = "SELECT   * FROM memberinfos  WHERE memberid='" +name + "'" ;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                height.setText(rs.getString(2));
                fat.setText(rs.getString(3));
                age.setText(rs.getString(5));
                biceps.setText(rs.getString(7));
                leg.setText(rs.getString(8));
                shoulder.setText(rs.getString(9));
                chest.setText(rs.getString(10));
                waistlane.setText(rs.getString(11));
                lastdate.setText(rs.getString(4));
            }
            rs.close();
            stmt.close();
            conn.close();


        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
