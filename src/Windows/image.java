/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author moi
 */
public class image extends JPanel {
    
    Image image;
    public image(){
        image=(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))).getImage();
    }
    
    public void paintComponent(Graphics g)
   {
     g.drawImage (image, 0, 0, null); // elle doit etre avant
     super.paintComponent(g); // lui il s'occupe de redessiner les composant enfant.
   }
}
