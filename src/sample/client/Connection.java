package sample.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class Connection {

    private Socket socket;
    private String userName;
    private UUID uuid;
    private DataInputStream in;
    private ObjectOutputStream oos;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.userName = "Anonymous";
        this.uuid = UUID.randomUUID();
        this.in = new DataInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public DataInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
