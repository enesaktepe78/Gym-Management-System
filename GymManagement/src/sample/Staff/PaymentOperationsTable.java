package sample.Staff;

import javafx.beans.property.SimpleStringProperty;

public class PaymentOperationsTable {
    private final SimpleStringProperty tmemberid;
    private final SimpleStringProperty ttotalpayment;
    private final SimpleStringProperty tpaid;

    private final SimpleStringProperty tpaydate;

    public PaymentOperationsTable(String tmemberid,String ttotalpayment,String tpaid,String tpaydate){
        this.tmemberid=new SimpleStringProperty(tmemberid);
        this.ttotalpayment=new SimpleStringProperty(ttotalpayment);
        this.tpaid=new SimpleStringProperty(tpaid);

        this.tpaydate=new SimpleStringProperty(tpaydate);
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

    public String getTtotalpayment() {
        return ttotalpayment.get();
    }

    public SimpleStringProperty ttotalpaymentProperty() {
        return ttotalpayment;
    }

    public void setTtotalpayment(String ttotalpayment) {
        this.ttotalpayment.set(ttotalpayment);
    }

    public String getTpaid() {
        return tpaid.get();
    }

    public SimpleStringProperty tpaidProperty() {
        return tpaid;
    }

    public void setTpaid(String tpaid) {
        this.tpaid.set(tpaid);
    }


    public String getTpaydate() {
        return tpaydate.get();
    }

    public SimpleStringProperty tpaydateProperty() {
        return tpaydate;
    }

    public void setTpaydate(String tpaydate) {
        this.tpaydate.set(tpaydate);
    }
}
