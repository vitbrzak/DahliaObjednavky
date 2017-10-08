package Panels;

import Frames.NahledyFrame;
import Frames.VyberDatumFrame;

/**
 *
 * @author Vit
 */
public class JPanelNahledy extends javax.swing.JPanel {

    public JPanelNahledy() {
        initComponents();
    }

//  private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonProdej = new javax.swing.JButton();
        jButtonZakaznici = new javax.swing.JButton();
        jButtonObjOdber = new javax.swing.JButton();

        jDialog1.setTitle("Seznam zákazníků");
        jDialog1.setMinimumSize(new java.awt.Dimension(1000, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 255, 153));
        setMaximumSize(new java.awt.Dimension(600, 550));
        setMinimumSize(new java.awt.Dimension(600, 550));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonProdej.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonProdej.setText("Prodej podle odrůd");
        jButtonProdej.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdejActionPerformed(evt);
            }
        });
        add(jButtonProdej, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 280, 40));

        jButtonZakaznici.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonZakaznici.setText("Seznam zákazníků");
        jButtonZakaznici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZakazniciActionPerformed(evt);
            }
        });
        add(jButtonZakaznici, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 280, 40));

        jButtonObjOdber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonObjOdber.setText("Objednávky podle způsobu odběru");
        jButtonObjOdber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjOdberActionPerformed(evt);
            }
        });
        add(jButtonObjOdber, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 280, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProdejActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdejActionPerformed
        VyberDatumFrame frame = new VyberDatumFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonProdejActionPerformed

    private void jButtonZakazniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZakazniciActionPerformed
        NahledyFrame frame = new NahledyFrame(1, "01.01.2017", "01.01.2017");
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonZakazniciActionPerformed

    private void jButtonObjOdberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjOdberActionPerformed
        NahledyFrame frame = new NahledyFrame(2, "01.01.2017", "01.01.2017");
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonObjOdberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonObjOdber;
    private javax.swing.JButton jButtonProdej;
    private javax.swing.JButton jButtonZakaznici;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
