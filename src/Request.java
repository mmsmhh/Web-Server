
public class Request {

	String status;
	String username;
	String fileName;
	String type;

	public Request(String status, String username, String fileName, String type) {
		this.status = status;
		this.username = username;
		this.fileName = fileName;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "GET " + fileName + " HTTP/1.1\n" + "localhost" + "\n" + type + "\n" + status;
	}

}
