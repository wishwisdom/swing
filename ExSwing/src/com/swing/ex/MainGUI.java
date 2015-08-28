package com.swing.ex;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swing.user.User;

public class MainGUI extends JFrame {
	private JPanel jp = new JPanel();

	private void LoginTest(){
		Status.add(Status.USER, new User("test1","dskajf"));
		Status.add("isLogin", new Boolean(true));
		// CurrentLocation은 이전에 눌렀던 Button에서 입력하고 나서 여기로 이동하는 것!!
		//Status.add(location, "서울");
	}
	public MainGUI() {
		{
			initStatus();
			LoginTest();
		}

		{
			setLayout(new BorderLayout());
			initHead();
		}
		// /----------------Main 생성-------------------------//
		{

			add((JPanel) (Status.get(Status.FIRSTPANEL)), BorderLayout.CENTER);
		}
		// /----------------Main End-------------------------//

		// Footer부분 padding
		{
			JPanel jpFooter = new JPanel();
			jpFooter.setSize(this.getWidth(), 20);
			jpFooter.add(new JLabel());

			add(jpFooter, BorderLayout.SOUTH);
		}

	}
	
	private void initHead(){
		JPanel jpHead = new JPanel();
		jpHead.add(new JLabel(((User) (Status.get("user"))).getId()));
		jp.setSize(this.getWidth(), 80);

		// North pading jpanel
		add(jpHead, BorderLayout.NORTH);
	}

	private void initStatus() {

		String[] stat = FileRelated.openFile("./properties/config.ini");
		for (String s : stat) {
			String[] split = s.split(" ");
			if (split[0].equals("isLogin")) {
				Status.add(split[0], false);
			} else
				Status.add(split[0], split[1]);
		}

		// 이동할 panel을 미리 생성해 둔다.
		Status.add(Status.FIRSTPANEL, new FirstPanel(this));
		//Status.add(Status.SECOUNTPANEL, new LocationPanel(this));
		// Status.add(Status.DETAILPANEL, new DetailViewPanel(this));
		// DetailView
	}

	public static void main(String[] args) {
		SwingConsole.run(new MainGUI(), 700, 700);
	}

}
