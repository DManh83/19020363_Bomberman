package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;

import static com.manh.bomberman.entitis.MapItem.SIZE;

public class Boom {
    private int x;
    private int y;
    public int checkBoom;
    private Image image;
    private final int lenghBoom;

    public Boom(int x, int y,int lenghBoom) {
        this.x = x-20;
        this.y = y;
        this.lenghBoom = lenghBoom;
        this.checkBoom = 1;
        this.image = new ImageIcon(getClass().getResource("/res/drawable/images/bomb.png")).getImage();
        boomBang();
    }

    public int isCheckBoom() {
        return checkBoom;
    }

    public void setCheckBoom(int checkBoom) {
        this.checkBoom = checkBoom;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y,SIZE,SIZE,null);
    }

    public Rectangle getRect(){
        return new Rectangle(x+15,y+15,SIZE-10,SIZE-10);
    }

    public BoomBang boomBang(){
        int xRaw= x-10;
        int yRaw= y-10;
        return new BoomBang(xRaw,yRaw, this.lenghBoom);
    }
}

