package com.manh.bomberman.gui;

import com.manh.bomberman.manager.GameManager;
import com.manh.bomberman.entitis.Bomber;
import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class PanelGame extends JPanel implements KeyListener,Runnable{
    private final GameManager gameManager = new GameManager();
    private final BitSet bitSet = new BitSet(256);
    boolean isRunning=true;
    public static final int TIME_DAT=20;
    private int timeLose = 0;
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
                gameManager.movePlayer(Bomber.LEFT);
            }else if (bitSet.get(KeyEvent.VK_RIGHT)){
                gameManager.movePlayer(Bomber.RIGHT);
            }else if (bitSet.get(KeyEvent.VK_UP)){
                gameManager.movePlayer(Bomber.UP);
            }else if (bitSet.get(KeyEvent.VK_DOWN)){
                gameManager.movePlayer(Bomber.DOWN);
            }try {
                if (bitSet.get(KeyEvent.VK_SPACE)){
                    if (t-time>=TIME_DAT) {
                        gameManager.myPlayerBomb(t);
                    }
                    time=t;
                }
            }catch (Exception ignored){
            }
            isRunning=gameManager.AI(t);

            if (!isRunning && !gameManager.isCheckWin()){
                Clip clip = Sound.getSound(getClass().getResource("/res/drawable/sounds/die.wav"));
                clip.start();
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
                Clip clip=Sound.getSound(getClass().getResource("/res/drawable/sounds/win.wav"));
                clip.start();
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

