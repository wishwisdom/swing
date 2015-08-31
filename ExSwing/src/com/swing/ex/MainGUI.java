package com.swing.ex;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swing.login.TopMain;
import com.swing.user.User;

public class MainGUI extends JFrame {
	private JPanel jp = new JPanel();
	private JButton btnPrev;
	private JButton btnNext;

	private void LoginTest(){
		Status.add(Status.USER, new User("test1","dskajf"));
		Status.add("isLogin", new Boolean(true));
		// CurrentLocation�� ������ ������ Button���� �Է��ϰ� ���� ����� �̵��ϴ� ��!!
		//Status.add(location, "����");
	}
	public MainGUI() {
		{
			initStatus();
			LoginTest();
		}

		{
			setLayout(new BorderLayout(5,5));
			initHead();
		}
		// /----------------Main ����-------------------------//
		{
			Status.add("CurrentPanel", Status.get(Status.FIRSTPANEL));
			add((JPanel) (Status.get(Status.FIRSTPANEL)), BorderLayout.CENTER);
		}
		// /----------------Main End-------------------------//

		// Footer�κ� padding
		{
			JPanel jpFooter = new JPanel();
			jpFooter.setSize(this.getWidth(), 20);
			jpFooter.add(new JLabel());

			add(jpFooter, BorderLayout.SOUTH);
		}

	}
	
	private void initHead(){
		JPanel jpHead = new TopMain(this);
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

		// �̵��� panel�� �̸� ������ �д�.
		Status.add(Status.FIRSTPANEL, new FirstPanel(this));
		//Status.add(Status.SECOUNTPANEL, new LocationPanel(this));
		// Status.add(Status.DETAILPANEL, new DetailViewPanel(this));
	}

	public static void main(String[] args) {
		SwingConsole.run(new MainGUI(), 800, 700);
	}

}
