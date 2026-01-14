/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import javax.swing.ImageIcon;
import oru.inf.InfDB;

/**
 *
 * @author user
 */
public class JFrameMeny extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JFrameMeny.class.getName());

    public JFrameMeny(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;

        initComponents();
        this.setLocationRelativeTo(null);

        lblanvandare.setText("Inloggad som " + inloggadAnvandare);
        HamtaNamn();
        sattBehorighet();

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/ngo_2024/bilder/sdg.png")
        );

        getContentPane().setBackground(Color.WHITE);

        btnminaprojekt.setVisible(false);
        if (KlassValAvRoll.arProjektchef(idb, inloggadAnvandare)) {
            /*<-- Endast projektchef kan se ''Mina projekt'' knappen*/
            btnminaprojekt.setVisible(true);
            btnminaprojekt.setEnabled(true);
        }
    }

    private void sattBehorighet() {
        /*<---- Metod som sätter behörigheten när användare öpggar in*/

        System.out.println("Sätter behörighet för: " + inloggadAnvandare);

        boolean arAdmin
                = KlassValAvRoll.arAdmin(idb, inloggadAnvandare);

        boolean arHandlaggare
                = KlassValAvRoll.arHandlaggare(idb, inloggadAnvandare);

        boolean arProjektchef
                = KlassValAvRoll.arProjektchef(idb, inloggadAnvandare);

        System.out.println("Handläggare: " + arHandlaggare);

    }

    private void HamtaNamn() {

        KlassAnvandare anv = new KlassAnvandare(idb, inloggadAnvandare);

        lblvalkommen.setText("Välkommen tillbaka " + anv.getFullNamn() + "!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        lblanvandare = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnProjekt = new javax.swing.JButton();
        btnminaprojekt = new javax.swing.JButton();
        btnhallbarhetsmal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnminprofil = new javax.swing.JButton();
        btnavdelning = new javax.swing.JButton();
        btnpartners = new javax.swing.JButton();
        btnland = new javax.swing.JButton();
        bildsdg = new javax.swing.JLabel();
        lblvalkommen = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblanvandare.setText("Användare");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Projekt"));

        btnProjekt.setBackground(new java.awt.Color(219, 20, 128));
        btnProjekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProjekt.setForeground(new java.awt.Color(255, 255, 255));
        btnProjekt.setText("Projekt");
        btnProjekt.addActionListener(this::btnProjektActionPerformed);

        btnminaprojekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnminaprojekt.setText("Mina projekt");
        btnminaprojekt.addActionListener(this::btnminaprojektActionPerformed);

        btnhallbarhetsmal.setBackground(new java.awt.Color(61, 176, 75));
        btnhallbarhetsmal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhallbarhetsmal.setForeground(new java.awt.Color(255, 255, 255));
        btnhallbarhetsmal.setText("Hållbarhetsmål");
        btnhallbarhetsmal.addActionListener(this::btnhallbarhetsmalActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhallbarhetsmal)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnminaprojekt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnhallbarhetsmal)
                    .addComponent(btnProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnminaprojekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Översikt"));
        jPanel3.setToolTipText("");

        btnminprofil.setBackground(new java.awt.Color(1, 174, 217));
        btnminprofil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnminprofil.setForeground(new java.awt.Color(255, 255, 255));
        btnminprofil.setText("Min profil");
        btnminprofil.setPreferredSize(new java.awt.Dimension(116, 23));
        btnminprofil.addActionListener(this::btnminprofilActionPerformed);

        btnavdelning.setBackground(new java.awt.Color(249, 181, 18));
        btnavdelning.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnavdelning.setForeground(new java.awt.Color(255, 255, 255));
        btnavdelning.setText("Avdelning");
        btnavdelning.addActionListener(this::btnavdelningActionPerformed);

        btnpartners.setBackground(new java.awt.Color(2, 85, 139));
        btnpartners.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnpartners.setForeground(new java.awt.Color(255, 255, 255));
        btnpartners.setText("Partners");
        btnpartners.setPreferredSize(new java.awt.Dimension(72, 23));
        btnpartners.addActionListener(this::btnpartnersActionPerformed);

        btnland.setBackground(new java.awt.Color(235, 28, 46));
        btnland.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnland.setForeground(new java.awt.Color(255, 255, 255));
        btnland.setText("Land");
        btnland.setPreferredSize(new java.awt.Dimension(116, 23));
        btnland.setVerifyInputWhenFocusTarget(false);
        btnland.addActionListener(this::btnlandActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnminprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpartners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnland, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnavdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnavdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnminprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnland, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnpartners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bildsdg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ngo_2024/bilder/sdg2.png"))); // NOI18N

        lblvalkommen.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblvalkommen.setText("Välkommen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bildsdg)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblanvandare, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblvalkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(bildsdg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblvalkommen, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblanvandare))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektActionPerformed

        JFrameProjekt projekt = new JFrameProjekt(idb, inloggadAnvandare);
        projekt.setVisible(true);
        /*<----Knapp som öppnar nya flik*/

    }//GEN-LAST:event_btnProjektActionPerformed

    private void btnpartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpartnersActionPerformed
        new JFramePartner(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnpartnersActionPerformed

    private void btnminprofilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminprofilActionPerformed
        JFrameMinProfil uppgifter = new JFrameMinProfil(idb, inloggadAnvandare);
        uppgifter.setVisible(true);
    }//GEN-LAST:event_btnminprofilActionPerformed

    private void btnavdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnavdelningActionPerformed
        JFrameAvdelning AvdJframe = new JFrameAvdelning(idb, inloggadAnvandare);
        AvdJframe.setVisible(true);
    }//GEN-LAST:event_btnavdelningActionPerformed

    private void btnhallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhallbarhetsmalActionPerformed
        new JFrameHallbarhetsmal(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnhallbarhetsmalActionPerformed

    private void btnlandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlandActionPerformed
        JFrameLand land = new JFrameLand(idb, inloggadAnvandare);
        land.setVisible(true);
    }//GEN-LAST:event_btnlandActionPerformed

    private void btnminaprojektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminaprojektActionPerformed
        JFrameProjektChefFonster tillgang = new JFrameProjektChefFonster(idb, inloggadAnvandare);
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
    private javax.swing.JLabel bildsdg;
    private javax.swing.JButton btnProjekt;
    private javax.swing.JButton btnavdelning;
    private javax.swing.JButton btnhallbarhetsmal;
    private javax.swing.JButton btnland;
    private javax.swing.JButton btnminaprojekt;
    private javax.swing.JButton btnminprofil;
    private javax.swing.JButton btnpartners;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblanvandare;
    private javax.swing.JLabel lblvalkommen;
    // End of variables declaration//GEN-END:variables
}
