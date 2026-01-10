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
import java.awt.Color;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class ProjektChefTillgang extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnvandare;
    private String landNamn;
    private int pid;
    private int landId;
    private HashMap<String, Integer> projektMap = new HashMap<>();
    private boolean visarMinaProjekt = true;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektChefTillgang.class.getName());

    public ProjektChefTillgang(InfDB idb, String inloggadAnvandare) {
        initComponents();
        this.inloggadAnvandare = inloggadAnvandare;
        this.idb = idb;

        andraRubrik();
        getLandNamn();
        fyllDropdown();

        //Fyll tabellen med statistik
        filterLand();
    }

    //Visuell kod för statistik tabellen
    private void andraRubrik() {
        DefaultTableModel tabellModell = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Land", "Projektnamn", "Projektchef", "Kostnad"}
        ) {
            @Override
            public boolean isCellEditable(int rad, int kolumn) {
                return false;
            }
        };

        tblMinaProjekt.setModel(tabellModell);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable tabell, Object varde, boolean markerad,
                    boolean fokus, int rad, int kolumn) {

                super.getTableCellRendererComponent(
                        tabell, varde, false, false, rad, kolumn);

                Integer andraRubrikIndex
                        = (Integer) tabell.getClientProperty("rubrik2Index");

                if (andraRubrikIndex != null && rad == andraRubrikIndex) {
                    setFont(tabell.getTableHeader().getFont());
                    setBackground(UIManager.getColor("TableHeader.background"));
                    setHorizontalAlignment(CENTER);
                } else {
                    setFont(tabell.getFont());
                    setBackground(Color.WHITE);
                    setHorizontalAlignment(LEFT);
                }

                return this;
            }
        };

        for (int i = 0; i < tblMinaProjekt.getColumnCount(); i++) {
            tblMinaProjekt.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    //Hämta och lägg in namn på länder för filter
    private void getLandNamn() {
        try {
            ArrayList<HashMap<String, String>> lander = idb.fetchRows("SELECT namn FROM land");
            filterLand.removeAllItems();
            for (HashMap<String, String> land : lander) {
                filterLand.addItem(land.get("namn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Sql som bara visar projekt kopplade till den inloggade projektchefen och valt land
    private String minaProjektSql() {
        return "SELECT land.namn, projektnamn, projektchef, kostnad "
                + "FROM projekt "
                + "JOIN land ON projekt.land = land.lid "
                + "WHERE land = " + landId + " "
                + "AND projektchef = (SELECT aid FROM anstalld WHERE epost = '" + inloggadAnvandare + "')";
    }

    //Sql som visar alla projekt kopplade till valt land
    private String allaProjektSql() {
        return "SELECT land.namn, projektnamn, projektchef, kostnad "
                + "FROM projekt "
                + "JOIN land ON projekt.land = land.lid "
                + "WHERE land = " + landId;
    }

    //Fyll statistik tabellen med projekt
    private void fyllProjektTabell(int landId, String sqlFraga) {
        try {
            DefaultTableModel modell = (DefaultTableModel) tblMinaProjekt.getModel();
            modell.setRowCount(0);

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> projekt : projektLista) {
                modell.addRow(new Object[]{
                    projekt.get("land"),
                    projekt.get("projektnamn"),
                    projekt.get("projektchef"),
                    projekt.get("kostnad"),});
            }

            modell.addRow(new Object[]{"", "", ""});

            int rubrik2Index = modell.getRowCount();

            modell.addRow(new Object[]{
                "Land",
                "Totala projekt",
                "Total kostnad",
                "Medel kostnad"
            });

            modell.addRow(new Object[]{
                landNamn,
                raknaProjekt(landId),
                raknaTotalKostnad(landId),
                raknaMedelKostnad(landId)
            });

            tblMinaProjekt.putClientProperty("rubrik2Index", rubrik2Index);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Räkna ihop projekt för valt land
    private int raknaProjekt(int landId) {
        try {
            String sql;
            if (visarMinaProjekt) {
                sql = "SELECT COUNT(*) AS antal FROM projekt WHERE land = " + landId + " "
                        + "AND projektchef = (SELECT aid FROM anstalld WHERE epost = '" + inloggadAnvandare + "')";
            } else {
                sql = "SELECT COUNT(*) AS antal FROM projekt WHERE land = " + landId;
            }
            HashMap<String, String> rad = idb.fetchRow(sql);
            if (rad != null && rad.get("antal") != null) {
                return Integer.parseInt(rad.get("antal"));
            }
        } catch (InfException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Räkna ihop totala kostanden för valt land
    private double raknaTotalKostnad(int landId) {
        try {
            String sql;
            if (visarMinaProjekt) {
                sql = "SELECT SUM(kostnad) AS total FROM projekt WHERE land = " + landId + " "
                        + "AND projektchef = (SELECT aid FROM anstalld WHERE epost = '" + inloggadAnvandare + "')";
            } else {
                sql = "SELECT SUM(kostnad) AS total FROM projekt WHERE land = " + landId;
            }
            HashMap<String, String> rad = idb.fetchRow(sql);

            if (rad != null && rad.get("total") != null) {
                return Double.parseDouble(rad.get("total"));
            }
        } catch (InfException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double raknaMedelKostnad(int landId) {
        try {
            String sql;
            if (visarMinaProjekt) {
                sql = "SELECT SUM(kostnad) AS total, COUNT(*) AS antal FROM projekt WHERE land = " + landId + " "
                        + "AND projektchef = (SELECT aid FROM anstalld WHERE epost = '" + inloggadAnvandare + "')";
            } else {
                sql = "SELECT SUM(kostnad) AS total, COUNT(*) AS antal FROM projekt WHERE land = " + landId;
            }
            HashMap<String, String> rad = idb.fetchRow(sql);

            if (rad != null && rad.get("total") != null && rad.get("antal") != null) {
                double kostnad = Double.parseDouble(rad.get("total"));
                int antal = Integer.parseInt(rad.get("antal"));

                double medelKostnad = kostnad / antal;
                return medelKostnad;
            }
        } catch (InfException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Fyll filter för projekt med projektnamn
    private void fyllDropdown() {
        try {
            boxprojekt.removeAllItems();
            projektMap.clear();

            String sqlFraga
                    = "SELECT p.projektnamn, p.pid "
                    + "FROM projekt p "
                    + "JOIN anstalld an ON p.projektchef = an.aid "
                    + "WHERE an.epost = '" + inloggadAnvandare + "'";

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> projekt : projektLista) {
                String projektnamn = projekt.get("projektnamn");
                Integer pid = Integer.parseInt(projekt.get("pid"));
                boxprojekt.addItem(projektnamn);
                projektMap.put(projektnamn, pid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Filtrera statistik utefter land och sql
    private void filterLand() {
        landNamn = (String) filterLand.getSelectedItem();

        if (landNamn == null || landNamn.isEmpty()) {
            return;
        }

        try {
            HashMap<String, String> landInfo = idb.fetchRow(
                    "SELECT lid FROM land WHERE namn = '" + landNamn + "'"
            );

            if (landInfo != null) {
                landId = Integer.parseInt(landInfo.get("lid"));

                String sql;
                if (visarMinaProjekt) {
                    sql = minaProjektSql();
                } else {
                    sql = allaProjektSql();
                }

                fyllProjektTabell(landId, sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    tpStatistik.addChangeListener(e ->)

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txthanteraprojekt = new javax.swing.JLabel();
        txtprojektstatistik = new javax.swing.JLabel();
        btnMinaProjekt = new javax.swing.JButton();
        btnAllaProjekt = new javax.swing.JButton();
        filterLand = new javax.swing.JComboBox<>();
        btnandraprojektuppgifter = new javax.swing.JButton();
        btnandrapartner = new javax.swing.JButton();
        btnandrahandlaggare = new javax.swing.JButton();
        lblprojektkostnad = new javax.swing.JLabel();
        txtprojektchef = new javax.swing.JLabel();
        boxprojekt = new javax.swing.JComboBox<>();
        tpStatistik = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMinaProjekt = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAllaProjekt = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txthanteraprojekt.setText("Hantera projekt");

        txtprojektstatistik.setText("Projektstatistik");

        btnMinaProjekt.setText("Mina projekt");
        btnMinaProjekt.addActionListener(this::btnMinaProjektActionPerformed);

        btnAllaProjekt.setText("Alla projekt");
        btnAllaProjekt.addActionListener(this::btnAllaProjektActionPerformed);

        filterLand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterLand.addActionListener(this::filterLandActionPerformed);

        btnandraprojektuppgifter.setText("Ändra projekt uppgifter");
        btnandraprojektuppgifter.addActionListener(this::btnandraprojektuppgifterActionPerformed);

        btnandrapartner.setText("Lägg till/Ta bort partners");

        btnandrahandlaggare.setText("Lägg till/Ta bort handläggare");
        btnandrahandlaggare.addActionListener(this::btnandrahandlaggareActionPerformed);

        lblprojektkostnad.setText("Kostnader för projekt");

        txtprojektchef.setText("Inloggad som projektchef");

        boxprojekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxprojekt.addActionListener(this::boxprojektActionPerformed);

        tblMinaProjekt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Land", "Projektnamn", "Projektchef", "Kostnad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMinaProjekt);

        tpStatistik.addTab("tab1", jScrollPane1);

        tblAllaProjekt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Land", "Projektnamn", "Projektchef", "Kostnad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAllaProjekt);

        tpStatistik.addTab("tab2", jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txthanteraprojekt)
                        .addGap(18, 18, 18)
                        .addComponent(boxprojekt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtprojektchef)
                    .addComponent(lblprojektkostnad)
                    .addComponent(btnandrapartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtprojektstatistik)
                    .addComponent(btnandraprojektuppgifter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnandrahandlaggare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMinaProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAllaProjekt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterLand, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpStatistik, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtprojektchef)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthanteraprojekt)
                    .addComponent(boxprojekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnandraprojektuppgifter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnandrapartner)
                .addGap(9, 9, 9)
                .addComponent(btnandrahandlaggare)
                .addGap(18, 18, 18)
                .addComponent(txtprojektstatistik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinaProjekt)
                    .addComponent(btnAllaProjekt)
                    .addComponent(filterLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblprojektkostnad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpStatistik, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapp som fyller tabell med egna projekt
    private void btnMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaProjektActionPerformed
        visarMinaProjekt = true;
        filterLand();
    }//GEN-LAST:event_btnMinaProjektActionPerformed

    //Filtrerar projekt på land
    private void filterLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterLandActionPerformed
        filterLand();
    }//GEN-LAST:event_filterLandActionPerformed

    private void btnandraprojektuppgifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnandraprojektuppgifterActionPerformed
        String valtProjekt = (String) boxprojekt.getSelectedItem();
        /*z-- Hämtar valt projekt frnå en dropdown, kontrollerar att de inte är tomt, hämtar id, öppnar fönster med det id:et*/
        if (valtProjekt == null) {
            System.out.println("Kunda ej hitta projekt");
            return;
        }

        Integer valtPid = projektMap.get(valtProjekt);

        ProjektInfo projekt = new ProjektInfo(idb, valtPid, inloggadAnvandare);
        projekt.setVisible(true);

    }//GEN-LAST:event_btnandraprojektuppgifterActionPerformed

    private void boxprojektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxprojektActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxprojektActionPerformed

    //Knapp som fyller tabell med alla projekt kopplade till land
    private void btnAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllaProjektActionPerformed
        visarMinaProjekt = false;
        filterLand();
    }//GEN-LAST:event_btnAllaProjektActionPerformed

    private void btnandrahandlaggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnandrahandlaggareActionPerformed
        HandlaggareFlik hf = new HandlaggareFlik(idb, inloggadAnvandare);
        hf.setVisible(true);
    }//GEN-LAST:event_btnandrahandlaggareActionPerformed

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
        // java.awt.EventQueue.invokeLater(() -> new ProjektChefTillgang().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxprojekt;
    private javax.swing.JButton btnAllaProjekt;
    private javax.swing.JButton btnMinaProjekt;
    private javax.swing.JButton btnandrahandlaggare;
    private javax.swing.JButton btnandrapartner;
    private javax.swing.JButton btnandraprojektuppgifter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> filterLand;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblprojektkostnad;
    private javax.swing.JTable tblAllaProjekt;
    private javax.swing.JTable tblMinaProjekt;
    private javax.swing.JTabbedPane tpStatistik;
    private javax.swing.JLabel txthanteraprojekt;
    private javax.swing.JLabel txtprojektchef;
    private javax.swing.JLabel txtprojektstatistik;
    // End of variables declaration//GEN-END:variables
}
