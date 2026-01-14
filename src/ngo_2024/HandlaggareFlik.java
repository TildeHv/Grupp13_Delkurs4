/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class HandlaggareFlik extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private HashMap<String, Integer> projektMap = new HashMap<>();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HandlaggareFlik.class.getName());

    public HandlaggareFlik(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        initComponents();
        getContentPane().setBackground(Color.WHITE);

        //Lägg in tabell namn
        tabellHandlaggare.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"FÖRNAMN", "EFTERNAMN", "EPOST", "TELEFON", "ANSVARIGHETSOMRÅDE", "MENTOR"}
        ));

        fylldropdownprojekt();
        dropdownprojekt.addActionListener(e -> uppdateraHandlaggarTabell());
        uppdateraHandlaggarTabell();
        btntabort.addActionListener(e -> taBortHandlaggare());

        this.setLocationRelativeTo(null);
    }

    //Justera bredd på tabell
    public void justeraKolumnBredd() {
        tabellHandlaggare.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int kolumn = 0; kolumn < tabellHandlaggare.getColumnCount(); kolumn++) {
            int maxBredd = 50;
            for (int row = 0; row < tabellHandlaggare.getRowCount(); row++) {
                TableCellRenderer renderer = tabellHandlaggare.getCellRenderer(row, kolumn);
                Component comp = tabellHandlaggare.prepareRenderer(renderer, row, kolumn);
                maxBredd = Math.max(comp.getPreferredSize().width + 30, maxBredd);
            }
            tabellHandlaggare.getColumnModel().getColumn(kolumn).setPreferredWidth(maxBredd);
        }

        int bredd = 0;
        for (int i = 0; i < tabellHandlaggare.getColumnCount(); i++) {
            bredd += tabellHandlaggare.getColumnModel().getColumn(i).getPreferredWidth();
        }
        bredd += 50;

        this.setSize(bredd, this.getHeight());
    }

    //Fyll tabellen med information om handläggare
    private void fyllHandlaggareTabell() {
        try {
            DefaultTableModel modell = (DefaultTableModel) tabellHandlaggare.getModel();
            modell.setRowCount(0);

            String sqlFraga
                    = "SELECT a.fornamn, a.efternamn, a.epost, a.telefon, "
                    + "h.ansvarighetsomrade, "
                    + "(SELECT an.fornamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_fornamn, "
                    + "(SELECT an.efternamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_efternamn "
                    + "FROM anstalld a "
                    + "JOIN handlaggare h ON a.aid = h.aid";

            ArrayList<HashMap<String, String>> lista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> rad : lista) {
                String mentorNamn = "";
                if (rad.get("mentor_fornamn") != null) {
                    mentorNamn = rad.get("mentor_fornamn") + " " + rad.get("mentor_efternamn");
                }

                modell.addRow(new Object[]{
                    rad.get("fornamn"),
                    rad.get("efternamn"),
                    rad.get("epost"),
                    rad.get("telefon"),
                    rad.get("ansvarighetsomrade"),
                    mentorNamn
                });
            }

            justeraKolumnBredd();
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

    public void uppdateraHandlaggarTabell() {
        try {
            DefaultTableModel modell = (DefaultTableModel) tabellHandlaggare.getModel();
            modell.setRowCount(0);

            String valdProjekt = (String) dropdownprojekt.getSelectedItem();

            String sqlFraga;

            if (valdProjekt == null || valdProjekt.equals("Alla projekt")) {

                sqlFraga
                        = "SELECT a.fornamn, a.efternamn, a.epost, a.telefon, "
                        + "h.ansvarighetsomrade, "
                        + "(SELECT an.fornamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_fornamn, "
                        + "(SELECT an.efternamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_efternamn "
                        + "FROM anstalld a "
                        + "JOIN handlaggare h ON a.aid = h.aid";
            } else {

                int pid = projektMap.get(valdProjekt);
                sqlFraga
                        = "SELECT a.fornamn, a.efternamn, a.epost, a.telefon, "
                        + "h.ansvarighetsomrade, "
                        + "(SELECT an.fornamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_fornamn, "
                        + "(SELECT an.efternamn FROM anstalld an WHERE an.aid = h.mentor) AS mentor_efternamn "
                        + "FROM ans_proj ph "
                        + "JOIN handlaggare h ON ph.aid = h.aid "
                        + "JOIN anstalld a ON h.aid = a.aid "
                        + "WHERE ph.pid = " + pid;

            }
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> lista = idb.fetchRows(sqlFraga);

            if (lista != null) {
                for (HashMap<String, String> rad : lista) {
                    String mentorNamn = "";
                    if (rad.get("mentor_fornamn") != null) {
                        mentorNamn = rad.get("mentor_fornamn") + " " + rad.get("mentor_efternamn");
                    }
                    modell.addRow(new Object[]{
                        rad.get("fornamn"),
                        rad.get("efternamn"),
                        rad.get("epost"),
                        rad.get("telefon"),
                        rad.get("ansvarighetsomrade"),
                        mentorNamn
                    });
                }
            }

            justeraKolumnBredd();
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rubrikHandlaggare.setText("Handläggare i projekt");

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

        btnlaggtill.setBackground(new java.awt.Color(1, 174, 217));
        btnlaggtill.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlaggtill.setForeground(new java.awt.Color(255, 255, 255));
        btnlaggtill.setText("LÄGG TILL");
        btnlaggtill.addActionListener(this::btnlaggtillActionPerformed);

        btntabort.setBackground(new java.awt.Color(235, 28, 46));
        btntabort.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntabort.setForeground(new java.awt.Color(255, 255, 255));
        btntabort.setText("TA BORT");
        btntabort.addActionListener(this::btntabortActionPerformed);

        jButton1.setBackground(new java.awt.Color(249, 181, 18));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TILLBAKA");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rubrikHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dropdownprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnlaggtill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btntabort, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rubrikHandlaggare)
                    .addComponent(dropdownprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntabort)
                    .addComponent(btnlaggtill)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlaggtillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaggtillActionPerformed
        new LaggTillHandlaggare(idb, inloggadAnvandare, this).setVisible(true);
    }//GEN-LAST:event_btnlaggtillActionPerformed

    private void btntabortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntabortActionPerformed

    }//GEN-LAST:event_btntabortActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel rubrikHandlaggare;
    private javax.swing.JTable tabellHandlaggare;
    // End of variables declaration//GEN-END:variables
}
