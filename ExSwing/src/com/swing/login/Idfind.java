package com.swing.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.swing.ex.FileRelated;
import com.swing.ex.Status;

class Idfind extends JDialog {
	JFrame jf;
	private void initStatus() {

		String[] stat = FileRelated.openFile("./properties/config.ini");
		for (String s : stat) {
			String[] split = s.split(" ");
			if (split[0].equals("isLogin")) {
				Status.add(split[0], false);
			} else
				Status.add(split[0], split[1]);
		}
	}
	public Idfind(JFrame jf) {
		//JDialog를 상속받아 설정 
		super(jf,"비밀번호 찾기",true);
		initStatus();
		setSize(400,180);
		this.jf = jf;
		setLayout(new FlowLayout());
		
		//이메일 입력 란, 패널에 등록
		JTextField txtfindemail = new JTextField("이메일 주소를 입력하여 주세요. ", 20);
		JLabel lblemail = new JLabel("이메일 입력 : ");
		JPanel pnlemail = new JPanel();
		pnlemail.add(lblemail);
		pnlemail.add(txtfindemail);
		
		//생년월일 입력 란, 패널에 등록
		JTextField txtfindbirth = new JTextField("생년월일을 입력하여 주세요.", 20);
		JLabel lblbirth = new JLabel("생년월일 입력 : ");
		JPanel pnlbirth = new JPanel();
		pnlbirth.add(lblbirth);
		pnlbirth.add(txtfindbirth);

		//버튼 생성
		JButton btnfind = new JButton("아이디 찾기");
		JButton btncancel = new JButton("나가기");
		
		//각패널들, 버튼 생성
		add(pnlemail);
		add(pnlbirth);
		add(btnfind);
		add(btncancel);
		
		//화면에 찾기 버튼을 누를 경우 실행 사항
		btnfind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			

			}
		});
		
		//화면의 취소 버튼을 누를 경우 실행 사항
		btncancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login(jf);
			}

		});

		setVisible(true);
	}

	public static void main(String[] args) {
		JFrame idfindform = new JFrame();	
		idfindform.setSize(400,200);
		idfindform.setDefaultCloseOperation(idfindform.EXIT_ON_CLOSE);
		
		new Idfind(idfindform);
		
		idfindform.setVisible(true);
	}

}
