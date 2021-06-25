import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Preview {
	
	private static JTable classTable;
	private static JTable consultationTable;
	private static JTable relatedTable;
	private static JTable othersTable;

	public static void main(String[] args) {
		showWindow();
		showClass();
		showConsultation();
		showRelated();
		showOthers();
	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("Preview");
		frame.setBounds(300, 170, 890, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneClass = new JScrollPane();
		scrollPaneClass.setBounds(10, 80, 213, 300);
		frame.getContentPane().add(scrollPaneClass);
		
		classTable = new JTable();
		scrollPaneClass.setViewportView(classTable);
		
		JScrollPane scrollPaneConsultation = new JScrollPane();
		scrollPaneConsultation.setBounds(227, 80, 213, 300);
		frame.getContentPane().add(scrollPaneConsultation);
		
		consultationTable = new JTable();
//		consultationTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneConsultation.setViewportView(consultationTable);
		
		JScrollPane scrollPaneRelated = new JScrollPane();
		scrollPaneRelated.setBounds(445, 80, 213, 300);
		frame.getContentPane().add(scrollPaneRelated);
		
		relatedTable = new JTable();
		relatedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneRelated.setViewportView(relatedTable);
		
		JScrollPane scrollPaneOthers = new JScrollPane();
		scrollPaneOthers.setBounds(661, 80, 213, 300);
		frame.getContentPane().add(scrollPaneOthers);
		
		othersTable = new JTable();
		othersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneOthers.setViewportView(othersTable);
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
		model.addColumn("Time In");
		model.addColumn("Time out");
		model.addColumn("Hours");
		
		
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
		model.addColumn("Time In");
		model.addColumn("Time out");
		model.addColumn("Hours");
		
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
		model.addColumn("Time In");
		model.addColumn("Time out");
		model.addColumn("Hours");
		
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
		model.addColumn("Time In");
		model.addColumn("Time out");
		model.addColumn("Hours");
		
		try {
			String querry = "select * from others";
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
			
				othersTable.setModel(model);
				othersTable.setAutoResizeMode(0);
				othersTable.getColumnModel().getColumn(0).setPreferredWidth(70);
				othersTable.getColumnModel().getColumn(1).setPreferredWidth(70);
				othersTable.getColumnModel().getColumn(2).setPreferredWidth(70);

		}catch(Exception err) {
			System.out.print("error: " + err);
		}
		
	}
}

