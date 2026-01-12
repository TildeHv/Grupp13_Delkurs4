/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.JOptionPane;

public class HandlaggareFlik extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private HashMap<String, Integer> projektMap = new HashMap<>();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HandlaggareFlik.class.getName());

    public HandlaggareFlik(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        initComponents();

        //Lägg in tabell namn
        tabellHandlaggare.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Fornamn", "Efternamn", "Epost", "Telefon", "Ansvarighetsomrade", "Mentor"}
        ));

        fyllHandlaggareTabell();
        fylldropdownprojekt();
        dropdownprojekt.addActionListener(e -> uppdateraHandlaggarTabell());
        uppdateraHandlaggarTabell();
        btntabort.addActionListener(e -> taBortHandlaggare());
    }

    //Fyll tabellen med information om handläggare
    public void fyllHandlaggareTabell() {
        try {
            DefaultTableModel modell = (DefaultTableModel) tabellHandlaggare.getModel();
            modell.setRowCount(0);

            String sqlFraga = "SELECT anstalld.fornamn, anstalld.efternamn, anstalld.epost, "
                    + "anstalld.telefon, handlaggare.ansvarighetsomrade, handlaggare.mentor "
                    + "FROM anstalld "
                    + "JOIN handlaggare ON anstalld.aid = handlaggare.aid";

            ArrayList<HashMap<String, String>> lista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> rad : lista) {
                modell.addRow(new Object[]{
                    rad.get("fornamn"),
                    rad.get("efternamn"),
                    rad.get("epost"),
                    rad.get("telefon"),
                    rad.get("ansvarighetsomrade"),
                    rad.get("mentor")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fylldropdownprojekt() {
        try {
            dropdownprojekt.removeAllItems();
            dropdownprojekt.addItem("Alla projekt");

            String sqlFraga
                    = "SELECT p.projektnamn, p.pid "
                    + "FROM projekt p "
                    + "JOIN anstalld an ON p.projektchef = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'";

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);
            System.out.println("Projektlista: " + projektLista);

            if (projektLista != null) {
                for (HashMap<String, String> projekt : projektLista) {
                    String projektnamn = projekt.get("projektnamn");
                    Integer pid = Integer.parseInt(projekt.get("pid"));
                    dropdownprojekt.addItem(projektnamn);
                    projektMap.put(projektnamn, pid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uppdateraHandlaggarTabell() {
        try {
            DefaultTableModel modell = (DefaultTableModel) tabellHandlaggare.getModel();
            modell.setRowCount(0);

            String valdProjekt = (String) dropdownprojekt.getSelectedItem();

            String sqlFraga;

            if (valdProjekt == null || valdProjekt.equals("Alla projekt")) {

                sqlFraga
                        = "SELECT a.fornamn, a.efternamn, a.epost, "
                        + "a.telefon, h.ansvarighetsomrade, h.mentor "
                        + "FROM anstalld a "
                        + "JOIN handlaggare h ON a.aid = h.aid ";
            } else {

                int pid = projektMap.get(valdProjekt);
                sqlFraga
                        = "SELECT a.fornamn, a.efternamn, a.epost, a.telefon, h.ansvarighetsomrade, h.mentor "
                        + "FROM ans_proj ph "
                        + "JOIN handlaggare h ON ph.aid = h.aid "
                        + "JOIN anstalld a ON h.aid = a.aid "
                        + "WHERE ph.pid = " + pid;

            }
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> lista = idb.fetchRows(sqlFraga);

            if (lista != null) {
                for (HashMap<String, String> rad : lista) {

                    modell.addRow(new Object[]{
                        rad.get("fornamn"),
                        rad.get("efternamn"),
                        rad.get("epost"),
                        rad.get("telefon"),
                        rad.get("ansvarighetsomrade"),
                        rad.get("mentor")
                    });
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void taBortHandlaggare() {
        try {
            int valdRad = tabellHandlaggare.getSelectedRow();
            if (valdRad == -1) {
                JOptionPane.showMessageDialog(this,
                        "Välj en rad först1");
                return;
            }
            String projektNamn = (String) dropdownprojekt.getSelectedItem();
            if (projektNamn == null || projektNamn.equals("Alla projekt")) {
                JOptionPane.showMessageDialog(this,
                        "Välj ett projekt först!");
                return;
            }
            
            int svar = JOptionPane.showConfirmDialog(this,
                    "Är du säker på att du vill ta bort handläggaren från projektet?",
                    "Bekräfta bortagning",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            
            if (svar != JOptionPane.YES_OPTION) {
                return;
            }

            String epost = (String) tabellHandlaggare.getValueAt(valdRad, 2);

            int aid = Integer.parseInt(idb.fetchSingle(
                    "SELECT aid FROM anstalld WHERE epost  = '" + epost + "'"
            ));

            int pid = projektMap.get(projektNamn);

            String sqlFraga = "DELETE FROM ans_proj WHERE aid = " + aid + " AND pid = " + pid;
            idb.delete(sqlFraga);

            uppdateraHandlaggarTabell();

            JOptionPane.showMessageDialog(this,
                    "Handläggare borttagen från projekt!");

        } catch (InfException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Fel uppstod vid borttagning av handläggare");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rubrikHandlaggare = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabellHandlaggare = new javax.swing.JTable();
        dropdownprojekt = new javax.swing.JComboBox<>();
        btnlaggtill = new javax.swing.JButton();
        btntabort = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rubrikHandlaggare.setText("Handläggare");

        tabellHandlaggare.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabellHandlaggare);

        dropdownprojekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnlaggtill.setText("Lägg till");
        btnlaggtill.addActionListener(this::btnlaggtillActionPerformed);

        btntabort.setText("Ta bort");
        btntabort.addActionListener(this::btntabortActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rubrikHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropdownprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnlaggtill)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btntabort))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rubrikHandlaggare)
                    .addComponent(dropdownprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlaggtill)
                    .addComponent(btntabort))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlaggtillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaggtillActionPerformed
     new LaggTillHandlaggare(idb, inloggadAnvandare, this).setVisible(true);
    }//GEN-LAST:event_btnlaggtillActionPerformed

    private void btntabortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabortActionPerformed

    }//GEN-LAST:event_btntabortActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new HandlaggareFlik().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlaggtill;
    private javax.swing.JButton btntabort;
    private javax.swing.JComboBox<String> dropdownprojekt;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel rubrikHandlaggare;
    private javax.swing.JTable tabellHandlaggare;
    // End of variables declaration//GEN-END:variables
}
