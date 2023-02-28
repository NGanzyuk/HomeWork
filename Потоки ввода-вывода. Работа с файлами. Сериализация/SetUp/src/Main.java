import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File games = new File("Games");
        games.mkdir();
        File src = new File("Games/src");
        File res = new File("Games/res");
        File savegames = new File("Games/savegames");
        File temp = new File("Games/temp");
        src.mkdir();
        res.mkdir();
        savegames.mkdir();
        temp.mkdir();
        File main = new File("Games/src/main");
        File test = new File("Games/src/test");
        main.mkdir();
        test.mkdir();
        File mainFile = new File("Games/src/main", "Main.java");
        File utilsFile = new File("Games/src/main", "Utils.java");
        try {
            mainFile.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        try{
            utilsFile.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        File drawables = new File("Games/res/drawables");
        File vectors = new File("Games/res/vectors");
        File icons = new File("Games/res/icons");
        drawables.mkdir();
        vectors.mkdir();
        icons.mkdir();
        File tempFile = new File("Games/temp", "temp.txt");
        try {
            tempFile.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
