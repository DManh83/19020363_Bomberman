package com.manh.bomberman.manager;

import com.manh.bomberman.entitis.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import static com.manh.bomberman.entitis.MapItem.SIZE;
import static com.manh.bomberman.gui.Gui.H_FARME;
import static com.manh.bomberman.gui.Gui.W_FARME;

public class GameManager {
    private Bomber bomber;
    private ArrayList<MapItem> arrMapItem;
    private ArrayList<Bomb> arrBomb;
    private ArrayList<Boss> arrBoss;
    private ArrayList<BombBang> arrBombBang;
    private ArrayList<Item> arrItem;
    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;
    private int timeDie;
    private boolean checkWin;
    private Random random=new Random();
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/res/drawable/images/background.jpg")).getImage()
    };

    public boolean isCheckWin() {
        return checkWin;
    }

    public void setCheckWin(boolean checkWin) {
        this.checkWin = checkWin;
    }

    public void initGame(){
        timeBoom=new ArrayList<>();
        timeWave=new ArrayList<>();
        arrBomb =new ArrayList<>();
        arrBoss= new ArrayList<>();
        arrBombBang = new ArrayList<>();
        arrItem=new ArrayList<>();
        bomber =new Bomber(W_FARME/2,H_FARME-90-SIZE, Bomber.DOWN,1);
        arrMapItem =new ArrayList<>();
        readMap();
        initBoss();
        initItem();
    }

    public void initBoss(){
        for (int i=0;i<5;i++){
            int orient= random.nextInt(4);
            MapItem point= arrMapItem.get(random.nextInt(255));
            while (point.bit!=0) {
                point= arrMapItem.get(random.nextInt(255));
            }
            int xRaw=point.getX();
            int yRaw=point.getY();
            Boss boss=new Boss(xRaw,yRaw,orient);
            arrBoss.add(boss);
        }
    }

    public void initItem(){
        for (MapItem mapItem : arrMapItem) {
            int show = random.nextInt(100) + 1;
            if (show > 80 && (mapItem.bit == 2
                    || mapItem.bit == 4 || mapItem.bit == 5)) {
                int xRaw = mapItem.getX();
                int yRaw = mapItem.getY();
                Item item = new Item(xRaw, yRaw);
                arrItem.add(item);
            }
        }
    }

    public void movePlayer(int newOrient){
        bomber.changeOrient(newOrient);
        bomber.move(arrMapItem, arrBomb,1);
        bomber.moveItem(arrItem);
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(MY_IMAGE[0],0,0,W_FARME,H_FARME,null);
        try {
            for (Bomb bomb : arrBomb) {
                bomb.draw(g2d);
            }
            for (BombBang bombBang : arrBombBang) {
                bombBang.draw(g2d, arrMapItem);
            }
            for (Item item:arrItem){
                item.draw(g2d);
            }
            for (MapItem mapItem : arrMapItem) {
                mapItem.draw(g2d);
            }

            for (Boss boss : arrBoss) {
                boss.drawBoss(g2d);
            }
            bomber.draw(g2d);
        }catch (ConcurrentModificationException ignored){

        }
    }

    public void myPlayerBoom(int t){
        Bomb bomb = bomber.DatBoom();
        if (arrBomb.size()< bomber.getSoBoom()){
            arrBomb.add(bomb);
            timeBoom.add(t);
        }
    }

    public boolean AI(int t){
        for (int i=arrBoss.size()-1;i>=0;i--){
            arrBoss.get(i).moveBoss(arrMapItem, arrBomb);
        }
        for (int i = 0; i< arrBomb.size(); i++){
            if (t-timeBoom.get(i) >=TIME_BANG){
                BombBang bombBang = arrBomb.get(i).boomBang();
                arrBomb.remove(i);
                arrBombBang.add(bombBang);
                timeBoom.remove(i);
                try {
                    bombBang.checkBoomToBoom(arrBomb, timeBoom);
                }catch (IndexOutOfBoundsException ignored){
                }
                timeWave.add(t);
            }
        }
        for (int i = 0; i< arrBombBang.size(); i++){
            arrBombBang.get(i).checkBoomToBoss(arrBoss);
            if (t-timeWave.get(i)>=TIME_WAVE){
                arrBombBang.remove(i);
                timeWave.remove(i);
            }
        }
        if (bomber.checkDieToBoss(arrBoss)){
            setCheckWin(false);
            return false;
        }
        for (int i = 0; i< arrBombBang.size(); i++){
            if(arrBombBang.get(i).checkBoomToPlayer(arrBombBang, bomber)){
                timeDie=t;
                setCheckWin(false);
                return false;
            }
        }
        if (arrBoss.isEmpty()){
            setCheckWin(true);
            return false;
        }
        return true;
    }

    public void readMap(){

        int intLine=0;
        try {
            String path = getClass().getResource("/res/data/map1.txt").getPath();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line= br.readLine();
            while (line!=null){
                for (int i=0;i<line.length();i++){
                    arrMapItem.add(new MapItem(i*SIZE,intLine*SIZE,Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line= br.readLine();
                intLine++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


