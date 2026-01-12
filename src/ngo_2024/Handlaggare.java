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
 * @author tovehanssons //Klassen kan eventuellt vara överflödig.
 */
public class Handlaggare {
    
    private InfDB idb;
    private String inloggadAnvandare;
    
    private String ansvarighetsomrade;
    private String mentor;
    
    public Handlaggare(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        hamtaData();
    }
    
    private void hamtaData() {
        try {
            String sqlFraga =
                    "SELECT h.ansvarighetsomrade, h.mentor " +
                    "FROM handlaggare h " + 
                    "JOIN anstalld an ON h.aid = an.aid " +
                    "WHERE an.epost = '" + inloggadAnvandare + "'";
            HashMap<String, String> rad = idb.fetchRow(sqlFraga);
            
            ansvarighetsomrade = rad.get("ansvarighetsomrade");
            mentor = rad.get("mentor");
            
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
        
    }
        
        public String getAnsvarighetsomrade() {
            return ansvarighetsomrade;
            
        }
        
        public String getMentor() {
            return mentor;
            
        }
        
        public int skapaNyttAid() throws InfException {
            String maxId = idb.fetchSingle("SELECT MAX(aid) FROM anstalld");
            return (maxId == null || maxId.isEmpty()) ? 1 : Integer.parseInt(maxId) + 1;
        }
        public boolean laggTillHandlaggare(String fornamn, String efternamn, 
                String adress, String epost, String telefon, String anstallningsdatum, String losenord,
                int avdid, String mentor) {
            try {
                int nyttAid = skapaNyttAid();
                
                String nyAnstalld =
                        "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningsdatum, losenord, avdelning) "
                        + "VALUES (" + nyttAid + ", '" + fornamn + "', '" + efternamn + 
                                "', '" + adress + "', '" + epost + "', '" + telefon + "', '" + anstallningsdatum + "', '" + losenord + "', " + avdid + ")";
                idb.insert(nyAnstalld);
                
                String nyHandlaggare = 
                        "INSERT INTO handlaggare (aid, ansvarighetsomrade, mentor) " 
                        + "VALUES (" + nyttAid + ", '" + ansvarighetsomrade + "', '" + mentor +"')";
                idb.insert(nyHandlaggare);
                return true;
            } catch (InfException e) {
                System.out.println("Fel vid skapande av handläggare: " + e.getMessage());
                return false;
            }
        }
    }
