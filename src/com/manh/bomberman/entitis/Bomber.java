package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bomber extends Entity{
    private int soBoom=1;
    private int speed=2;
    private int timeMove;
    private int lenghBoomBang=1;
    private boolean isPlayerRun= false;
    private int imageIndex=0;

    public Bomber(int x, int y, int orient, int timeMove) {
        super(x, y, orient);
        this.timeMove = timeMove;
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
    public int getOrient() {
        return super.getOrient();
    }

    @Override
    public void setOrient(int orient) {
        super.setOrient(orient);
    }

    @Override
    public void draw(Graphics2D g2d){
        switch (getOrient()){
            case LEFT:{
                if (isStop()){
                    g2d.drawImage(IMAGES_BOMBER_LEFT[0],getX(),getY(),SIZE+5,SIZE+15,null);
                }
                else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_BOMBER_LEFT[imageIndex / 10 % IMAGES_BOMBER_LEFT.length], getX(), getY(),SIZE+5,SIZE+15, null);
                }
                break;
            }
            case RIGHT:{
                if (isStop()){
                    g2d.drawImage(IMAGES_BOMBER_RIGHT[0],getX(),getY(),SIZE+5,SIZE+15,null);
                }
                else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_BOMBER_RIGHT[imageIndex / 10 % IMAGES_BOMBER_RIGHT.length],getX(), getY(), SIZE+5,SIZE+15,null);
                }
                break;
            }
            case UP:{
                if (isStop()){
                    g2d.drawImage(IMAGES_BOMBER_UP[0],getX(),getY(),SIZE+5,SIZE+15,null);
                }
                else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_BOMBER_UP[imageIndex / 10 % IMAGES_BOMBER_UP.length], getX(), getY(),SIZE+5,SIZE+15, null);
                }
                break;
            }
            case DOWN:{
                if (isStop()){
                    g2d.drawImage(IMAGES_BOMBER_DOWN[0],getX(),getY(),SIZE+5,SIZE+15,null);
                }
                else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_BOMBER_DOWN[imageIndex / 10 % IMAGES_BOMBER_LEFT.length], getX(), getY(),SIZE+5,SIZE+15,null);
                }
            }
            break;
        }
        isPlayerRun=false;
    }

    @Override
    public Rectangle getRect(){
        return new Rectangle(getX(),getY()+25,SIZE-10,SIZE-10);
    }

    public int getSoBoom() {
        return soBoom;
    }

    public void setSoBoom(int soBoom) {
        this.soBoom = soBoom+1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void changeOrient(int newOrient){
        setOrient(newOrient);
        isPlayerRun=true;
    }

    public boolean isStop() {
        return !isPlayerRun;
    }

    public boolean checkMoveMap(ArrayList<MapItem> arrMapItem){
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
        return false;
    }

    public void setMoveBoom(ArrayList<Bomb> arrBomb){
        for (Bomb bomb : arrBomb) {
            Rectangle rectangle = getRect().intersection(bomb.getRect());
            if (rectangle.isEmpty()) {
                bomb.setCheckBoom(0);
            }
        }
    }

    public boolean checkMoveBoom(ArrayList<Bomb> arrBomb){
        setMoveBoom(arrBomb);
        for (Bomb bomb : arrBomb) {
            Rectangle rectangle = getRect().intersection(bomb.getRect());
            if (!rectangle.isEmpty() && bomb.isCheckBoom() == 0) {
                return false;
            }
        }
        return true;
    }

    public void move(ArrayList<MapItem> arrMapItem, ArrayList<Bomb> arrBomb, int t){
        if (t%timeMove!=0){
            return;
        }
        int xRaw=getX();
        int yRaw=getY();
        switch (getOrient()){
            case LEFT:
                xRaw-=speed;
                break;
            case RIGHT:
                xRaw+=speed;
                break;
            case UP:
                yRaw-=speed;
                break;
            case DOWN:
                yRaw+=speed;
            default:
        }
        int xRaw1=getX();
        int yRaw1=getY();
        setX(xRaw);
        setY(yRaw);
        boolean checkMovePlayer= checkMoveMap(arrMapItem);
        boolean checkMovePlayerToBoom= checkMoveBoom(arrBomb);
        if (checkMovePlayer){
            setX(xRaw1);
            setY(yRaw1);
        }
        if (!checkMovePlayerToBoom){
            setX(xRaw1);
            setY(yRaw1);
        }
    }

    public boolean checkDieToBoss(ArrayList<Boss> arrBoss){
        for (Boss boss : arrBoss) {
            Rectangle rectangle = getRect().intersection(boss.getRect());
            if (!rectangle.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Bomb DatBoom(){
        int xRaw= getX()+SIZE/2;
        int yRaw= getY()+SIZE/2;
        int xBoom= xRaw-xRaw%SIZE+15;
        int yBoom= yRaw-yRaw%SIZE;
        int lengBoom=this.lenghBoomBang;
        return new Bomb(xBoom, yBoom,lengBoom);
    }

    public void moveItem(ArrayList<Item> arrItem){
        for (int i=0;i<arrItem.size();i++){
            Rectangle rectangle=getRect().intersection(arrItem.get(i).getRect());
            if (!rectangle.isEmpty()){
                if (arrItem.get(i).getBitItem()==0){
                    setSoBoom(soBoom);
                    arrItem.remove(i);
                }
                else if (arrItem.get(i).getBitItem()==1){
                    lenghBoomBang++;
                    arrItem.remove(i);
                }
                else if (arrItem.get(i).getBitItem()==2){
                    setSpeed(getSpeed()+1);
                    arrItem.remove(i);
                }
            }
        }
    }
}

