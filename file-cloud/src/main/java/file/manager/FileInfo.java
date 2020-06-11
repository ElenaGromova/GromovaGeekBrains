package file.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInfo {
    private String filename;
    private  long lenght;

    public String getFilename() {
        return filename;
    }

    public long getLenght() {
        return lenght;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setLenght(long lenght) {
        this.lenght = lenght;
    }

    public FileInfo(Path path) {
        try {
            this.filename = path.getFileName().toString();
            if (Files.isDirectory(path)) {
                this.lenght = -1L;
            }
            else {
                this.lenght = Files.size(path);
            }
        }
        catch (IOException e) {
          throw  new RuntimeException("Something wrong with file^" + path.toAbsolutePath().toString());
        }

    }
}
