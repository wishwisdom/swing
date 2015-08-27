package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGUI extends JFrame {
	private JPanel jp = new JPanel();
	
	public MainGUI() {
		{
			initStatus();
		}
		
		{
			setLayout(new BorderLayout());
			JPanel jpHead = new JPanel();
			jpHead.add(new JLabel("dkd"));
			jp.setSize(this.getWidth(), 80);
			
			// North pading jpanel
			add(jpHead,BorderLayout.NORTH);
		}
		///----------------Main 생성-------------------------//
		{
			JPanel jpFront = new FirstPanel(this);
			add(jpFront,BorderLayout.CENTER);
		}
		///----------------Main End-------------------------//
		
		// Footer부분 padding
		{
			JPanel jpFooter = new JPanel();
			jpFooter.setSize(this.getWidth(), 20);
			jpFooter.add(new JLabel());
			
			add(jpFooter,BorderLayout.SOUTH);
		}
		
	}
	
	private void initStatus(){
		
		String[] stat = FileRelated.openFile("./properties/config.ini");
		for(String s : stat){
			String[] split = s.split(" ");
			if(split[0].equals("isLogin")){
				Status.add(split[0], false);
			}else
				Status.add(split[0], split[1]);
		}
		
		// 이동할 panel을 미리 생성해 둔다.
		Status.add(Status.FIRSTPANEL, new FirstPanel(this));
		Status.add(Status.SECOUNTPANEL, new LocationPanel(this));
		//Status.add(Status.DETAILPANEL, new DetailViewPanel(this));
		// DetailView
	}
	
	public static void main(String[] args) {
		SwingConsole.run(new MainGUI(), 700, 700);
	}

}
