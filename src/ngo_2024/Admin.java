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
 */ //Denna klass kan eventuellt vara överflödig.
public class Admin {
    
    private InfDB idb;
    private String inloggadAnvandare;
    
    public Admin (InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
    }
    
    public void laggTillAvdelning(String namn) {
        try {
            idb.insert(
                    "INSERT INTO avdelning (namn) VALUES ('" + namn + "')"
                    
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

