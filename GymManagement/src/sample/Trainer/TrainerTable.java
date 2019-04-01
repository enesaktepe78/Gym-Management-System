package sample.Trainer;

import javafx.beans.property.SimpleStringProperty;

public class TrainerTable {

    private final SimpleStringProperty tmemberid;
    private final SimpleStringProperty theight;
    private final SimpleStringProperty tfat;
    private final SimpleStringProperty tweight;
    private final SimpleStringProperty tbiceps;
    private final SimpleStringProperty twaistlane;
    private final SimpleStringProperty tleg;
    private final SimpleStringProperty tshoulder;
    private final SimpleStringProperty tchest;
    private final SimpleStringProperty tage;

    public TrainerTable(String tmemberid,String theight,String tfat,String tweight,String tbiceps,String twaistlane,String tleg,String tshoulder,String tchest,String tage){
        this.tmemberid=new SimpleStringProperty(tmemberid);
        this.theight=new SimpleStringProperty(theight);
        this.tfat=new SimpleStringProperty(tfat);
        this.tweight=new SimpleStringProperty(tweight);
        this.tbiceps=new SimpleStringProperty(tbiceps);
        this.twaistlane=new SimpleStringProperty(twaistlane);
        this.tleg=new SimpleStringProperty(tleg);
        this.tshoulder=new SimpleStringProperty(tshoulder);
        this.tchest=new SimpleStringProperty(tchest);
        this.tage=new SimpleStringProperty(tage);
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

    public String getTheight() {
        return theight.get();
    }

    public SimpleStringProperty theightProperty() {
        return theight;
    }

    public void setTheight(String theight) {
        this.theight.set(theight);
    }

    public String getTfat() {
        return tfat.get();
    }

    public SimpleStringProperty tfatProperty() {
        return tfat;
    }

    public void setTfat(String tfat) {
        this.tfat.set(tfat);
    }

    public String getTweight() {
        return tweight.get();
    }

    public SimpleStringProperty tweightProperty() {
        return tweight;
    }

    public void setTweight(String tweight) {
        this.tweight.set(tweight);
    }

    public String getTbiceps() {
        return tbiceps.get();
    }

    public SimpleStringProperty tbicepsProperty() {
        return tbiceps;
    }

    public void setTbiceps(String tbiceps) {
        this.tbiceps.set(tbiceps);
    }

    public String getTwaistlane() {
        return twaistlane.get();
    }

    public SimpleStringProperty twaistlaneProperty() {
        return twaistlane;
    }

    public void setTwaistlane(String twaistlane) {
        this.twaistlane.set(twaistlane);
    }

    public String getTleg() {
        return tleg.get();
    }

    public SimpleStringProperty tlegProperty() {
        return tleg;
    }

    public void setTleg(String tleg) {
        this.tleg.set(tleg);
    }

    public String getTshoulder() {
        return tshoulder.get();
    }

    public SimpleStringProperty tshoulderProperty() {
        return tshoulder;
    }

    public void setTshoulder(String tshoulder) {
        this.tshoulder.set(tshoulder);
    }

    public String getTchest() {
        return tchest.get();
    }

    public SimpleStringProperty tchestProperty() {
        return tchest;
    }

    public void setTchest(String tchest) {
        this.tchest.set(tchest);
    }

    public String getTage() {
        return tage.get();
    }

    public SimpleStringProperty tageProperty() {
        return tage;
    }

    public void setTage(String tage) {
        this.tage.set(tage);
    }
}
