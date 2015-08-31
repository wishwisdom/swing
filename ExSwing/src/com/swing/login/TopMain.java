package com.swing.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swing.ex.FileRelated;
import com.swing.ex.LocationPanel;
import com.swing.ex.MyPagePanel;
import com.swing.ex.Status;

public class TopMain extends JPanel implements ActionListener{
	JFrame jf;
	JLabel lblloginstate;
	JLabel lblareaselect;
	JLabel lblLog;
	JButton btnlogin;
	JButton btnMyHome;
	String buttonname = "Login";
	String statename = "\" Guest \"";

	private void init(){
		// �α��� ���¿� ���� ��ȭ�ϴ� �� ����
		lblloginstate.setFont(new Font("Time", Font.BOLD, 13));
		lblloginstate.setForeground(Color.black);
		lblloginstate.setSize(140, 25);
		lblloginstate.setLocation(507, 55);
		
		// ������ �α��� ��ư����(���� �߰� �κ��� if������ logout��ư�� �ٲ�)
		btnlogin.setSize(100, 25);
		btnlogin.setLocation(500, 30);
	}
	
	private void createUI(){
		lblLog = new JLabel();
		lblLog.setIcon(FileRelated.openImage("log.jpg", 80, 40));
		
		lblloginstate = new JLabel(statename);
		btnlogin = new JButton(buttonname);
		btnMyHome = new JButton("MyHome");
		
		for(int i = 0 ; i < 3; i++){
			JPanel padding = new JPanel();
			padding.setSize(100,20);
			add(padding);
		}
		// �� ��ü �߰�
		add(lblLog);
		for(int i = 0 ; i < 17; i++){
			JPanel padding = new JPanel();
			padding.setSize(100,20);
			add(padding);
		}
		add(lblloginstate);
		add(btnMyHome);
		add(btnlogin);
		
		btnMyHome.setEnabled(false);
		btnMyHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel current = (JPanel)Status.get("CurrentPanel");
				JPanel nextPanel = new MyPagePanel(jf);
				
				
				current.setVisible(false);
				jf.remove(current);
				nextPanel.setVisible(true);
				jf.add( nextPanel, BorderLayout.CENTER);
				/*nextPanel.init();
				nextPanel.setVisible(true);
				nextPanel.repaint();*/
				jf.repaint();
				
				
			}
		});
	}
	public TopMain(JFrame JF) {
		this.jf = JF;
		//initStatus();
		setSize(640, 150);
		setLocation(0, 0);
		setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

		
		createUI();
		init();
		btnlogin.addActionListener(this);
	}

	private void loginAction(){
		new Login(jf);
		/*
		 * �α��ι�ư Ŭ�� �� �α���Ŭ������ �ҷ��� ����ϰ� �α���Ŭ�������� �α����� �̷������ �Ʒ��� ���� ��ư ������
		 * �Է��ϰ� �α��� ��ư�� ����� �α׾ƿ���ư�� �߰�
		 */
		
		if (Status.get("isLogin")!= null &&(Boolean) Status.get("isLogin")) {
			buttonname = "LogOut";
			statename = "ȯ���մϴ�.";
			btnlogin.setText(buttonname);
			lblloginstate.setText(statename);
			btnMyHome.setEnabled(true);
		} else {
			buttonname = "Login";
			statename = "\" Guest \"";
			btnlogin.setText(buttonname);
			lblloginstate.setText(statename);

		}
	}
	
	private void logOutAction(){
		int logOut = JOptionPane.showConfirmDialog(jf, "������ �α׾ƿ��� �ϽÁٽ��ϱ�?");
		switch(logOut){
			case JOptionPane.OK_OPTION:
				System.out.println("OK");
				btnMyHome.setEnabled(false);
				changePanel();
				FileRelated.saveTable("boards.txt");
				FileRelated.saveTable("users.txt");
				break;
			case JOptionPane.CANCEL_OPTION:
			case JOptionPane.NO_OPTION:
			case JOptionPane.CLOSED_OPTION:
				System.out.println("Cancel");
		}
	}
	private void changePanel(){
		buttonname = "Login";
		statename = "\" Guest \"";
		btnlogin.setText(buttonname);
		lblloginstate.setText(statename);
		
		JPanel current = (JPanel)Status.get("CurrentPanel");
		JPanel nextPane = (JPanel)Status.get("first");
		Status.add("CurrentPanel", Status.get(Status.FIRSTPANEL));
		
		
		
		current.setVisible(false);
		jf.remove(current);
		nextPane.setVisible(true);
		jf.add( nextPane, BorderLayout.CENTER);
		/*nextPanel.init();
		nextPanel.setVisible(true);
		nextPanel.repaint();*/
		jf.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String event = ((JButton)e.getSource()).getText();
		
		switch(event){
		case "Login":
			loginAction();
			//repaint();
			break;
		case "LogOut":
			logOutAction();
			
			break;
		}
		
	}
	

	/*public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(640, 150);
		jf.setLayout(null);

		jf.add(new TopMain(jf));

		jf.setVisible(true);

	}*/
}