package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;

public class Controller {

    DataOutputStream out;
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    Button connectionButton;
    @FXML
    TextArea userListTextArea;

    /**
     * Method sends messages & displays them in the appropriate View of sample.xml
     */

    @FXML
    private void send() {
        try {
            String text = textField.getText();
            textField.clear();
            textField.requestFocus();
            textArea.appendText(text + "\n");
            out.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method creates connection each user, receives messages from server &,
     * depending on the response, directs it to the appropriate View of sample.xml
     */

    @FXML
    private void connect() {
        try {
            connectionButton.setDisable(true);
            Socket socket = new Socket("127.0.0.1", 8188); // Создаём сокет, для подключения к серверу
            out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            new Thread(() -> {  // Создаём поток, для приёма сообщений от сервера
                while (true) {
                    String response = null; // Принимаем сообщение от сервера
                    try {
                        response = in.readObject().toString();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (response.startsWith("**userList**")) {
                        String[] usersName = response.split("//"); //**userList**//user1//user2//user3
                        userListTextArea.setText("");
                        Arrays.stream(usersName).forEach(s -> userListTextArea.appendText(s + "\n"));
                    } else textArea.appendText(response + "\n"); // Печатаем на консоль принятое сообщение от сервера
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
