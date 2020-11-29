package com.manh.bomberman.gui;

import com.manh.bomberman.manager.GameManager;
import com.manh.bomberman.entitis.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class PanelGame extends JPanel implements KeyListener,Runnable{
    private GameManager gameManager=new GameManager();
    private BitSet bitSet=new BitSet(256);
    boolean isRunning=true;
    public static final int TIME_DAT=20;
    public void initPanelGame() {
        gameManager.initGame();
        Thread t= new Thread(this);
        t.start();
        setFocusable(true);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.draw(g2d);
    }
    @Override
    public void run() {
        int time=0;
        int t=0;
        while (isRunning){
            t++;
            if (bitSet.get(KeyEvent.VK_LEFT)){
                gameManager.movePlayer(Player.LEFT);
            }else if (bitSet.get(KeyEvent.VK_RIGHT)){
                gameManager.movePlayer(Player.RIGHT);
            }else if (bitSet.get(KeyEvent.VK_UP)){
                gameManager.movePlayer(Player.UP);
            }else if (bitSet.get(KeyEvent.VK_DOWN)){
                gameManager.movePlayer(Player.DOWN);
            }try {
                if (bitSet.get(KeyEvent.VK_SPACE)){
                    if (t-time>=TIME_DAT) {
                        gameManager.myPlayerBoom(t);
                    }
                    time=t;
                }
            }catch (Exception ignored){
            }
            isRunning=gameManager.AI(t);
            if (!isRunning && !gameManager.isCheckWin()){
                int result= JOptionPane.showConfirmDialog(null,"Are you want Play Again?","GAME OVER",JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    bitSet.clear();
                    gameManager.initGame();
                    isRunning=true;
                }else {
                    System.exit(0);
                }
            }
            if (!isRunning && gameManager.isCheckWin()){
                int result= JOptionPane.showConfirmDialog(null,"Are you want Play Again?","YOU WIN",JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    bitSet.clear();
                    t=0;
                    gameManager.initGame();
                    isRunning=true;
                }else {
                    System.exit(0);
                }
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear();e.getKeyCode();
    }
}

