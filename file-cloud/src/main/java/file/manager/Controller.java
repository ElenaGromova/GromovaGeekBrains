package file.manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void menuItenFileAction(ActionEvent actionEvent) {
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
        fileList.getItems().addAll(scanFiles(path));
        fileList.getItems().sort(new Comparator<FileInfo>() {
            @Override
            public int compare(FileInfo o1, FileInfo o2) {
                if ((int)Math.signum(o1.getLenght()) != (int)Math.signum(o2.getLenght())){

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


}
