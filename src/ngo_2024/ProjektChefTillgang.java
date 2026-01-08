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

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProjektChefTillgang.class.getName());

    public ProjektChefTillgang(InfDB idb, String inloggadAnvandare) {
        initComponents();
        this.inloggadAnvandare = inloggadAnvandare;
        this.idb = idb;

        andraRubrik();
        getLandNamn();
    }

    private void andraRubrik() {
        DefaultTableModel tabellModell = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Projektnamn", "Projektchef", "Kostnad"}
        ) {
            @Override
            public boolean isCellEditable(int rad, int kolumn) {
                return false;
            }
        };
        tblprojekt.setModel(tabellModell);

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

        for (int i = 0; i < tblprojekt.getColumnCount(); i++) {
            tblprojekt.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

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

    private void fyllProjektTabell(int landId) {
        try {
            DefaultTableModel modell = (DefaultTableModel) tblprojekt.getModel();
            modell.setRowCount(0);

            String sqlFraga = "SELECT projektnamn, projektchef, kostnad "
                    + "FROM projekt "
                    + "WHERE land = " + landId;

            ArrayList<HashMap<String, String>> projektLista = idb.fetchRows(sqlFraga);

            for (HashMap<String, String> projekt : projektLista) {
                modell.addRow(new Object[]{
                    projekt.get("projektnamn"),
                    projekt.get("projektchef"),
                    projekt.get("kostnad"),});
            }

            modell.addRow(new Object[]{"", "", ""});

            int rubrik2Index = modell.getRowCount();

            modell.addRow(new Object[]{
                "Land",
                "Totala projekt",
                "Total kostnad"
            });

            modell.addRow(new Object[]{
                landNamn,
                raknaProjekt(landId),
                raknaTotalKostnad(landId)
            });

            tblprojekt.putClientProperty("rubrik2Index", rubrik2Index);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int raknaProjekt(int landId) {
        try {
            String sql = "SELECT COUNT(*) AS antal FROM projekt WHERE land = " + landId;
            HashMap<String, String> rad = idb.fetchRow(sql);
            if (rad != null && rad.get("antal") != null) {
                return Integer.parseInt(rad.get("antal"));
            }
        } catch (InfException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double raknaTotalKostnad(int landId) {
        try {
            String sql = "SELECT SUM(kostnad) AS total FROM projekt WHERE land = " + landId;
            HashMap<String, String> rad = idb.fetchRow(sql);
            if (rad != null && rad.get("total") != null) {
                return Double.parseDouble(rad.get("total"));
            }
        } catch (InfException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txthanteraprojekt = new javax.swing.JLabel();
        txtprojektstatistik = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtallaprojekt = new javax.swing.JButton();
        filterLand = new javax.swing.JComboBox<>();
        btnandraprojektuppgifter = new javax.swing.JButton();
        btnandrapartner = new javax.swing.JButton();
        btnandrahandlaggare = new javax.swing.JButton();
        lblprojektkostnad = new javax.swing.JLabel();
        txtprojektchef = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblprojekt = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txthanteraprojekt.setText("Hantera projekt");

        txtprojektstatistik.setText("Projektstatistik");

        jButton1.setText("Mina projekt");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        txtallaprojekt.setText("Alla projekt");

        filterLand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterLand.addActionListener(this::filterLandActionPerformed);

        btnandraprojektuppgifter.setText("Ändra projekt uppgifter");
        btnandraprojektuppgifter.addActionListener(this::btnandraprojektuppgifterActionPerformed);

        btnandrapartner.setText("Lägg till/Ta bort partners");

        btnandrahandlaggare.setText("Lägg till/Ta bort handläggare");

        lblprojektkostnad.setText("Kostnader för projekt");

        txtprojektchef.setText("Inloggad som projektchef");

        tblprojekt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Projektnamn", "Projektchef", "Kostnad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblprojekt);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txthanteraprojekt)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtprojektchef)
                    .addComponent(lblprojektkostnad)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnandrapartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtprojektstatistik)
                        .addComponent(btnandraprojektuppgifter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnandrahandlaggare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtallaprojekt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(filterLand, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jButton1)
                    .addComponent(txtallaprojekt)
                    .addComponent(filterLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblprojektkostnad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filterLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterLandActionPerformed
        landNamn = (String) filterLand.getSelectedItem();
        if (landNamn != null && !landNamn.isEmpty()) {
            try {
                HashMap<String, String> landInfo = idb.fetchRow(
                        "SELECT * FROM land WHERE namn = '" + landNamn + "'"
                );
                if (landInfo != null) {
                    int landId = Integer.parseInt(landInfo.get("lid"));
                    fyllProjektTabell(landId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_filterLandActionPerformed

    private void btnandraprojektuppgifterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnandraprojektuppgifterActionPerformed
     /*   Projektforandrare pi = new Projektforandrare(idb, inloggadAnvandare, pid);
        pi.setVisible(true);
        */
    }//GEN-LAST:event_btnandraprojektuppgifterActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
    private javax.swing.JButton btnandrahandlaggare;
    private javax.swing.JButton btnandrapartner;
    private javax.swing.JButton btnandraprojektuppgifter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> filterLand;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblprojektkostnad;
    private javax.swing.JTable tblprojekt;
    private javax.swing.JButton txtallaprojekt;
    private javax.swing.JLabel txthanteraprojekt;
    private javax.swing.JLabel txtprojektchef;
    private javax.swing.JLabel txtprojektstatistik;
    // End of variables declaration//GEN-END:variables
}
