package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import com.swing.Board.Board;
import com.swing.Board.BoardDAO;
import com.swing.user.User;

public class LocationPanel extends JPanel{
	private Status status;
	JFrame main;
	JPanel self =this;
	JPanel nextPanel = null;
	JPanel locatePanel = null;
	Dimension dfHead = new Dimension(650,200);
	/*Dimension dfMain = new Dimension(480,450);*/
	Dimension dfMain = new Dimension(630,400);
	Dimension dfBtn = new Dimension(150,150);
	int maxGapX = 10;
	int maxGapY = 10;
	
	private String location = "viewLocation";
	
	private void LoginTest(){
		Status.add(Status.USER, new User("test","dskajf"));
		Status.add("isLogin", new Boolean(true));
	}
	
	public LocationPanel(JFrame main){
		this.main = main;
		locatePanel = new JPanel();
		
		setLayout(new FlowLayout());
		
		//////////////////////////////////////////////////
		JPanel jpHeadLine = new JPanel();
		jpHeadLine.setLayout(new BorderLayout());
		jpHeadLine.setPreferredSize(dfHead);
		
		JButton jlHHH = new JButton("hhh");
		
		jpHeadLine.add(jlHHH, BorderLayout.CENTER);
		add(jpHeadLine);
		//////////////////////////////////////////////////
		initLayout();
	}
	public void initLayout(){
		///////----------------------------
		
		
		
		
		LoginTest();
		
		
		
		
		
		
		
		
		///////----------------------------
		// CurrentLocation은 이전에 눌렀던 Button에서 입력하고 나서 여기로 이동하는 것!!
		Status.add(location, "Location");
		//Status.add("isLogin",new Boolean(true));
		if(Status.get(location) == null){
			System.out.println("LocationPanel");
			return;
		}
		Board[] boards = BoardDAO.getInstance().getLocationBoard(String.valueOf(Status.get(location)));
		
		GridLayout g = new GridLayout(0,3,maxGapX,maxGapY);
		if(boards.length > 8){
			locatePanel.setLayout(g);
			
			locatePanel.setAutoscrolls(true);
			JScrollPane jsPane = new JScrollPane(locatePanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			//jsPane.setLayout(new ScrollPaneLayout());
			jsPane.setAutoscrolls(true);
			jsPane.setBounds(0, 0, dfMain.width, dfMain.height);
			//jsPane.setViewportView(locatePanel);
			add(jsPane);
			
		}else{
			locatePanel.setLayout(g);
			locatePanel.setSize(dfMain);
			add(locatePanel);
		}
		
		for(Board b : boards){
			JButton test = new JButton();
			test.setPreferredSize(dfBtn);
			
			test.setIcon(FileRelated.openImage(b.getImagePath(), dfBtn.width, dfBtn.height));
			test.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// Status에는 Object가 들어있다.
					//if(Status.get("isLogin") instanceof Boolean && (Boolean.parseBoolean(String.valueOf(Status.get("isLogin"))) == true)){
					if(Boolean.parseBoolean(String.valueOf(Status.get("isLogin"))) == true){
						self.setVisible(false);
						main.remove(self);
						
						// Board를 누르면 Detail View로 넘어가게 한다.
						//selBoard
						
						// Board를 넣긴다.
						Status.add("selBoard", b);
						
						
						nextPanel = new DetailViewPanel(main);
						main.add(nextPanel,BorderLayout.CENTER);
						nextPanel.setVisible(true);
						main.repaint();
					}else{
						System.out.println("not logined" + Status.get("isLogin"));
					}
				}
			});
			locatePanel.add(test);
		}
		
	}

}
