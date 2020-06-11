package file.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInfo {
    public static final String UP_TOKEN = "[..]";

    private String filename;
    private  long length;

    public String getFilename() {
        return filename;
    }

    public long getLenght() {
        return length;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setLenght(long length) {
        this.length = length;
    }

    public boolean isDirectory(){
        return length == -1L;
    }

    public boolean isUpElement(){
        return length == -2L;
    }

    public FileInfo(String filename, long length){
        this.filename = filename;
        this.length = length;
    }

    public FileInfo(Path path) {
        try {
            this.filename = path.getFileName().toString();
            if (Files.isDirectory(path)) {
                this.length = -1L;
            }
            else {
                this.length = Files.size(path);
            }
        }
        catch (IOException e) {
          throw  new RuntimeException("Something wrong with file^" + path.toAbsolutePath().toString());
        }

    }
}
