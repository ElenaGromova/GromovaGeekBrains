package main.java;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    VBox leftPanel, rightPanel;

    public void Quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copyAction(ActionEvent actionEvent) {
       PanelController leftPC = (PanelController)leftPanel.getProperties().get("ctrl");
       PanelController rightPC = (PanelController)rightPanel.getProperties().get("ctrl");

//        ProgressBar pb = new ProgressBar();
//        for (int i = 0; i < 100; i++){
//            pb.setProgress(1.0/100*i);
//        }

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
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not copy", ButtonType.OK);
            alert.showAndWait();
        }
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
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not move", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
    }
}


