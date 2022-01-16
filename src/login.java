

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;












public class login extends JFrame {

	
	JLabel emailLbl, passwordLbl, titleLBl;
	JPanel titlePnl, centerPnl, emailLblPnl, emailTxtPnl, passwordLblPnl, passwordTxtPnl, loginBtnPnl;
	JTextField emailTxt;
	JPasswordField passwordTxt;
	JButton loginBtn;
	
	private Connect con = Connect.getConnection();
	public static int staffID; 
	
	public void init() {
		
		//Button
		loginBtn = new JButton("Login");

		
		//set Button
		loginBtn.setPreferredSize(new Dimension(150,40));
		loginBtn.setFocusPainted(false);
		loginBtn.setBorder(null);
		loginBtn.setForeground(Color.white);
		loginBtn.setBackground(Color.black);
		
		
		//Label
		titleLBl = new JLabel("Toko Laundry");
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");
		
		//Set Label
		titleLBl.setFont(new Font("Arial", Font.BOLD, 20));
		titleLBl.setForeground(Color.white);
		titleLBl.setPreferredSize(new Dimension(150,40));
		emailLbl.setPreferredSize(new Dimension(150,30));
		emailLbl.setFont(new Font("Arial", Font.BOLD, 12));
		emailLbl.setForeground(Color.white);
		passwordLbl.setPreferredSize(new Dimension(150,30));
		passwordLbl.setFont(new Font("Arial", Font.BOLD, 12));
		passwordLbl.setForeground(Color.white);
	
		
		//TextField
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension(180,30));
		emailTxt.setBorder(null);
		passwordTxt = new JPasswordField();
		passwordTxt.setPreferredSize(new Dimension(180,30));
		passwordTxt.setBorder(null);
	
		//Panel
		titlePnl = new JPanel();
		centerPnl = new JPanel(new GridLayout(2,2,0,15));
		emailLblPnl = new JPanel();
		emailTxtPnl = new JPanel();
		passwordLblPnl = new JPanel();
		passwordTxtPnl = new JPanel();
		loginBtnPnl = new JPanel();
		
		//Add comp to panel
		titlePnl.add(titleLBl);
		emailLblPnl.add(emailLbl);
		emailTxtPnl.add(emailTxt);
		passwordLblPnl.add(passwordLbl);
		passwordTxtPnl.add(passwordTxt);
		loginBtnPnl.add(loginBtn);
		centerPnl.add(emailLblPnl);
		centerPnl.add(emailTxtPnl);
		centerPnl.add(passwordLblPnl);
		centerPnl.add(passwordTxtPnl);
	
		//set Panel
		loginBtnPnl.setPreferredSize(new Dimension(200,100));
		loginBtnPnl.setOpaque(false);
		emailLblPnl.setOpaque(false);
		emailTxtPnl.setOpaque(false);
		passwordLblPnl.setOpaque(false);
		passwordTxtPnl.setOpaque(false);
		titlePnl.setOpaque(false);
		centerPnl.setOpaque(false);
		
	}
	
	public void validationData(ResultSet rs) {
		
		try {
			if (rs.next()) {
				staffID = rs.getInt("staffID");
				int role = rs.getInt("role");
				
				if (role == 2) {
					new kasirForm();
					dispose();
				}
				
			} else {
				JOptionPane.showMessageDialog(null,"Invalid email or password!","Alert", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listener() {
		loginBtn.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailTxt.getText();
				String password = passwordTxt.getText();
				String result = "";
				
				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else if (password.isEmpty()){
					JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					String query = "SELECT * FROM staff WHERE email =? and password =?";
					PreparedStatement ps = con.prepareStatement(query);
					
					try {
						ps.setString(1, email);
						ps.setString(2, password);
						validationData(ps.executeQuery());
						System.out.println(ps.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				
				}
				
				
				
			}
		});
	}
	
	public void frame() {
		init();
		listener();
		JPanel mainPanel = new JPanel(new BorderLayout());
		setVisible(true);
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Login Form");
		setLayout(new BorderLayout());
		add(mainPanel);
		mainPanel.add(titlePnl, BorderLayout.NORTH);
		mainPanel.add(centerPnl, BorderLayout.CENTER);
		mainPanel.add(loginBtnPnl, BorderLayout.SOUTH);
		mainPanel.setBackground(new Color(70, 113, 232));
	}
	
	public login() {
		frame();
	}
	
	
}
