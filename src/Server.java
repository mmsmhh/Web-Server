import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Server {

	private static ArrayList<ClientHandeler> ListOfClients;
	public volatile static ArrayList<Request> Requests = new ArrayList<Request>();;

	public static void main(String[] args) throws IOException {

		ListOfClients = new ArrayList<ClientHandeler>();

		ServerSocket welcomeSocket = new ServerSocket(80);

		Thread loop = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {

					while (true) {

						if (!Requests.isEmpty()) {

							Request r = Requests.get(0);

							ClientHandeler myclient = null;

							for (ClientHandeler c : ListOfClients) {
								if (c.username.equals(r.getUsername())) {
									myclient = c;
								}
							}

							File f = new File("Drive/" + r.getFileName() + "." + r.getType());
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());

							if (f.exists() && !f.isDirectory()) {

								FileInputStream fr = new FileInputStream(
										"Drive/" + r.getFileName() + "." + r.getType());
								byte b[] = new byte[(int) f.length()];
								fr.read(b, 0, b.length);
								String resp = "200" + " HTTP/1.1\r\n" + timestamp + "\r\n" + r.getType() + "\r\n"
										+ r.getStatus() + "\r\n" + b.length;

								myclient.outToClient.writeBytes(resp + '\n');
								

								OutputStream os = myclient.Socket.getOutputStream();
								os.write(b, 0, b.length);

							} else {

								String resp = "404" + " HTTP/1.1\r\n" + timestamp + "\r\n" + r.getType() + "\r\n"
										+ r.getStatus();

								myclient.outToClient.writeBytes(resp + '\n');
							}

							if (r.getStatus().equals("close")) {

								for (int i = 0; i < ListOfClients.size(); i++) {
									if (ListOfClients.get(i).username.equals(r.getUsername())) {
										ListOfClients.remove(i);
										break;
									}
								}

								myclient.Socket.close();
								myclient.close();
							}

							Requests.remove(0);

						}

						/* include your file name which you want to move */

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		loop.start();

		try {
			while (true) {

				Socket connectionSocket = welcomeSocket.accept();

				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));

				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				ClientHandeler t = new ClientHandeler(connectionSocket, inFromClient, outToClient);
				ListOfClients.add(t);
				t.start();
			}
		} finally {
			welcomeSocket.close();
		}

	}

}
