package uvpm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings({ "serial", "unused" })
public class LoginPage extends JFrame {
	
	
	Connection con;
    ResultSet rs;


	private JPanel contentPane;
	private JTextField nameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginPage() {
		
		con=SqlConnection.ConnectDatabase();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 508);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your Name :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(26, 106, 197, 45);
		contentPane.add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(206, 114, 271, 33);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		
		JLabel lblNewLabel_pass = new JLabel("Enter Password");
		lblNewLabel_pass.setForeground(Color.WHITE);
		lblNewLabel_pass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_pass.setBounds(29, 171, 138, 33);
		contentPane.add(lblNewLabel_pass);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(206, 178, 271, 33);
		contentPane.add(passwordField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboBox.setBackground(Color.PINK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "HOD", "Security"}));
		comboBox.setBounds(206, 239, 271, 33);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Login As :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(32, 246, 114, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton login = new JButton("Login");
		login.setFont(new Font("Times New Roman", Font.BOLD, 20));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            
			            Statement s=con.createStatement();
			            String query="select * from user_info where u_name='"+nameTextField.getText()+"'";
			            ResultSet rs=s.executeQuery(query);
			            Store.name=nameTextField.getText();
			            String uType="";
			            String password="";
			            String pass= String.valueOf(passwordField.getPassword());
			            String type=comboBox.getSelectedItem().toString();
			            while(rs.next()) {
			                password=rs.getString("u_pass");
			                uType=rs.getString("u_type");
			            }
			            
			            if(uType.equals(type) && password.equals(pass)) {
			                if(type=="Student") {
			                	dispose();
			                	HomeStudent obj=new HomeStudent();
			                	obj.setTitle("Vechile Parking Management");
			                	obj.setVisible(true);
			                	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			                	obj.setResizable(true);
			                }
			                else if(type=="HOD") {
			                	dispose();
			                	HomeAdmin obj=new HomeAdmin();
			                	obj.setTitle("Vechile Parking Management");
			                	obj.setVisible(true);
			                	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			                	obj.setResizable(true);
			                }
			                else {
			                	dispose();
			                	HomeSecurity obj=new HomeSecurity();
			                	obj.setTitle("Vechile Parking Management");
			                	obj.setVisible(true);
			                	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			                	obj.setResizable(true);
			                }
			            }

			            else {
			                JOptionPane.showMessageDialog(login, "Wrong Username & Password",password, JOptionPane.WARNING_MESSAGE);
			            }
			        }
			        catch (Exception ex) {
			            System.out.println(ex);
			        }
			}
		});
		login.setForeground(Color.BLACK);
		login.setBounds(26, 316, 120, 37);
		contentPane.add(login);
		
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Times New Roman", Font.BOLD, 20));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					nameTextField.setText("");
					passwordField.setText("");
			}
		});
		reset.setForeground(Color.BLACK);
		reset.setBounds(380, 383, 121, 33);
		contentPane.add(reset);
		
		JLabel lblNewLabel_2 = new JLabel("               University Vechile Parking System");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_2.setBounds(10, 23, 665, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton newAcc = new JButton("New Account");
		newAcc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		newAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NewAccount obj=new NewAccount();
                obj.setTitle("Vechile Parking Management");
                obj.setVisible(true);
                obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                obj.setResizable(true);
			}
		});
		newAcc.setForeground(Color.BLACK);
		newAcc.setBounds(206, 316, 271, 37);
		contentPane.add(newAcc);
		
		JButton forgot = new JButton("Fogot Password");
		forgot.setFont(new Font("Times New Roman", Font.BOLD, 18));
		forgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
            	ForgotPassword obj=new ForgotPassword();
            	obj.setTitle("Vechile Parking Management");
            	obj.setVisible(true);
            	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	obj.setResizable(true);
			}
		});
		forgot.setForeground(Color.BLACK);
		forgot.setBounds(207, 383, 163, 33);
		contentPane.add(forgot);
		
		
		
	/*	JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/icons8_car_48px.png")));
		lblNewLabel_3.setBounds(29, 23, 63, 109);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		lblNewLabel_4.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/icons8_motorcycle_48px.png")));
		lblNewLabel_4.setBounds(130, 53, 72, 48);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		lblNewLabel_5.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/icons8_cyclist_40px.png")));
		lblNewLabel_5.setBounds(248, 56, 45, 48);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(" ");
		lblNewLabel_6.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/icons8_jeep_wrangler_64px.png")));
		lblNewLabel_6.setBounds(331, 31, 77, 73);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(" ");
		lblNewLabel_7.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/icons8_garage_48px_1.png")));
		lblNewLabel_7.setBounds(418, 30, 63, 94);
		contentPane.add(lblNewLabel_7); */
		
		
		JLabel lblNewLabel_8 = new JLabel(" ");
		lblNewLabel_8.setIcon(new ImageIcon(LoginPage.class.getResource("/uvpm/img/cutm-logo-1.png")));
		lblNewLabel_8.setBounds(763, 10, 96, 109);
		contentPane.add(lblNewLabel_8);
	}
}
