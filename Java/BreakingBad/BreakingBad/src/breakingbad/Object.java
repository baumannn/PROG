/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

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
    private ImageIcon icon;
    private boolean broken;
    private Animation anim;

    public Object(int xPos, int yPos, Image image) {
        this.xPos = xPos;
        this.yPos = yPos;
        icon = new ImageIcon(image);
        anim = new Animation();
        broken = false;
    }
    
    
    public void refresh(long time) {
        anim.refresh(time);
    }
    
    public boolean broken() {
        return broken;
    }
    
    public void setFrame(int i) {
        anim.setIndex(i);
    }
    public int getFrame() {
        return anim.getIndex();
    }
    
    public boolean realBroken() {
        if(anim.getIndex()==3) {
            return true;
        }
        return false;
    }
    
    public void breakBrick() {
        anim.setIndex(0);
        broken = true;
    }
    
    public void addFrame(Image img, long dur) {
        anim.addFrame(img, dur);
    }
    
    public void playOnce(){
        anim.playOnce();
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
        return anim.getImage();
    }

    public Rectangle getRect() {
        return new Rectangle(getXPos(), getYPos(), getWidth(), getHeight());
    }

    public boolean intersects(Object obj) {
        return getRect().intersects(obj.getRect());
    }
    
    public Rectangle getRect2() {
        return new Rectangle(getXPos() + 25, getYPos() + 15, 50, 30);
    }

    public boolean intersects2(Object obj) {
        return getRect().intersects(obj.getRect2());
    }
    
    public boolean intersects(int x, int y) {
        return getRect().contains(x, y);
    }
    
}
