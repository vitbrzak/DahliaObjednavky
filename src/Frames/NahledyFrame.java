package Frames;

/**
 *
 * @author vitbr
 */
public final class NahledyFrame extends javax.swing.JFrame {

    /**
     * Creates new form NahledyFrame
     */
    static int volba;
    static String datumOd;
    static String datumDo;

    public NahledyFrame(int volba, String datumOd, String datumDo) {
        NahledyFrame.volba = volba;
        NahledyFrame.datumOd = datumOd;
        NahledyFrame.datumDo = datumDo;
        initComponents();
        myInit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNahledZakaznici = new Panels.JPanelNahledZakaznici();
        jPanelNahledObjPodleOdberu = new Panels.JPanelNahledObjPodleOdberu();
        jPanelNahledProdej = new Panels.JPanelNahledProdej(datumOd, datumDo);

        setTitle("Náhled");
        setBackground(new java.awt.Color(204, 255, 153));
        setMinimumSize(new java.awt.Dimension(1000, 630));
        setPreferredSize(new java.awt.Dimension(1000, 630));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanelNahledZakaznici, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 610));
        getContentPane().add(jPanelNahledObjPodleOdberu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, -1, 610));
        getContentPane().add(jPanelNahledProdej, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 610));

        getAccessibleContext().setAccessibleName("Nahledy");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void myInit() {
        jPanelNahledObjPodleOdberu.setVisible(false);
        jPanelNahledZakaznici.setVisible(false);
        jPanelNahledProdej.setVisible(false);
        switch (volba) {
            case 1:
                jPanelNahledZakaznici.setVisible(true);
                break;
            case 2:
                jPanelNahledObjPodleOdberu.setVisible(true);
                break;
            case 3:
                jPanelNahledProdej.setVisible(true);
                break;
        }
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NahledyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NahledyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NahledyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NahledyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NahledyFrame(volba, datumOd, datumDo).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Panels.JPanelNahledObjPodleOdberu jPanelNahledObjPodleOdberu;
    private Panels.JPanelNahledProdej jPanelNahledProdej;
    private Panels.JPanelNahledZakaznici jPanelNahledZakaznici;
    // End of variables declaration//GEN-END:variables

}
