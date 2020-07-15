package client.side;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Network network;

    @FXML
    VBox leftPanel, rightPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new Network((args) -> {
        });
    }

    public void exitAction() {
        network.close();
        Platform.exit();
    }

    public void moveAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController)leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController)rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFileName() == null && rightPC.getSelectedFileName() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFileName() != null){
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFileName() != null){
            srcPC = rightPC;
            dstPC = leftPC;
        }

        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFileName());
        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());

        try {
            Files.move(srcPath, dstPath);
            network.sendMessage("move " + srcPath.getFileName());
            srcPC.updateList(Paths.get(srcPC.getCurrentPath()));
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not move", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void copyAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController)leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController)rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFileName() == null && rightPC.getSelectedFileName() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFileName() != null){
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFileName() != null){
            srcPC = rightPC;
            dstPC = leftPC;
        }

        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFileName());
        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());

        try {
            Files.copy(srcPath, dstPath);
            network.sendMessage("copy " + srcPath.getFileName());
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not copy", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController)leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController)rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFileName() == null && rightPC.getSelectedFileName() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        PanelController srcPC = null;
        if (leftPC.getSelectedFileName() != null){
            srcPC = leftPC;
        }
        if (rightPC.getSelectedFileName() != null){
            srcPC = rightPC;
        }

        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFileName());

        try {
            Files.delete(srcPath);
            network.sendMessage("delete " + srcPath.getFileName());
            srcPC.updateList(Paths.get(srcPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not delete", ButtonType.OK);
            alert.showAndWait();
        }
    }

}