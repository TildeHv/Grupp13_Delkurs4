/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
/**
 */
public class Validering {
    public static boolean ValideraEpost(String ePost) { 
       return ePost.matches ("^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");  // ^början $slut.  a-z tillåter bokstäver förutomåäö. 
    }
    
    public static boolean ValideraTelefon(String telefon){
        return telefon.matches ("^\\+?[0-9]{7,15}$"); 
    }
    
    public static boolean ValideraAdress(String adress) { 
        return adress.matches ("^[A-Za-zÅÄÖåäö0-9 .\\-]{5,50}$"); 
    }
    
   public static boolean ValideraNamn(String namn){
       return namn.matches ("^[A-Za-zÅÄÖåäö'\\ -]{2,40}$");
   }
   
   public static boolean ValideraDatum(String datum) {
       return datum.matches ("\\d{4}-\\d{2}-\\d{2}");
   }
    
}



    


