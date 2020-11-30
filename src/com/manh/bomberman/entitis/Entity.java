package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;

public abstract class Entity {
    private int x;
    private int y;
    private int orient;
    private int speed;
    private Image image;

    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int UP=2;
    public static final int DOWN=3;
    public static final int SIZE = 45;


    public final Image[] IMAGES_BOMBER_LEFT = {
            new ImageIcon(getClass().getResource("/res/drawable/images/player_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_left_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_left_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_left_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_left_5.png")).getImage(),
    };
    public final Image[] IMAGES_BOMBER_RIGHT = {
            new ImageIcon(getClass().getResource("/res/drawable/images/player_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_right_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_right_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_right_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_right_5.png")).getImage(),
    };
    public final Image[] IMAGES_BOMBER_UP = {
            new ImageIcon(getClass().getResource("/res/drawable/images/player_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_up_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_up_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_up_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_up_5.png")).getImage(),
    };

    public final Image[] IMAGES_BOMBER_DOWN = {
            new ImageIcon(getClass().getResource("/res/drawable/images/player_down_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_down_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_down_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_down_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/player_down_5.png")).getImage(),
    };

    public final Image[] BOSS ={
            new ImageIcon(getClass().getResource("/res/drawable/images/boss_left.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boss_right.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boss_up.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boss_down.png")).getImage(),
    };

    public final Image BOMB = new ImageIcon(getClass().getResource("/res/drawable/images/bomb.png")).getImage();

    public final Image[] BOOM_BANG = {
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_left_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_right_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_up_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images//bombbang_down_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_mid_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_left_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_right_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_up_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/bombbang_down_1.png")).getImage(),

    };

    public final Image[] MAP_DESTROY ={
            new ImageIcon(getClass().getResource("/res/drawable/images/del_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/del_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/del_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/del_4.png")).getImage(),
    };

    public final Image[] ITEM_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/item_bomb.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/item_bombsize.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/item_shoe.png")).getImage(),
    };

    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/stone.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/wood.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/tree.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boxleft.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/boxright.png")).getImage(),
    };

    public Entity(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void draw(Graphics2D g2d);

    public abstract Rectangle getRect();
}
