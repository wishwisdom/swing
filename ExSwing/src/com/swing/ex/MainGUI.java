package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGUI extends JFrame {
	private JPanel jp = new JPanel();
	private CardLayout card = new CardLayout();
	
	public MainGUI() {
		//System.out.println(FileRelated.openFile(this));
		setLayout(new BorderLayout());
		
		JPanel jpHead = new JPanel();
		jpHead.add(new JLabel("dkd"));
		jp.setSize(this.getWidth(), 80);
		
		// North pading jpanel
		add(jpHead,BorderLayout.NORTH);
		
		
		
		///----------------Main 생성-------------------------//
		
		JPanel jpFront = new FirstPanel(this);
		
		Status.add(Status.FIRSTPANEL, new FirstPanel(this));
		Status.add(Status.SECOUNTPANEL, new NextPanel(this));
		
		
		add(jpFront,BorderLayout.CENTER);
		
		///----------------Main End-------------------------//
		
		// Footer부분 padding
		JPanel jpFooter = new JPanel();
		jpFooter.setSize(this.getWidth(), 20);
		jpFooter.add(new JLabel("Footer"));
		
		add(jpFooter,BorderLayout.SOUTH);
		
		
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new MainGUI(), 700, 700);
		
	}

}
