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
 * @author tovehanssons
 */

public class Partners {

    private InfDB idb;

    private String pid;
    private String namn;
    private String kontaktperson;
    private String kontaktepost;
    private String telefon;
    private String adress;
    private String branch;
    private String stad;


    public Partners(InfDB idb, String pid) {
        
        this.idb = idb;
        this.pid = pid;
        hamtaUppgifter();
    }

    private void hamtaUppgifter() {
        try {
            String sqlFraga =
                    "SELECT pid, namn, kontaktperson, kontaktepost, telefon, "
                    + "adress, branch, stad " +
                    "FROM partner " +
                    "WHERE pid = '" + pid + "'";
            HashMap<String, String> rad = idb.fetchRow(sqlFraga);

            if (rad != null) {
                pid = rad.get("pid");
                namn = rad.get("namn");
                kontaktperson = rad.get("kontaktperson");
                kontaktepost = rad.get("kontaktepost");
                telefon = rad.get("telefon");
                adress = rad.get("adress");
                branch = rad.get("branch");
                stad = rad.get("stad");
            }

        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }

    //Getters
    public String getPid() { 
        return pid;
        
    }
    
    public String getNamn() { 
        return namn;
        
    }
    
    public String getKontaktperson() { 
        return kontaktperson;
        
    }
    
    public String getKontaktepost() {
        return kontaktepost;
        
    }
    
    public String getTelefon() {
        return telefon;
        
    }
    
    public String getAdress() {
        return adress;
        
    }
    
    public String getBranch() {
        return branch;
        
    }
    
    public String getStad() {
        return stad;
        
    }
    
    
       // För att enkelt visa i listor
    @Override
    public String toString() {
        return namn + " (" + pid + ")";
    }
    

    // Hämta alla partner (admin / generell vy)
    public static ArrayList<Partners> hamtaAlla(InfDB idb) {
        ArrayList<Partners> lista = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> rader =
                    idb.fetchRows("SELECT pid FROM partner ORDER BY namn");

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    String pid = rad.get("pid");
                    lista.add(new Partners(idb, pid));
                }
            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    // Hämta partners för ett projekt (via projekt_partner)
    public static ArrayList<Partners> hamtaForProjekt(InfDB idb, String projektPid) {
        ArrayList<Partners> lista = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> rader =
                    idb.fetchRows(
                            "SELECT partner_pid FROM projekt_partner " +
                            "WHERE pid = '" + projektPid + "'"
                    );

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    lista.add(new Partners(idb, rad.get("partner_pid")));
                }
            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    // Hämta partners för en handläggare: partners i projekt där handläggaren deltar (ans_proj)
    public static ArrayList<Partners> hamtaForHandlaggare(InfDB idb, String inloggadEpost) {
        ArrayList<Partners> lista = new ArrayList<>();
        try {
            ArrayList<HashMap<String, String>> rader =
                    idb.fetchRows(
                            "SELECT DISTINCT pp.partner_pid " +
                            "FROM ans_proj ap " +
                            "JOIN anstalld an ON ap.aid = an.aid " +
                            "JOIN projekt_partner pp ON pp.pid = ap.pid " +
                            "WHERE an.epost = '" + esc(inloggadEpost) + "'"
                    );

            if (rader != null) {
                for (HashMap<String, String> rad : rader) {
                    lista.add(new Partners(idb, rad.get("partner_pid")));
                }
            }
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("'", "''").trim();
    }
}