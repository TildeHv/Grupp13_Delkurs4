/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import java.awt.Color;

public class ProjektFlik extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private int projektId;
    private String aktuellSql;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektFlik.class.getName());

    public ProjektFlik(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        initComponents();
        btnTillbaka.setBackground(new Color(249, 181, 18));

        projInfoKnapp.setEnabled(false);

        skapaProjektTabell();

        //Lägg in text i status filter
        filterBox.removeAllItems();
        filterBox.addItem("Alla statusar");
        filterBox.addItem("Planerat");
        filterBox.addItem("Pågående");
        filterBox.addItem("Avslutat");

        aktuellSql = getAnstalldSql();
        filtreraDatum();
        addTabellLyssnare(tabellMinaProjekt);
        addTabellLyssnare(tabellAllaProjekt);
        addChangeListener();
    }

    //Skapar tabell för projekten
    private void skapaProjektTabell() {
        tabellMinaProjekt.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"pid", "Projektnamn", "Status", "Prioritet", "startdatum", "slutdatum"}
        ));

        tabellAllaProjekt.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"pid", "Projektnamn", "Status", "Prioritet", "startdatum", "slutdatum"}
        ));

        Calendar cal = Calendar.getInstance();
        Date idag = cal.getTime();
        cal.add(Calendar.YEAR, -3);
        Date arTillbaka = cal.getTime();

        SpinnerDateModel startModel
                = new SpinnerDateModel(arTillbaka, null, idag, Calendar.DAY_OF_MONTH);
        SpinnerDateModel slutModel
                = new SpinnerDateModel(idag, null, idag, Calendar.DAY_OF_MONTH);

        startDatumSpinner.setModel(startModel);
        slutDatumSpinner.setModel(slutModel);

        startDatumSpinner.setEditor(
                new JSpinner.DateEditor(startDatumSpinner, "yyyy-MM-dd"));
        slutDatumSpinner.setEditor(
                new JSpinner.DateEditor(slutDatumSpinner, "yyyy-MM-dd"));

        startDatumSpinner.addChangeListener(e -> filtreraDatum());
        slutDatumSpinner.addChangeListener(e -> filtreraDatum());

        tpProjekt.setTitleAt(0, "Mina Projekt");
        tpProjekt.setTitleAt(1, "Alla Projekt");
    }

    //Sql som visar projekt kopplade till den inloggade anställda
    public String getAnstalldSql() {
        return "SELECT DISTINCT projekt.* "
                + "FROM projekt "
                + "LEFT JOIN ans_proj ON projekt.pid = ans_proj.pid "
                + "LEFT JOIN anstalld ON ans_proj.aid = anstalld.aid "
                + "WHERE (anstalld.epost = '" + inloggadAnvandare + "' "
                + "OR projekt.projektchef = (SELECT aid FROM anstalld "
                + "WHERE epost = '" + inloggadAnvandare + "'))";
    }

    //Sql som visar alla projekt som är kopplade till den inloggade anställdas avdelning
    public String getAvdelningsProjektSql() {
        return "SELECT DISTINCT projekt.* "
                + "FROM projekt "
                + "LEFT JOIN ans_proj ON projekt.pid = ans_proj.pid "
                + "LEFT JOIN anstalld ON ans_proj.aid = anstalld.aid "
                + "WHERE (anstalld.avdelning = (SELECT avdelning FROM anstalld WHERE epost = '" + inloggadAnvandare + "') "
                + "OR projekt.projektchef IN (SELECT aid FROM anstalld "
                + "WHERE avdelning = (SELECT avdelning FROM anstalld "
                + "WHERE epost = '" + inloggadAnvandare + "')))";
    }

    //Hämta och lägg in projektinformation i tabellen
    public void getProjektInfo(String sqlFraga) {
        try {
            int valtIndex = tpProjekt.getSelectedIndex();
            DefaultTableModel modell = null;

            if (valtIndex == 0) {
                modell = (DefaultTableModel) tabellMinaProjekt.getModel();
            } else if (valtIndex == 1) {
                modell = (DefaultTableModel) tabellAllaProjekt.getModel();
            }
            modell.setRowCount(0);

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> projekt : projektLista) {
                modell.addRow(new Object[]{
                    projekt.get("pid"),
                    projekt.get("projektnamn"),
                    projekt.get("status"),
                    projekt.get("prioritet"),
                    projekt.get("startdatum"),
                    projekt.get("slutdatum")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lägg till tabell lyssnare
    private void addTabellLyssnare(JTable table) {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int rad = table.getSelectedRow();
                if (rad >= 0) {
                    projektId = Integer.parseInt(
                            table.getValueAt(rad, 0).toString()
                    );
                    projInfoKnapp.setEnabled(true);
                }
            }
        });
    }

    private void addChangeListener() {
        tpProjekt.addChangeListener(e -> {
            int valtIndex = tpProjekt.getSelectedIndex();

            if (valtIndex == 0) {
                aktuellSql = getAnstalldSql();
            } else if (valtIndex == 1) {
                aktuellSql = getAvdelningsProjektSql();
            }

            filtreraDatum();
        });
    }

    //Filtrera projekt på datum
    private void filtreraDatum() {
        if (aktuellSql == null) {
            return;
        }

        Date from = (Date) startDatumSpinner.getValue();
        Date to = (Date) slutDatumSpinner.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String sql = aktuellSql
                + " AND startdatum <= '" + sdf.format(to) + "'"
                + " AND (slutdatum IS NULL OR slutdatum >= '" + sdf.format(from) + "')";

        String status = (String) filterBox.getSelectedItem();
        if (!"Alla statusar".equals(status)) {
            sql += " AND status = '" + status + "'";
        }

        getProjektInfo(sql);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        filterBox = new javax.swing.JComboBox<>();
        projInfoKnapp = new javax.swing.JButton();
        startDatumSpinner = new javax.swing.JSpinner();
        slutDatumSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tpProjekt = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellMinaProjekt = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabellAllaProjekt = new javax.swing.JTable();
        btnTillbaka = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PROJEKT");

        filterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterBox.addActionListener(this::filterBoxActionPerformed);

        projInfoKnapp.setText("Öppna");
        projInfoKnapp.addActionListener(this::projInfoKnappActionPerformed);

        jLabel2.setText("Startdatum");

        jLabel3.setText("Slutdatum");

        tabellMinaProjekt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabellMinaProjekt);

        tpProjekt.addTab("tab1", jScrollPane1);

        tabellAllaProjekt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabellAllaProjekt);

        tpProjekt.addTab("tab2", jScrollPane2);

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(this::btnTillbakaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startDatumSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(slutDatumSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(projInfoKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startDatumSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slutDatumSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(tpProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projInfoKnapp)
                    .addComponent(btnTillbaka))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //kod för filter av status
    private void filterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBoxActionPerformed
        filtreraDatum();
    }//GEN-LAST:event_filterBoxActionPerformed

    //Knapp för att öppna specifikt projekt
    private void projInfoKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projInfoKnappActionPerformed
        if (projektId > 0) {
            new ProjektInfo(idb, projektId, inloggadAnvandare).setVisible(true);
        }
    }//GEN-LAST:event_projInfoKnappActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        // TODO add your handling code here:'
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
        // java.awt.EventQueue.invokeLater(() -> new ProjektFlik().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> filterBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton projInfoKnapp;
    private javax.swing.JSpinner slutDatumSpinner;
    private javax.swing.JSpinner startDatumSpinner;
    private javax.swing.JTable tabellAllaProjekt;
    private javax.swing.JTable tabellMinaProjekt;
    private javax.swing.JTabbedPane tpProjekt;
    // End of variables declaration//GEN-END:variables
}
