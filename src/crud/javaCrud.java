package crud;
import java.awt.EventQueue;
import java.sql.*;		

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mysql.cj.xdevapi.Table;

import net.proteanit.sql.DbUtils;

import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class javaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCrud window = new javaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaCrud() {
		initialize();
		Connect();
		table_load();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con ;
	PreparedStatement pst= null;
	ResultSet rs;
	private JTextField textField;
	private JTable table;
	
	public void Connect() {
		try {
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			 
			con=DriverManager.getConnection("jdbc:mysql://localhost/javacrud","root","");
		}
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load() {
		
		try {
			pst=con.prepareStatement("select * from book");
			rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	//	panel_1.setModel(DbUtils.)	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void initialize() {	
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(130, 10, 158, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 149, 315, 240);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 30, 135, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(28, 83, 135, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(28, 147, 135, 31);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(141, 38, 96, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(141, 91, 96, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(141, 155, 96, 19);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				bname= txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText(); 
				try {
					pst = con.prepareStatement("insert into book (name,edition,price)values (?,?,?)");
					pst.setString(1,bname);
					pst.setString(2,edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Addeddd...!!!!");
					//tableload();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}
				catch(SQLException el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 198, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnExit.setBounds(105, 198, 85, 21);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(200, 198, 85, 21);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(353, 149, 303, 240);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 233, 293, -218);
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 399, 315, 63);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book Id");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(10, 10, 135, 31);
		panel_2.add(lblNewLabel_1_1_2);
		
//		textField_3.setColumns(10);
//		textField_3.setBounds(162, 18, 96, 19);
//		panel_2.add(textField_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 18, 96, 19);
		panel_2.add(textField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(372, 425, 85, 21);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(494, 425, 85, 21);
		frame.getContentPane().add(btnDelete);
	}
}
