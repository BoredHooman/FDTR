import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.PopupFactory;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleConstants.ColorConstants;

import com.itextpdf.text.BaseColor;

//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Font;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;
import java.awt.Color;
//import java.awt.Font;


public class Preview {
	
	private static JTable classTable;
	private static JTable consultationTable;
	private static JTable relatedTable;
	private static JTable othersTable;
	private static JTable totalHrsTable;
	

	public static void main(String[] args) {
		showWindow();
		showClass();
		showConsultation();
		showRelated();
		showOthers();
		showTotalHrs();
	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("Preview");
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(200, 100, 1000, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(0);
		
		JScrollPane scrollPaneClass = new JScrollPane();
		scrollPaneClass.setBounds(10, 227, 213, 300);
		frame.getContentPane().add(scrollPaneClass);
		
		classTable = new JTable();
		classTable.setBackground(new Color(245, 245, 220));
		scrollPaneClass.setViewportView(classTable);
		
		JScrollPane scrollPaneConsultation = new JScrollPane();
		scrollPaneConsultation.setBounds(233, 227, 213, 300);
		frame.getContentPane().add(scrollPaneConsultation);
		
		consultationTable = new JTable();
		consultationTable.setBackground(new Color(245, 245, 220));
		consultationTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneConsultation.setViewportView(consultationTable);
		
		JScrollPane scrollPaneRelated = new JScrollPane();
		scrollPaneRelated.setBounds(456, 227, 213, 300);
		frame.getContentPane().add(scrollPaneRelated);
		
		relatedTable = new JTable();
		relatedTable.setBackground(new Color(245, 245, 220));
		relatedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneRelated.setViewportView(relatedTable);
		
		JScrollPane scrollPaneOthers = new JScrollPane();
		scrollPaneOthers.setBounds(679, 227, 213, 300);
		frame.getContentPane().add(scrollPaneOthers);
		
		othersTable = new JTable();
		othersTable.setBackground(new Color(245, 245, 220));
		othersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneOthers.setViewportView(othersTable);
		
		JScrollPane scrollPaneTotalHrs = new JScrollPane();
		scrollPaneTotalHrs.setBounds(902, 227, 73, 300);
		frame.getContentPane().add(scrollPaneTotalHrs);
		
		totalHrsTable = new JTable();
		totalHrsTable.setBackground(new Color(245, 245, 220));
//		totalHrsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTotalHrs.setViewportView(totalHrsTable);
		
		JButton btnNewButton = new JButton("Generate PDF");
//		btnNewButton.setFont(new Font("Bell MT", Font.BOLD, 17));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(135, 206, 235));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection con = connect();
					String file_name = "";
					JFileChooser j = new JFileChooser();
					j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int x = j.showSaveDialog(frame);
					
					if(x==JFileChooser.APPROVE_OPTION) {
						file_name = j.getSelectedFile().getPath(); 
					}
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(file_name + "dtr.pdf"));
					
					document.open();
					
					Paragraph rp = new Paragraph("Republic of the Philippines");
					rp.setAlignment(Element.ALIGN_CENTER);
					document.add(rp);
					
					Paragraph msu = new Paragraph("Mindanao State University");
					msu.setAlignment(Element.ALIGN_CENTER);
					document.add(msu);
					
					Paragraph it = new Paragraph("ILIGAN INSTITUTE OF TECHNOLOGY");
					it.setAlignment(Element.ALIGN_CENTER);
					document.add(it);
					
					Paragraph ic = new Paragraph("Iligan City");
					ic.setAlignment(Element.ALIGN_CENTER);
					document.add(ic);
					
					Font f = new Font(Font.BOLD);
					
					Paragraph fdtr = new Paragraph("FACULTY DAILY TIME RECORD", f);
					fdtr.setAlignment(Element.ALIGN_CENTER);
					document.add(fdtr);
					
					Paragraph monYear = new Paragraph("For the month of");
					monYear.setAlignment(Element.ALIGN_CENTER);
					document.add(monYear);
					
					document.add(new Paragraph("\n"));

					
					String employeeQuery = "select * from employee";
					Statement employee_st  = con.createStatement();
					ResultSet employee_rs = employee_st.executeQuery(employeeQuery);
					
					while(employee_rs.next()) {
						document.add(new Paragraph(employee_rs.getString("id_number")));
						document.add(new Paragraph(employee_rs.getString("name")));
						document.add(new Paragraph(employee_rs.getString("department")));
					}
					
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("Class"));
					document.add(new Paragraph("\n"));
					
					String classQ = "select * from class";
					Statement class_st  = con.createStatement();
					ResultSet class_rs = class_st.executeQuery(classQ);

					PdfPTable tbl = new PdfPTable(4);
					
					tbl.addCell("Day");
					tbl.addCell("Time In");
					tbl.addCell("Time Out");
					tbl.addCell("Hrs");
					
