/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parabola;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Baumann
 */

public class Object {
    
    private int xPos;      
    private int yPos;
    private ImageIcon icon;    //icono.

    public Object(int xPos, int yPos, Image image) {
        this.xPos = xPos;
        this.yPos = yPos;
        icon = new ImageIcon(image);
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getXPos() {
        return xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setImageIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getImageIcon() {
        return icon;
    }

    public int getWidth() {
        return icon.getIconWidth();
    }

    public int getHeight() {
        return icon.getIconHeight();
    }

    public Image getImage() {
        return icon.getImage();
    }

    public Rectangle getRect() {
        return new Rectangle(getXPos(), getYPos(), getWidth(), getHeight());
    }

    public boolean intersects(Object obj) {
        return getRect().intersects(obj.getRect());
    }
    
    public Rectangle getRect2() {
        return new Rectangle(getXPos() + getWidth()/3, getYPos() + getHeight()/3, getWidth()/3, getHeight()/3);
    }

    public boolean intersects2(Object obj) {
        return getRect().intersects(obj.getRect2());
    }
    
    public boolean intersects(int x, int y) {
        return getRect().contains(x, y);
    }
    
}
