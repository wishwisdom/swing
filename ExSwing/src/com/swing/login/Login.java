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
		// 로그인 기본 화면
		super(jf, "Login", true);
		this.jf = jf;
		// 타이틀 입력
		JLabel lbltitle = new JLabel("여행2조");
		setSize(500, 600);
		lbltitle.setFont(new Font("궁서체", Font.BOLD, 36));
		setBounds(50, 100, 300, 200);
		setLayout(new FlowLayout());
		Status.add("isLogin", false);
		// 아이디 텍스트필드
		txtlogid.selectAll();

		// 비밀번호 텍스트 필드
		
		txtlogpw.selectAll();

		// 로그인 메인화면의 버튼
		JButton btnlogin = new JButton("확인");
		JButton btnregister = new JButton("회원가입");

		// modal에 인터페이스 추가

		add(lbltitle);
		add(txtlogid);
		add(txtlogpw);
		add(btnlogin);
		add(btnregister);

		// 회원가입 버튼 클릭 시 이벤트 실행
		btnregister.addActionListener(this);
		// 로그인 확인 버튼 클릭 시
		btnlogin.addActionListener(this);

		setVisible(true);
	}

	private void loginAction() {
		String textid = txtlogid.getText();
		String textpwd = txtlogpw.getText();
		UserDAO dao = UserDAO.getInstance();
		User user = dao.findUser(textid);
		if (user == null) {
			// 아이디 잘못 입력시 다이얼로그 구성내용
			JDialog modalidfalse = new JDialog(jf, "아이디를 잘못 입력하셨어요", true);
			modalidfalse.setLayout(new FlowLayout());
			modalidfalse.setSize(300, 300);
			// 다이얼로그에 표시되는 라벨
			JLabel lblidfalse = new JLabel("아이디를 잊어버리셨나요?");
			// 다이얼로그에 표시되는 버튼
			JButton btnidfalseyes = new JButton("네(아이디를 찾겠습니다)");
			JButton btnidfalseno = new JButton("아니오(로그인화면으로 되돌려보내주세요)");
			// 다이얼로그에 위에 개체들 삽입
			modalidfalse.add(lblidfalse);
			modalidfalse.add(btnidfalseyes);
			modalidfalse.add(btnidfalseno);

			// 아이디 잘못 입력했을 때 다이얼로그에서 아이디를 찾겠습니다 버튼을 클릭 시 발생하는 이벤트
			btnidfalseyes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*
					 * 찾는다는 버튼이 클릭되었을 경우 화면이 숨겨지고 idfind클래스 실행
					 */
					modalidfalse.setVisible(false);
					modalidfalse.dispose();
					new Idfind(jf);

				}

			});
			// 아이디 잘못 입력했을 때 다이얼 로그에서 아이디를 안찾는 버튼 클릭 시 발생하는 이벤트
			btnidfalseno.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// 아니오 라고 누를 시 아이디 잘못 입력 시 다이얼 로그가 종료되고 로그인의 메인으로 이동
					modalidfalse.setVisible(false);
					modalidfalse.dispose();
				}
			});
			// 로그인 메인 화면에서 커서를 앞으로 옴기고 전체 블럭을 잡아 사용자가 사용하기 편하게
			txtlogid.requestFocus();
			txtlogid.selectAll();
			modalidfalse.setVisible(true);

		} else if (!user.getPwd().equals(textpwd)) {
			// 아이디는 맞고 비밀번호는 잘못 입력할 경우 실행되는 다이어로그 구성
			JDialog modalpwfalse = new JDialog(jf, "비밀번호를 잘못 입력하셨어요", true);
			modalpwfalse.setLayout(new FlowLayout());
			modalpwfalse.setSize(300, 300);
			JLabel pwfalsel = new JLabel("비밀번호를 잊어버리셨나요?");
			// 비밀번호 잘 못된 경우 다이어로그 버튼 구성
			JButton pwfalseyes = new JButton("네(비밀번호를 찾겠습니다)");
			JButton pwfalseno = new JButton("아니오(로그인화면으로 되돌려보내주세요)");
			// 위의 개체들을 삽입
			modalpwfalse.add(pwfalsel);
			modalpwfalse.add(pwfalseyes);
			modalpwfalse.add(pwfalseno);
			// 비밀번호 실패 다이어로그에서 비밀번호찾겠다는 버튼 클릭 시 발생 이벤트
			pwfalseyes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// 종료 후 비밀번호 찾기 클래스 불러오기
					modalpwfalse.setVisible(false);
					modalpwfalse.dispose();
					new Pwfind(jf);
				}

			});
			// 비밀번호 찾기 다이어 로그에서 비밀번호를 찾지 않겠다는 버튼 클릭 시 이벤트
			pwfalseno.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// 비밀번호 찾기 다이어 로그 종료 후 로그인 메인 화면 출력
					modalpwfalse.setVisible(false);
					modalpwfalse.dispose();

				}
			});
			// 로그인 화면에서 비밀번호 커서를 맨앞으로, 전체 블럭으로 사용자 입력 편리하게
			txtlogpw.requestFocus();
			txtlogpw.selectAll();
			modalpwfalse.setVisible(true);
		} else {
			// 접속 성공 시 실행되는 다이어 로그 구성
			JDialog modalidpwtrue = new JDialog(jf, "접속에 성공하셨습니다.", true);
			modalidpwtrue.setLayout(new FlowLayout());
			modalidpwtrue.setSize(300, 300);
			JLabel lblidpwtrue = new JLabel(textid + "님 환영합니다.");
			JButton truebt = new JButton("확인");
			// 다이어로그 개체 구성
			modalidpwtrue.add(lblidpwtrue);
			modalidpwtrue.add(truebt);

			// status클래스에 로그인의 정보
			Status.add("isLogin", true);
			Status.add("user", user);
			// 로그인 정상입력 후 버튼 클릭 시 이벤트
			truebt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// 정상 입력 시 로그인 메인 전부 종료
					modalidpwtrue.setVisible(false);
					modalidpwtrue.dispose();
					setVisible(false);

				}

			});

			modalidpwtrue.setVisible(true);

		}
	}

	private void regiterAction() {
		// 회원가입 버튼클릭 시 다이어로그 구성내용
		JDialog modalregister = new JDialog(jf, "회원가입", true);
		modalregister.setLayout(new FlowLayout());
		modalregister.setSize(300, 300);
		JLabel lblregister = new JLabel("회원가입하시겠습니까?");
		// 회원가입 다이어로그 버튼 구성
		JButton btnregisteryes = new JButton("네");
		JButton btnregisterno = new JButton("아니오");
		// 회원가입에 위의 개체 구성 요소 추가
		modalregister.add(lblregister);
		modalregister.add(btnregisteryes);
		btnregisteryes.setBounds(30, 40, 50, 50);
		modalregister.add(btnregisterno);
		btnregisterno.setBounds(100, 40, 50, 50);

		btnregisteryes.addActionListener(new ActionListener() {

			// 회원가입에 다이어로그에서 회원가입 하겠다는 버튼 클릭시 이벤트
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 현재 프로그램 내린 후 휘석형님 회원가입 클래스 에 연결
				/*
				 * modalregister.setVisible(false); modalregister.dispose();
				 */
				modalregister.setVisible(false);
				new RegisterPanel(jf);
				modalregister.dispose();
			}
		});

		// 회원가입에 다이어로그에서 회원가입 안한다는 버튼 클릭 시 이벤트
		btnregisterno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 회원가입 다이어로그 내린 후 기본 로그인 화면으로
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
		case "회원가입":
			regiterAction();
			break;
		case "확인":
			loginAction();
			break;
		}

	}
}
