/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.plaf.basic.BasicTableHeaderUI;

public class PartnersFonster extends javax.swing.JFrame {
    //Klassen PartnersFonster är ett fönster som visar vilka partner beroende på roll.
    //Admin: Ser alla partners och kan lägga till/ta bort/ändra i systemet.
    // Projektchef: Ser alla partners kopplade till valt projekt och kan ta bort från projekt.
    // Handläggare: Ser endast partners som finns i projekten handläggaren deltar i.

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(PartnersFonster.class.getName());

    private InfDB idb;
    private String inloggadAnvandare;
    private ArrayList<Partners> visadePartners = new ArrayList<>();

    public PartnersFonster(InfDB idb, String inloggadAnvandare) {
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        
        getContentPane().setBackground(Color.WHITE);
        
        initComponents();
       
        jScrollPane1.setHorizontalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Anpassar utseendet för gränssnittet (typsnitt, radstorlek)
    jtPartners.setFont(new Font("SansSerif", Font.PLAIN, 12));
    jtPartners.setRowHeight(22);

    jtPartners.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));


    jtPartners.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


    jtPartners.getColumnModel().getColumn(0).setPreferredWidth(190); // Namn
    jtPartners.getColumnModel().getColumn(1).setPreferredWidth(130); // Kontaktperson
    jtPartners.getColumnModel().getColumn(2).setPreferredWidth(190); // Kontaktepost
    jtPartners.getColumnModel().getColumn(3).setPreferredWidth(120); // Telefon
    jtPartners.getColumnModel().getColumn(4).setPreferredWidth(150); // Adress
    jtPartners.getColumnModel().getColumn(5).setPreferredWidth(160); // Branch
    jtPartners.getColumnModel().getColumn(6).setPreferredWidth(50); // Stad

    jtPartners.getTableHeader().setUI(new BasicTableHeaderUI());
    jtPartners.getTableHeader().setBackground(new Color(61, 176, 75));
    jtPartners.getTableHeader().setForeground(Color.WHITE);
    jtPartners.getTableHeader().setOpaque(true);

        //Rollstyrning för Admin och Projektchef när det gäller ta bort/ändra/lägg till
        
        cbPartners.removeAllItems();
        
        sattBehorighet();

     boolean arProjektchef = ValAvRoll.arProjektchef(idb, inloggadAnvandare);

     if (arProjektchef) {
     fyllProjektCombo();
}

     fyllPartnersTabell();

     cbPartners.addActionListener(e -> {
    if (arProjektchef) {
       fyllPartnersTabell();
    }
});
    }
//Adminen och Projektchefens behörigheter
    private void sattBehorighet() {
    boolean arAdmin = ValAvRoll.arAdmin(idb, inloggadAnvandare);
    boolean arProjektchef = ValAvRoll.arProjektchef(idb, inloggadAnvandare);

    
    btnLaggtillPartners.setVisible(arAdmin || arProjektchef);
    btnTabortPartners.setVisible(arAdmin || arProjektchef);
    btnAndraPartners.setVisible(arAdmin);

   
    if (arAdmin) {
    btnTabortPartners.setText("Ta bort partner");
    btnLaggtillPartners.setText("Lägg till partner");
} else if (arProjektchef) {
    btnTabortPartners.setText("Ta bort från projekt");
    btnLaggtillPartners.setText("Lägg till partner i projekt");
    
    }

    //Projektchefen får välja projekt i combobox.
    
    cbPartners.setVisible(arProjektchef);

    if (!arProjektchef) {
        cbPartners.removeAllItems();
    }

    }
    
    //Fyller tabellen för partners beroende på användarens roll.

