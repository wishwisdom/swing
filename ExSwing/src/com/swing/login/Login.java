package com.swing.login;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swing.ex.Status;
import com.swing.user.User;
import com.swing.user.UserDAO;

public class Login extends JDialog implements ActionListener {

	JFrame jf;
	JDialog self = this;
	JTextField txtlogid = new JTextField("test", 20);
	JTextField txtlogpw = new JPasswordField("1", 20);
	

	public Login(JFrame jf) {
		// �α��� �⺻ ȭ��
		super(jf, "Login", true);
		this.jf = jf;
		// Ÿ��Ʋ �Է�
		JLabel lbltitle = new JLabel("����2��");
		setSize(500, 600);
		lbltitle.setFont(new Font("�ü�ü", Font.BOLD, 36));
		setBounds(50, 100, 300, 200);
		setLayout(new FlowLayout());
		Status.add("isLogin", false);
		// ���̵� �ؽ�Ʈ�ʵ�
		txtlogid.selectAll();

		// ��й�ȣ �ؽ�Ʈ �ʵ�
		
		txtlogpw.selectAll();

		// �α��� ����ȭ���� ��ư
		JButton btnlogin = new JButton("Ȯ��");
		JButton btnregister = new JButton("ȸ������");

		// modal�� �������̽� �߰�

		add(lbltitle);
		add(txtlogid);
		add(txtlogpw);
		add(btnlogin);
		add(btnregister);

		// ȸ������ ��ư Ŭ�� �� �̺�Ʈ ����
		btnregister.addActionListener(this);
		// �α��� Ȯ�� ��ư Ŭ�� ��
		btnlogin.addActionListener(this);

		setVisible(true);
	}

