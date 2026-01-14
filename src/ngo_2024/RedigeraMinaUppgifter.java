/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.JOptionPane;

public class RedigeraMinaUppgifter extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private KlassAnvandare anv;
    private javax.swing.JFrame huvudFonster;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RedigeraMinaUppgifter.class.getName());

    public RedigeraMinaUppgifter(InfDB idb, String inloggadAnvandare, javax.swing.JFrame huvudFonster) {
        initComponents();
        this.setLocationRelativeTo(null);

        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.huvudFonster = huvudFonster;
        anv = new KlassAnvandare(idb, inloggadAnvandare);

        txtadress.setText(anv.getAdress());
        /*<--- Sätter fälten*/
        txttelefon.setText(anv.getTelefon());
        txtepost.setText(anv.getEpost());

        getContentPane().setBackground(Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtadress = new javax.swing.JTextField();
        txtepost = new javax.swing.JTextField();
        txttelefon = new javax.swing.JTextField();
        btnandra = new javax.swing.JButton();
        lblandrauppgifter = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTillbaka = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtadress.setText("Adress");
        txtadress.addActionListener(this::txtadressActionPerformed);

        txtepost.setText("Epost");

        txttelefon.setText("Telefon");

        btnandra.setBackground(new java.awt.Color(61, 176, 75));
        btnandra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnandra.setForeground(new java.awt.Color(255, 255, 255));
        btnandra.setText("SPARA");
        btnandra.addActionListener(this::btnandraActionPerformed);

        lblandrauppgifter.setText("Ändra dina uppgifter");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("EPOST:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ADRESS:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("TELEFONNUMMER:");

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
                            .addComponent(jLabel2)
                            .addComponent(txtadress, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtepost, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnandra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblandrauppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblandrauppgifter)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtadress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtepost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnandra))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtadressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtadressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtadressActionPerformed

    private void btnandraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnandraActionPerformed

        String epost = txtepost.getText().trim();
        String telefon = txttelefon.getText().trim();
        String adress = txtadress.getText().trim();

        boolean harFel = false;

        if (!Validering.ValideraEpost(epost)) {
            /* <-- Kollar valideringen*/
            JOptionPane.showMessageDialog(this,
                    "Ogiltig E-postadress",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (!Validering.ValideraTelefon(telefon)) {
            JOptionPane.showMessageDialog(this,
                    "Ogiltigt Telefonnummer",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (!Validering.ValideraAdress(adress)) {
            JOptionPane.showMessageDialog(this,
                    "Ogiltig adress",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (harFel) {
            return;

        }

        try {
            /*<--- Kollar sqlfrågan och uppdaterar raderna i databasen mot motsvarande rader där eposten stämmer mot inloggadanvändare*/
            String sqlFraga
                    = "UPDATE anstalld SET "
                    + "adress = '" + txtadress.getText() + "', "
                    + "telefon = '" + txttelefon.getText() + "', "
                    + "epost = '" + txtepost.getText() + "' "
                    + "WHERE epost = '" + inloggadAnvandare + "'";

            idb.update(sqlFraga);
            if (huvudFonster instanceof JFrameMinProfil minProfil) {
                minProfil.fyllUppgifter();

            }
            System.out.println("Uppdatering lyckades!");
            JOptionPane.showMessageDialog(this,
                    "Uppdatering lyckades!",
                    "Lyckad Uppdatering",
                    JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();

        } catch (InfException e) {
            System.out.println("Fel vid uppdatering: " + e.getMessage());
        }


    }//GEN-LAST:event_btnandraActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

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
        // java.awt.EventQueue.invokeLater(() -> new Uppgiftsforandrare().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton btnandra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblandrauppgifter;
    private javax.swing.JTextField txtadress;
    private javax.swing.JTextField txtepost;
    private javax.swing.JTextField txttelefon;
    // End of variables declaration//GEN-END:variables
}
