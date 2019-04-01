package sample.Staff;


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
import javafx.stage.Stage;
import sample.AlertMaker;
import sample.DataManage;
import sample.LoginHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProductSelling implements Initializable {
    Connection conn;
    ResultSet rs=null;
    PreparedStatement prs=null;
    @FXML
    private ComboBox memberid;
    @FXML
    private  ComboBox productid;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;

    private int   stock;

    @FXML   private TableView<ProductSellingTable> tableview;
    @FXML   private TableColumn<ProductSellingTable,String> tmemberid;
    @FXML   private TableColumn<ProductSellingTable,String> tproductid;
    @FXML   private TableColumn<ProductSellingTable,String> tamount;
    @FXML   private TableColumn<ProductSellingTable,String> tdate;

    ObservableList<ProductSellingTable> list;

    ProductData productData=new ProductData();

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list= FXCollections.observableArrayList();

        String query = "SELECT  * FROM product ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new ProductSellingTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        tmemberid.setCellValueFactory(new PropertyValueFactory<ProductSellingTable, String>("tmemberid"));
        tproductid.setCellValueFactory(new PropertyValueFactory<ProductSellingTable, String>("tproductid"));
        tamount.setCellValueFactory(new PropertyValueFactory<ProductSellingTable, String>("tamount"));
        tdate.setCellValueFactory(new PropertyValueFactory<ProductSellingTable, String>("tdate"));
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
            conn= DataManage.dbConnect();
            String query = "SELECT * FROM member";
            prs = conn.prepareStatement(query);
            rs = prs.executeQuery(query);
            while (rs.next()){
                memberid.getItems().add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            conn= DataManage.dbConnect();
            String query = "SELECT * FROM productselling";
            prs = conn.prepareStatement(query);
            rs = prs.executeQuery(query);
            while (rs.next()){
                productid.getItems().add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sell(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn=DataManage.dbConnect();


        date.setOnAction((ActionEvent event) ->{
            date.getValue(); });

       String query2 = "SELECT stock FROM productselling WHERE productid='" +productid.getValue()+"'";
        PreparedStatement prs2 = conn.prepareStatement(query2);
        ResultSet rs2 = prs2.executeQuery(query2);
        while (rs2.next()){
            stock= Integer.parseInt(rs2.getString(1));
        }
        if(stock<Integer.parseInt(amount.getText())){
            AlertMaker.AlertError("There are not enough products");
            return;
        }

        String query="INSERT INTO product(memberid,productid,amount,date)"+"VALUES(?,?,?,?)";
        prs=conn.prepareStatement(query);
        prs.setString(1, (String) memberid.getValue());
        prs.setString(2, (String) productid.getValue());
        prs.setString(3,amount.getText());
        prs.setDate(4, java.sql.Date.valueOf(date.getValue()));
        prs.executeUpdate();


        String updateTableSQL = "UPDATE productselling SET stock = ? WHERE productid ='" +productid.getValue() +"'";
        PreparedStatement preparedStatement = DataManage.dbConnect().prepareStatement(updateTableSQL);
        preparedStatement.setString(1, String.valueOf(stock- Integer.parseInt(amount.getText())));
        preparedStatement.executeUpdate();

        AlertMaker.AlertInformation("Sold");

    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("product.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
