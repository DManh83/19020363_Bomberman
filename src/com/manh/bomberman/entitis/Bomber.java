package com.manh.bomberman.entitis;

import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class Bomber extends Entity{
    private int soBomb =1;
    private int speed=2;
    private int timeMove;
    private int lenghBombBang =1;
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

    public int getSoBomb() {
        return soBomb;
    }

    public void setSoBomb(int soBomb) {
        this.soBomb = soBomb +1;
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

    public void setMoveBomb(ArrayList<Bomb> arrBomb){
        for (Bomb bomb : arrBomb) {
            Rectangle rectangle = getRect().intersection(bomb.getRect());
            if (rectangle.isEmpty()) {
                bomb.setCheckBomb(0);
            }
        }
    }

    public boolean checkMoveBomb(ArrayList<Bomb> arrBomb){
        setMoveBomb(arrBomb);
        for (Bomb bomb : arrBomb) {
            Rectangle rectangle = getRect().intersection(bomb.getRect());
            if (!rectangle.isEmpty() && bomb.isCheckBomb() == 0) {
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
        boolean checkMovePlayerToBomb= checkMoveBomb(arrBomb);
        if (checkMovePlayer){
            setX(xRaw1);
            setY(yRaw1);
        }
        if (!checkMovePlayerToBomb){
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

    public Bomb DatBomb(){
        int xRaw= getX()+SIZE/2;
        int yRaw= getY()+SIZE/2;
        int xBomb= xRaw-xRaw%SIZE+15;
        int yBomb= yRaw-yRaw%SIZE;
        int lengBomb=this.lenghBombBang;
        return new Bomb(xBomb, yBomb,lengBomb);
    }

    public void moveItem(ArrayList<Item> arrItem){
        for (int i=0;i<arrItem.size();i++){
            Rectangle rectangle=getRect().intersection(arrItem.get(i).getRect());
            if (!rectangle.isEmpty()){
                if (arrItem.get(i).getBitItem()==0){
                    setSoBomb(soBomb);
                    arrItem.remove(i);
                }
                else if (arrItem.get(i).getBitItem()==1){
                    lenghBombBang++;
                    arrItem.remove(i);
                }
                else if (arrItem.get(i).getBitItem()==2){
                    setSpeed(getSpeed()+1);
                    arrItem.remove(i);
                }
                Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/item.wav"));
                clip.start();
            }
        }
    }
}

