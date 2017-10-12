package Frames;

/**
 *
 * @author vitbr
 */
public final class ObjednavkaFrame extends javax.swing.JFrame {

    /**
     * Creates new form ObjednavkaFrame
     */
    public static int volba;  // 0 = JPanelObjednavka, 1 = JPanelObjednavkaEshop
    public static String OXID;
    public static int idZakaznik;
    public static int idObj;
    public static int cisloObj;
    public static String datum;
    public static int doprava;
    public ObjednavkaFrame(int volba, String OXID, int idZakaznik, int idObj, int cisloObj, String datum, int doprava) {
        ObjednavkaFrame.volba = volba;
        ObjednavkaFrame.OXID = OXID;
        ObjednavkaFrame.idZakaznik = idZakaznik;
        ObjednavkaFrame.idObj = idObj;
        ObjednavkaFrame.cisloObj = cisloObj;
        ObjednavkaFrame.datum = datum;
        ObjednavkaFrame.doprava = doprava;
        initComponents();
        myInit();
    }


    public void myInit() {
        jPanelObjednavka.setVisible(false);
        jPanelObjednavkaEshop.setVisible(false);
        switch (volba) {
            case 0:
                jPanelObjednavka.setVisible(true);
                break;
            case 1:
                jPanelObjednavkaEshop.setVisible(true);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelObjednavka = new Panels.JPanelObjednavka(idZakaznik, idObj, cisloObj, datum, doprava);
        jPanelObjednavkaEshop = new Panels.JPanelObjednavkaEshop(OXID, idZakaznik, idObj, cisloObj, datum, doprava);

        setTitle("Objednávka");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 153));
        setMinimumSize(new java.awt.Dimension(600, 610));
        setPreferredSize(new java.awt.Dimension(600, 610));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelObjednavka.setMinimumSize(new java.awt.Dimension(600, 610));
        jPanelObjednavka.setPreferredSize(new java.awt.Dimension(600, 610));
        getContentPane().add(jPanelObjednavka, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 610));
        getContentPane().add(jPanelObjednavkaEshop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 610));

        getAccessibleContext().setAccessibleName("Objednavka");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ObjednavkaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ObjednavkaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ObjednavkaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ObjednavkaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
            new ObjednavkaFrame(volba, OXID, idZakaznik, idObj, cisloObj, datum, doprava).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Panels.JPanelObjednavka jPanelObjednavka;
    private Panels.JPanelObjednavkaEshop jPanelObjednavkaEshop;
    // End of variables declaration//GEN-END:variables
}
