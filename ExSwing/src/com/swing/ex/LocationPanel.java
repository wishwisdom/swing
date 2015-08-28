package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.swing.Board.Board;
import com.swing.Board.BoardDAO;
import com.swing.user.User;

public class LocationPanel extends JPanel{
	JFrame main;
	JPanel self =this;
	JPanel nextPanel = null;
	JPanel locatePanel = null;
	Dimension dfHead = new Dimension(600,300);
	/*Dimension dfMain = new Dimension(480,450);*/
	Dimension dfMain = new Dimension(630,400);
	Dimension dfBtn = new Dimension(150,150);
	int maxGapX = 10;
	int maxGapY = 10;
	
	private String location = "viewLocation";
	
	/*private void LoginTest(){
		Status.add(Status.USER, new User("test1","dskajf"));
		Status.add("isLogin", new Boolean(true));
		// CurrentLocation은 이전에 눌렀던 Button에서 입력하고 나서 여기로 이동하는 것!!
		//Status.add(location, "서울");
	}*/
	
	public LocationPanel(JFrame main){
		this.main = main;
		
		
		init();
		
	}
	public void init(){
		locatePanel = new JPanel();
		setLayout(new FlowLayout());
		initTopImage();
		initLayout();
	}
	
	private void initTopImage(){
		JPanel jpHeadLine = new JPanel();
		jpHeadLine.setLayout(new BorderLayout());
		jpHeadLine.setPreferredSize(dfHead);
		
		JLabel lblTop = new JLabel();
		lblTop.setIcon(FileRelated.openImage(String.valueOf(Status.get(Status.LOCTION))+".jpg", dfHead.width, dfHead.height));
		
		
		jpHeadLine.add(lblTop, BorderLayout.CENTER);
		add(jpHeadLine);
	}
	
	public void initLayout(){
		///////----------------------------
		
		//LoginTest();
		//Status.add("isLogin",new Boolean(true));
		
		if(Status.get(location) == null){
			System.out.println("LocationPanel");
			return;
		}
		Board[] boards = BoardDAO.getInstance().getLocationBoard(String.valueOf(Status.get(location)));
		
		
		locatePanel.setLayout(new BorderLayout());
		
		JPanel jpContainer = new JPanel(new FlowLayout());
		JScrollPane jsPane = new JScrollPane(jpContainer);
		jsPane.setPreferredSize(new Dimension(650,318));
			{
				for(Board b : boards){
					JButton test = new JButton();
					test.setPreferredSize(dfBtn);
					test.setBorderPainted(false);
					test.setBorderPainted(false);
					test.setContentAreaFilled(false);
					test.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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
					jpContainer.add(test);
				}
			}
		locatePanel.add(jsPane, BorderLayout.CENTER);
		add(locatePanel);
	}
}
