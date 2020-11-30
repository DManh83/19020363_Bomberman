package com.manh.bomberman.entitis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BombBang extends Entity{
    public int lengh=2;
    private int lenghLeft = lengh;
    private int lenghRight = lengh;
    private int lenghUp = lengh;
    private int lenghDown = lengh;
    private int xBossDie;
    private int yBossDie;
    private int imageIndex=0;

    public BombBang(int x, int y, int lenghWave) {
        super(x, y);
        this.lenghLeft=lenghWave;
        this.lenghRight=lenghWave;
        this.lenghDown=lenghWave;
        this.lenghUp=lenghWave;
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
    public void draw(Graphics2D g2d) {

    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    public boolean checkBoomToPlayer(ArrayList<BombBang> arrBombBang, Bomber bomber){
        for (int i = 0; i< arrBombBang.size(); i++){
            Rectangle rectangle1= getRect(getX()+10,getY()+20).intersection(bomber.getRect());
            if (!rectangle1.isEmpty()){
                return true;
            }
            for (int j=1;j<=lenghLeft;j++){
                int xRaw = getX() - j * SIZE + 10;
                int yRaw = getY() + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(bomber.getRect());
                if (!rectangle.isEmpty()){
                    return true;
                }
            }
            for (int j=1;j<=lenghRight;j++){
                int xRaw = getX() + j * SIZE + 10;
                int yRaw = getY() + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(bomber.getRect());
                if (!rectangle.isEmpty()){
                    return true;
                }
            }
            for (int j=1;j<=lenghUp;j++){
                int xRaw = getX() + 10;
                int yRaw = getY() - j * SIZE + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(bomber.getRect());
                if (!rectangle.isEmpty()){
                    return true;
                }
            }
            for (int j=1;j<=lenghDown;j++){
                int xRaw = getX() + 10;
                int yRaw = getY() + j * SIZE + 20;
                Rectangle rectangle=getRect(xRaw,yRaw).intersection(bomber.getRect());
                if (!rectangle.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public void checkBoomToBoss(ArrayList<Boss> arrBoss) {
        for (int i = 0; i < arrBoss.size(); i++) {
            try {
                Rectangle rectangle1= getRect(getX()+10,getY()+20).intersection(arrBoss.get(i).getRect());
                if (!rectangle1.isEmpty()){
                    xBossDie= arrBoss.get(i).getX();
                    yBossDie=arrBoss.get(i).getY();
                    arrBoss.remove(i);
                }
                for (int j = 1; j <= lenghLeft; j++) {
                    int xRaw = getX() - j * SIZE + 10;
                    int yRaw = getY() + 20;
                    Rectangle rectangle0 = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (!rectangle0.isEmpty()) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        arrBoss.remove(i);
                    }
                }
                for (int j = 1; j <= lenghRight; j++) {
                    int xRaw = getX() + j * SIZE + 10;
                    int yRaw = getY() + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (!rectangle.isEmpty()) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        arrBoss.remove(i);
                    }
                }
                for (int j = 1; j <= lenghUp; j++) {
                    int xRaw = getX() + 10;
                    int yRaw = getY() - j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (!rectangle.isEmpty()) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        arrBoss.remove(i);

                    }
                }
                for (int j = 1; j <= lenghDown; j++) {
                    int xRaw = getX() + 10;
                    int yRaw = getY() + j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (!rectangle.isEmpty()) {
                        xBossDie= arrBoss.get(i).getX();
                        yBossDie=arrBoss.get(i).getY();
                        arrBoss.remove(i);
                    }
                }
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }

    public void checkBoomToBoom(ArrayList<Bomb> arrBomb, ArrayList<Integer> timeBoom) {
        for (int i = 0; i < arrBomb.size(); i++) {
            Rectangle rectangle1= getRect(getX()+10,getY()+20).intersection(arrBomb.get(i).getRect());
            if (!rectangle1.isEmpty()){
                timeBoom.set(i,0);
            }
            for (int j=1;j<=lenghLeft;j++) {
                int xRaw = getX() - j * SIZE + 10;
                int yRaw = getY() + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBomb.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghRight;j++) {
                int xRaw = getX() + j*SIZE + 10;
                int yRaw = getY() + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBomb.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghUp;j++) {
                int xRaw = getX() + 10;
                int yRaw = getY() - j*SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBomb.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j=1;j<=lenghDown;j++) {
                int xRaw = getX()+10;
                int yRaw = getY()+j*SIZE+20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBomb.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    timeBoom.set(i, 0);
                }
            }
        }
    }

    public void draw(Graphics2D g2d, ArrayList<MapItem> arrMapItem){
        drawMid(g2d);
        drawLeft(g2d, arrMapItem);
        drawRight(g2d, arrMapItem);
        drawUp(g2d, arrMapItem);
        drawDown(g2d, arrMapItem);
        if (xBossDie!=0 || yBossDie!=0) {
            Image image = new ImageIcon(getClass().getResource("/res/drawable/images/boss_die_1.png")).getImage();
            g2d.drawImage(image, xBossDie, yBossDie, null);
        }
    }

    public Rectangle getRect(int xRaw,int yRaw) {
        return new Rectangle(xRaw+5, yRaw+3, SIZE-10, SIZE-10);
    }

    public void drawMid(Graphics2D g2d) {
        g2d.drawImage(BOOM_BANG[4], getX() + 12, getY() + 16, null);
    }

    public void drawLeft(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghLeft; j++) {
            int xRaw = getX() - j * SIZE + 11;
            int yRaw = getY() + 20;
            if (j==lenghLeft) {
                g2d.drawImage(BOOM_BANG[0], xRaw+5, yRaw-6, null);
            }else {
                g2d.drawImage(BOOM_BANG[5], xRaw, yRaw-6, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        lenghLeft = lenghLeft - (lenghLeft - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghLeft = lenghLeft - (lenghLeft - j);
                    }
                }
            }
        }
    }

    public void drawRight(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghRight;j++) {
            int xRaw = getX() + j*SIZE + 15;
            int yRaw = getY() + 20;
            if (j==lenghRight) {
                g2d.drawImage(BOOM_BANG[1], xRaw-5, yRaw-6, null);
            }else {
                g2d.drawImage(BOOM_BANG[6], xRaw, yRaw-6, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        lenghRight = lenghRight - (lenghRight - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghRight = lenghRight - (lenghRight - j);
                    }
                }

            }
        }
    }

    public void drawUp(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghUp;j++) {
            int xRaw = getX() + 15;
            int yRaw = getY() - j*SIZE + 12;
            if (j==lenghUp) {
                g2d.drawImage(BOOM_BANG[2], xRaw, yRaw+5, null);
            }else {
                g2d.drawImage(BOOM_BANG[7], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        g2d.drawImage(MAP_DESTROY[imageIndex/20% MAP_DESTROY.length], arrMapItem.get(i).getX(), arrMapItem.get(i).getY(),null);
                        lenghUp = lenghUp - (lenghUp - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghUp = lenghUp - (lenghUp - j);
                    }
                }
            }

        }
    }

    public void drawDown(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j=1;j<=lenghDown;j++) {
            int xRaw = getX()+14;
            int yRaw = getY()+j*SIZE+18;
            if (j==lenghDown) {
                g2d.drawImage(BOOM_BANG[3], xRaw, yRaw-5, null);
            }else {
                g2d.drawImage(BOOM_BANG[8], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw,yRaw).intersection(arrMapItem.get(i).getRect());
                if (!rectangle.isEmpty()) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4
                            || arrMapItem.get(i).bit == 5 ) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        g2d.drawImage(MAP_DESTROY[imageIndex/20% MAP_DESTROY.length], arrMapItem.get(i).getX(), arrMapItem.get(i).getY(),null);
                        lenghDown = lenghDown - (lenghDown - j);

                    } else if (arrMapItem.get(i).bit != 2 && arrMapItem.get(i).bit != 4
                            && arrMapItem.get(i).bit != 5 && arrMapItem.get(i).bit != 0) {
                        lenghDown = lenghDown - (lenghDown - j);
                    }
                }
            }
        }
    }
}

