package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;

public class Bomb extends Entity{
    public int checkBoom;
    private int lenghBoom;

    public Bomb(int x, int y, int lenghBoom) {
        super(x - 20, y);
        this.lenghBoom = lenghBoom;
        this.checkBoom = 1;
        setImage(BOMB);
        boomBang();
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

    public int isCheckBoom() {
        return checkBoom;
    }

    public void setCheckBoom(int checkBoom) {
        this.checkBoom = checkBoom;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(getImage(),getX(),getY(),SIZE,SIZE,null);
    }

    public Rectangle getRect(){
        return new Rectangle(getX()+15,getY()+15,SIZE-10,SIZE-10);
    }

    public BombBang boomBang(){
        int xRaw= getX()-10;
        int yRaw= getY()-10;
        return new BombBang(xRaw,yRaw, this.lenghBoom);
    }
}

