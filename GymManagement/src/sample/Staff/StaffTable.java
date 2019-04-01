package sample.Staff;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StaffTable {
    private final SimpleStringProperty tid;
    private final SimpleStringProperty tname;
    private final SimpleStringProperty tsurname;
    private final SimpleStringProperty tgender;
    private final SimpleStringProperty temail;
    private final SimpleStringProperty tphone;
    private final SimpleStringProperty tusername;
    private final SimpleStringProperty tpassword;
    private final SimpleStringProperty tduration;
    private final SimpleStringProperty tstartdate;
    private final SimpleStringProperty tfinishdate;
    private final SimpleStringProperty ttrainer;


    public StaffTable(String tid, String tname,String tsurname, String tgender, String temail,
                      String tphone, String tusername, String tpassword,String tduration,String tstartdate,String tfinishdate,String ttrainer) {
        super();
        this.tid = new SimpleStringProperty(tid);
        this.tname = new SimpleStringProperty(tname);
        this.tsurname = new SimpleStringProperty(tsurname);
        this.tgender = new SimpleStringProperty(tgender);
        this.temail = new SimpleStringProperty(temail);
        this.tphone = new SimpleStringProperty(tphone);
        this.tusername = new SimpleStringProperty(tusername);
        this.tpassword = new SimpleStringProperty(tpassword);
        this.tduration=new SimpleStringProperty(tduration);
        this.tstartdate=new SimpleStringProperty(tstartdate);
        this.tfinishdate=new SimpleStringProperty(tfinishdate);
        this.ttrainer=new SimpleStringProperty(ttrainer);
    }

    public String getTid() {
        return tid.get();
    }

    public SimpleStringProperty tidProperty() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid.set(tid);
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

    public String getTsurname() {
        return tsurname.get();
    }

    public SimpleStringProperty tsurnameProperty() {
        return tsurname;
    }

    public void setTsurname(String tsurname) {
        this.tsurname.set(tsurname);
    }

    public String getTgender() {
        return tgender.get();
    }

    public SimpleStringProperty tgenderProperty() {
        return tgender;
    }

    public void setTgender(String tgender) {
        this.tgender.set(tgender);
    }

    public String getTemail() {
        return temail.get();
    }

    public SimpleStringProperty temailProperty() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail.set(temail);
    }

    public String getTphone() {
        return tphone.get();
    }

    public SimpleStringProperty tphoneProperty() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone.set(tphone);
    }

    public String getTusername() {
        return tusername.get();
    }

    public SimpleStringProperty tusernameProperty() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername.set(tusername);
    }

    public String getTpassword() {
        return tpassword.get();
    }

    public SimpleStringProperty tpasswordProperty() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword.set(tpassword);
    }

    public String getTduration() {
        return tduration.get();
    }

    public SimpleStringProperty tdurationProperty() {
        return tduration;
    }

    public void setTduration(String tduration) {
        this.tduration.set(tduration);
    }

    public String getTstartdate() {
        return tstartdate.get();
    }

    public SimpleStringProperty tstartdateProperty() {
        return tstartdate;
    }

    public void setTstartdate(String tstartdate) {
        this.tstartdate.set(tstartdate);
    }

    public String getTfinishdate() {
        return tfinishdate.get();
    }

    public SimpleStringProperty tfinishdateProperty() {
        return tfinishdate;
    }

    public void setTfinishdate(String tfinishdate) {
        this.tfinishdate.set(tfinishdate);
    }

    public String getTtrainer() {
        return ttrainer.get();
    }

    public SimpleStringProperty ttrainerProperty() {
        return ttrainer;
    }

    public void setTtrainer(String ttrainer) {
        this.ttrainer.set(ttrainer);
    }
}
