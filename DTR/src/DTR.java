import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.crypto.Data;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import javax.print.attribute.standard.DocumentName;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDayChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class DTR {
	private JFrame frame;
	private JLabel lblClock;
	private JLabel fullName;
	private JTextField name;
	private JLabel department;
	private JTextField departmentField;
	private JLabel headOfDepartment;
	private JTextField headOfDepartmenttextField;
	private JLabel lblMonth;
	private JDateChooser dateChooser;
	private JTable table;
	private JComboBox comboBox;
	private String[] type = {"Class", "Consultation", "Relative Activities", "Others"};
	private JLabel lblTimelbl;
	private JLabel lblDate;
	private JLabel lblDate_1;
	private JButton btnGeneratePdf;
	private JLabel employeeIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DTR window = new DTR();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clock() {
		
		Thread clock = new Thread() {
			public void run() {
				try {
					while(true) {
						Calendar cal = new GregorianCalendar();
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						int year = cal.get(Calendar.YEAR);
						
						int second = cal.get(Calendar.SECOND);
						int minutes = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR_OF_DAY);
						
						
						SimpleDateFormat sdf12  = new SimpleDateFormat("hh:mm:ss aa");
						Date date = cal.getTime();
						String time12 = sdf12.format(date);
						
//						lblClock.setText("Time "+ hour +":"+minutes+":"+second+" Date "+month+"/"+day+"/"+year);
//						lblClock.setText(hour +":"+minutes+":"+second);
						lblClock.setText(time12);

						lblDate_1.setText(month + 1 +"/"+day+"/"+year);
						
						sleep(1000);
					}
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	public void btnNewButton() {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		int second = cal.get(Calendar.SECOND);
		int minutes = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);

		lblClock.setText("Time "+ hour +":"+minutes+":"+second+" Date "+month+"/"+day+"/"+year);
	}

	/**
	 * Create the application.
	 */
	public DTR() {
		initialize();
		clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 204));
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBackground(UIManager.getColor("menu"));
		frame.setBounds(500, 100, 900, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Faculty Daily Time Record");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 51, 51));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel.setBounds(96, 26, 304, 50);
		frame.getContentPane().add(lblNewLabel);
		
		lblClock = new JLabel("Time");
		lblClock.setForeground(new Color(0, 51, 51));
		lblClock.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblClock.setBounds(473, 45, 134, 14);
		frame.getContentPane().add(lblClock);
		
		fullName = new JLabel("Full Name: ");
		fullName.setForeground(new Color(0, 51, 51));
		fullName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		fullName.setBounds(10, 128, 80, 15);
		frame.getContentPane().add(fullName);
		
		name = new JTextField();
		name.setBackground(new Color(255, 245, 238));
		name.setBounds(10, 153, 238, 27);
		name.setColumns(10);
		frame.getContentPane().add(name);

		
		department = new JLabel("Department: ");
		department.setForeground(new Color(0, 51, 51));
		department.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		department.setBounds(10, 189, 90, 15);
		frame.getContentPane().add(department);
		
		departmentField = new JTextField();
		departmentField.setBackground(new Color(255, 245, 238));
		departmentField.setColumns(10);
		departmentField.setBounds(10, 214, 238, 27);
		frame.getContentPane().add(departmentField);
		
		headOfDepartment = new JLabel("Head of \r\ndepartment: ");
		headOfDepartment.setForeground(new Color(0, 51, 51));
		headOfDepartment.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		headOfDepartment.setBounds(10, 250, 145, 15);
		frame.getContentPane().add(headOfDepartment);
		
		headOfDepartmenttextField = new JTextField();
		headOfDepartmenttextField.setBackground(new Color(255, 245, 238));
		headOfDepartmenttextField.setColumns(10);
		headOfDepartmenttextField.setBounds(10, 278, 238, 27);
		frame.getContentPane().add(headOfDepartmenttextField);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(10, 342, 238, 27);
		frame.getContentPane().add(dateChooser_1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(250, 240, 230));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		DefaultTableModel model  = new DefaultTableModel();
		
		// Labels on table
		Object[] row = new Object[4];

		//Time in and insert other information.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				save();
				
				String selectedValue = comboBox.getSelectedItem().toString();

				if(name.getText().trim().isEmpty() || departmentField.getText().equals("") || 
						sdf.format(dateChooser_1.getDate()).equals(null) || headOfDepartmenttextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter all data!");
					// clear all text fields
					name.setText("");
					departmentField.setText("");
					headOfDepartmenttextField.setText("");
					dateChooser_1.setDate(new Date());
				}
				
				// get Time In
				lblClock.getText();
				
				// clear all text fields
				name.setText("");
				departmentField.setText("");
				headOfDepartmenttextField.setText("");
				dateChooser_1.setDate(new Date());
				
				// insert inputed data to the table
				row[0] = sdf.format(dateChooser_1.getDate());
				row[1] = lblClock.getText();
				row[2] = "Holiday"; 
				row[3] = selectedValue;
				model.addRow(row);
			}
		});
		btnNewButton.setBounds(664, 466, 74, 74);
		Image img = new ImageIcon(this.getClass().getResource("Finger-Print-icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		frame.getContentPane().add(btnNewButton);
		
		lblMonth = new JLabel("Date: ");
		lblMonth.setForeground(new Color(0, 51, 51));
		lblMonth.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		lblMonth.setBounds(10, 316, 56, 15);
		frame.getContentPane().add(lblMonth);

		// Type
		comboBox = new JComboBox(type);
		comboBox.setBackground(new Color(255, 245, 238));
		comboBox.setBounds(10, 406, 238, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblType = new JLabel("Type: ");
		lblType.setForeground(new Color(0, 51, 51));
		lblType.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		lblType.setBounds(10, 380, 56, 15);
		frame.getContentPane().add(lblType);
		
		//Time Out Button
//		JButton btnTimeOut = new JButton("Time Out");
//		btnTimeOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				row[2] = lblClock.getText();
//				model.addRow(row);
//			}
//		});
//		btnTimeOut.setBounds(785, 470, 89, 32);
//		frame.getContentPane().add(btnTimeOut);
		

		//Table
		JTable table  = new JTable();
		
		Object[]  columns = {"Day", "Time In", "Time Out", "Type"};
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setSelectionBackground(Color.RED);
		table.setGridColor(Color.RED);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(268,163,616,280);
		frame.getContentPane().add(pane);
		
		lblTimelbl = new JLabel("Time");
		lblTimelbl.setForeground(new Color(0, 51, 51));
		lblTimelbl.setFont(new Font("Lucida Fax", Font.BOLD, 15));
		lblTimelbl.setBounds(473, 20, 62, 14);
		frame.getContentPane().add(lblTimelbl);
		
		lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(0, 51, 51));
		lblDate.setFont(new Font("Lucida Fax", Font.BOLD, 15));
		lblDate.setBounds(662, 20, 40, 14);
		frame.getContentPane().add(lblDate);
		
		lblDate_1 = new JLabel("Date");
		lblDate_1.setForeground(new Color(0, 51, 51));
		lblDate_1.setFont(new Font("Lucida Fax", Font.BOLD, 18));
		lblDate_1.setBounds(662, 45, 182, 14);
		frame.getContentPane().add(lblDate_1);
		
		btnGeneratePdf = new JButton("");
		btnGeneratePdf.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				String path = "";
//				JFileChooser j = new JFileChooser();
				JFileChooser j=new JFileChooser();
				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int x = j.showSaveDialog(frame);
				
				if(x==JFileChooser.APPROVE_OPTION) {
					path = j.getSelectedFile().getPath(); 
				}
				
				Document doc = new Document();
				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path+"DTR123.pdf"));
					doc.open();
					
					PdfPTable tbl = new PdfPTable(4);
					
					tbl.addCell("Day");
					tbl.addCell("Time In");
					tbl.addCell("Time Out");
					tbl.addCell("Type");
					
					for(int i = 0; i< table.getRowCount(); i++) {
						String day= table.getValueAt(i, 0).toString();	
						String timeIn= table.getValueAt(i, 1).toString();
						String timeOut= table.getValueAt(i, 2).toString();
						String type= table.getValueAt(i, 3).toString();
						
						tbl.addCell(day);
						tbl.addCell(timeIn);
						tbl.addCell(timeOut);
						tbl.addCell(type);
					}

					doc.add(tbl);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				doc.close();
			}
		});
		btnGeneratePdf.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGeneratePdf.setBounds(761, 466, 64, 74);
		Image imgPDF = new ImageIcon(this.getClass().getResource("PDF-icon.png")).getImage();
		btnGeneratePdf.setIcon(new ImageIcon(imgPDF));
		frame.getContentPane().add(btnGeneratePdf);
		
		employeeIcon = new JLabel("");
		employeeIcon.setBounds(10, 11, 96, 96);
		Image imgEmployee = new ImageIcon(this.getClass().getResource("administrator-icon.png")).getImage();
		employeeIcon.setIcon(new ImageIcon(imgEmployee));
		frame.getContentPane().add(employeeIcon);
		
	}
	
	
	static Connection connect() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/fdtr";
			Class.forName(driver);
			return DriverManager.getConnection(url, "root", "");
		}catch(Exception e) {
			System.out.print("Couldn't connect: " + e);
		}
		return null;
	}
	
	public void save() {
		
		Connection con = connect();
		try{
			String query = "insert into employee (name) values(?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name.getText());
			ps.execute();

		}catch(Exception err) {
			System.out.print("error : " + err);
		}
		
		
	}
}
