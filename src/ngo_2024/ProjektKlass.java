/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class ProjektKlass {

    private InfDB idb;
    private String InloggadAnvandare;
    private String projektnamn;
    private String beskrivning;
    private String startdatum;
    private String slutdatum;
    private String kostnad;
    private String status;
    private String projektchef;
    private String land;
    private String prioritet;
    private int pid;

    public ProjektKlass(InfDB idb, String InloggadAnvandare, int pid) {
        this.InloggadAnvandare = InloggadAnvandare;
        this.idb = idb;
        this.pid = pid;
        hamtaProjekt();
    }

    private void hamtaProjekt() {
        /*Metod för getters*/

        try {

            String sqlFraga
                    = "SELECT pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land "
                    + "FROM projekt "
                    + "WHERE pid = " + pid;

            HashMap<String, String> rad = idb.fetchRow(sqlFraga);

            if (rad == null) {
                System.out.println("Error");
            } else {
                System.out.println("Lyckades!");

                projektnamn = rad.get("projektnamn");
                beskrivning = rad.get("beskrivning");
                startdatum = rad.get("startdatum");
                slutdatum = rad.get("slutdatum");
                kostnad = rad.get("kostnad");
                status = rad.get("status");
                prioritet = rad.get("prioritet");
                projektchef = rad.get("projektchef");
                land = rad.get("land");

            }

        } catch (InfException e) {
            e.printStackTrace();
        }
    }

    public String getProjektnamn() {
        return projektnamn;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public String getKostnad() {
        return kostnad;
    }

    public String getStartdatum() {
        return startdatum;
    }

    public String getSlutdatum() {
        return slutdatum;
    }

    public String getProjektchef() {
        return projektchef;

    }

    public String getLand() {
        return land;

    }

    public String getPrioritet() {
        return prioritet;
    }

    public String getStatus() {
        return status;
    }

}
