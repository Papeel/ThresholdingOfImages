import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Entrar
 */
public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemThreshold = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        JMenuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 400));
        setPreferredSize(new java.awt.Dimension(650, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        jMenuFile.setText("Archivo");

        jMenuItemOpen.setText("Abrir");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);
        jMenuFile.add(jSeparator1);

        jMenuItemExit.setText("Salir");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar2.add(jMenuFile);

        jMenuEdit.setText("Editar");

        jMenuItemThreshold.setText("Elegir umbral");
        jMenuItemThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThresholdActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemThreshold);

        jMenuBar2.add(jMenuEdit);

        jMenuHelp.setText("Ayuda");

        JMenuAbout.setText("Acerca de");
        JMenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(JMenuAbout);

        jMenuBar2.add(jMenuHelp);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int res = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JPG imagen", "jpg"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("PNG imagen", "png"));
        
        if( fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            Mat backup = this.currentImagen;
            this.currentImagen = Imgcodecs.imread(fc.getSelectedFile().getAbsolutePath());
            
            try{
                JInternalFrame iW = createInternalWindow(this.currentImagen);
                cleanDesktop();
                MainWindow.count = 0;
                configureInternalWindow(iW, false, null);
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "La imagen no tiene un formato adecuado", "Mensaje", JOptionPane.ERROR_MESSAGE);
                this.currentImagen=backup;
            }
        }
        fc.resetChoosableFileFilters();
    }//GEN-LAST:event_jMenuItemOpenActionPerformed
    
    private void cleanDesktop(){
        for (JInternalFrame v : desktop.getAllFrames())
            v.dispose();
    }
    
    private JInternalFrame createInternalWindow(Mat image){
        InternalWindow iW = new InternalWindow();
        iW.setImage((BufferedImage)HighGui.toBufferedImage(image));
        return iW;
    }
    
    private void configureInternalWindow(JInternalFrame iW, boolean close, String title) {
        if (title == null)
            iW.setTitle(fc.getSelectedFile().getName());
        else
            iW.setTitle(title);
        iW.setClosable(close);
        iW.setMaximizable(true);
        iW.setIconifiable(true);
        iW.setLocation(((count*10)%this.getWidth()), ((count*10)%this.getHeight()));
        if(count == Integer.MAX_VALUE)
            count=0;
        else
            count++;
        desktop.add(iW);
        iW.show();
    }
    
    private void jMenuItemThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThresholdActionPerformed
        if (this.currentImagen != null) {
            String threshold = JOptionPane.showInputDialog(null, "Introduzca un umbral entre 0 y 255", "Selección de umbral", JOptionPane.PLAIN_MESSAGE);
            if(threshold == null)return;
            try{ 
                int data = Integer.parseInt(threshold);
                if (data >= 0 && data <= 255) {
                     JInternalFrame iw = createInternalWindow(umbralizar(this.currentImagen, data));
                     configureInternalWindow(iw, true, threshold);
                }else{
                    JOptionPane.showMessageDialog(null, "Debes añadir un número (entre 0 y 255) ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Debes añadir un número (entre 0 y 255) ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Debes abrir una imagen para poder umbralizar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jMenuItemThresholdActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(res == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void JMenuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuAboutActionPerformed
        JOptionPane.showMessageDialog(null, "Autores: Nelson González Machín, Samuel Guerra Marrero", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_JMenuAboutActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int shift = 30;
        for (JInternalFrame v : desktop.getAllFrames()) {
            int x = v.getX();
            int y = v.getY();
            if(x >= this.getWidth() - 15)
                x = this.getWidth() - shift - 15;
            if(y >= this.getHeight() - 65)
                y = this.getHeight() - shift - 65;
            v.setLocation(x, y);
        }
    }//GEN-LAST:event_formComponentResized
    
    private BufferedImage getImage(File file) {
        BufferedImage bI = null;
        try {
            bI = ImageIO.read(file);
        } catch(IOException e) {}
        return bI;
    }
    
    private Mat umbralizar(Mat imagen_original, Integer umbral) {
        // crear dos imágenes en niveles de gris con el mismo tamaño que la original
        Mat imagenGris = new Mat(imagen_original.rows(), imagen_original.cols(), CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(imagen_original.rows(), imagen_original.cols(),
CvType.CV_8U);
         // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original, imagenGris, Imgproc.COLOR_BGR2GRAY);
         
        // umbraliza la imagen:  
        // ‐ píxeles con nivel de gris > umbral se ponen a 1
        // ‐ píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris, imagenUmbralizada, umbral, 255, Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    private static int count = 0;
    private final JFileChooser fc  = new JFileChooser();
    private Mat currentImagen;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMenuAbout;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemThreshold;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
