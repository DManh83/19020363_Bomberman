package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.manh.bomberman.entitis.MapItem.SIZE;

public class Item {
    private int x;
    private int y;
    private int bitItem;
    private Image image;
    public final Image[] ITEM_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/item_bomb.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/item_bombsize.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/item_shoe.png")).getImage(),
    };

    public Item(int x, int y) {
        Random random = new Random();
        int rd= random.nextInt(3);
        this.x = x;
        this.y = y;
        this.bitItem=rd;
        this.image=ITEM_IMAGE[rd];
    }

    public int getBitItem() {
        return bitItem;
    }

    public Rectangle getRect(){
        return new Rectangle(x+image.getWidth(null)/2,y+image.getHeight(null)/2,SIZE-30,SIZE-25);
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(image,x+4,y+4,SIZE-15,SIZE-15,null);
    }
}

