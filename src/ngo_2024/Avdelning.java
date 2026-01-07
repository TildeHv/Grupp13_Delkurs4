/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

/**
 *
 * @author Albin Malmquist
 */
public class Avdelning {
    //fält
    private int avdid;
    private String namn;
    private String beskrivning;
    private String adress;
    private String epost;
    private String telefon;
    private String stad;
    private String chef;
    
    //konstruktor
    public Avdelning(int avdid, String namn, String beskrivning, String adress, String epost, String telefon, String stad, String chef) {
        this.avdid = avdid;
        this.namn = namn;
        this.beskrivning = beskrivning;
        this.adress = adress;
        this.epost = epost;
        this.telefon = telefon;
        this.stad = stad;
        this.chef = chef;
    }
    
    public int getAvdid() {return avdid;}
    public void setAvdid(int avdid) {this.avdid = avdid;}
    
    public String getNamn() {return namn;}
    public void setNamn(String namn) {this.namn = namn;}
    
    public String getBeskrivning() {return beskrivning;}
    public void setBeskrivning(String beskrivning) {this.beskrivning = beskrivning;}
    
    public String getAdress() {return adress;}
    public void setAdress(String adress) {this.adress = adress;}
    
    public String getEpost() {return epost;}
    public void setEpost(String epost) {this.epost = epost;}
    
    public String getTelefon() {return telefon;}
    public void setTelefon(String telefon) {this.telefon = telefon;}
    
    public String getStad() {return stad;}
    public void setStad(String stad) {this.stad = stad;}
    
    public String getChef() {return chef;}
    public void setChef(String chef) {this.chef = chef;}

    @Override
    public String toString(){
        return namn;
    }
}
