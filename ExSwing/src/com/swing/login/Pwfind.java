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
		// Jdilog ����
		super(jf, "��й�ȣ ã�� ", true);
		initStatus();
		setSize(300, 200);
		this.jf = jf;
		setLayout(new FlowLayout());
		// ���̵� �Է¶� �� �ؽ�Ʈ �ʵ�
		JLabel lblid = new JLabel("���̵� �Է� : ");
		JTextField txtfindid = new JTextField("���̵� �Է��Ͽ� �ּ���. ", 20);
		txtfindid.selectAll();

		// �̸��� �Է¶� �� �ؽ�Ʈ �ʵ�
		JLabel lblemail = new JLabel("�̸��� �Է� : ");
		JTextField txtfindemail = new JTextField("�̸��� �ּҸ� �Է��Ͽ� �ּ���. ", 20);
		txtfindemail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtfindemail.setText("");

			}
		});

		// ��ư ����
		JButton btnfind = new JButton("��й�ȣ ã��");
		JButton btncancel = new JButton("������");

		// ���̾�α׿� �� ��ü�� �߰�
		add(lblid);
		add(txtfindid);
		add(lblemail);
		add(txtfindemail);
		add(btnfind);
		add(btncancel);

		// ã�ڴٴ� ��ư Ŭ�� �� �߻� �̺�Ʈ
		btnfind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO dao = UserDAO.getInstance();
				String findid = txtfindid.getText();
				String findemail = txtfindemail.getText();
				JDialog modalfind = new JDialog(jf, "��й�ȣã��", true);
				modalfind.setLayout(new FlowLayout());
				modalfind.setSize(300, 300);
				JButton btnfind = new JButton("Ȯ��");
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
					modalfind.add(new JLabel("������ ��й�ȣ�� " + "[" + findpwd
							+ "]" + "�Դϴ�."));
					modalfind.add(btnfind);
					modalfind.setVisible(true);
				} else {
					modalfind.add(new JLabel("���ԵǾ� ���� ���� ���̵� �Դϴ�"));
					modalfind.setVisible(true);
				}
			}
		});
		// ��ҹ�ư Ŭ�� �� �߻� �̺�Ʈ
		btncancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ȭ�� ����
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
