/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

public class LandUppgifter extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private String valtLandId;
    private javax.swing.JFrame huvudFonster;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LandUppgifter.class.getName());

    public LandUppgifter(InfDB idb, String inloggadAnvandare, String valtLandId, javax.swing.JFrame huvudFonster) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.valtLandId = valtLandId;
        this.huvudFonster = huvudFonster;
        initComponents();
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        //Rubrik för land
        getLandNamn();
        laggTillUppgifter();
        
        
    }
    
    private void getLandNamn() {
        try {
            HashMap<String, String> landNamn = idb.fetchRow("SELECT namn FROM land WHERE lid = '" + valtLandId + "'");
            if (landNamn != null) {
                landRubrik.setText(landNamn.get("namn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Få information om ett land
    private void laggTillUppgifter() {
        try {
            HashMap<String, String> landInfo = idb.fetchRow("SELECT * FROM land WHERE lid = '" + valtLandId + "'");
            if (landInfo != null) {
                txtSprak.setText(landInfo.get("sprak"));
                txtValuta.setText(landInfo.get("valuta"));
                txtTidszon.setText(landInfo.get("tidszon"));
                txtEkonomi.setText(landInfo.get("ekonomi"));
                txtPolitiskStruktur.setText(landInfo.get("politisk_struktur"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        landRubrik = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSprak = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValuta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTidszon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEkonomi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPolitiskStruktur = new javax.swing.JTextField();
        btnAndraUppgifter = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        landRubrik.setText("Landnamn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("SPRÅK");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("VALUTA");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("TIDSZON");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("EKONOMI");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("POLITISK STRUKTUR");

        btnAndraUppgifter.setBackground(new java.awt.Color(61, 176, 75));
        btnAndraUppgifter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAndraUppgifter.setForeground(new java.awt.Color(255, 255, 255));
        btnAndraUppgifter.setText("SPARA");
        btnAndraUppgifter.addActionListener(this::btnAndraUppgifterActionPerformed);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(landRubrik, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTidszon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(txtEkonomi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSprak, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtValuta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPolitiskStruktur, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAndraUppgifter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(landRubrik)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSprak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEkonomi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTidszon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPolitiskStruktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnAndraUppgifter))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapp för att ändra och uppdatera information om ett land
    private void btnAndraUppgifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraUppgifterActionPerformed
        //Hämta text
        String sprak = txtSprak.getText().trim();
        String valuta = txtValuta.getText().trim();
        String tidszon = txtTidszon.getText().trim();
        String ekonomi = txtEkonomi.getText().trim();
        String politiskStruktur = txtPolitiskStruktur.getText().trim();

        boolean harFel = false;

        if (!Validering.ValideraKostnad(valuta)) {
            JOptionPane.showMessageDialog(this,
                    "Valuta får endast vara positiva siffror utan mellanrum",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (sprak.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Språk får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (tidszon.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tidszon får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (ekonomi.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ekonomi får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (politiskStruktur.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Politisk struktur får inte vara tomt!",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            harFel = true;
        }

        if (harFel) {
            return;
        }

        //Uppdatera databasen
        try {
            String sqlFraga = "UPDATE land SET "
                    + "sprak = '" + sprak + "', "
                    + "valuta = '" + valuta + "', "
                    + "tidszon = '" + tidszon + "', "
                    + "ekonomi = '" + ekonomi + "', "
                    + "politisk_struktur = '" + politiskStruktur + "' "
                    + "WHERE lid = '" + valtLandId + "'";

            idb.update(sqlFraga);

            JOptionPane.showMessageDialog(this, "Land uppgifter uppdaterade");

            if (huvudFonster instanceof Land land) {
                land.getLandNamn();
            }

            this.dispose();
        } catch (InfException e) {
            System.out.println("Fel vid uppdatering av land uppgifter: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAndraUppgifterActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new LandUppgifter().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndraUppgifter;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel landRubrik;
    private javax.swing.JTextField txtEkonomi;
    private javax.swing.JTextField txtPolitiskStruktur;
    private javax.swing.JTextField txtSprak;
    private javax.swing.JTextField txtTidszon;
    private javax.swing.JTextField txtValuta;
    // End of variables declaration//GEN-END:variables
}
