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
		//JDialog�� ��ӹ޾� ���� 
		super(jf,"��й�ȣ ã��",true);
		initStatus();
		setSize(400,180);
		this.jf = jf;
		setLayout(new FlowLayout());
		
		//�̸��� �Է� ��, �гο� ���
		JTextField txtfindemail = new JTextField("�̸��� �ּҸ� �Է��Ͽ� �ּ���. ", 20);
		JLabel lblemail = new JLabel("�̸��� �Է� : ");
		JPanel pnlemail = new JPanel();
		pnlemail.add(lblemail);
		pnlemail.add(txtfindemail);
		
		//������� �Է� ��, �гο� ���
		JTextField txtfindbirth = new JTextField("��������� �Է��Ͽ� �ּ���.", 20);
		JLabel lblbirth = new JLabel("������� �Է� : ");
		JPanel pnlbirth = new JPanel();
		pnlbirth.add(lblbirth);
		pnlbirth.add(txtfindbirth);

		//��ư ����
		JButton btnfind = new JButton("���̵� ã��");
		JButton btncancel = new JButton("������");
		
		//���гε�, ��ư ����
		add(pnlemail);
		add(pnlbirth);
		add(btnfind);
		add(btncancel);
		
		//ȭ�鿡 ã�� ��ư�� ���� ��� ���� ����
		btnfind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			

			}
		});
		
		//ȭ���� ��� ��ư�� ���� ��� ���� ����
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
