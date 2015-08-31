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
	JTextField txtid, txtpwd, txtemail, txtbirth;// Ŭ���� ������ ����
	JButton btnCancel;
	String idcontents = "���̵� �Է��Ͽ� �ּ���";
	String pwdcontents = "��й�ȣ�� �Է��Ͽ� �ּ���";
	String emailcontents = "�̸����� �Է��Ͽ� �ּ���";
	String birthcontents = "��������� �Է��Ͽ� �ּ���";

	void modalcreatecongratulation() {

		JDialog modalcreatecong = new JDialog(jf, "�˸�", true);
		modalcreatecong.setSize(200, 150);
		JLabel lblcreate = new JLabel("ȸ�������� ���ϵ帳�ϴ�. ��ſ� ����Ǽ���", JLabel.CENTER);
		JButton btncreate = new JButton("Ȯ��");
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
		// Layout ��ġ������
		super(jf, "ȸ������", true);
		setSize(200, 500);
		this.jf = jf;
		setLayout(new FlowLayout());
		this.setLayout(new FlowLayout());

		// ���̵�
		add(new JLabel("      ���̵� :"));
		JLabel lblid = new JLabel(idcontents);
		txtid = new JTextField(15);
		add(txtid);
		add(lblid);

		// ��й�ȣ
		add(new JLabel("  �н����� :"));
		JLabel lblpwd = new JLabel(pwdcontents);
		txtpwd = new JTextField(15);
		add(txtpwd);
		add(lblpwd);

		// ����
		add(new JLabel("  �����ּ� : "));
		add(new JLabel(" Ex) honggildong@naver.com "));
		JLabel lblemail = new JLabel(emailcontents);
		txtemail = new JTextField(15);
		add(txtemail);
		add(lblemail);

		// �������
		add(new JLabel("  ������� :"));
		add(new JLabel(" Ex) 900125 (���� 6�ڸ� ǥ��)"));
		JLabel lblbirth = new JLabel(birthcontents);
		txtbirth = new JTextField(6);
		add(txtbirth);
		add(lblbirth);

		JButton btnSignUp = new JButton("���ԿϷ�");
		btnCancel = new JButton("���");
		add(btnSignUp);
		add(btnCancel);

		// ��ư ������ ����
		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ư�� �ʿ��� ������
				UserDAO dao = UserDAO.getInstance();
				String email = txtemail.getText();
				String birth = txtbirth.getText();
				String id = txtid.getText();
				String pwd = txtpwd.getText();
				Pattern ptbirth = Pattern.compile("[0-9]{6}");
				Matcher matcher = ptbirth.matcher(birth);

				boolean joinOK = true;
				
				if (id.equals("") || dao.hasUser(id) || id.indexOf(" ") > -1) {
					idcontents = "����Ͻ� �� ���� ���̵��Դϴ�.";
					lblid.setText(idcontents);
					joinOK = false;
				}
				if (pwd.equals("") || pwd.indexOf(" ") > -1) {
					pwdcontents = "����Ͻ� �� ���� ��й�ȣ�Դϴ�.";
					lblpwd.setText(pwdcontents);
					joinOK = false;
				}
				if (email.equals(null) || email.indexOf("@") < 0
						|| email.indexOf(" ") > -1) {
					emailcontents = "�߸��� �̸��� �ּ� ����Դϴ�.";
					lblemail.setText(emailcontents);
					joinOK = false;
				}
				if (!matcher.find()) {
					birthcontents = "�߸��� ������� ����Դϴ�.";
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
