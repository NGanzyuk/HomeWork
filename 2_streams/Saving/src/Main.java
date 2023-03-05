import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class Main {

    public static void saveGame(String filePath, GameProgress progress) throws IOException {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm_ss");
        try (FileOutputStream newSave = new FileOutputStream(filePath + LocalDateTime.now().format(myFormat) + "_save.dat");
             ObjectOutputStream newObjectSave = new ObjectOutputStream(newSave)) {

            newObjectSave.writeObject(progress);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void zipFiles(String zipPath, File[] files) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath + "saves.zip"))) {
            for (File file : files) {
                try {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(entry);
                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipOut.write(buffer);
                    zipOut.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openZip(String zipPath, String zipOutPath) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zipIn.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(zipOutPath + "/" + name);
                for (int i = zipIn.read(); i != -1; i = zipIn.read()) {
                    fout.write(i);
                }
                fout.flush();
                zipIn.closeEntry();
                fout.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String filePath) throws Exception {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            gameProgress = (GameProgress) ois.readObject();

        } catch (Exception ex) {
            ex.getMessage();
        }
        System.out.println(gameProgress);
    }

    public static void main(String[] args) throws Exception {
        GameProgress progress1 = new GameProgress(50, 2, 10, 435.25);
        String filePath = "C:/Games/savegames/";
        saveGame(filePath, progress1);
        String zipPath = "C:/Games/savegames/";
        File dir = new File("C:/Games/savegames");
        zipFiles(zipPath, dir.listFiles());
        for (File file : dir.listFiles()) {
            if (!file.getName().endsWith(".zip")) {
                file.delete();
            }
        }
        String openZipPath = "C:/Games/savegames/saves.zip";
        String unpackTo = "C:/Games";
        openZip(openZipPath, unpackTo);
        String saveFilePath = "C:/Games/temp/28-02-2023_04_44_48_save.dat";
        openProgress(saveFilePath);
    }
}
