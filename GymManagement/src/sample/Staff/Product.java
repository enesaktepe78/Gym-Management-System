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
import org.joda.time.DateTime;
import org.joda.time.Days;
import sample.Admin.Controller;
import sample.AlertMaker;
import sample.DataManage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;

import java.util.ResourceBundle;

public class Product implements Initializable{
    Connection conn;
    PreparedStatement prs=null;

    @FXML
    private TextField productid;
    @FXML
    private TextField productname;
    @FXML
    private TextField type;
    @FXML
    private TextField cost;
    @FXML
    private DatePicker expiredate;
    @FXML
    private TextField stock;

    @FXML   private TableView<ProductTable> tableview;
    @FXML   private TableColumn<ProductTable,String> tproductid;
    @FXML   private TableColumn<ProductTable,String> tname;
    @FXML   private TableColumn<ProductTable,String> ttype;
    @FXML   private TableColumn<ProductTable,String> tcost;
    @FXML   private TableColumn<ProductTable, Date> texpiredate;
    @FXML   private TableColumn<ProductTable,String> tstock;

    ObservableList<ProductTable> list;

    ProductData productData=new ProductData();

    Controller controller=new Controller();

    public void Table() throws SQLException, ClassNotFoundException {
        conn = DataManage.dbConnect();
        list=FXCollections.observableArrayList();

        String query = "SELECT  * FROM productselling ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new ProductTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)));
        }
        tproductid.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("tproductid"));
        tname.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("tname"));
        ttype.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("ttype"));
        tcost.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("tcost"));
        texpiredate.setCellValueFactory(new PropertyValueFactory<ProductTable, Date>("texpiredate"));
        tstock.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("tstock"));
        tableview.setItems(null);
        tableview.setItems(list);
        rs.close();
        stmt.close();
        conn.close();
    }

    public void add(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn=DataManage.dbConnect();

        expiredate.setOnAction((ActionEvent event) ->{
            expiredate.getValue(); });

        if(productData.isProductExist(productid.getText())){
            AlertMaker.AlertError("You can not add same product id");
            return;
        }

        if(!productid.getText().isEmpty()&&!productname.getText().isEmpty()&&!stock.getText().isEmpty()){
            String query="INSERT INTO productselling(productid,name,type,cost,expiredate,stock)"+ "VALUES(?,?,?,?,?,?)";
            prs = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(query);
            prs.setString(1,productid.getText());
            prs.setString(2,productname.getText());
            prs.setString(3,type.getText());
            prs.setString(4,cost.getText());
            prs.setDate(5, java.sql.Date.valueOf(expiredate.getValue()));
            prs.setString(6,stock.getText());
            prs.executeUpdate();

            AlertMaker.AlertInformation("Added");
        }else{
            AlertMaker.AlertError("Please fill required places");
        }

        DataManage.dbDisconnect(conn);

    }

    public void delete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        conn=DataManage.dbConnect();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM productselling  WHERE productid='" + productid.getText()+"'";
        int result = stmt.executeUpdate(query);
        if(result==1){
            AlertMaker.AlertInformation("Deleted");
            }
    }

    public void update(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if(cost.getText().isEmpty()&&stock.getText().isEmpty()){
            AlertMaker.AlertError("Fill in required places");
            return;
        }
        conn = DataManage.dbConnect();
        String query = "UPDATE productselling SET cost='" + cost.getText() + "', stock='" + stock.getText() + "' WHERE productid=" + productid.getText();
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        if (result == 1) {
            AlertMaker.AlertInformation("Updated");
        }
        stmt.close();
        DataManage.dbDisconnect(conn);

    }

    public void back(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"/sample/Staff/staff.fxml");
    }

    public void productselling(ActionEvent actionEvent) throws IOException {
        controller.buton(actionEvent,"/sample/Staff/productselling.fxml");
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