	private void loginAction() {
		String textid = txtlogid.getText();
		String textpwd = txtlogpw.getText();
		UserDAO dao = UserDAO.getInstance();
		User user = dao.findUser(textid);
		if (user == null) {
			// ���̵� �߸� �Է½� ���̾�α� ��������
			JDialog modalidfalse = new JDialog(jf, "���̵� �߸� �Է��ϼ̾��", true);
			modalidfalse.setLayout(new FlowLayout());
			modalidfalse.setSize(300, 300);
			// ���̾�α׿� ǥ�õǴ� ��
			JLabel lblidfalse = new JLabel("���̵� �ؾ�����̳���?");
			// ���̾�α׿� ǥ�õǴ� ��ư
			JButton btnidfalseyes = new JButton("��(���̵� ã�ڽ��ϴ�)");
			JButton btnidfalseno = new JButton("�ƴϿ�(�α���ȭ������ �ǵ��������ּ���)");
			// ���̾�α׿� ���� ��ü�� ����
			modalidfalse.add(lblidfalse);
			modalidfalse.add(btnidfalseyes);
			modalidfalse.add(btnidfalseno);

			// ���̵� �߸� �Է����� �� ���̾�α׿��� ���̵� ã�ڽ��ϴ� ��ư�� Ŭ�� �� �߻��ϴ� �̺�Ʈ
			btnidfalseyes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*
					 * ã�´ٴ� ��ư�� Ŭ���Ǿ��� ��� ȭ���� �������� idfindŬ���� ����
					 */
					modalidfalse.setVisible(false);
					modalidfalse.dispose();
					new Idfind(jf);

				}

			});
			// ���̵� �߸� �Է����� �� ���̾� �α׿��� ���̵� ��ã�� ��ư Ŭ�� �� �߻��ϴ� �̺�Ʈ
			btnidfalseno.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// �ƴϿ� ��� ���� �� ���̵� �߸� �Է� �� ���̾� �αװ� ����ǰ� �α����� �������� �̵�
					modalidfalse.setVisible(false);
					modalidfalse.dispose();
				}
			});
			// �α��� ���� ȭ�鿡�� Ŀ���� ������ �ȱ�� ��ü ���� ��� ����ڰ� ����ϱ� ���ϰ�
			txtlogid.requestFocus();
			txtlogid.selectAll();
			modalidfalse.setVisible(true);

		} else if (!user.getPwd().equals(textpwd)) {
			// ���̵�� �°� ��й�ȣ�� �߸� �Է��� ��� ����Ǵ� ���̾�α� ����
			JDialog modalpwfalse = new JDialog(jf, "��й�ȣ�� �߸� �Է��ϼ̾��", true);
			modalpwfalse.setLayout(new FlowLayout());
			modalpwfalse.setSize(300, 300);
			JLabel pwfalsel = new JLabel("��й�ȣ�� �ؾ�����̳���?");
			// ��й�ȣ �� ���� ��� ���̾�α� ��ư ����
			JButton pwfalseyes = new JButton("��(��й�ȣ�� ã�ڽ��ϴ�)");
			JButton pwfalseno = new JButton("�ƴϿ�(�α���ȭ������ �ǵ��������ּ���)");
			// ���� ��ü���� ����
			modalpwfalse.add(pwfalsel);
			modalpwfalse.add(pwfalseyes);
			modalpwfalse.add(pwfalseno);
			// ��й�ȣ ���� ���̾�α׿��� ��й�ȣã�ڴٴ� ��ư Ŭ�� �� �߻� �̺�Ʈ
			pwfalseyes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// ���� �� ��й�ȣ ã�� Ŭ���� �ҷ�����
					modalpwfalse.setVisible(false);
					modalpwfalse.dispose();
					new Pwfind(jf);
				}

			});
			// ��й�ȣ ã�� ���̾� �α׿��� ��й�ȣ�� ã�� �ʰڴٴ� ��ư Ŭ�� �� �̺�Ʈ
			pwfalseno.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// ��й�ȣ ã�� ���̾� �α� ���� �� �α��� ���� ȭ�� ���
					modalpwfalse.setVisible(false);
					modalpwfalse.dispose();

				}
			});
			// �α��� ȭ�鿡�� ��й�ȣ Ŀ���� �Ǿ�����, ��ü ������ ����� �Է� ���ϰ�
			txtlogpw.requestFocus();
			txtlogpw.selectAll();
			modalpwfalse.setVisible(true);
		} else {
			// ���� ���� �� ����Ǵ� ���̾� �α� ����
			JDialog modalidpwtrue = new JDialog(jf, "���ӿ� �����ϼ̽��ϴ�.", true);
			modalidpwtrue.setLayout(new FlowLayout());
			modalidpwtrue.setSize(300, 300);
			JLabel lblidpwtrue = new JLabel(textid + "�� ȯ���մϴ�.");
			JButton truebt = new JButton("Ȯ��");
			// ���̾�α� ��ü ����
			modalidpwtrue.add(lblidpwtrue);
			modalidpwtrue.add(truebt);

			// statusŬ������ �α����� ����
			Status.add("isLogin", true);
			Status.add("user", user);
			// �α��� �����Է� �� ��ư Ŭ�� �� �̺�Ʈ
			truebt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// ���� �Է� �� �α��� ���� ���� ����
					modalidpwtrue.setVisible(false);
					modalidpwtrue.dispose();
					setVisible(false);

				}

			});

			modalidpwtrue.setVisible(true);

		}
	}

	private void regiterAction() {
		// ȸ������ ��ưŬ�� �� ���̾�α� ��������
		JDialog modalregister = new JDialog(jf, "ȸ������", true);
		modalregister.setLayout(new FlowLayout());
		modalregister.setSize(300, 300);
		JLabel lblregister = new JLabel("ȸ�������Ͻðڽ��ϱ�?");
		// ȸ������ ���̾�α� ��ư ����
		JButton btnregisteryes = new JButton("��");
		JButton btnregisterno = new JButton("�ƴϿ�");
		// ȸ�����Կ� ���� ��ü ���� ��� �߰�
		modalregister.add(lblregister);
		modalregister.add(btnregisteryes);
		btnregisteryes.setBounds(30, 40, 50, 50);
		modalregister.add(btnregisterno);
		btnregisterno.setBounds(100, 40, 50, 50);

		btnregisteryes.addActionListener(new ActionListener() {

			// ȸ�����Կ� ���̾�α׿��� ȸ������ �ϰڴٴ� ��ư Ŭ���� �̺�Ʈ
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ���� ���α׷� ���� �� �ּ����� ȸ������ Ŭ���� �� ����
				/*
				 * modalregister.setVisible(false); modalregister.dispose();
				 */
				modalregister.setVisible(false);
				new RegisterPanel(jf);
				modalregister.dispose();
			}
		});

		// ȸ�����Կ� ���̾�α׿��� ȸ������ ���Ѵٴ� ��ư Ŭ�� �� �̺�Ʈ
		btnregisterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ȸ������ ���̾�α� ���� �� �⺻ �α��� ȭ������
				modalregister.setVisible(false);
				modalregister.dispose();

			}
		});

		modalregister.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String event = ((JButton) e.getSource()).getText();

		switch (event) {
		case "ȸ������":
			regiterAction();
			break;
		case "Ȯ��":
			loginAction();
			break;
		}

	}
}
