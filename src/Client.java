import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {

	static String username = null;
	static Socket clientSocket = null;
	static boolean firstlayer = false;
	static boolean secondlayer = false;
	static String filename = "";
	static Thread read;
	public static String doAfter = "keep-alive";
	static String folder = "Download-";

	public static void main(String argv[]) throws IOException {

		while (username == null || username.isEmpty()) {
			username = JOptionPane.showInputDialog("Please enter your username.");
		}

		clientSocket = new Socket("mmsmhh", 80);

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		outToServer.writeBytes("username#" + username + '\n');
		folder += username;
		new File(folder).mkdirs();

		GUI gui = new GUI();
		gui.frame.setVisible(true);

		gui.txt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String request = "GET txt1 HTTP/1.1\n" + "localhost" + "\n" + "txt" + "\n" + doAfter;
				filename = "txt1.txt";
				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.txt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String request = "GET txt2 HTTP/1.1\n" + "localhost" + "\n" + "txt" + "\n" + doAfter;
				filename = "txt2.txt";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		gui.txt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET txt3 HTTP/1.1\n" + "localhost" + "\n" + "txt" + "\n" + doAfter;
				filename = "txt3.txt";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.txt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET txt4 HTTP/1.1\n" + "localhost" + "\n" + "txt" + "\n" + doAfter;
				filename = "txt4.txt";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		gui.png1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET png1 HTTP/1.1\n" + "localhost" + "\n" + "png" + "\n" + doAfter;
				filename = "png1.png";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.png2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET png2 HTTP/1.1\n" + "localhost" + "\n" + "png" + "\n" + doAfter;
				filename = "png2.png";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		gui.jpg1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET jpg1 HTTP/1.1\n" + "localhost" + "\n" + "jpg" + "\n" + doAfter;
				filename = "jpg1.jpg";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.jpg2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET jpg2 HTTP/1.1\n" + "localhost" + "\n" + "jpg" + "\n" + doAfter;
				filename = "jpg2.jpg";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		gui.mp41.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET mp41 HTTP/1.1\n" + "localhost" + "\n" + "mp4" + "\n" + doAfter;
				filename = "mp41.mp4";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.mp42.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String request = "GET mp42 HTTP/1.1\n" + "localhost" + "\n" + "mp4" + "\n" + doAfter;
				filename = "mp42.mp4";

				try {
					outToServer.writeBytes(request + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		gui.closeCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (gui.closeCheckBox.isSelected()) {
					doAfter = "close";

				} else {
					doAfter = "keep-alive";
				}
			}
		});

		read = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				String word = "";
				try {
					while (true) {
						String FromServer = inFromServer.readLine();

						System.out.println(FromServer);
						word += FromServer + "\n";

						if (FromServer.contains("200"))
							firstlayer = true;

						if (FromServer.contains("close") || FromServer.contains("keep-alive"))
							if (firstlayer) {

								int len = Integer.parseInt(inFromServer.readLine());

								byte[] b = new byte[4092];
								InputStream is = clientSocket.getInputStream();
								FileOutputStream fr = new FileOutputStream(folder + "/" + filename);

								int n = 0;
								while (len > 0 && (n = is.read(b, 0, (int) Math.min(b.length, len))) != -1) {
									fr.write(b, 0, n);
									len -= n;
								}

								System.out.println("Ended");
								gui.txt.append("\n" + word);
								gui.txt.append("\n" + "--------");

								word = "";
								firstlayer = false;
								if (FromServer.contains("close")) {
									clientSocket.close();
									gui.frame.setEnabled(false);
									read.stop();
								}
							}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		read.start();

		String sentence = "";

		while (!sentence.toLowerCase().equals("exit")) {
			try {
				sentence = inFromUser.readLine();
				String[] both = sentence.split(" ");
				filename = both[0];
				String doAfter = both[1];

				String name = filename.split("\\.")[0];

				String type = filename.split("\\.")[1];

				String request = "GET " + name + " HTTP/1.1\n" + "localhost" + "\n" + type + "\n" + doAfter;

				outToServer.writeBytes(request + '\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
