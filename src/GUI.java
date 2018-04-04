import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class GUI {

	public JFrame frame;

	/**
	 * Launch the application.
	 */

	public JButton txt1, txt2, png1, png2, jpg1, jpg2, txt3, txt4, mp41, mp42;
	public JTextArea txt;
	public JCheckBox closeCheckBox;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(251, 11, 265, 294);
		frame.getContentPane().add(scrollPane_1);
		
		txt = new JTextArea();
		txt.setBounds(251, 11, 265, 294);
		
		txt.setVisible(true);
		scrollPane_1.setViewportView(txt);

		frame.getContentPane().add(scrollPane_1);

		
		
		txt1 = new JButton("txt 1");
		txt1.setBounds(21, 31, 89, 23);
		frame.getContentPane().add(txt1);

		txt2 = new JButton("txt 2");
		txt2.setBounds(21, 65, 89, 23);
		frame.getContentPane().add(txt2);

		txt3 = new JButton("txt 3");
		txt3.setBounds(120, 31, 89, 23);
		frame.getContentPane().add(txt3);

		txt4 = new JButton("txt 4");
		txt4.setBounds(120, 65, 89, 23);
		frame.getContentPane().add(txt4);

		png1 = new JButton("png 1");
		png1.setBounds(21, 209, 89, 23);
		frame.getContentPane().add(png1);

		jpg2 = new JButton("jpg 2");
		jpg2.setBounds(120, 127, 89, 23);
		frame.getContentPane().add(jpg2);

		jpg1 = new JButton("jpg 1");
		jpg1.setBounds(21, 127, 89, 23);
		frame.getContentPane().add(jpg1);

		mp41 = new JButton("mp4 1");
		mp41.setBounds(120, 209, 89, 23);
		frame.getContentPane().add(mp41);

		mp42 = new JButton("mp4 2");
		mp42.setBounds(120, 243, 89, 23);
		frame.getContentPane().add(mp42);

		png2 = new JButton("png 2");
		png2.setBounds(21, 243, 89, 23);
		frame.getContentPane().add(png2);
		
		 closeCheckBox = new JCheckBox("Close");
		closeCheckBox.setBounds(21, 282, 97, 23);
		frame.getContentPane().add(closeCheckBox);
	}
}
