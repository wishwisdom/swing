package com.swing.ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

public class MyPagePanel extends JPanel{
	private JFrame main;
	private JPanel self = this;
	private JPanel jpTop;
	private JPanel jpBoard;
	
	private JLabel lblID;
	
	private final int PANELWIDTH = 650;
	private final int PANELHEIGHT= 700;
	private final int PROFILEWIDTH = 150;
	private final int PROFILEHEIGHT = 130;
	
	private String imageProfilePath;
	
	private Dimension dmTop = new Dimension(PANELWIDTH,100);
	
	public MyPagePanel(JFrame main){
		this.main = main;
		//testInit();
		setLayout(new BorderLayout());
		//setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		initPanel();
		Status.add("CurrentPanel", this);
	}
	
	private void initPanel(){
		initTop();
		initBoard();
	}
	
	private void initTop(){
		jpTop = new JPanel(new FlowLayout());
		jpTop.setSize(dmTop);
//		imageProfilePath;
		User user = (User)Status.get("user");
		
		
		JButton btnImageProfile = new JButton("프로필 사진을 \n넣어주세요.");
		// Button의 모양을 없애주는 부분
		if(user.getProfilePath() != null){
			btnImageProfile.setText(null);
			btnImageProfile.setIcon(FileRelated.openCircleImage(user.getProfilePath(), PROFILEWIDTH, PROFILEHEIGHT));
			btnImageProfile.setOpaque(false);
		}
		btnImageProfile.setBorderPainted(false);
		//btnImageProfile.setBorderPainted(false);
		btnImageProfile.setContentAreaFilled(false);
		btnImageProfile.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		/**/
		/////////////////////////////
		btnImageProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeProfile();
				User user = (User)Status.get("user");
				
				if(imageProfilePath != null && !imageProfilePath.equals("")){
					System.out.println(imageProfilePath);
					user.setProfilePath(imageProfilePath);
					//((JButton)e.getSource()).setIcon( FileRelated.openCircleImage(user.getProfilePath(), 100, 100));
					btnImageProfile.setText(null);
					btnImageProfile.setIcon(FileRelated.openCircleImage(user.getProfilePath(), PROFILEWIDTH, PROFILEHEIGHT));
					btnImageProfile.setOpaque(false);
					btnImageProfile.setBorderPainted(false);
					btnImageProfile.setBorderPainted(false);
					btnImageProfile.setContentAreaFilled(false);
					btnImageProfile.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
					
				}
			}
		});
		
		/////////////////////////////
		
		lblID = new JLabel(user.getId());
		lblID.setFont(new Font(lblID.getFont().getFontName(),Font.TYPE1_FONT,40));
		lblID.setPreferredSize(new Dimension(PROFILEWIDTH,PROFILEHEIGHT));
		
		jpTop.add(btnImageProfile);
		jpTop.add(lblID);

		add(jpTop, BorderLayout.NORTH);
	}
	
	private void changeProfile(){
		imageProfilePath = FileRelated.openFile(this);
		if(imageProfilePath.equals("CANCEL")){
			imageProfilePath = "";
		}else if(imageProfilePath == null){
			imageProfilePath = "";
		}
	}
	private void initBoard(){
		jpBoard = new JPanel(new BorderLayout());
		JPanel jpContainScroll = new JPanel(new GridLayout(3,3,10,10));
		
		JScrollPane scroll = new JScrollPane(jpContainScroll);
		scroll.setPreferredSize(new Dimension(PANELWIDTH,PANELHEIGHT-100));
		
		jpBoard.setSize(PANELWIDTH, PANELHEIGHT - 160);
		
		BoardDAO dao = BoardDAO.getInstance();
		User user = (User) Status.get("user");
		Board[] results = dao.getMyBoard(user.getId());
		
		
		for(Board board : results){
			JButton image = new JButton();
			image.setName(""+board.getBoardNum());
			image.setIcon(FileRelated.openImage(board.getImagePath(), 150, 150));
			image.setBorderPainted(false);
			image.setBorderPainted(false);
			image.setContentAreaFilled(false);
			image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jpContainScroll.add(image);
			image.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Status에는 Object가 들어있다.
					//if(Status.get("isLogin") instanceof Boolean && (Boolean.parseBoolean(String.valueOf(Status.get("isLogin"))) == true)){
					if(Boolean.parseBoolean(String.valueOf(Status.get("isLogin"))) == true){
						self.setVisible(false);
						main.remove(self);
						
						Status.add("selBoard", board);
						
						JPanel nextPanel = new DetailViewPanel(main);
						
						main.add(nextPanel,BorderLayout.CENTER);
						nextPanel.setVisible(true);
						main.repaint();
					}else{
						System.out.println("not logined" + Status.get("isLogin"));
					}
				}
			});
			
			
		}
		
		jpBoard.add(scroll,BorderLayout.CENTER);
		jpBoard.setBorder(BorderFactory.createLineBorder(Color.black));
		add(jpBoard,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.SOUTH);
	}
	
	/*private void testInit(){
		String[] stat = FileRelated.openFile("./properties/config.ini");
		for(String s : stat){
			String[] split = s.split(" ");
			if(split[0].equals("isLogin")){
				Status.add(split[0], false);
			}else
				Status.add(split[0], split[1]);
		}
		Status.add(Status.USER, new User("test1","asdf"));
		Status.add("selBoard",new Board(1,"test","yeonah1.jpg","서울",3,2,"15/08/20",1,"contents"));
		Status.add(Status.PREPANEL, new FirstPanel(main));
		//who = new Board(1,"test","ye1onah1.jpg","contents","서울",3,1,"15/08/20",1);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		jf .add(new MyPagePanel(jf));
		
		SwingConsole.run(jf, 700, 600);

	}*/

}
