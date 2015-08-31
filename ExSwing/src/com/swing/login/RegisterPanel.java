package com.swing.login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.swing.ex.FileRelated;
import com.swing.ex.Status;
import com.swing.user.User;
import com.swing.user.UserDAO;

public class RegisterPanel extends JDialog {
	JFrame jf;
	JPanel modalself = new JPanel();
	JTextField txtid, txtpwd, txtemail, txtbirth;// 클래스 변수로 선언
	JButton btnCancel;
	String idcontents = "아이디를 입력하여 주세요";
	String pwdcontents = "비밀번호를 입력하여 주세요";
	String emailcontents = "이메일을 입력하여 주세요";
	String birthcontents = "생년월일을 입력하여 주세요";

	void modalcreatecongratulation() {

		JDialog modalcreatecong = new JDialog(jf, "알림", true);
		modalcreatecong.setSize(200, 150);
		JLabel lblcreate = new JLabel("회원가입을 축하드립니다. 즐거운 감상되세요", JLabel.CENTER);
		JButton btncreate = new JButton("확인");
		// jf.setLayout(new FlowLayout());
		modalcreatecong.setLayout(new FlowLayout());
		modalcreatecong.add(lblcreate);
		modalcreatecong.add(btncreate);

		btncreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modalcreatecong.dispose();
			}
		});
		modalcreatecong.setVisible(true);
	}

	public RegisterPanel(JFrame jf) {
		// Layout 배치설정자
		super(jf, "회원가입", true);
		setSize(200, 500);
		this.jf = jf;
		setLayout(new FlowLayout());
		this.setLayout(new FlowLayout());

		// 아이디
		add(new JLabel("      아이디 :"));
		JLabel lblid = new JLabel(idcontents);
		txtid = new JTextField(15);
		add(txtid);
		add(lblid);

		// 비밀번호
		add(new JLabel("  패스워드 :"));
		JLabel lblpwd = new JLabel(pwdcontents);
		txtpwd = new JTextField(15);
		add(txtpwd);
		add(lblpwd);

		// 메일
		add(new JLabel("  메일주소 : "));
		add(new JLabel(" Ex) honggildong@naver.com "));
		JLabel lblemail = new JLabel(emailcontents);
		txtemail = new JTextField(15);
		add(txtemail);
		add(lblemail);

		// 생년월일
		add(new JLabel("  생년월일 :"));
		add(new JLabel(" Ex) 900125 (숫자 6자리 표현)"));
		JLabel lblbirth = new JLabel(birthcontents);
		txtbirth = new JTextField(6);
		add(txtbirth);
		add(lblbirth);

		JButton btnSignUp = new JButton("가입완료");
		btnCancel = new JButton("취소");
		add(btnSignUp);
		add(btnCancel);

		// 버튼 리스너 연결
		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 버튼에 필요한 변수들
				UserDAO dao = UserDAO.getInstance();
				String email = txtemail.getText();
				String birth = txtbirth.getText();
				String id = txtid.getText();
				String pwd = txtpwd.getText();
				Pattern ptbirth = Pattern.compile("[0-9]{6}");
				Matcher matcher = ptbirth.matcher(birth);

				boolean joinOK = true;
				
				if (id.equals("") || dao.hasUser(id) || id.indexOf(" ") > -1) {
					idcontents = "사용하실 수 없는 아이디입니다.";
					lblid.setText(idcontents);
					joinOK = false;
				}
				if (pwd.equals("") || pwd.indexOf(" ") > -1) {
					pwdcontents = "사용하실 수 없는 비밀번호입니다.";
					lblpwd.setText(pwdcontents);
					joinOK = false;
				}
				if (email.equals(null) || email.indexOf("@") < 0
						|| email.indexOf(" ") > -1) {
					emailcontents = "잘못된 이메일 주소 양식입니다.";
					lblemail.setText(emailcontents);
					joinOK = false;
				}
				if (!matcher.find()) {
					birthcontents = "잘못된 생년월일 양식입니다.";
					lblbirth.setText(birthcontents);
					joinOK = false;
				}
				// User user = dao.findUser(id);
				System.out.println(joinOK);
				if (joinOK) {
					dao.insertUser(new User(id, pwd, birth, email));
					FileRelated.saveTable("users.txt");
					
					modalcreatecongratulation();
					modalself.setVisible(false);
					dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

		setVisible(true);
	}
	/*
	 * public static void main(String[] args) { JFrame registerform = new
	 * JFrame();
	 * registerform.setDefaultCloseOperation(registerform.EXIT_ON_CLOSE);
	 * registerform.setSize(300, 300);
	 * 
	 * new RegisterPanel(registerform); }
	 */

}
