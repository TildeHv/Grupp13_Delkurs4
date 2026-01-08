/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Albin Malmquist
 */
public class AvdelningSQL {

    //fält
    private InfDB idb;

    public AvdelningSQL(InfDB idb) {
        this.idb = idb;
    }

    public Avdelning hamtaAvdelningMedId(int avdid) {

        try {
            String sql
                    = "SELECT * FROM avdelning WHERE avdid = " + avdid + ";";

            HashMap<String, String> rad = idb.fetchRow(sql);

            if (rad != null) {
                return new Avdelning(
                        Integer.parseInt(rad.get("avdid")),
                        rad.get("namn"),
                        rad.get("beskrivning"),
                        rad.get("adress"),
                        rad.get("epost"),
                        rad.get("telefon"),
                        rad.get("stad"),
                        rad.get("chef")
                );
            }
        } catch (InfException e) {
            System.out.println("Fel vid hämtning av avdelning: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getPersonalForAvdelning(int avdid) {
        ArrayList<HashMap<String, String>> resultat = new ArrayList<>();

        try {
            String sql
                    = "SELECT aid, fornamn, efternamn, epost, telefon "
                    + "FROM anstalld WHERE avdelning = " + avdid + ";";

            resultat = idb.fetchRows(sql);
        } catch (InfException e) {
            System.out.println("Hämtning av personal misslyckades: " + e.getMessage());
        }
        return resultat;
    }

    public ArrayList<HashMap<String, String>> sokHandlaggare(int avdid, String sokterm) {
        ArrayList<HashMap<String, String>> resultat = new ArrayList<>();

        try {
            String sql
                    = "SELECT an.aid, an.fornamn, an.efternamn, an.epost, an.telefon " + "FROM anstalld an "
                    + "JOIN handlaggare h ON an.aid = h.aid " + "WHERE an.avdelning = " + avdid + " "
                    + "AND (an.fornamn LIKE '%" + sokterm + "%' " + "OR an.efternamn LIKE '%" + sokterm + "%' "
                    + "OR an.epost LIKE '%" + sokterm + "%')";

            resultat = idb.fetchRows(sql);

        } catch (InfException e) {
            System.out.println("Sökning av handläggare misslyckades: " + e.getMessage());
        }
        return resultat;
    }

    public boolean laggTillAvdelning(int avdid, String namn, String beskrivning,
            String adress, String epost, String telefon,
            String stad, int chef) {

        try {
            String sql
                    = "INSERT INTO avdelning (avdid, namn, beskrivning, adress, epost, telefon, stad, chef) "
                    + "VALUES (" + avdid + ", '" + namn + "', '" + beskrivning + "','" + adress + "','" + epost + "','" + telefon + "', '" + stad + "', " + chef + ");";
            idb.insert(sql);
              System.out.println("SQL: " + sql);
            return true;
          

        } catch (InfException e) {
            System.out.println("Skapandet av avdelning misslyckades: " + e.getMessage());
        }
        return false;
    }

    public int skapaNyttAvdid() throws InfException {
        String maxId = idb.fetchSingle("SELECT MAX(avdid) FROM avdelning");
        return (maxId == null || maxId.isEmpty()) ? 1 : Integer.parseInt(maxId) + 1;
    }

    public boolean redigeraAvdelning(int avdid, String namn, String beskrivning,
            String adress, String epost, String telefon,
            String stad, int chef) {

        try {
            String sql
                    = "UPDATE avdelning SET "
                    + "namn = '" + namn + "', "
                    + "beskrivning = '" + beskrivning + "', "
                    + "adress = '" + adress + "', "
                    + "epost = '" + epost + "', "
                    + "telefon = '" + telefon + "', "
                    + "stad = '" + stad + "', "
                    + "chef = " + chef + " "
                    + "WHERE avdid = '" + avdid + "' ";
            idb.update(sql);
            return true;

        } catch (InfException e) {
            System.out.println("Redigering av avdelning misslyckades: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<HashMap<String, String>> hamtaAllaAvdelningar() throws InfException {
        String sql = "SELECT avdid, namn FROM avdelning ORDER BY avdid";
        return idb.fetchRows(sql);
    }

}