					while(class_rs.next()) {
						tbl.addCell(class_rs.getString("id"));
						tbl.addCell(class_rs.getString("time_in"));
						tbl.addCell(class_rs.getString("time_out"));
						tbl.addCell(class_rs.getString("hrs"));
					}
					document.add(tbl);
					document.add(new Paragraph("\n"));
					
					document.add(new Paragraph("Consultation"));
					document.add(new Paragraph("\n"));
					
					String consultationQ = "select * from consultation";
					Statement consultation_st  = con.createStatement();
					ResultSet consultation_rs = consultation_st.executeQuery(consultationQ);
					
					PdfPTable consult_tbl = new PdfPTable(4);
					
					consult_tbl.addCell("Day");
					consult_tbl.addCell("Time In");
					consult_tbl.addCell("Time Out");
					consult_tbl.addCell("Hrs");
					
					while(consultation_rs.next()) {
						consult_tbl.addCell(consultation_rs.getString("id"));
						consult_tbl.addCell(consultation_rs.getString("time_in"));
						consult_tbl.addCell(consultation_rs.getString("time_out"));
						consult_tbl.addCell(consultation_rs.getString("hrs"));
					}
					document.add(consult_tbl);
					document.add(new Paragraph("\n"));

					document.add(new Paragraph("Related"));
					document.add(new Paragraph("\n"));
					
					String relatedQ = "select * from related";
					Statement related_st  = con.createStatement();
					ResultSet related_rs = class_st.executeQuery(relatedQ);

					PdfPTable related_tbl = new PdfPTable(4);
					
					related_tbl.addCell("Day");
					related_tbl.addCell("Time In");
					related_tbl.addCell("Time Out");
					related_tbl.addCell("Hrs");
					
					while(related_rs.next()) {
						related_tbl.addCell(related_rs.getString("id"));
						related_tbl.addCell(related_rs.getString("time_in"));
						related_tbl.addCell(related_rs.getString("time_out"));
						related_tbl.addCell(related_rs.getString("hrs"));
					}
					document.add(related_tbl);
					document.add(new Paragraph("\n"));
					
					document.add(new Paragraph("Others"));
					document.add(new Paragraph("\n"));
					
					String othersQ = "select * from others";
					Statement others_st  = con.createStatement();
					ResultSet others_rs = class_st.executeQuery(othersQ);

					PdfPTable others_tbl = new PdfPTable(4);
					
					others_tbl.addCell("Day");
					others_tbl.addCell("Time In");
					others_tbl.addCell("Time Out");
					others_tbl.addCell("Hrs");
					
					while(others_rs.next()) {
						others_tbl.addCell(others_rs.getString("id"));
						others_tbl.addCell(others_rs.getString("time_in"));
						others_tbl.addCell(others_rs.getString("time_out"));
						others_tbl.addCell(others_rs.getString("hrs"));
					}
					document.add(others_tbl);
					document.add(new Paragraph("\n"));

					document.close();
					
				}catch(Exception err) {
					System.out.print("Error: " + err);
				}
			}
		});
		btnNewButton.setBounds(569, 561, 195, 57);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAllData();
				frame.dispose();
			}
		});
		btnExit.setForeground(Color.WHITE);
