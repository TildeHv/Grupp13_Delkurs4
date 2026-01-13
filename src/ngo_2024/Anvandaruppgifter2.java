/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import oru.inf.InfDB;

public class Anvandaruppgifter2 extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Anvandaruppgifter2.class.getName());

    public Anvandaruppgifter2(InfDB idb, String inloggadAnvandare) {
        initComponents();

        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        fyllUppgifter();

        getContentPane().setBackground(Color.WHITE);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/ngo_2024/bilder/bild1.png"));
        
        int targetWidth = 80;
        int targetHeight = (originalIcon.getIconHeight() * targetWidth) / originalIcon.getIconWidth();

        Image scaledImage = originalIcon.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        lblBild.setIcon(new ImageIcon(scaledImage));
        

        setResizable(false);
    }

    private void fyllUppgifter() { // Tar getters från Användare-klassen och sätter namn på fälten

        Anvandare anv = new Anvandare(idb, inloggadAnvandare);

        lblanvandarnamn.setText(anv.getFullNamn());
        lbltelefon.setText(anv.getTelefon());
        lblepost.setText(anv.getEpost());
        lblavdelning.setText("AVDELNING: " + anv.getAvdelning());
        lblaid.setText("AID: " + anv.getAid());
        lbladress.setText(anv.getAdress());
        lblanstallningsdatum.setText("ANSTÄLLNINGSDATUM: " + anv.getAnstallningsdatum());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblanvandarnamn = new javax.swing.JLabel();
        lbladress = new javax.swing.JLabel();
        lblepost = new javax.swing.JLabel();
        lbltelefon = new javax.swing.JLabel();
        lblaid = new javax.swing.JLabel();
        lblanstallningsdatum = new javax.swing.JLabel();
        lblavdelning = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        lblBild = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblanvandarnamn.setText("Anvandarnamn");

        lbladress.setText("Adress");

        lblepost.setText("Epostadress");

        lbltelefon.setText("Telefonnummer");

        lblaid.setText("AID");

        lblanstallningsdatum.setText("Anställningsdatum");

        lblavdelning.setText("Avdelning");

        jButton1.setBackground(new java.awt.Color(1, 174, 217));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ÄNDRA UPPGIFTER");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        btnTillbaka.setBackground(new java.awt.Color(249, 181, 18));
        btnTillbaka.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTillbaka.setForeground(new java.awt.Color(255, 255, 255));
        btnTillbaka.setText("TILLBAKA");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        lblBild.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblanvandarnamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblepost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbltelefon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblanstallningsdatum, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(lblavdelning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbladress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblBild, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblanvandarnamn)
                        .addGap(18, 18, 18)
                        .addComponent(lbladress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblepost))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblBild, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbltelefon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblaid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblanstallningsdatum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblavdelning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(jButton1))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Uppgiftsforandrare uppgifter = new Uppgiftsforandrare(idb, inloggadAnvandare);
        uppgifter.setVisible(true); //Öppnar en ny uppgiftsförändrare baserat på den inloggade användaren
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        // tillbakaknapp
        dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("MAIN");
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
        //java.awt.EventQueue.invokeLater(() -> new Anvandaruppgifter2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblBild;
    private javax.swing.JLabel lbladress;
    private javax.swing.JLabel lblaid;
    private javax.swing.JLabel lblanstallningsdatum;
    private javax.swing.JLabel lblanvandarnamn;
    private javax.swing.JLabel lblavdelning;
    private javax.swing.JLabel lblepost;
    private javax.swing.JLabel lbltelefon;
    // End of variables declaration//GEN-END:variables
}
