package com.manh.bomberman.entitis;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boss extends Entity{
    private final Random random= new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public Boss(int x, int y, int orient) {
        super(x, y, orient);
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
    public int getOrient() {
        return super.getOrient();
    }

    @Override
    public void setOrient(int orient) {
        super.setOrient(orient);
    }

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
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
    public void draw(Graphics2D g2d){ g2d.drawImage(getImage(),getX(),getY(),SIZE,SIZE,null); }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX(),getY()+15,SIZE-10,SIZE-5);
    }

    public void changeOrient(int newOrient){
        setOrient(newOrient);
    }

    public void creatOrient(){
        int percent= random.nextInt(100);
        if (percent>95){
            int newOrient=random.nextInt(4);
            changeOrient(newOrient);
            setImage(BOSS[newOrient]);
        }
    }

    public boolean checkMoveBomb(ArrayList<Bomb> arrBomb){
        for (Bomb bomb : arrBomb) {
            Rectangle rectangle = getRect().intersection(bomb.getRect());
            if (!rectangle.isEmpty() && bomb.isCheckBomb() == 0) {
                return false;
            }
        }
        return true;
    }

    public void moveBoss(ArrayList<MapItem> arrMapItem, ArrayList<Bomb> arrBomb) {
        int speed = 2;
        int xRaw = getX();
        int yRaw = getY();
        switch (getOrient()) {
            case LEFT:
                xRaw -= speed;
                break;
            case RIGHT:
                xRaw += speed;
                break;
            case UP:
                yRaw -= speed;
                break;
            case DOWN:
                yRaw += speed;
            default:
        }
        int xRaw1 = getX();
        int yRaw1 = getY();
        setX(xRaw);
        setY(yRaw);
        boolean checkMoveBoss = checkMove(arrMapItem);
        boolean checkMoveBossBomb= checkMoveBomb(arrBomb);
        if (checkMoveBoss){
            setX(xRaw1);
            setY(yRaw1);
        }
        if (!checkMoveBossBomb){
            setX(xRaw1);
            setY(yRaw1);
        }
        creatOrient();
    }

    public boolean checkMove(ArrayList<MapItem> arrMapItem) {
        for (MapItem mapItem : arrMapItem){
            if (mapItem.bit == 5 || mapItem.bit == 1 || mapItem.bit == 2 || mapItem.bit == 3 ||
                    mapItem.bit == 4 || mapItem.bit== 6 ||  mapItem.bit== 7 || mapItem.bit== 8
                    || mapItem.bit== 9) {
                Rectangle rectangle = getRect().intersection(mapItem.getRect());
                if (!rectangle.isEmpty()) {
                    return true;
                }
            }
        }
        return  false;
    }
}

