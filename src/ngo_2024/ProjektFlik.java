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

public class ProjektFlik extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private String projektnamn;
    private int projektId;
    private ArrayList<String> projektLista = new ArrayList<>();
    private String aktuellSql;
    private int aktuellPid;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektFlik.class.getName());

    public ProjektFlik(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        initComponents();

        projInfoKnapp.setEnabled(false);

        skapaProjektTabell();

        //Lägg in text i status filter
        filterBox.removeAllItems();
        filterBox.addItem("Alla statusar");
        filterBox.addItem("Planerat");
        filterBox.addItem("Pågående");
        filterBox.addItem("Avslutat");

        this.aktuellSql = getAnstalldSql();
        filtreraDatum();
        addTabellLyssnare();
    }

    //Skapar tabell för projekten
    private void skapaProjektTabell() {
        projektTabell.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"pid", "Projektnamn", "Status", "Prioritet", "startdatum", "slutdatum"}
        ));

        projektTabell.getColumnModel().getColumn(0).setMinWidth(0);
        projektTabell.getColumnModel().getColumn(0).setMaxWidth(0);
        projektTabell.getColumnModel().getColumn(0).setPreferredWidth(0);

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
                +"WHERE epost = '" + inloggadAnvandare + "')))";
    }

    //Hämta och lägg in projektinformation i tabellen
    public void getProjektInfo(String sqlFraga) {
        try {
            DefaultTableModel modell = (DefaultTableModel) projektTabell.getModel();
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

    //Öppna specifikt projekt
    private void openProjektInfo(String projektnamn) {
        ProjektInfo projektInfo = new ProjektInfo(idb, projektId, inloggadAnvandare);
        projektInfo.setVisible(true);
    }

    //Lägg till tabell lyssnare
    private void addTabellLyssnare() {
        projektTabell.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int rad = projektTabell.getSelectedRow();
                if (rad >= 0) {
                    projektId = Integer.parseInt(
                            projektTabell.getValueAt(rad, 0).toString()
                    );
                    projInfoKnapp.setEnabled(true);
                }
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        projektTabell = new javax.swing.JTable();
        MinaProjKnapp = new javax.swing.JButton();
        filterBox = new javax.swing.JComboBox<>();
        AvdProjKnapp = new javax.swing.JButton();
        projInfoKnapp = new javax.swing.JButton();
        startDatumSpinner = new javax.swing.JSpinner();
        slutDatumSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PROJEKT");

        projektTabell.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(projektTabell);

        MinaProjKnapp.setText("Mina projekt");
        MinaProjKnapp.addActionListener(this::MinaProjKnappActionPerformed);

        filterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterBox.addActionListener(this::filterBoxActionPerformed);

        AvdProjKnapp.setText("Avdelningens projekt");
        AvdProjKnapp.addActionListener(this::AvdProjKnappActionPerformed);

        projInfoKnapp.setText("Öppna");
        projInfoKnapp.addActionListener(this::projInfoKnappActionPerformed);

        jLabel2.setText("Startdatum");

        jLabel3.setText("Slutdatum");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projInfoKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MinaProjKnapp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AvdProjKnapp)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinaProjKnapp)
                    .addComponent(AvdProjKnapp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(projInfoKnapp)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapp som visar den inloggades projekt
    private void MinaProjKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinaProjKnappActionPerformed
        aktuellSql = getAnstalldSql();
        filtreraDatum();
    }//GEN-LAST:event_MinaProjKnappActionPerformed

    //kod för filter av status
    private void filterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBoxActionPerformed
        filtreraDatum();
    }//GEN-LAST:event_filterBoxActionPerformed

    //Knapp som visar avdelningens projekt
    private void AvdProjKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvdProjKnappActionPerformed
        aktuellSql = getAvdelningsProjektSql();
        filtreraDatum();
    }//GEN-LAST:event_AvdProjKnappActionPerformed

    //Knapp för att öppna specifikt projekt
    private void projInfoKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projInfoKnappActionPerformed
        if (projektId > 0) {
            new ProjektInfo(idb, projektId, inloggadAnvandare).setVisible(true);
        }
    }//GEN-LAST:event_projInfoKnappActionPerformed

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
    private javax.swing.JButton AvdProjKnapp;
    private javax.swing.JButton MinaProjKnapp;
    private javax.swing.JComboBox<String> filterBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton projInfoKnapp;
    private javax.swing.JTable projektTabell;
    private javax.swing.JSpinner slutDatumSpinner;
    private javax.swing.JSpinner startDatumSpinner;
    // End of variables declaration//GEN-END:variables
}
