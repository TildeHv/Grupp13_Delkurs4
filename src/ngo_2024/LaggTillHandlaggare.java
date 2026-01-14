/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;

/**
 *
 * @author Tilde
 */
public class LaggTillHandlaggare extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private javax.swing.JFrame huvudFonster;
    private HashMap<String, String> projektMap = new HashMap<>();
    private HashMap<String, String> handlaggareMap = new HashMap<>();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LaggTillHandlaggare.class.getName());

    public LaggTillHandlaggare(InfDB idb, String inloggadAnvandare, javax.swing.JFrame huvudFonster) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.huvudFonster = huvudFonster;
        initComponents();
        this.setLocationRelativeTo(null);

        getContentPane().setBackground(Color.WHITE);

        fyllComboBoxar();
    }

    //Lägg till handläggare i projekt
    private void laggTillUppgifter() {
        try {
            String projektVal = (String) filterProjekt.getSelectedItem();
            String handlaggareVal = (String) filterHandlaggare.getSelectedItem();

            if (projektVal == null || handlaggareVal == null) {
                JOptionPane.showMessageDialog(this, "Välj projekt och handläggare");
                return;
            }

            int projektId = Integer.parseInt(projektMap.get(projektVal));
            int handlaggareId = Integer.parseInt(handlaggareMap.get(handlaggareVal));

            if (idb.fetchSingle("SELECT pid FROM projekt where pid = " + projektId) == null) {
                JOptionPane.showMessageDialog(this, "Projektet finns inte");
                return;
            }

            if (idb.fetchSingle("SELECT aid FROM handlaggare where aid = " + handlaggareId) == null) {
                JOptionPane.showMessageDialog(this, "Handläggaren finns inte");
                return;
            }

            String check
                    = "SELECT * FROM ans_proj WHERE pid = " + projektId
                    + " AND aid = " + handlaggareId;

            if (idb.fetchSingle(check) != null) {
                JOptionPane.showMessageDialog(this,
                        "Handläggaren är redan kopplad till detta projekt");
                return;
            }

            String sqlFraga = "INSERT INTO ans_proj (pid, aid) VALUES ("
                    + projektId + ", " + handlaggareId + ")";

            idb.insert(sqlFraga);
            JOptionPane.showMessageDialog(this, "Ny handläggare tillagd till projekt");

            if (huvudFonster instanceof JFrameHandlaggare handlaggareFlik) {
                handlaggareFlik.uppdateraHandlaggarTabell();
            }

            this.dispose();

        } catch (Exception e) {
            System.out.println("Fel vid skapande av handläggare: " + e.getMessage());
        }
    }

    //Fyll comboboxar med val
    private void fyllComboBoxar() {
        try {
            filterProjekt.removeAllItems();
            projektMap.clear();

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(
                    "SELECT projekt.pid, projekt.projektnamn "
                    + "FROM projekt "
                    + "JOIN anstalld ON projekt.projektchef = anstalld.aid "
                    + "WHERE anstalld.epost = '" + inloggadAnvandare + "'"
                    + "ORDER BY projekt.projektnamn"
            );

            if (projektLista != null) {
                for (HashMap<String, String> p : projektLista) {
                    String id = p.get("pid");
                    String namn = p.get("projektnamn");
                    filterProjekt.addItem(namn);
                    projektMap.put(namn, id);
                }
            }

            filterHandlaggare.removeAllItems();
            handlaggareMap.clear();

            ArrayList<HashMap<String, String>> handlaggareLista = idb.fetchRows("SELECT anstalld.aid, fornamn, efternamn FROM anstalld JOIN handlaggare ON anstalld.aid = handlaggare.aid ORDER BY fornamn, efternamn");

            if (handlaggareLista != null) {
                for (HashMap<String, String> h : handlaggareLista) {
                    String id = h.get("aid");
                    String namn = h.get("fornamn") + " " + h.get("efternamn");
                    filterHandlaggare.addItem(namn);
                    handlaggareMap.put(namn, id);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rubrikHandlaggare = new javax.swing.JLabel();
        btnSpara = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        filterProjekt = new javax.swing.JComboBox<>();
        filterHandlaggare = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rubrikHandlaggare.setText("Lägg till handläggare i projekt");

        btnSpara.setBackground(new java.awt.Color(61, 176, 75));
        btnSpara.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSpara.setForeground(new java.awt.Color(255, 255, 255));
        btnSpara.setText("SPARA");
        btnSpara.addActionListener(this::btnSparaActionPerformed);

        btnTillbaka.setBackground(new java.awt.Color(249, 181, 18));
        btnTillbaka.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTillbaka.setForeground(new java.awt.Color(255, 255, 255));
        btnTillbaka.setText("TILLBAKA");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        filterProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        filterHandlaggare.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("PROJEKT");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("HANDLÄGGARE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSpara, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rubrikHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filterHandlaggare, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE)
                                .addComponent(filterProjekt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rubrikHandlaggare)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTillbaka)
                    .addComponent(btnSpara))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaActionPerformed
        laggTillUppgifter();
    }//GEN-LAST:event_btnSparaActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new LaggTillHandlaggare().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSpara;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> filterHandlaggare;
    private javax.swing.JComboBox<String> filterProjekt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel rubrikHandlaggare;
    // End of variables declaration//GEN-END:variables
}
