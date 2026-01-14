/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo_2024;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author tovehanssons
 */
public class RedigeraPartners extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RedigeraPartners.class.getName());
    private String pid;
    private InfDB idb;
    private JFramePartner partnersfonster;
    
    
    /**
     * //Klassen RedigeraPartners gör att man kan skapa eller lägga till en partner.
     */
    public RedigeraPartners(JFramePartner partnersfonster, InfDB idb, String pid) {
        this.partnersfonster = partnersfonster;
        this.idb = idb;
        this.pid = pid; 
    
        initComponents();
           
    fyllStadCombo();

    if (pid == null) {
        lblRedigeraPartners.setText("Lägg till partner");
    } else {
        lblRedigeraPartners.setText("Redigera partner");
        fyllFaltFranDB();
    }

    btnTBRedPartners.addActionListener(e -> dispose());
    btnSparaRedPartners.addActionListener(e -> spara());
    
    }       
    
   private void fyllStadCombo() {
    cbRedStad.removeAllItems();

    for (int i = 1; i <= 6; i++) {
        cbRedStad.addItem(String.valueOf(i));
        }
    }

    private void fyllFaltFranDB() {
        KlassPartners p = new KlassPartners(idb, pid);

        tfRedNamn.setText(p.getNamn());
        tfRedKontaktperson.setText(p.getKontaktperson());
        tfRedKontaktepost.setText(p.getKontaktepost());
        tfRedTelefon.setText(p.getTelefon());
        tfRedAdress.setText(p.getAdress());
        tfRedBranch.setText(p.getBranch());

        String stad = p.getStad();
        if (stad != null) {
        cbRedStad.setSelectedItem(stad.trim());
        }
    }

    public void spara() {
        String namn = tfRedNamn.getText();
        String kontaktperson = tfRedKontaktperson.getText();
        String epost = tfRedKontaktepost.getText();
        String telefon = tfRedTelefon.getText();
        String adress = tfRedAdress.getText();
        String branch = tfRedBranch.getText();

        int stad;
      
        try {
            stad = Integer.parseInt((String) cbRedStad.getSelectedItem());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Välj en giltig stad (1–6).");
            return; 
    
        }
            //Validering
            
            if (!Validering.ValideraNamn(namn)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ogiltlig namn");
                return;
            }
            
            if (!Validering.ValideraNamn(kontaktperson)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ogiltlig kontaktperson");
                return;
                
            }
            
            if (!Validering.ValideraEpost(epost)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ogiltlig e-post");
                return;
            }
            
            if (!Validering.ValideraTelefon(telefon)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ogiltligt telefonnummer");
            return;
                    
        }
            if (!Validering.ValideraAdress(adress)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ogiltlig adress");
                return;
            }
            
            try {
                
           

    if (pid == null) {
        // Lägg till (ny pid)
        String maxId = idb.fetchSingle("SELECT MAX(pid) FROM partner");
        int nyttId = (maxId == null || maxId.isEmpty()) ? 1 : Integer.parseInt(maxId) + 1;

        String sqlFraga =
                "INSERT INTO partner (pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, stad) VALUES (" +
                nyttId + ", '" + esc(namn) + "', '" + esc(kontaktperson) + "', '" + esc(epost) + "', '" +
                esc(telefon) + "', '" + esc(adress) + "', '" + esc(branch) + "', " + stad + ")";

        idb.insert(sqlFraga);
        
       
    } else {
        
         int pidInt = Integer.parseInt(pid.trim());
        
        String sqlFraga =
                "UPDATE partner SET " +
                        "namn = '" + esc(namn) + "', " +
                        "kontaktperson = '" + esc(kontaktperson) + "', " +
                        "kontaktepost = '" + esc(epost) + "', " +
                        "telefon = '" + esc(telefon) + "', " +
                        "adress = '" + esc(adress) + "', " +
                        "branch = '" + esc(branch) + "', " +
                        "stad = " + stad + " " + 
                        "WHERE pid = " + pidInt;

        idb.update(sqlFraga);
    }

    if (partnersfonster != null) {
        partnersfonster.laddaOmPartners();
    }

    dispose();

} catch (InfException ex) {
    javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
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

        lblRedigeraPartners = new javax.swing.JLabel();
        lblNamnRedPartners = new javax.swing.JLabel();
        lblKontaktpersonRedPartners = new javax.swing.JLabel();
        lblKontaktepostRedPartners = new javax.swing.JLabel();
        lblTelefonRedPartners = new javax.swing.JLabel();
        lblAdressRedPartners = new javax.swing.JLabel();
        lblBranchRedPartners = new javax.swing.JLabel();
        lblStadRedPartners = new javax.swing.JLabel();
        tfRedNamn = new javax.swing.JTextField();
        tfRedKontaktperson = new javax.swing.JTextField();
        tfRedKontaktepost = new javax.swing.JTextField();
        tfRedTelefon = new javax.swing.JTextField();
        tfRedBranch = new javax.swing.JTextField();
        tfRedAdress = new javax.swing.JTextField();
        btnSparaRedPartners = new javax.swing.JButton();
        btnTBRedPartners = new javax.swing.JButton();
        cbRedStad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblRedigeraPartners.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblRedigeraPartners.setText("Redigera Partners");

        lblNamnRedPartners.setText("Namn:");

        lblKontaktpersonRedPartners.setText("Kontaktperson:");

        lblKontaktepostRedPartners.setText("Kontaktepost:");

        lblTelefonRedPartners.setText("Telefon:");

        lblAdressRedPartners.setText("Adress:");

        lblBranchRedPartners.setText("Branch:");

        lblStadRedPartners.setText("Stad:");

        tfRedKontaktperson.addActionListener(this::tfRedKontaktpersonActionPerformed);

        tfRedTelefon.addActionListener(this::tfRedTelefonActionPerformed);

        btnSparaRedPartners.setText("Spara");

        btnTBRedPartners.setText("Tillbaka");

        cbRedStad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbRedStad.addActionListener(this::cbRedStadActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNamnRedPartners)
                                    .addComponent(lblKontaktpersonRedPartners)
                                    .addComponent(lblKontaktepostRedPartners)
                                    .addComponent(lblTelefonRedPartners)
                                    .addComponent(lblBranchRedPartners)
                                    .addComponent(lblAdressRedPartners))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblStadRedPartners, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfRedBranch)
                            .addComponent(tfRedAdress)
                            .addComponent(tfRedTelefon)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfRedNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfRedKontaktperson)
                            .addComponent(tfRedKontaktepost)
                            .addComponent(cbRedStad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addComponent(lblRedigeraPartners)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTBRedPartners)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSparaRedPartners)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblRedigeraPartners)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamnRedPartners)
                    .addComponent(tfRedNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKontaktpersonRedPartners)
                    .addComponent(tfRedKontaktperson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKontaktepostRedPartners, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfRedKontaktepost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRedTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefonRedPartners))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRedAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdressRedPartners))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRedBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBranchRedPartners))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStadRedPartners)
                    .addComponent(cbRedStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTBRedPartners)
                    .addComponent(btnSparaRedPartners))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfRedKontaktpersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRedKontaktpersonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRedKontaktpersonActionPerformed

    private void tfRedTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRedTelefonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRedTelefonActionPerformed

    private void cbRedStadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRedStadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRedStadActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new RedigeraPartners().setVisible(true));
    }
    
       private static String esc(String s) {
                if (s == null) return "";
                return s.replace("'", "''").trim();
             
                             
            }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSparaRedPartners;
    private javax.swing.JButton btnTBRedPartners;
    private javax.swing.JComboBox<String> cbRedStad;
    private javax.swing.JLabel lblAdressRedPartners;
    private javax.swing.JLabel lblBranchRedPartners;
    private javax.swing.JLabel lblKontaktepostRedPartners;
    private javax.swing.JLabel lblKontaktpersonRedPartners;
    private javax.swing.JLabel lblNamnRedPartners;
    private javax.swing.JLabel lblRedigeraPartners;
    private javax.swing.JLabel lblStadRedPartners;
    private javax.swing.JLabel lblTelefonRedPartners;
    private javax.swing.JTextField tfRedAdress;
    private javax.swing.JTextField tfRedBranch;
    private javax.swing.JTextField tfRedKontaktepost;
    private javax.swing.JTextField tfRedKontaktperson;
    private javax.swing.JTextField tfRedNamn;
    private javax.swing.JTextField tfRedTelefon;
    // End of variables declaration//GEN-END:variables
}
