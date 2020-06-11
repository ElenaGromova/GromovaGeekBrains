package file.manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    @FXML
    ListView<FileInfo> fileList;
    @FXML
    TextField pathField;

    Path root;
    Path selectedCopyFile;
    Path selectedMoveFile;

    public void menuItemFileAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileList.setCellFactory(new Callback<ListView<FileInfo>, ListCell<FileInfo>>() {
            @Override
            public ListCell<FileInfo> call(ListView<FileInfo> param) {
                return new ListCell<FileInfo>(){
                    @Override
                    protected void updateItem(FileInfo item, boolean empty){
                        super.updateItem(item, empty);
                        if (item == null || empty){
                            setText(null);
                            setStyle("");
                        }
                        else {
                            String formattedFilename = String.format("%-30s", item.getFilename());
                            String formattedFileLength = String.format("%,d bytes", item.getLenght());
                            if (item.getLenght() == -1L){
                                formattedFileLength = String.format("%-s", "[ DIR ]");
                            }
                            if (item.getLenght() == -2L){
                                formattedFileLength = "";
                            }
                            String text = String.format("%s %-20s", formattedFilename, formattedFileLength);
                            setText(item.getFilename());
                        }
                    }
                };
            }
        });
        goToPath(Paths.get("1"));
    }

    public void goToPath(Path path){
        root = path;
        pathField.setText(root.toAbsolutePath().toString());
        fileList.getItems().clear();
        fileList.getItems().add(new FileInfo(FileInfo.UP_TOKEN, -2L));
        fileList.getItems().addAll(scanFiles(path));
        fileList.getItems().sort(new Comparator<FileInfo>() {
            @Override
            public int compare(FileInfo o1, FileInfo o2) {
                if (o1.getFilename().equals(FileInfo.UP_TOKEN)){
                    return -1;
                }
                if ((int)Math.signum(o1.getLenght()) != (int)Math.signum(o2.getLenght())){
                    return o1.getFilename().compareTo(o2.getFilename());
                }
                return new Long(o1.getLenght() - o2.getLenght()).intValue();
            }
        });
    }

    public List<FileInfo> scanFiles(Path root){
        try {
//            List<FileInfo> out = new ArrayList<>();
//            List<Path> pathsInRoot = Files.list(root).collect(Collectors.toList());
//            for (Path p : pathsInRoot) {
//                out.add(new FileInfo(p));
//            }
//            return out;
            return Files.list(root).map(FileInfo::new).collect(Collectors.toList());
        }
        catch (IOException e){
            throw new RuntimeException("Files scan exeption: " + root);
        }

        // return Files.list(root).map(FileInfo::new).collect(Collectors.toList());
    }


    public void fileListClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            FileInfo fileInfo = fileList.getSelectionModel().getSelectedItem();
            if (fileInfo != null){
                if (fileInfo.isDirectory()){
                    Path pathTo = root.resolve(fileInfo.getFilename());
                    goToPath(pathTo);
                }
                if (fileInfo.isUpElement()){
                    Path pathTo = root.toAbsolutePath().getParent();
                    goToPath(pathTo);
                }
            }

        }
    }

    public void refresh(){
        goToPath(root);
    }

    public void copyAction(ActionEvent actionEvent) {
        FileInfo fileInfo = fileList.getSelectionModel().getSelectedItem();
        if ( (selectedCopyFile == null) && (fileInfo == null || fileInfo.isUpElement() || fileInfo.isDirectory())){
            return;
        }
        if (selectedCopyFile == null){
            selectedCopyFile = root.resolve(fileInfo.getFilename());
            ((Button) actionEvent.getSource()).setText("Coping: " + fileInfo.getFilename());
            return;
        }
        if (selectedCopyFile != null){
            try {
                Files.copy(selectedCopyFile, root.resolve(selectedCopyFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                selectedCopyFile = null;
                ((Button) actionEvent.getSource()).setText("Copy");
                refresh();
            }
            catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно скопировать файл");
                alert.showAndWait();
            }
        }
    }

    public void moveAction(ActionEvent actionEvent) {
        FileInfo fileInfo = fileList.getSelectionModel().getSelectedItem();
        if ( (selectedMoveFile == null) && (fileInfo == null || fileInfo.isUpElement() || fileInfo.isDirectory())){
            return;
        }
        if (selectedMoveFile == null){
            selectedMoveFile = root.resolve(fileInfo.getFilename());
            ((Button) actionEvent.getSource()).setText("Moving: " + fileInfo.getFilename());
            return;
        }
        if (selectedMoveFile != null){
            try {
                Files.move(selectedMoveFile, root.resolve(selectedMoveFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                selectedMoveFile = null;
                ((Button) actionEvent.getSource()).setText("Move");
                refresh();
            }
            catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно переместить файл");
                alert.showAndWait();
            }
        }
    }

    public void deleteAction(ActionEvent actionEvent){
        FileInfo fileInfo = fileList.getSelectionModel().getSelectedItem();
        if (fileInfo == null || fileInfo.isUpElement() || fileInfo.isDirectory()){
            return;
        }
        try {
            Files.delete(root.resolve(fileInfo.getFilename()));
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно удалить файл");
            alert.showAndWait();
        }
    }
}
