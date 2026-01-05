/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author Tilde
 */
public class ProjektInfo extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private String projektnamn;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektInfo.class.getName());

    public ProjektInfo(InfDB idb, String projektnamn, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.projektnamn = projektnamn;
        initComponents();
        
        txtprojekt.setText(projektnamn);
        
        HamtaProjektinfo();
    }

    private void HamtaProjektinfo() {
      
     ProjektKlass projekt = new ProjektKlass(idb, inloggadAnvandare, projektnamn);
                            
                lblprojektnamn.setText(projekt.getProjektnamn());
                lblprojektchef.setText(projekt.getProjektchef());
                lblstartdatum.setText(projekt.getStartdatum());
                lblslutdatum.setText(projekt.getSlutdatum());
                lblbudget.setText(projekt.getKostnad());   
                lblstatus.setText(projekt.getStatus());
                txtbeskrivning.setText(projekt.getBeskrivning());
                lblland.setText(projekt.getLand());
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
        lblbudget = new javax.swing.JLabel();
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

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtprojekt.setText("Projekt");

        lblprojektnamn.setText("Namn");

        lblprojektchef.setText("Projektchef");

        lblbudget.setText("Budget");

        lblstartdatum.setText("Startdatum");

        txtansvarig.setText("Ansvarig");

        lblslutdatum.setText("Slutdatum");

        lblstatus.setText("Status");

        txtbeskrivning.setText("jTextField1");
        txtbeskrivning.addActionListener(this::txtbeskrivningActionPerformed);

        txtbudget.setText("Budget");

        txtslutdatum.setText("Slutdatum");

        txtstatus.setText("Status");

        txtland.setText("Land");

        lblland.setText("Land");

        jLabel1.setText("Om projektet");

        txtstartdatum.setText("Startdatum");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbudget)
                    .addComponent(txtslutdatum)
                    .addComponent(txtstatus)
                    .addComponent(txtansvarig)
                    .addComponent(txtprojekt)
                    .addComponent(txtland)
                    .addComponent(txtstartdatum))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblprojektnamn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(lblland))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblbudget)
                            .addComponent(lblstatus)
                            .addComponent(lblstartdatum)
                            .addComponent(lblslutdatum)
                            .addComponent(lblprojektchef, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(txtbeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprojekt)
                    .addComponent(lblprojektnamn)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtansvarig, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblprojektchef))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblbudget)
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
                            .addComponent(lblland)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtbeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbeskrivningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbeskrivningActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblbudget;
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
