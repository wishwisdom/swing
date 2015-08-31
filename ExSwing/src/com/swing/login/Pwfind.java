package com.swing.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.swing.ex.FileRelated;
import com.swing.ex.Status;
import com.swing.user.UserDAO;

public class Pwfind extends JDialog {
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

	public Pwfind(JFrame jf) {
		// Jdilog 설정
		super(jf, "비밀번호 찾기 ", true);
		initStatus();
		setSize(300, 200);
		this.jf = jf;
		setLayout(new FlowLayout());
		// 아이디 입력라벨 및 텍스트 필드
		JLabel lblid = new JLabel("아이디 입력 : ");
		JTextField txtfindid = new JTextField("아이디를 입력하여 주세요. ", 20);
		txtfindid.selectAll();

		// 이메일 입력라벨 및 텍스트 필드
		JLabel lblemail = new JLabel("이메일 입력 : ");
		JTextField txtfindemail = new JTextField("이메일 주소를 입력하여 주세요. ", 20);
		txtfindemail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtfindemail.setText("");

			}
		});

		// 버튼 생성
		JButton btnfind = new JButton("비밀번호 찾기");
		JButton btncancel = new JButton("나가기");

		// 다이어로그에 각 객체들 추가
		add(lblid);
		add(txtfindid);
		add(lblemail);
		add(txtfindemail);
		add(btnfind);
		add(btncancel);

		// 찾겠다는 버튼 클릭 시 발생 이벤트
		btnfind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO dao = UserDAO.getInstance();
				String findid = txtfindid.getText();
				String findemail = txtfindemail.getText();
				JDialog modalfind = new JDialog(jf, "비밀번호찾기", true);
				modalfind.setLayout(new FlowLayout());
				modalfind.setSize(300, 300);
				JButton btnfind = new JButton("확인");
				btnfind.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						modalfind.setVisible(false);
						modalfind.dispose();
						setVisible(false);
						dispose();

					}

				});

				if (dao.hasUser(findid)) {
					String findpwd = dao.findUser(findid).getPwd();
					modalfind.add(new JLabel("고객님의 비밀번호는 " + "[" + findpwd
							+ "]" + "입니다."));
					modalfind.add(btnfind);
					modalfind.setVisible(true);
				} else {
					modalfind.add(new JLabel("가입되어 있지 않은 아이디 입니다"));
					modalfind.setVisible(true);
				}
			}
		});
		// 취소버튼 클릭 시 발생 이벤트
		btncancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재화면 종료
				setVisible(false);
				dispose();

			}

		});
		setVisible(true);
	}

	/*public static void main(String[] args) {
		JFrame pwfindform = new JFrame();
		pwfindform.setSize(600, 600);
		pwfindform.setDefaultCloseOperation(pwfindform.EXIT_ON_CLOSE);
		pwfindform.add(new Pwfind(pwfindform));

		pwfindform.setVisible(true);
	}*/
}
