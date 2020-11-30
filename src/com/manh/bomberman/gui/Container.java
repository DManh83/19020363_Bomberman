package com.manh.bomberman.gui;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    public static final String PANEL_GAME= "PanelGame";
    public static final String PANEL_MENU= "PanelMenu";
    private final PanelGame panelGame;
    private final CardLayout cardLayout;
    public Container(){
        cardLayout =new CardLayout();
        panelGame=new PanelGame();
        PanelMenu panelMenu = new PanelMenu(this);
        setLayout(cardLayout);
        add(panelGame,PANEL_GAME);
        add(panelMenu,PANEL_MENU);
        cardLayout.show(this,PANEL_MENU);
        addKeyListener(panelGame);
        setFocusable(true);
    }
    public void showCard(String name){
        if (name.equals(PANEL_GAME)){
            cardLayout.show(this,name);
            panelGame.initPanelGame();
        }else if (name.equals(PANEL_MENU)){
            cardLayout.show(this,PANEL_MENU);
        }
    }
}
