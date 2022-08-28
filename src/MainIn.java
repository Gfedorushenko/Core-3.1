import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainIn {
    public static void main(String[] args) {
        GameProgress game1=new GameProgress(400,2,3,2.1);
        GameProgress game2=new GameProgress(200,5,9,5.1);
        GameProgress game3=new GameProgress(100,9,7,1.1);
        String dir="D://Games/savegames/";
        List<String> fileList = Arrays.asList("Game1.txt",
                "Game2.txt",
                "Game3.txt");
        saveGame(game1,dir+fileList.get(0));
        saveGame(game2,dir+fileList.get(1));
        saveGame(game3,dir+fileList.get(2));

        zipFiles(dir,fileList);


        for (int i = 0; i < fileList.size(); i++) {
            File newFile = new File(dir+fileList.get(i));
            System.out.println(newFile.delete());
        }


    }

    public static void saveGame(GameProgress game,String file){
        try(FileOutputStream fos = new FileOutputStream(file)){
            byte[] bytes =game.toString().getBytes();
            fos.write(bytes,0,bytes.length);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String dir,List fileList){
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dir+"output.zip"));
        )
        {
            for (int i = 0; i < fileList.size(); i++) {
                FileInputStream fis= new FileInputStream((String) (dir+fileList.get(i)));
                ZipEntry entry=new ZipEntry((String) fileList.get(i));
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
            }
            zout.closeEntry();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}