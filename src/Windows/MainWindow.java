/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import Object.Rayon;
import java.awt.Button;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author moi
 */
public class MainWindow extends javax.swing.JFrame {

    private File selectedFile;
    private ArrayList<Rayon> listRayon= new ArrayList<>();
    
    /**
     * Creates new form NewJFrame
     */
    public MainWindow() {
        initComponents();
       jLabel2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventaire");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\gerardj\\Desktop\\java\\Magnetto\\Magnetto wheels\\img\\50648.gif")); // NOI18N
        jLabel2.setText("Opération en cours veuillez patienter");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));

        button1.setActionCommand("Selectionnez le fichier  excel de l'inventaireet générer les fichier texte ");
        button1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        button1.setLabel("Selectionnez le fichier excel de l'inventaire\n et générer les fichiers textes ");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, 182));

        button2.setLabel("Lire les fichiers textes remplis");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        getContentPane().add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 368, 182));

        button3.setLabel("Bilan");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        getContentPane().add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 197, 182));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Windows/logo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        JFileChooser fileChooser =new JFileChooser();
        
        
        
        
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(this);
        if (result==JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
            
            jLabel2.setVisible(true);
            listRayon=Tools.FileLecture.extractData(this,selectedFile,listRayon);
            
            Tools.FileEcriture.generateFile(listRayon);
            
            jLabel2.setVisible(false);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if(selectedFile==null){
        	JFileChooser fileChooser2=new JFileChooser();
        	fileChooser2.setCurrentDirectory(new File(System.getProperty("user.dir")));
        	
        	int value = fileChooser2.showOpenDialog(this);
        	if(value==JFileChooser.APPROVE_OPTION){
        		selectedFile=fileChooser2.getSelectedFile();
        	}
        	listRayon=Tools.FileLecture.extractData(this,selectedFile,listRayon);
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(this);
        
        if(result== JFileChooser.APPROVE_OPTION){
            jLabel2.setVisible(true);
            List<File> d = Arrays.asList(fileChooser.getSelectedFile().listFiles());
            Tools.FileLecture.ReadCompleteFile(this,d , listRayon);
            Tools.FileEcriture.remplirFichierExcel(selectedFile,listRayon);
            jLabel2.setVisible(false);
        }
                
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
               
        Tools.FileEcriture.genererFichierBilan(this,selectedFile, listRayon);
        jLabel2.setVisible(false);
    }//GEN-LAST:event_button3ActionPerformed

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
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(
        null, ex.getClass().getSimpleName() + ": " + ex.getMessage());
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public ArrayList<Rayon> getListRayon() {
        return listRayon;
    }

    public void setListRayon(ArrayList<Rayon> listRayon) {
        this.listRayon = listRayon;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public Button getButton3() {
        return button3;
    }

    public void setButton3(Button button3) {
        this.button3 = button3;
        
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    

}
