package com.manh.bomberman.gui;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    public static final String PANEL_GAME= "PanelGame";
    public static final String PANEL_MENU= "PanelMenu";
    private PanelGame panelGame;
    private PanelMenu panelMenu;
    private CardLayout cardLayout;
    public Container(){
        cardLayout =new CardLayout();
        panelGame=new PanelGame();
        panelMenu=new PanelMenu(this);
        setLayout(cardLayout);
        add(panelGame,PANEL_GAME);
        add(panelMenu,PANEL_MENU);
        cardLayout.show(this,PANEL_MENU);
        addKeyListener(panelGame);
        setFocusable(true);
    }
    public void showCard(String name){
        if (name == PANEL_GAME){
            cardLayout.show(this,name);
            panelGame.initPanelGame();
        }else if (name == PANEL_MENU){
            cardLayout.show(this,PANEL_MENU);
        }
    }
}
