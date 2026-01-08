/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.logging.Logger;
import java.util.logging.Level;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        txtandraprojektuppgifter.setText("Ändra projekt uppgifter");

        lblbeskrivning.setText("Beskrivning");
        lblbeskrivning.addActionListener(this::lblbeskrivningActionPerformed);

        txtbeskrivning.setText("Beskrivning");

        txtprojektnamn.setText("Projektnamn");

        lblprojektnamn.setText("Projektnamn");
        lblprojektnamn.addActionListener(this::lblprojektnamnActionPerformed);

        txtStatus.setText("Status");

        txtprioritet.setText("Prioritet");

        scrollprioritet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Låg", "Medel", "IHög" }));
        scrollprioritet.addActionListener(this::scrollprioritetActionPerformed);

        scrollstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pågående", "Avslutat", "Pausat" }));
        scrollstatus.setToolTipText("");
        scrollstatus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollstatus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtkostnad.setText("Kostnad");

        lblkostnad.setText("Kostnad");
        lblkostnad.addActionListener(this::lblkostnadActionPerformed);

        txtstartdatum.setText("Startdatum");

        txtslutdatum.setText("Slutdatum");

        btnspara.setText("Spara");
        btnspara.addActionListener(this::btnsparaActionPerformed);

        lblstartdatum.setText("Startdatum");

        lblslutdatum.setText("Slutdatum");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtprojektnamn)
                                    .addComponent(txtStatus)
                                    .addComponent(txtprioritet)
                                    .addComponent(txtbeskrivning)
                                    .addComponent(txtkostnad)
                                    .addComponent(txtstartdatum)
                                    .addComponent(txtslutdatum))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblbeskrivning)
                                    .addComponent(lblprojektnamn)
                                    .addComponent(scrollstatus, 0, 223, Short.MAX_VALUE)
                                    .addComponent(scrollprioritet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblkostnad)
                                    .addComponent(lblstartdatum)
                                    .addComponent(lblslutdatum)))
                            .addComponent(txtandraprojektuppgifter)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnspara)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtandraprojektuppgifter)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbeskrivning))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblprojektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprojektnamn))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtprioritet)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scrollstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollprioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkostnad)
                    .addComponent(lblkostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtstartdatum)
                    .addComponent(lblstartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtslutdatum)
                    .addComponent(lblslutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnspara)
                .addContainerGap(19, Short.MAX_VALUE))
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

        if (!Validering.ValideraDatum(nyttStartdatum)) {
            JOptionPane.showMessageDialog(this,
                    "Ogiltigt datum! Måste skrivas som ÅÅÅÅ-MM-DD",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validering.ValideraDatum(nyttSlutdatum)) {
            JOptionPane.showMessageDialog(this,
                    "Ogiltigt datum! Måste skrivas som ÅÅÅÅ-MM-DD",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sqlFraga
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
