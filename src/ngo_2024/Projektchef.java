/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;

/**
 *
 * @author tovehanssons //Denna klass kan eventuellt vara överflödig.
 */
public class Projektchef {
    
    private InfDB idb; 
    private String inloggadAnvandare;
    
    public Projektchef(InfDB idb, String inloggadAnvandare) {
    this.idb = idb;
    this.inloggadAnvandare = inloggadAnvandare;
}

public ArrayList<String> hamtaEgnaProjekt() {
    try {
        return idb.fetchColumn(
                "SELECT p.pid " + 
                "FROM projekt p " +
                "JOIN anstalld an ON p.projektchef = an.aid " +
                "WHERE an.epost = '" + inloggadAnvandare + "'" 
        );
    } catch (InfException e) {
        return new ArrayList<>();
    }
    
} 

}
