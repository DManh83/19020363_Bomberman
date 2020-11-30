package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;

public class MapItem extends Entity{
    public int bit;

    public MapItem(int x, int y, int bit) {
        super(x, y);
        this.bit = bit;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public Rectangle getRect(){
        return new Rectangle(getX(),getY()+15,SIZE-10,SIZE-10);
    }

    @Override
    public void draw(Graphics2D g2d){
        if (bit!=0) {
            g2d.drawImage(MY_IMAGE[bit-1], getX(), getY(),SIZE+2,SIZE+2,null);
        }
    }
}

