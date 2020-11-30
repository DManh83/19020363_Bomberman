package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Item extends Entity{
    private int bitItem;

    public Item(int x, int y) {
        super(x, y);
        Random random = new Random();
        int rd= random.nextInt(3);
        this.bitItem=rd;
        setImage(ITEM_IMAGE[rd]);
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
    public Image getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(Image image) {
        super.setImage(image);
    }

    @Override
    public Rectangle getRect(){
        return new Rectangle(getX()+getImage().getWidth(null)/2,getY()+getImage().getHeight(null)/2,SIZE-30,SIZE-25);
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(getImage(),getX()+4,getY()+4,SIZE-15,SIZE-15,null);
    }

    public int getBitItem() {
        return bitItem;
    }
}

