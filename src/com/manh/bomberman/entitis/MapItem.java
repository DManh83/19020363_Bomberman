package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;

public class MapItem {
    private int x;
    private int y;
    public int bit;
    public static final int SIZE=45;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/stone.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/wood.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/tree.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boxleft.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boxright.png")).getImage(),
    };

    public MapItem(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y+15,SIZE-10,SIZE-10);
    }
    public void draw(Graphics2D g2d){
        if (bit!=0) {
            g2d.drawImage(MY_IMAGE[bit-1], x,y,SIZE+2,SIZE+2,null);
        }
    }
}

