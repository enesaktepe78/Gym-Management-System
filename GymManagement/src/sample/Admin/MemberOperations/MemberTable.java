package sample.Admin.MemberOperations;

import javafx.beans.property.SimpleStringProperty;

public class MemberTable {
    private final SimpleStringProperty tid;
    private final SimpleStringProperty tname;
    private final SimpleStringProperty tsurname;
    private final SimpleStringProperty temail;
    private final SimpleStringProperty tphone;
    private final SimpleStringProperty tusername;
    private final SimpleStringProperty tpassword;


    public MemberTable(String tid, String tname,String tsurname,  String temail,
                      String tphone, String tusername, String tpassword) {
        super();
        this.tid = new SimpleStringProperty(tid);
        this.tname = new SimpleStringProperty(tname);
        this.tsurname = new SimpleStringProperty(tsurname);
        this.temail = new SimpleStringProperty(temail);
        this.tphone = new SimpleStringProperty(tphone);
        this.tusername = new SimpleStringProperty(tusername);
        this.tpassword = new SimpleStringProperty(tpassword);
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
}
