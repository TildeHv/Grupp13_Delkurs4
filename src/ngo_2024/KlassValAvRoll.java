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
 * @author tovehanssons
 */
public class KlassValAvRoll {
//Klassen ValAvRoll är en hjälpklass för att avgöra vilken roll som är inloggad
//baserat på e-postadress    

    //Kontollerar om användaren är en adminstratör
    public static boolean arAdmin(InfDB idb, String inloggadAnvandare) {

        try {
            String sqlFraga
                    = "SELECT a.aid "
                    + "FROM admin a "
                    + "JOIN anstalld an on a.aid = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'";

            return idb.fetchSingle(sqlFraga) != null;
        } catch (InfException e) {
            return false;

        }
    }

    //Kontrollerar om användaren är en handläggare
    public static boolean arHandlaggare(InfDB idb, String inloggadAnvandare) {
        try {
            String sqlFraga
                    = "SELECT h.aid "
                    + "FROM handlaggare h "
                    + "JOIN anstalld an ON h.aid = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'";

            return idb.fetchSingle(sqlFraga) != null;
        } catch (InfException e) {
            return false;
        }
    }

    //Kontrollerar om användaren är en projektchef
    public static boolean arProjektchef(InfDB idb, String inloggadAnvandare) {

        try {
            String sqlFraga
                    = "SELECT p.projektchef "
                    + "FROM projekt p "
                    + "JOIN anstalld an ON p.projektchef = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'";
            return idb.fetchSingle(sqlFraga) != null;
        } catch (InfException e) {
            return false;

        }
    }

    public static java.util.ArrayList<String> hamtaProjektForProjektchef(InfDB idb, String inloggadAnvandare) {
        try {
            return idb.fetchColumn(
                    "SELECT p.pid "
                    + "FROM projekt p "
                    + "JOIN anstalld an ON p.projektchef = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'"
            );
        } catch (InfException e) {
            return new java.util.ArrayList<>();
        }
    }

}
