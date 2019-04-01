package sample.Staff;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ProductTable {

    private final SimpleStringProperty tproductid;
    private final SimpleStringProperty tname;
    private final SimpleStringProperty ttype;
    private final SimpleStringProperty tcost;
    private final SimpleStringProperty texpiredate;
    private final SimpleStringProperty tstock;

    public ProductTable(String tproductid, String tname, String ttype, String tcost, java.util.Date texpiredate, String tstock) {
        super();
        this.tproductid =new SimpleStringProperty(tproductid);
        this.tname = new SimpleStringProperty(tname);
        this.ttype = new SimpleStringProperty(ttype);
        this.tcost =new  SimpleStringProperty(tcost);
        this.texpiredate = new SimpleStringProperty(texpiredate.toString());
        this.tstock =new SimpleStringProperty(tstock);
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

    public String getTname() {
        return tname.get();
    }

    public SimpleStringProperty tnameProperty() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname.set(tname);
    }

    public String getTtype() {
        return ttype.get();
    }

    public SimpleStringProperty ttypeProperty() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype.set(ttype);
    }

    public String getTcost() {
        return tcost.get();
    }

    public SimpleStringProperty tcostProperty() {
        return tcost;
    }

    public void setTcost(String tcost) {
        this.tcost.set(tcost);
    }

    public String getTexpiredate() {
        return texpiredate.get();
    }

    public SimpleStringProperty texpiredateProperty() {
        return texpiredate;
    }

    public void setTexpiredate(String texpiredate) {
        this.texpiredate.set(texpiredate);
    }

    public String getTstock() {
        return tstock.get();
    }

    public SimpleStringProperty tstockProperty() {
        return tstock;
    }

    public void setTstock(String tstock) {
        this.tstock.set(tstock);
    }
}
