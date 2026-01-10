/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.JOptionPane;

public class Uppgiftsforandrare extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private Anvandare anv;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Uppgiftsforandrare.class.getName());

    public Uppgiftsforandrare(InfDB idb, String inloggadAnvandare) {
        initComponents();

        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        anv = new Anvandare(idb, inloggadAnvandare);

        txtadress.setText(anv.getAdress());
        /*<--- Sätter fälten*/
        txttelefon.setText(anv.getTelefon());
        txtepost.setText(anv.getEpost());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtadress.setText("Adress");
        txtadress.addActionListener(this::txtadressActionPerformed);

        txtepost.setText("Epost");

        txttelefon.setText("Telefon");

        btnandra.setText("Ändra");
        btnandra.addActionListener(this::btnandraActionPerformed);

        lblandrauppgifter.setText("Ändra dina uppgifter");

        jLabel1.setText("Epostadress");

        jLabel2.setText("Adress");

        jLabel3.setText("Telefonnummer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblandrauppgifter)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtepost, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtadress, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99))))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnandra)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblandrauppgifter)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtepost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(btnandra)
                .addContainerGap(89, Short.MAX_VALUE))
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
        
        if (harFel){
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
            System.out.println("Uppdatering lyckades!");
            JOptionPane.showMessageDialog(this,
                    "Uppdatering lyckades!",
                    "Lyckad Uppdatering",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (InfException e) {
            System.out.println("Fel vid uppdatering: " + e.getMessage());
        }

    }//GEN-LAST:event_btnandraActionPerformed

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
