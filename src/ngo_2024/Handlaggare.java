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
 * @author tovehanssons
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
    }
