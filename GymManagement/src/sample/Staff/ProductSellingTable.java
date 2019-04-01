package sample.Staff;

import javafx.beans.property.SimpleStringProperty;

public class ProductSellingTable {
    private final SimpleStringProperty tmemberid;
    private final SimpleStringProperty tproductid;
    private final SimpleStringProperty tamount;
    private final SimpleStringProperty tdate;

    public ProductSellingTable(String tmemberid,String tproductid,String tamount,String tdate){
        this.tmemberid=new SimpleStringProperty(tmemberid);
        this.tproductid=new SimpleStringProperty(tproductid);
        this.tamount=new SimpleStringProperty(tamount);
        this.tdate=new SimpleStringProperty(tdate);
    }

    public String getTmemberid() {
        return tmemberid.get();
    }

    public SimpleStringProperty tmemberidProperty() {
        return tmemberid;
    }

    public void setTmemberid(String tmemberid) {
        this.tmemberid.set(tmemberid);
    }

    public String getTproductid() {
        return tproductid.get();
    }

    public SimpleStringProperty tproductidProperty() {
        return tproductid;
    }

    public void setTproductid(String tproductid) {
        this.tproductid.set(tproductid);
    }

    public String getTamount() {
        return tamount.get();
    }

    public SimpleStringProperty tamountProperty() {
        return tamount;
    }

    public void setTamount(String tamount) {
        this.tamount.set(tamount);
    }

    public String getTdate() {
        return tdate.get();
    }

    public SimpleStringProperty tdateProperty() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate.set(tdate);
    }
}