//		btnExit.setFont(new Font("Bell MT", Font.BOLD, 17));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(780, 561, 195, 57);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Name: ");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblNewLabel.setBounds(10, 182, 41, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDepartment = new JLabel("Department: ");
		lblDepartment.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblDepartment.setBounds(582, 182, 87, 21);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblNewLabel_1 = new JLabel("Republic of the Philippines");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		lblNewLabel_1.setBounds(391, 11, 160, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mindanao State University");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(391, 36, 169, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ILIGAN INSTITUTE OF TECHNOLOGY");
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(368, 57, 230, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Iligan City");
		lblNewLabel_1_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(440, 78, 73, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("FACULTY DAILY TIME RECORD");
		lblNewLabel_1_1_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1.setBounds(354, 99, 246, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("For the month of");
		lblNewLabel_1_1_1_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_2.setBounds(425, 120, 111, 21);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_2);
		frame.setVisible(true);
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
	
	public static void showClass() {
		
		Connection con = connect();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Day");
		model.addColumn("In");
		model.addColumn("Out");
		model.addColumn("Hrs");
		
		
		try {
			String querry = "select * from class";
			Statement st  = con.createStatement();
			ResultSet rs = st.executeQuery(querry);
			
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("id"),
						rs.getString("time_in"),
						rs.getString("time_out"),
						rs.getString("hrs"),
				});
			}
				rs.close();
				st.close();
			
//				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//				classTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
				classTable.setModel(model);
				classTable.setAutoResizeMode(0);
				classTable.getColumnModel().getColumn(0).setPreferredWidth(52);
				classTable.getColumnModel().getColumn(1).setPreferredWidth(52);
				classTable.getColumnModel().getColumn(2).setPreferredWidth(52);
				classTable.getColumnModel().getColumn(3).setPreferredWidth(52);


		}catch(Exception err) {
			System.out.print("error: " + err);
		}
		
	}
	
	public static void showConsultation() {
		
		Connection con = connect();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("In");
		model.addColumn("Out");
		model.addColumn("Hrs");
		
		try {
			String querry = "select * from consultation";
			Statement st  = con.createStatement();
			ResultSet rs = st.executeQuery(querry);
			
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("time_in"),
						rs.getString("time_out"),
						rs.getString("hrs"),
				});
			}
				rs.close();
				st.close();
			
				consultationTable.setModel(model);
				consultationTable.setAutoResizeMode(0);
				consultationTable.getColumnModel().getColumn(0).setPreferredWidth(70);
				consultationTable.getColumnModel().getColumn(1).setPreferredWidth(70);
				consultationTable.getColumnModel().getColumn(2).setPreferredWidth(70);

		}catch(Exception err) {
			System.out.print("error: " + err);
		}
	}
	
	public static void showRelated() {
		
		Connection con = connect();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("In");
		model.addColumn("Out");
		model.addColumn("Hrs");
		
		try {
			String querry = "select * from related";
			Statement st  = con.createStatement();
			ResultSet rs = st.executeQuery(querry);
			
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("time_in"),
						rs.getString("time_out"),
						rs.getString("hrs"),
				});
			}
				rs.close();
				st.close();
			
				relatedTable.setModel(model);
				relatedTable.setAutoResizeMode(0);
				relatedTable.getColumnModel().getColumn(0).setPreferredWidth(70);
				relatedTable.getColumnModel().getColumn(1).setPreferredWidth(70);
				relatedTable.getColumnModel().getColumn(2).setPreferredWidth(70);

		}catch(Exception err) {
			System.out.print("error: " + err);
		}
	}
	
	public static void showOthers() {
		
		Connection con = connect();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("In");
		model.addColumn("Out");
		model.addColumn("Hrs");
		  Paragraph header = new Paragraph("Copy");
//	                .setFont(PopupFactory.getSharedInstance())
//	                .setFontSize(14)
//	                .setFontColor(ColorConstants.ALIGN_JUSTIFIED);
		
		try {
			String query = "select * from others";
			Statement st  = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("time_in"),
						rs.getString("time_out"),
						rs.getString("hrs"),
				});
			}
				rs.close();
				st.close();
			
				othersTable.setModel(model);
				othersTable.setAutoResizeMode(0);
				othersTable.getColumnModel().getColumn(0).setPreferredWidth(70);
				othersTable.getColumnModel().getColumn(1).setPreferredWidth(70);
				othersTable.getColumnModel().getColumn(2).setPreferredWidth(70);

		}catch(Exception err) {
			System.out.print("error: " + err);
		}
	}
	
	public static void showTotalHrs() {
		Connection con = connect();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Total Hrs");
		
//		try {
//			String querry = "select * from class";
//			Statement st  = con.createStatement();
//			ResultSet rs = st.executeQuery(querry);
//			
//			while(rs.next()) {
//				model.addRow(new Object[] {
//						rs.getString("id"),
//						rs.getString("time_in"),
//						rs.getString("time_out"),
//						rs.getString("hrs"),
//				});
//			}
//				rs.close();
//				st.close();
//			
				totalHrsTable.setModel(model);
//				totalHrsTable.setAutoResizeMode(0);
//				totalHrsTable.getColumnModel().getColumn(0).setPreferredWidth(52);
//				totalHrsTable.getColumnModel().getColumn(1).setPreferredWidth(52);
//				totalHrsTable.getColumnModel().getColumn(2).setPreferredWidth(52);
//				totalHrsTable.getColumnModel().getColumn(3).setPreferredWidth(52);
//
//
//		}catch(Exception err) {
//			System.out.print("error: " + err);
//		}
		
	}
	
	public static void deleteAllData() {
		Connection con = connect();
		try {
			String deleteQueryClass = "truncate class";
			PreparedStatement ps_class = con.prepareStatement(deleteQueryClass);
			ps_class.execute();
			
			String deleteQueryRelated = "truncate related";
			PreparedStatement ps_relative = con.prepareStatement(deleteQueryRelated);
			ps_relative.execute();
			
			String deleteQueryConsultation = "truncate consultation"; 
			PreparedStatement ps_consulation = con.prepareStatement(deleteQueryConsultation);
			ps_consulation.execute();
			
			String deleteOthers = "truncate others";
			PreparedStatement ps_others = con.prepareStatement(deleteOthers);
			ps_others.execute();
			con.close();
			
		}catch(Exception err) {
			System.out.print("error: " + err);
		}
	}
}

