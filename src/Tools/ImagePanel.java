/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author gerardj
 */
public class ImagePanel extends JPanel {
    
    Image image;

  public ImagePanel() {
    image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\gerardj\\Desktop\\java\\Magnetto\\Magnetto wheels\\img\\50648.gif");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }
  }
}
