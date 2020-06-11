package Java_Core_Lesson8.client;

import Java_Core_Lesson8.server.AuthService;
import Java_Core_Lesson8.server.ClientHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import static Java_Core_Lesson8.server.AuthService.getNickById;

public class Controller implements Initializable {
    @FXML
    TextField msgField;

    @FXML
    TextArea chatArea;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox AutorizationPanel;

    @FXML
    HBox RegistrationPanel;

    @FXML
    HBox upperPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField logField;

    @FXML
    TextField nickField;

    @FXML
    PasswordField passField;

    @FXML
    ListView<String> clientsList;

    @FXML
    ListView<String> clientsBlackList;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8150;

    List<TextArea> textAreas;
    int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        upperPanel.setVisible(true);
        upperPanel.setManaged(true);
        bottomPanel.setVisible(false);
        bottomPanel.setManaged(false);
        clientsList.setVisible(false);
        clientsList.setManaged(false);

        clientsBlackList.setVisible(false);
        clientsBlackList.setManaged(false);
        AutorizationPanel.setVisible(false);
        AutorizationPanel.setManaged(false);
        RegistrationPanel.setVisible(false);
        RegistrationPanel.setManaged(false);

        chatArea.clear();
        textAreas = new ArrayList<>();
        textAreas.add(chatArea);
    }

    public void authView(ActionEvent actionEvent) {
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        AutorizationPanel.setVisible(true);
        AutorizationPanel.setManaged(true);
        RegistrationPanel.setVisible(false);
        RegistrationPanel.setManaged(false);
        bottomPanel.setVisible(false);
        bottomPanel.setManaged(false);
        clientsList.setVisible(false);
        clientsList.setManaged(false);

        clientsBlackList.setVisible(false);
        clientsBlackList.setManaged(false);

        chatArea.clear();
    }

    public void regView(ActionEvent actionEvent) {
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        AutorizationPanel.setVisible(false);
        AutorizationPanel.setManaged(false);
        RegistrationPanel.setVisible(true);
        RegistrationPanel.setManaged(true);
        bottomPanel.setVisible(false);
        bottomPanel.setManaged(false);
        clientsList.setVisible(false);
        clientsList.setManaged(false);

        clientsBlackList.setVisible(false);
        clientsBlackList.setManaged(false);

        chatArea.clear();
    }

    public void chatView(){
        upperPanel.setVisible(false);
        upperPanel.setManaged(false);
        AutorizationPanel.setVisible(false);
        AutorizationPanel.setManaged(false);
        RegistrationPanel.setVisible(false);
        RegistrationPanel.setManaged(false);
        bottomPanel.setVisible(true);
        bottomPanel.setManaged(true);
        clientsList.setVisible(true);
        clientsList.setManaged(true);

        clientsBlackList.setVisible(true);
        clientsBlackList.setManaged(true);
        clientsBlackList.getItems().add("Черный список:");

        chatArea.clear();
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        socket.setSoTimeout(120000);
                        String str = in.readUTF();
                        if (str.startsWith("/authok") || str.startsWith("/regok")) {
                            chatView();
                            break;
                        } else {
                            for (TextArea o : textAreas) {
                                o.appendText(str + "\n");
                            }
                        }
                    }
                    while (true) {
                        socket.setSoTimeout(1200000);
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/serverclosed")) break;
                            if (str.startsWith("/clientslist ")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(() -> {
                                    clientsList.getItems().clear();
                                    clientsList.getItems().add("Участники чата:");
                                    //HashSet blackList = new HashSet<>(AuthService.blackListInit(id));
                                    for (int i = 1; i < tokens.length; i++) {
                                        clientsList.getItems().add(tokens[i]);
//                                        if (blackList.contains(tokens[i])){
//                                            //clientsList.getItems().get(tokens[i])
//                                        }

                                    }
                                });
                            }
                        } else {
                            chatArea.appendText(str + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //setAuthorized(false);
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Dispose() {
        System.out.println("Отправляем сообщение на сервер о завершении работы");
        try {
            if (out != null) {
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToReg() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/reg " + logField.getText() + " " + passField.getText() + " " + nickField.getText());
            System.out.println("отправка данных с формы регистрации " + "/reg " + logField.getText() + " " + passField.getText() + " " + nickField.getText());
            loginField.clear();
            passwordField.clear();
            nickField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectClient(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            MiniStage ms = new MiniStage(clientsList.getSelectionModel().getSelectedItem(), out, textAreas);
            ms.show();
        }
    }


}
