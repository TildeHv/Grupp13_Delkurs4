/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author tovehanssons
 */
public class ValAvRoll {
    
    public static boolean arAdmin(InfDB idb, String inloggadAnvandare) {
        
        try {
            String sqlFraga = 
                    "SELECT a.aid " + 
                    "FROM admin a " +
                    "JOIN anstalld an on a.aid = an.aid " +
                    "WHERE an.epost = '" + inloggadAnvandare + "'";
            
            
            return idb.fetchSingle(sqlFraga) != null;
        } catch (InfException e) {
            return false;
     
        }
    }
    
    public static boolean arHandlaggare(InfDB idb, String inloggadAnvandare) {
        try {
            String sqlFraga =
                    "SELECT h.aid " + 
                    "FROM handlaggare h " +
                    "JOIN anstalld an ON h.aid = an.aid " +
                    "WHERE an.epost = '" + inloggadAnvandare + "'";
            
           
            
            return idb.fetchSingle(sqlFraga) !=null;
        } catch (InfException e) {
            return false;
        }
    }
    
    public static boolean arProjektchef(InfDB idb, String inloggadAnvandare) {
        
       try {
           String sqlFraga = 
                   "SELECT p.projektchef " +
                   "FROM projekt p " +
                   "JOIN anstalld an ON p.projektchef = an.aid " +
                   "WHERE an.epost = '" + inloggadAnvandare + "'";
           return idb.fetchSingle(sqlFraga) !=null;
       } catch (InfException e) {
           return false;
           
       }
       
       }
    }
    


