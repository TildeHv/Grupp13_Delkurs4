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
    private String inloggadAnvandare;
    private String aid;
    private String fornamn;
    private String efternamn;
    private String adress;
    private String epost;
    private String telefon;
    private String anstallningsdatum;
    private String avdelning;
    
    public Anvandare(InfDB idb, String inloggadAnvandare) {
        
        this.inloggadAnvandare = inloggadAnvandare;
        this.idb = idb;
        hamtaUppgifter();
        System.out.println("Skapar Anvandare med aid: " + aid);
    }
    
    private void hamtaUppgifter() {

        
        try {
            
            System.out.println(aid);
          String sqlFraga =
          "SELECT fornamn, aid, efternamn, adress, epost, telefon, " +
          "anstallningsdatum, avdelning " +
          "FROM anstalld " +      
          "WHERE ePost = '" + inloggadAnvandare + "'";
          
          System.out.print(sqlFraga);
        
          
          HashMap<String, String> rad = idb.fetchRow(sqlFraga);
          
           if (rad == null) {
    System.out.println("Ingen rad hittades för aid: " + aid);
} else {
    System.out.println("Hämtade användare: " + rad.get("fornamn") + " " + rad.get("efternamn"));
}
          
          fornamn = rad.get("fornamn");
          efternamn = rad.get("efternamn");
          adress = rad.get ("adress");
          epost = rad.get ("epost");
          telefon = rad.get ("telefon");
          anstallningsdatum = rad.get ("anstallningsdatum");
          avdelning = rad.get ("avdelning");
          aid = rad.get("aid");

          
          System.out.println(efternamn);
          
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

