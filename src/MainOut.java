import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainOut {
    public static void main(String[] args) {
        openZip("D://Games/savegames/", "D://Games/savegames/output.zip");
        System.out.println(openProgress("D://Games/savegames/Game1.txt"));
    }

    public static void openZip(String dir, String fileName) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(dir + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String file) {
        GameProgress gemeProgress = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gemeProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gemeProgress;
    }
}