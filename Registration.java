package uvpm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({ "unused", "serial" })
public class Registration extends JFrame {
	
	
	Connection con;
    ResultSet rs;

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField vNoTxt;
	private JTextField insNoTxt;
	private JTextField dlNoTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		
		con=SqlConnection.ConnectDatabase();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 589);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	/*	JLabel name = new JLabel("Enter your Name :");
		name.setForeground(new Color(0, 255, 255));
		name.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		name.setBounds(36, 54, 223, 42);
		contentPane.add(name);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(284, 57, 291, 39);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);  */
		
		JLabel vNo = new JLabel("Vechile Number :");
		vNo.setForeground(new Color(0, 255, 255));
		vNo.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		vNo.setBounds(36, 106, 205, 33);
		contentPane.add(vNo);
		
		vNoTxt = new JTextField();
		vNoTxt.setBounds(284, 109, 291, 33);
		contentPane.add(vNoTxt);
		vNoTxt.setColumns(10);
		
		JLabel insNo = new JLabel("Insurance Number :");
		insNo.setForeground(new Color(0, 255, 255));
		insNo.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		insNo.setBounds(36, 149, 228, 33);
		contentPane.add(insNo);
		
		insNoTxt = new JTextField();
		insNoTxt.setBounds(284, 152, 291, 33);
		contentPane.add(insNoTxt);
		insNoTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DL Number :");
		lblNewLabel.setForeground(new Color(0, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(44, 200, 215, 26);
		contentPane.add(lblNewLabel);
		
		dlNoTxt = new JTextField();
		dlNoTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = dlNoTxt.getText();
	            int l = value.length();
	            if (l<=16) {
	               dlNoTxt.setEditable(true);
	              
	            } else {
	               dlNoTxt.setEditable(false);
	              
	            }
			}
		});
		dlNoTxt.setBounds(284, 195, 291, 30);
		contentPane.add(dlNoTxt);
		dlNoTxt.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(250, 235, 215));
		comboBox.setForeground(new Color(255, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2 Wheeler", "4 Wheeler"}));
		comboBox.setBounds(358, 300, 143, 26);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Choose your Vechile Type :");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(57, 291, 291, 41);
		contentPane.add(lblNewLabel_1);
		
		JButton apply = new JButton("Apply");
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=Store.name;
				String vNumber=vNoTxt.getText();
                String insurance= insNoTxt.getText();
                String dlNo =dlNoTxt.getText();
                String type=comboBox.getSelectedItem().toString();
                if(name.length()==0) {
                	JOptionPane.showMessageDialog(apply, "Please Fill name");
                }
                else if(vNumber.length()==0) {
                	JOptionPane.showMessageDialog(apply, "Please Fill Vechile Number");
                }
                else {
                  try {
                    

                    String query = "INSERT INTO register_vechile (s_name,vechile_number,insu_number,dl_number,v_type,approval)values('" + name + "','" +vNumber + "','" +insurance + "','" +dlNo + "','"+type+"','Waiting' );";

                    Statement sta = con.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x > 0) {
                        JOptionPane.showMessageDialog(apply, "Welcome,  You have sucessfully applied for parking solt");
                        dispose();
                        HomeStudent obj=new HomeStudent();
       	                obj.setTitle("Vechile Parking Management");
       	                obj.setVisible(true);
       	                obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	                obj.setResizable(true);
                    } else {
                        JOptionPane.showMessageDialog(apply,
                                "Please fill all fields");
                    }
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                }
			}
		});
		
		apply.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		apply.setForeground(new Color(0, 255, 0));
		apply.setBounds(108, 375, 163, 42);
		contentPane.add(apply);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==reset){
					nameTxt.setText("");
					vNoTxt.setText("");
					insNoTxt.setText("");
					dlNoTxt.setText("");
	            }
			}
		});
		reset.setForeground(Color.RED);
		reset.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		reset.setBounds(338, 375, 117, 42);
		contentPane.add(reset);
	}
}
