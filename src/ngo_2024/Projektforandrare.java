/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Projektforandrare extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(Projektforandrare.class.getName());

    private InfDB idb;
    private String InloggadAnvandare;
    private int pid;

    public Projektforandrare(InfDB idb, String InloggadAnvandare, int pid) {
        initComponents();
        this.InloggadAnvandare = InloggadAnvandare;
        this.idb = idb;
        this.pid = pid;

        try {
            /*F<--- yller alla fält*/
            ProjektKlass projekt = new ProjektKlass(idb, InloggadAnvandare, pid);

            lblbeskrivning.setText(projekt.getBeskrivning());
            lblprojektnamn.setText(projekt.getProjektnamn());
            lblkostnad.setText(projekt.getKostnad());
            scrollstatus.setSelectedItem(projekt.getStatus());
            scrollprioritet.setSelectedItem(projekt.getPrioritet());
            lblstartdatum.setText(projekt.getStartdatum());
            lblslutdatum.setText(projekt.getSlutdatum());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fel vid hämtning av projekt: " + e.getMessage());
        }
        
        getContentPane().setBackground(Color.WHITE);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtandraprojektuppgifter = new javax.swing.JLabel();
        lblbeskrivning = new javax.swing.JTextField();
        txtbeskrivning = new javax.swing.JLabel();
        txtprojektnamn = new javax.swing.JLabel();
        lblprojektnamn = new javax.swing.JTextField();
        txtStatus = new javax.swing.JLabel();
        txtprioritet = new javax.swing.JLabel();
        scrollprioritet = new javax.swing.JComboBox<>();
        scrollstatus = new javax.swing.JComboBox<>();
        txtkostnad = new javax.swing.JLabel();
        lblkostnad = new javax.swing.JTextField();
        txtstartdatum = new javax.swing.JLabel();
        txtslutdatum = new javax.swing.JLabel();
        btnspara = new javax.swing.JButton();
        lblstartdatum = new javax.swing.JTextField();
        lblslutdatum = new javax.swing.JTextField();
        btnTillbaka = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("jLabel1");

        txtandraprojektuppgifter.setText("Ändra projekt uppgifter");

        lblbeskrivning.setText("Beskrivning");
        lblbeskrivning.addActionListener(this::lblbeskrivningActionPerformed);

        txtbeskrivning.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtbeskrivning.setText("BESKRIVNING:");

        txtprojektnamn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtprojektnamn.setText("PROJEKTNAMN:");

        lblprojektnamn.setText("Projektnamn");
        lblprojektnamn.addActionListener(this::lblprojektnamnActionPerformed);

        txtStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtStatus.setText("STATUS:");

        txtprioritet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtprioritet.setText("PRIORITET:");

        scrollprioritet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Låg", "Medel", "Hög" }));
        scrollprioritet.addActionListener(this::scrollprioritetActionPerformed);

        scrollstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pågående", "Avslutat", "Pausat" }));
        scrollstatus.setToolTipText("");
        scrollstatus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollstatus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtkostnad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtkostnad.setText("KOSTNAD:");

        lblkostnad.setText("Kostnad");
        lblkostnad.addActionListener(this::lblkostnadActionPerformed);

        txtstartdatum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtstartdatum.setText("STARTDATUM:");

        txtslutdatum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtslutdatum.setText("SLUTDATUM:");

        btnspara.setBackground(new java.awt.Color(61, 176, 75));
        btnspara.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnspara.setForeground(new java.awt.Color(255, 255, 255));
        btnspara.setText("SPARA");
        btnspara.addActionListener(this::btnsparaActionPerformed);

        lblstartdatum.setText("Startdatum");

        lblslutdatum.setText("Slutdatum");

        btnTillbaka.setBackground(new java.awt.Color(249, 181, 18));
        btnTillbaka.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTillbaka.setForeground(new java.awt.Color(255, 255, 255));
        btnTillbaka.setText("TILLBAKA");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbeskrivning)
                            .addComponent(txtprojektnamn)
                            .addComponent(txtStatus)
                            .addComponent(txtprioritet)
                            .addComponent(txtkostnad)
                            .addComponent(txtstartdatum)
                            .addComponent(txtslutdatum)
                            .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblstartdatum, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblkostnad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollprioritet, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollstatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, 272, Short.MAX_VALUE)
                            .addComponent(lblprojektnamn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnspara, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbeskrivning, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblslutdatum)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtandraprojektuppgifter)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(0, 196, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtandraprojektuppgifter))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblbeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbeskrivning))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblprojektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtprojektnamn)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scrollprioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprioritet))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkostnad)
                    .addComponent(lblkostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblstartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstartdatum))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblslutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtslutdatum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnspara))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblbeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblbeskrivningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbeskrivningActionPerformed

    private void scrollprioritetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrollprioritetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollprioritetActionPerformed

    private void btnsparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsparaActionPerformed
        String nyttNamn = lblprojektnamn.getText();
        String nyKostnad = lblkostnad.getText();
        String nyBeskrivning = lblbeskrivning.getText();
        String nyttStartdatum = lblstartdatum.getText();
        String nyttSlutdatum = lblslutdatum.getText();

        boolean harFel = false;

        if (nyttNamn.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Projektnamn får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (nyBeskrivning.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Beskrivning får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (!Validering.ValideraDatum(nyttStartdatum)) {
            /*<--- Validerar*/
            JOptionPane.showMessageDialog(this,
                    "Ogiltigt datum! Måste skrivas som ÅÅÅÅ-MM-DD",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (!Validering.ValideraDatum(nyttSlutdatum)) {
            JOptionPane.showMessageDialog(this,
                    "Ogiltigt datum! Måste skrivas som ÅÅÅÅ-MM-DD",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }
        
        if (!Validering.ValideraKostnad(nyKostnad)) {
            JOptionPane.showMessageDialog(this,
                    "Kostnad får endast vara positivt och utan mellanrum",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }
        
        if (harFel) {
            return;
        }

        try {
            String sqlFraga /*<--- Hittar fälten i databasen och ändrar det till det nya*/
                    = "UPDATE projekt SET "
                    + "projektnamn = '" + nyttNamn.replace("'", "''") + "', "
                    + "kostnad = '" + nyKostnad.replace("'", "''") + "', "
                    + "beskrivning = '" + nyBeskrivning.replace("'", "''") + "', "
                    + "startdatum = '" + nyttStartdatum.replace("'", "''") + "', "
                    + "slutdatum = '" + nyttSlutdatum.replace("'", "''") + "' "
                    + "WHERE pid = " + pid;
            idb.update(sqlFraga);

            System.out.println("Uppdatering lyckades!");
            JOptionPane.showMessageDialog(this,
                    "Uppdatering lyckades!",
                    "Lyckad Uppdatering",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (InfException e) {
            System.out.println("Fel vid uppdatering: " + e.getMessage());
        }
    }//GEN-LAST:event_btnsparaActionPerformed

    private void lblprojektnamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblprojektnamnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblprojektnamnActionPerformed

    private void lblkostnadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblkostnadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblkostnadActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new Projektforandrare().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton btnspara;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField lblbeskrivning;
    private javax.swing.JTextField lblkostnad;
    private javax.swing.JTextField lblprojektnamn;
    private javax.swing.JTextField lblslutdatum;
    private javax.swing.JTextField lblstartdatum;
    private javax.swing.JComboBox<String> scrollprioritet;
    private javax.swing.JComboBox<String> scrollstatus;
    private javax.swing.JLabel txtStatus;
    private javax.swing.JLabel txtandraprojektuppgifter;
    private javax.swing.JLabel txtbeskrivning;
    private javax.swing.JLabel txtkostnad;
    private javax.swing.JLabel txtprioritet;
    private javax.swing.JLabel txtprojektnamn;
    private javax.swing.JLabel txtslutdatum;
    private javax.swing.JLabel txtstartdatum;
    // End of variables declaration//GEN-END:variables
}