private void fyllPartnersTabell() {
    DefaultTableModel model = (DefaultTableModel) jtPartners.getModel();
    model.setRowCount(0);

    visadePartners.clear(); 

    ArrayList<Partners> lista;

if (ValAvRoll.arAdmin(idb, inloggadAnvandare)) {

    // Admin ser alla partners
    lista = Partners.hamtaAlla(idb);

} else if (ValAvRoll.arProjektchef(idb, inloggadAnvandare)) {

    // Projektchef ser partners för valt projekt
    String projektPid = (String) cbPartners.getSelectedItem();
    lista = (projektPid == null)
            ? new ArrayList<>()
            : Partners.hamtaForProjekt(idb, projektPid);

} else if (ValAvRoll.arHandlaggare(idb, inloggadAnvandare)) {

    lista = Partners.hamtaForHandlaggare(idb, inloggadAnvandare);

} else {
    lista = new ArrayList<>();
}


    for (Partners p : lista) {
        visadePartners.add(p);   

        model.addRow(new Object[]{
            p.getNamn(),
            p.getKontaktperson(),
            p.getKontaktepost(),
            p.getTelefon(),
            p.getAdress(),
            p.getBranch(),
            p.getStad()
        });
    }
}

        public void laddaOmPartners() {
        fyllPartnersTabell();
        }


    private void fyllProjektCombo() {
    cbPartners.removeAllItems();
    
    for (String pid : ValAvRoll.hamtaProjektForProjektchef(idb, inloggadAnvandare)) {
    cbPartners.addItem(pid);
}

    

    if (cbPartners.getItemCount() > 0) {
        cbPartners.setSelectedIndex(0);
    }
    
    }
    
    private String valjBefintligPartnerDialog(String projektPid) {
    try {
        String sqlFraga =
               "SELECT partner.pid, partner.namn FROM partner " +
                "WHERE partner.pid NOT IN ( " +
                "SELECT projekt_partner.partner_pid " +
                "FROM projekt_partner " +
                "WHERE projekt_partner.pid = '" + projektPid + "'" +
                ") ORDER BY partner.namn";

        ArrayList<HashMap<String, String>> rader = idb.fetchRows(sqlFraga);

        if (rader == null || rader.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Det finns inga partners att lägga till (alla är redan kopplade)."
            );
            return null;
        }

        java.util.LinkedHashMap<String, String> namnTillPid = new java.util.LinkedHashMap<>();

        for (HashMap<String, String> rad : rader) {
            String pid = rad.get("pid");
            String namn = rad.get("namn");

            String uniktNamn = namn;
            while (namnTillPid.containsKey(uniktNamn)) {
                uniktNamn += " ";
            }

            namnTillPid.put(uniktNamn, pid);
        }

        Object valt = javax.swing.JOptionPane.showInputDialog(
            this,
            "Välj en partner att lägga till i projektet:",
            "Lägg till partner i projekt",
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            namnTillPid.keySet().toArray(),
            null
        );

        if (valt == null) return null;

        return namnTillPid.get(valt.toString());

    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        return null;   
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtPartners = new javax.swing.JTable();
        lblPartners = new javax.swing.JLabel();
        cbPartners = new javax.swing.JComboBox<>();
        btnAndraPartners = new javax.swing.JButton();
        btnTabortPartners = new javax.swing.JButton();
        btnLaggtillPartners = new javax.swing.JButton();
        btnTbPartners = new javax.swing.JButton();
        lblProjektitel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtPartners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Namn", "Kontaktperson", "Kontaktepost", "Telefon", "Adress", "Branch", "Stad"
            }
        ));
        jScrollPane1.setViewportView(jtPartners);

        lblPartners.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lblPartners.setText("Partners");

        cbPartners.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAndraPartners.setBackground(new java.awt.Color(2, 85, 139));
        btnAndraPartners.setText("Ändra");
        btnAndraPartners.addActionListener(this::btnAndraPartnersActionPerformed);

        btnTabortPartners.setBackground(new java.awt.Color(235, 28, 46));
        btnTabortPartners.setForeground(new java.awt.Color(255, 255, 255));
        btnTabortPartners.setText("Ta bort");
        btnTabortPartners.addActionListener(this::btnTabortPartnersActionPerformed);

        btnLaggtillPartners.setBackground(new java.awt.Color(1, 174, 217));
        btnLaggtillPartners.setText("Lägg till");
        btnLaggtillPartners.addActionListener(this::btnLaggtillPartnersActionPerformed);

        btnTbPartners.setBackground(new java.awt.Color(249, 181, 18));
        btnTbPartners.setText("Tillbaka");
        btnTbPartners.addActionListener(this::btnTbPartnersActionPerformed);

        lblProjektitel.setText("Projekt:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ngo_2024/bilder/partnersbild.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTbPartners)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnLaggtillPartners)
                            .addGap(18, 18, 18)
                            .addComponent(btnTabortPartners)
                            .addGap(18, 18, 18)
                            .addComponent(btnAndraPartners))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPartners)
                            .addGap(767, 767, 767)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblProjektitel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbPartners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblPartners)
                        .addGap(18, 37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPartners, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektitel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndraPartners)
                    .addComponent(btnTabortPartners)
                    .addComponent(btnLaggtillPartners)
                    .addComponent(btnTbPartners))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAndraPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraPartnersActionPerformed
            int rad = jtPartners.getSelectedRow();

    if (rad == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Välj en partner först.");
        return;
    }

    Partners valdPartner = visadePartners.get(rad);
    String pid = valdPartner.getPid();

    new RedigeraPartners(this, idb, pid).setVisible(true);
    
    }//GEN-LAST:event_btnAndraPartnersActionPerformed

    private void btnLaggtillPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggtillPartnersActionPerformed
           
    boolean arAdmin = ValAvRoll.arAdmin(idb, inloggadAnvandare);
    boolean arProjektchef = ValAvRoll.arProjektchef(idb, inloggadAnvandare);

    if (arAdmin) {
        // Admin: skapa helt ny partner i partner-tabellen
        new RedigeraPartners(this, idb, null).setVisible(true);
        return;
    }

    if (arProjektchef) {
        // Projektchef: koppla befintlig partner till valt projekt
        String projektPid = (String) cbPartners.getSelectedItem();

        if (projektPid == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Välj ett projekt först.");
            return;
        }

        String valdPartnerPid = valjBefintligPartnerDialog(projektPid);
        if (valdPartnerPid == null) return;

        try {
            idb.insert(
                "INSERT INTO projekt_partner (pid, partner_pid) VALUES ('" +
                projektPid + "', '" + valdPartnerPid + "')"
            );

            laddaOmPartners();

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        return;
    }

    javax.swing.JOptionPane.showMessageDialog(this, "Du har inte behörighet att lägga till partners.");

   
    }//GEN-LAST:event_btnLaggtillPartnersActionPerformed

    private void btnTabortPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabortPartnersActionPerformed

    int rad = jtPartners.getSelectedRow();

    if (rad == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Välj en partner först.");
        return;
    }

    Partners valdPartner = visadePartners.get(rad);
    String partnerPid = valdPartner.getPid();

    boolean arAdmin = ValAvRoll.arAdmin(idb, inloggadAnvandare);
    boolean arProjektchef = ValAvRoll.arProjektchef(idb, inloggadAnvandare);

    if (!arAdmin && !arProjektchef) {
        javax.swing.JOptionPane.showMessageDialog(this, "Du har inte behörighet att ta bort.");
        return;
    }

    String projektPid = null;
    if (arProjektchef) {
        projektPid = (String) cbPartners.getSelectedItem();
        if (projektPid == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Välj ett projekt först.");
            return;
        }
    }
    
    String text = arAdmin
            ? "Vill du radera '" + valdPartner.getNamn() + 
            "'?\nDen kommer isåfall bli borttagen från systemet helt och hållet."
            : "Vill du ta bort '" + valdPartner.getNamn() + "' från projektet?";

    int svar = javax.swing.JOptionPane.showConfirmDialog(
            this,
            text,
            "Bekräfta borttagning",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (svar != javax.swing.JOptionPane.YES_OPTION) {
        return;
    }

    try {
        if (arAdmin) {
            // Adminen tar bort partner helt
            idb.delete("DELETE FROM projekt_partner WHERE partner_pid = '" + partnerPid + "'");
            idb.delete("DELETE FROM partner WHERE pid = '" + partnerPid + "'");

        } else {
            // Projektchef tar bort koppling från valt projekt
            idb.delete(
                "DELETE FROM projekt_partner " +
                "WHERE pid = '" + projektPid + "' " +
                "AND partner_pid = '" + partnerPid + "'"
            );
        }

        laddaOmPartners();

    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
    }


    }//GEN-LAST:event_btnTabortPartnersActionPerformed

    private void btnTbPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTbPartnersActionPerformed
        dispose();
    }//GEN-LAST:event_btnTbPartnersActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new PartnersFonster().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndraPartners;
    private javax.swing.JButton btnLaggtillPartners;
    private javax.swing.JButton btnTabortPartners;
    private javax.swing.JButton btnTbPartners;
    private javax.swing.JComboBox<String> cbPartners;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtPartners;
    private javax.swing.JLabel lblPartners;
    private javax.swing.JLabel lblProjektitel;
    // End of variables declaration//GEN-END:variables
}
