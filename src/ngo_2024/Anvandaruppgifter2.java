/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

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
    }

    private void fyllUppgifter() { // Tar getters från Användare-klassen och sätter namn på fälten

        Anvandare anv = new Anvandare(idb, inloggadAnvandare);

        lblanvandarnamn.setText(anv.getFullNamn());
        lbltelefon.setText(anv.getTelefon());
        lblepost.setText(anv.getEpost());
        lblavdelning.setText("Avdelning: " + anv.getAvdelning());
        lblaid.setText("AID: " + anv.getAid());
        lbladress.setText(anv.getAdress());
        lblanstallningsdatum.setText("Anställningsdatum: " + anv.getAnstallningsdatum());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblanvandarnamn.setText("Anvandarnamn");

        lbladress.setText("Adress");

        lblepost.setText("Epostadress");

        lbltelefon.setText("Telefonnummer");

        lblaid.setText("AID");

        lblanstallningsdatum.setText("Anställningsdatum");

        lblavdelning.setText("Avdelning");

        jButton1.setText("Ändra uppgifter");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblanvandarnamn)
                            .addComponent(lblanstallningsdatum)
                            .addComponent(lblepost)
                            .addComponent(lbladress)
                            .addComponent(lbltelefon)
                            .addComponent(lblaid)
                            .addComponent(lblavdelning))
                        .addGap(170, 170, 170))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblanvandarnamn)
                .addGap(18, 18, 18)
                .addComponent(lbladress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblepost)
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
    private javax.swing.JLabel lbladress;
    private javax.swing.JLabel lblaid;
    private javax.swing.JLabel lblanstallningsdatum;
    private javax.swing.JLabel lblanvandarnamn;
    private javax.swing.JLabel lblavdelning;
    private javax.swing.JLabel lblepost;
    private javax.swing.JLabel lbltelefon;
    // End of variables declaration//GEN-END:variables
}
