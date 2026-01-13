/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class Ngo_2024 {

    private static InfDB idb;

    public static void main(String[] args) {
    try {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}    
        
       SwingUtilities.invokeLater(() -> {
            try {
                idb = new InfDB("sdgsweden", "3306", "dbAdmin2024", "dbAdmin2024PW");
                new Inloggning(idb).setVisible(true);
                System.out.println("funkar");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }
}