/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;

/**
 *
 * @author user
 */
public class Meny extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Meny.class.getName());

    public Meny(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;

        initComponents();
        lblanvandare.setText("Välkommen " + inloggadAnvandare);
        sattBehorighet();

        btnminaprojekt.setVisible(false);
        if (ValAvRoll.arProjektchef(idb, inloggadAnvandare)) {
            /*<-- Endast projektchef kan se ''Mina projekt'' knappen*/
            btnminaprojekt.setVisible(true);
            btnminaprojekt.setEnabled(true);
        }
    }

    private void sattBehorighet() {
        /*<---- Metod som sätter behörigheten när användare öpggar in*/

        System.out.println("Sätter behörighet för: " + inloggadAnvandare);

        boolean arAdmin
                = ValAvRoll.arAdmin(idb, inloggadAnvandare);

        boolean arHandlaggare
                = ValAvRoll.arHandlaggare(idb, inloggadAnvandare);

        boolean arProjektchef
                = ValAvRoll.arProjektchef(idb, inloggadAnvandare);

        System.out.println("Handläggare: " + arHandlaggare);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblanvandare = new javax.swing.JLabel();
        btnProjekt = new javax.swing.JButton();
        btnpartners = new javax.swing.JButton();
        btnminprofil = new javax.swing.JButton();
        btnavdelning = new javax.swing.JButton();
        btnhallbarhetsmal = new javax.swing.JButton();
        btnland = new javax.swing.JButton();
        btnminaprojekt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblanvandare.setText("Användare");

        btnProjekt.setBackground(new java.awt.Color(219, 20, 128));
        btnProjekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProjekt.setForeground(new java.awt.Color(255, 255, 255));
        btnProjekt.setText("Projekt");
        btnProjekt.addActionListener(this::btnProjektActionPerformed);

        btnpartners.setBackground(new java.awt.Color(2, 85, 139));
        btnpartners.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnpartners.setForeground(new java.awt.Color(255, 255, 255));
        btnpartners.setText("Partners");
        btnpartners.setPreferredSize(new java.awt.Dimension(72, 23));
        btnpartners.addActionListener(this::btnpartnersActionPerformed);

        btnminprofil.setBackground(new java.awt.Color(1, 174, 217));
        btnminprofil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnminprofil.setForeground(new java.awt.Color(255, 255, 255));
        btnminprofil.setText("Min profil");
        btnminprofil.addActionListener(this::btnminprofilActionPerformed);

        btnavdelning.setBackground(new java.awt.Color(249, 181, 18));
        btnavdelning.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnavdelning.setForeground(new java.awt.Color(255, 255, 255));
        btnavdelning.setText("Avdelning");
        btnavdelning.addActionListener(this::btnavdelningActionPerformed);

        btnhallbarhetsmal.setBackground(new java.awt.Color(61, 176, 75));
        btnhallbarhetsmal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhallbarhetsmal.setForeground(new java.awt.Color(255, 255, 255));
        btnhallbarhetsmal.setText("Hållbarhetsmål");
        btnhallbarhetsmal.addActionListener(this::btnhallbarhetsmalActionPerformed);

        btnland.setBackground(new java.awt.Color(235, 28, 46));
        btnland.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnland.setForeground(new java.awt.Color(255, 255, 255));
        btnland.setText("Land");
        btnland.addActionListener(this::btnlandActionPerformed);

        btnminaprojekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnminaprojekt.setText("Mina projekt");
        btnminaprojekt.addActionListener(this::btnminaprojektActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblanvandare, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnminaprojekt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(btnminprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnavdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnpartners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhallbarhetsmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnland, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblanvandare)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnpartners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnminprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnavdelning)
                    .addComponent(btnhallbarhetsmal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProjekt)
                    .addComponent(btnland))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnminaprojekt)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektActionPerformed

        ProjektFlik projekt = new ProjektFlik(idb, inloggadAnvandare);
        projekt.setVisible(true);
        /*<----Knapp som öppnar nya flik*/

    }//GEN-LAST:event_btnProjektActionPerformed

    private void btnpartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpartnersActionPerformed
        new PartnersFonster(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnpartnersActionPerformed

    private void btnminprofilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminprofilActionPerformed
        Anvandaruppgifter2 uppgifter = new Anvandaruppgifter2(idb, inloggadAnvandare);
        uppgifter.setVisible(true);
    }//GEN-LAST:event_btnminprofilActionPerformed

    private void btnavdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnavdelningActionPerformed
        AvdelningJframe AvdJframe = new AvdelningJframe(idb, inloggadAnvandare);
        AvdJframe.setVisible(true);
    }//GEN-LAST:event_btnavdelningActionPerformed

    private void btnhallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhallbarhetsmalActionPerformed
        new Hallbarhetsmal(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnhallbarhetsmalActionPerformed

    private void btnlandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlandActionPerformed
        Land land = new Land(idb, inloggadAnvandare);
        land.setVisible(true);
    }//GEN-LAST:event_btnlandActionPerformed

    private void btnminaprojektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminaprojektActionPerformed
        ProjektChefTillgang tillgang = new ProjektChefTillgang(idb, inloggadAnvandare);
        tillgang.setVisible(true);
    }//GEN-LAST:event_btnminaprojektActionPerformed

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
        // java.awt.EventQueue.invokeLater(() -> new Meny().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProjekt;
    private javax.swing.JButton btnavdelning;
    private javax.swing.JButton btnhallbarhetsmal;
    private javax.swing.JButton btnland;
    private javax.swing.JButton btnminaprojekt;
    private javax.swing.JButton btnminprofil;
    private javax.swing.JButton btnpartners;
    private javax.swing.JLabel lblanvandare;
    // End of variables declaration//GEN-END:variables
}
