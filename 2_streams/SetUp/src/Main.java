import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static StringBuilder createDirectory(File dir, StringBuilder log){

        if (dir.mkdir()){
            log.append(LocalDateTime.now()+": Директория "+dir.getName()+" "+dir.getPath()+" успешно создана"+"\n");
        }
        else{
            log.append(LocalDateTime.now()+": Директория "+dir.getName()+" "+dir.getPath()+" не создана"+"\n");
        }

        return log;
    }

    public static StringBuilder createFile(File file, StringBuilder log){
        try {
            if(file.createNewFile()){
                log.append(LocalDateTime.now()+": Файл "+file.getName()+" "+file.getPath()+" успешно создан"+"\n");
            }
            else {
                log.append(LocalDateTime.now()+": Файл "+file.getName()+" "+file.getPath()+" не создан"+"\n");
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return log;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder logs = new StringBuilder();
        ArrayList<File> dirList = new ArrayList<> ();
        dirList.add(new File("C:/Games"));
        dirList.add(new File("C:/Games/src"));
        dirList.add(new File("C:/Games/res"));
        dirList.add(new File("C:/Games/savegames"));
        dirList.add(new File("C:/Games/temp"));
        dirList.add(new File("C:/Games/src/main"));
        dirList.add(new File("C:/Games/src/test"));
        dirList.add(new File("C:/Games/res/drawables"));
        dirList.add(new File("C:/Games/res/vectors"));
        dirList.add(new File("C:/Games/res/icons"));
        for (File dir: dirList) {
            logs = createDirectory(dir, logs);
        }

        ArrayList<File> fileList = new ArrayList<> ();
        fileList.add(new File("C:/Games/src/main", "Main.java"));
        fileList.add(new File("C:/Games/src/main", "Utils.java"));
        fileList.add(new File("C:/Games/temp", "temp.txt"));
        for (File file: fileList) {
            logs = createFile(file, logs);
        }

        try(FileOutputStream fos = new FileOutputStream("C:/Games/temp.txt")) {
           byte[] bytes = logs.toString().getBytes();
           fos.write(bytes, 0, bytes.length);
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
