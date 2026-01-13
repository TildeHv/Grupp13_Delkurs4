/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import oru.inf.InfDB;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
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
        
        getContentPane().setLayout(null);
        ImageIcon icon = new ImageIcon(
        getClass().getResource("/ngo_2024/bilder/bild10.png")
        );
        Image img = icon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        lblbild10.setIcon(new ImageIcon(img));

        getContentPane().setBackground(Color.WHITE);
  
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnminprofil = new javax.swing.JButton();
        btnavdelning = new javax.swing.JButton();
        btnpartners = new javax.swing.JButton();
        btnland = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnProjekt = new javax.swing.JButton();
        btnhallbarhetsmal = new javax.swing.JButton();
        btnminaprojekt = new javax.swing.JButton();
        lblbild10 = new javax.swing.JLabel();
        lblanvandare = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Administration"));

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
        btnland.addActionListener(this::btnlandActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnminprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnavdelning, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnland, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnpartners, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnminprofil)
                    .addComponent(btnpartners, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnavdelning)
                    .addComponent(btnland))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 310, 120));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Projekt"));

        btnProjekt.setBackground(new java.awt.Color(219, 20, 128));
        btnProjekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProjekt.setForeground(new java.awt.Color(255, 255, 255));
        btnProjekt.setText("Projekt");
        btnProjekt.addActionListener(this::btnProjektActionPerformed);

        btnhallbarhetsmal.setBackground(new java.awt.Color(61, 176, 75));
        btnhallbarhetsmal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnhallbarhetsmal.setForeground(new java.awt.Color(255, 255, 255));
        btnhallbarhetsmal.setText("Hållbarhetsmål");
        btnhallbarhetsmal.addActionListener(this::btnhallbarhetsmalActionPerformed);

        btnminaprojekt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnminaprojekt.setText("Mina projekt");
        btnminaprojekt.addActionListener(this::btnminaprojektActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnhallbarhetsmal)
                .addGap(21, 21, 21))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnminaprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProjekt)
                    .addComponent(btnhallbarhetsmal))
                .addGap(18, 18, 18)
                .addComponent(btnminaprojekt)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 310, 120));

        lblbild10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ngo_2024/bilder/bild10.png"))); // NOI18N
        lblbild10.setMaximumSize(new java.awt.Dimension(100, 100));
        lblbild10.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel1.add(lblbild10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 330, 70));

        lblanvandare.setText("Användare");
        jPanel1.add(lblanvandare, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektActionPerformed

        ProjektFlik projekt = new ProjektFlik(idb, inloggadAnvandare);
        projekt.setVisible(true);
        /*<----Knapp som öppnar nya flik*/

    }//GEN-LAST:event_btnProjektActionPerformed

    private void btnhallbarhetsmalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhallbarhetsmalActionPerformed
        new Hallbarhetsmal(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnhallbarhetsmalActionPerformed

    private void btnminaprojektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminaprojektActionPerformed
        ProjektChefTillgang tillgang = new ProjektChefTillgang(idb, inloggadAnvandare);
        tillgang.setVisible(true);
    }//GEN-LAST:event_btnminaprojektActionPerformed

    private void btnlandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlandActionPerformed
        Land land = new Land(idb, inloggadAnvandare);
        land.setVisible(true);
    }//GEN-LAST:event_btnlandActionPerformed

    private void btnpartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpartnersActionPerformed
        new PartnersFonster(idb, inloggadAnvandare).setVisible(true);
    }//GEN-LAST:event_btnpartnersActionPerformed

    private void btnavdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnavdelningActionPerformed
        AvdelningJframe AvdJframe = new AvdelningJframe(idb, inloggadAnvandare);
        AvdJframe.setVisible(true);
    }//GEN-LAST:event_btnavdelningActionPerformed

    private void btnminprofilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnminprofilActionPerformed
        Anvandaruppgifter2 uppgifter = new Anvandaruppgifter2(idb, inloggadAnvandare);
        uppgifter.setVisible(true);
    }//GEN-LAST:event_btnminprofilActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblanvandare;
    private javax.swing.JLabel lblbild10;
    // End of variables declaration//GEN-END:variables
}
