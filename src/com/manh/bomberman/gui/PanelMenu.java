package com.manh.bomberman.gui;

import res.drawable.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel implements ActionListener {
    private JButton jbStart;
    private JButton jbExit;
    public static final String START="start";
    public static final String EXIT="exit";
    private Container container;
    public PanelMenu(Container container){
        initPanelMenu();
        initComponents();
        initListener();
        this.container = container;
    }

    public final Image[] image={
            new ImageIcon(getClass().getResource("/res/drawable/images/menupanel.jpg")).getImage(),
    };

    public final Icon[] icons={
            new ImageIcon(getClass().getResource("/res/drawable/images/startButton1.png")),
            new ImageIcon(getClass().getResource("/res/drawable/images/exitButton1.png")),
            new ImageIcon(getClass().getResource("/res/drawable/images/startButton2.png")),
            new ImageIcon(getClass().getResource("/res/drawable/images/exitButton2.png")),
    };

    private void initPanelMenu() {
        setBackground(Color.green);
        setLayout(null);
    }
    private void initComponents() {
        jbStart =new JButton(icons[0]);
        jbStart.setRolloverIcon(icons[2]);
        jbStart.setSize(icons[0].getIconWidth(),icons[0].getIconHeight());
        jbStart.setLocation(Gui.W_FARME/3 - 50, Gui.H_FARME-jbStart.getHeight()-80);
        add(jbStart);


        jbExit =new JButton(icons[1]);
        jbExit.setRolloverIcon(icons[3]);
        jbExit.setSize(icons[2].getIconWidth(),icons[0].getIconHeight());
        jbExit.setLocation(jbStart.getX()+jbStart.getWidth()+50,jbStart.getY());
        add(jbExit);
    }

    public void initListener(){
        jbStart.addActionListener(this);
        jbStart.setActionCommand(START);
        jbExit.addActionListener(this);
        jbExit.setActionCommand(EXIT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(image[0],0,0, Gui.W_FARME, Gui.H_FARME,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run= e.getActionCommand();
        switch (run){
            case START:{
                Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/click.wav"));
                clip.start();
                container.showCard(Container.PANEL_GAME);
                break;
            }
            case EXIT:{
                Clip clip= Sound.getSound(getClass().getResource("/res/drawable/sounds/click.wav"));
                clip.start();
                System.exit(0);
            }
        }
    }
}
