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
public class Anvandare {
    
    private InfDB idb;
    
    private String aid;
    private String fornamn;
    private String efternamn;
    private String adress;
    private String epost;
    private String telefon;
    private String anstallningsdatum;
    private String avdelning;
    
    public Anvandare(InfDB idb, String aid) {
        
        this.idb = idb;
        this.aid = aid;
        hamtaUppgifter();
    }
    
    private void hamtaUppgifter() {
        
        try {
          String sqlFraga =
                  "SELECT fornamn, efternamn, adress, epost, telefon, " 
                  + "anstallningsdatum, abdelning " +
                  "FROM anstalld" +
                  " WHERE aid = '" + aid + "'";
          
          HashMap<String, String> rad = idb.fetchRow(sqlFraga);
          
          fornamn = rad.get("fornamn");
          efternamn = rad.get("efternamn");
          adress = rad.get ("adress");
          epost = rad.get ("epost");
          telefon = rad.get ("telefon");
          anstallningsdatum = rad.get ("anstallningsdatum");
          avdelning = rad.get ("avdelning");
          
        } catch (InfException e) {
          System.out.println(e.getMessage());
            
        }
    }
    
    //Getters
    public String getAid() {
        return aid;
        
    }
    
    public String getFornamn() {
        return fornamn;
    }
    
    public String getEfternamn() {
        return efternamn;
    }
    
    public String getFullNamn() {
        return fornamn + " " + efternamn;
        
    }
    
    public String getAdress() {
        return adress;
        
    }
    
    public String getEpost() {
        return epost;
        
    }
    
    public String getTelefon() {
        return telefon;
        
    }
    
    public String getAnstallningsdatum() {
        return anstallningsdatum;
    }
    
    public String getAvdelning() {
        return avdelning;
      
    } 
        
      }

