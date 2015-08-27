package com.swing.ex;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private CardLayout cards = new CardLayout();
    
    public MainPanel() {
        setLayout(cards);
    }
     
    
    public void changePanel(String selectedNum) {
        cards.show(this,selectedNum);
    }
}
