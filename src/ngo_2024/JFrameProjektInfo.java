/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;

public class JFrameProjektInfo extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private int projektId;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JFrameProjektInfo.class.getName());

    public JFrameProjektInfo(InfDB idb, int projektId, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.projektId = projektId;
        initComponents();
        this.setLocationRelativeTo(null);
        HamtaProjektinfo();

        btnAndraKnapp.setVisible(false);
        /*z--Sätter knappen till false om man inte är projektchef*/

        if (KlassValAvRoll.arProjektchef(idb, inloggadAnvandare)) {
            btnAndraKnapp.setVisible(true);
            btnAndraKnapp.setEnabled(true);
        }

        btnAndraKnapp.setEnabled(false);
        /*Sätter tillgången till false om man inte är projektchef för det projektet*/

        boolean arProjektchef = arProjektchefForProjekt(idb, inloggadAnvandare, projektId);

        if (arProjektchef) {
            btnAndraKnapp.setEnabled(true);
        }

        getContentPane().setBackground(Color.WHITE);
    }

    private void HamtaProjektinfo() {
        /*<--- Hämtar information från projektklass och sätter i den i respektive fält */

        try {
            String sqlFraga = "SELECT p.projektnamn, p.projektchef, p.startdatum, p.slutdatum, "
                    + "p.kostnad, p.status, p.beskrivning, "
                    + "(SELECT l.namn FROM land l WHERE l.lid = p.land) AS land_namn "
                    + "FROM projekt p "
                    + "WHERE p.pid = " + projektId;

            HashMap<String, String> projekt = idb.fetchRow(sqlFraga);

            if (projekt != null) {
                lblprojektnamn.setText(projekt.get("projektnamn") != null ? projekt.get("projektnamn") : "");
                lblstartdatum.setText(projekt.get("startdatum") != null ? projekt.get("startdatum") : "");
                lblslutdatum.setText(projekt.get("slutdatum") != null ? projekt.get("slutdatum") : "");
                lblkostnad.setText(projekt.get("kostnad") != null ? projekt.get("kostnad") : "");
                lblstatus.setText(projekt.get("status") != null ? projekt.get("status") : "");
                txtbeskrivning.setText(projekt.get("beskrivning") != null ? projekt.get("beskrivning") : "");
                lblland.setText(projekt.get("land_namn") != null ? projekt.get("land_namn") : "Ingen");

                String chefAid = projekt.get("projektchef");
                if (chefAid != null) {
                    HashMap<String, String> chefData = idb.fetchRow(
                            "SELECT fornamn, efternamn FROM anstalld WHERE aid = " + chefAid
                    );
                    if (chefData != null) {
                        String chefNamn = (chefData.get("fornamn") != null ? chefData.get("fornamn") : "")
                                + " "
                                + (chefData.get("efternamn") != null ? chefData.get("efternamn") : "");
                        lblprojektchef.setText(chefNamn.trim());
                    } else {
                        lblprojektchef.setText("Ingen");
                    }
                } else {
                    lblprojektchef.setText("Ingen");
                }
            }

        } catch (InfException e) {
            e.printStackTrace();
        }
    }

    public static boolean arProjektchefForProjekt(InfDB idb, String inloggadAnvandare, int projektID) {
        /*<-- Använder sql fråga för att titta att  projekt pid och projektchefens aid är samma*/
        try {
            String sqlFraga
                    = "SELECT projekt.pid FROM projekt "
                    + "JOIN anstalld ON projekt.projektchef = anstalld.aid "
                    + "WHERE projekt.pid = " + projektID
                    + " AND anstalld.epost = '" + inloggadAnvandare + "'";

            ArrayList<HashMap<String, String>> result = idb.fetchRows(sqlFraga);
            return result != null && !result.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        txtprojekt = new javax.swing.JLabel();
        lblprojektnamn = new javax.swing.JLabel();
        lblprojektchef = new javax.swing.JLabel();
        lblkostnad = new javax.swing.JLabel();
        lblstartdatum = new javax.swing.JLabel();
        txtansvarig = new javax.swing.JLabel();
        lblslutdatum = new javax.swing.JLabel();
        lblstatus = new javax.swing.JLabel();
        txtbeskrivning = new javax.swing.JTextField();
        txtbudget = new javax.swing.JLabel();
        txtslutdatum = new javax.swing.JLabel();
        txtstatus = new javax.swing.JLabel();
        txtland = new javax.swing.JLabel();
        lblland = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtstartdatum = new javax.swing.JLabel();
        btnAndraKnapp = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtprojekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtprojekt.setText("PROJEKT:");

        lblprojektnamn.setText("Namn");

        lblprojektchef.setText("Projektchef");

        lblkostnad.setText("Kostnad");

        lblstartdatum.setText("Startdatum");

        txtansvarig.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtansvarig.setText("ANSVARIG:");

        lblslutdatum.setText("Slutdatum");

        lblstatus.setText("Status");

        txtbeskrivning.addActionListener(this::txtbeskrivningActionPerformed);

        txtbudget.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtbudget.setText("KOSTNAD:");

        txtslutdatum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtslutdatum.setText("SLUTDATUM:");

        txtstatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtstatus.setText("STATUS:");

        txtland.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtland.setText("LAND:");

        lblland.setText("Land");

        jLabel1.setText("Om projektet");

        txtstartdatum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtstartdatum.setText("STARTDATUM:");

        btnAndraKnapp.setBackground(new java.awt.Color(1, 174, 217));
        btnAndraKnapp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAndraKnapp.setForeground(new java.awt.Color(255, 255, 255));
        btnAndraKnapp.setText("ÄNDRA UPPGIFTER");
        btnAndraKnapp.addActionListener(this::btnAndraKnappActionPerformed);

        btnTillbaka.setBackground(new java.awt.Color(249, 181, 18));
        btnTillbaka.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTillbaka.setForeground(new java.awt.Color(255, 255, 255));
        btnTillbaka.setText("TILLBAKA");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        jLabel2.setText("Information om projekt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbudget)
                            .addComponent(txtslutdatum)
                            .addComponent(txtstatus)
                            .addComponent(txtansvarig)
                            .addComponent(txtland)
                            .addComponent(txtstartdatum)
                            .addComponent(txtprojekt))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblkostnad)
                            .addComponent(lblstatus)
                            .addComponent(lblstartdatum)
                            .addComponent(lblslutdatum)
                            .addComponent(lblprojektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblprojektchef, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblland))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtbeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAndraKnapp)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprojekt)
                    .addComponent(lblprojektnamn)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtansvarig, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblprojektchef))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblkostnad)
                            .addComponent(txtbudget))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblstartdatum)
                            .addComponent(txtstartdatum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtslutdatum)
                            .addComponent(lblslutdatum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstatus)
                            .addComponent(lblstatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtland)
                            .addComponent(lblland))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addComponent(txtbeskrivning))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnAndraKnapp))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbeskrivningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbeskrivningActionPerformed

    private void btnAndraKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraKnappActionPerformed
        RedigeraProjekt projekt = new RedigeraProjekt(idb, inloggadAnvandare, projektId);
        projekt.setVisible(true);
    }//GEN-LAST:event_btnAndraKnappActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new ProjektInfo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndraKnapp;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblkostnad;
    private javax.swing.JLabel lblland;
    private javax.swing.JLabel lblprojektchef;
    private javax.swing.JLabel lblprojektnamn;
    private javax.swing.JLabel lblslutdatum;
    private javax.swing.JLabel lblstartdatum;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JLabel txtansvarig;
    private javax.swing.JTextField txtbeskrivning;
    private javax.swing.JLabel txtbudget;
    private javax.swing.JLabel txtland;
    private javax.swing.JLabel txtprojekt;
    private javax.swing.JLabel txtslutdatum;
    private javax.swing.JLabel txtstartdatum;
    private javax.swing.JLabel txtstatus;
    // End of variables declaration//GEN-END:variables
}
