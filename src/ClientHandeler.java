import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;

public class ClientHandeler extends Thread {

	Socket Socket;
	String username;
	BufferedReader inFromClient;
	DataOutputStream outToClient;

	public ClientHandeler(Socket connectionSocket, BufferedReader inFromClient, DataOutputStream outToClient) {

		this.Socket = connectionSocket;
		this.inFromClient = inFromClient;
		this.outToClient = outToClient;
	}

	@Override
	public void run() {
		try {

			while (true) {

				String filenamex = inFromClient.readLine();

				if (filenamex.contains("#")) {
					username = filenamex.split("#")[1];
				}

				else {

					String filename = filenamex.split(" ")[1];
					String host = inFromClient.readLine();
					String type = inFromClient.readLine();
					String doAfter = inFromClient.readLine();
					Request myReq = new Request(doAfter, username, filename, type);
					Server.Requests.add(myReq);
					System.out.println(myReq.toString());
					System.out.println(username + " sent a HTTP request to get " + filename + "." + type);
					

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		// TODO Auto-generated method stub
		this.stop();
	}

}
