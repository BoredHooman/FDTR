import javax.swing.JFrame;

public class Preview {

	public static void main(String[] args) {
		showWindow();
	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("Preview");
		frame.setBounds(200, 100, 900, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}

}
