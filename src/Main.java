
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder outText = new StringBuilder();
        String startDir="D://";
        List<String> dirList = Arrays.asList("Games",
                                             "Games/src",
                                             "Games/src/main",
                                             "Games/src/test",
                                             "Games/res",
                                             "Games/res/drawables",
                                             "Games/res/vectors",
                                             "Games/res/icons",
                                             "Games/savegames",
                                             "Games/temp");

        List<String> fileList = Arrays.asList("Games/src/main/Main.java",
                                              "Games/src/main/Utils.java",
                                              "Games/temp/temp.txt");

        for (int i = 0; i < dirList.size(); i++) {
            File dir = new File(startDir + dirList.get(i));
            if(dir.mkdir())
                outText.append("Была создана папка  " + dirList.get(i) + "\n");
            else
                outText.append("Не получилось создать папку  " + dirList.get(i) + "\n");
        }


        for (int i = 0; i < fileList.size(); i++) {
            File myfile = new File(startDir + fileList.get(i));
            try{
                if(myfile.createNewFile())
                    outText.append("Был создан файл  " + fileList.get(i) + "\n");
                else
                    outText.append("Не получилось создать файл  " + fileList.get(i) + "\n");

            }catch (IOException ex){
                outText.append("Исключение при создании файлал  " + ex + "\n");
            }
        }

        File outFile = new File(startDir+fileList.get(2));
        try(FileWriter writer = new FileWriter(outFile)){//new File(fileList.get(2)))){
            writer.write(outText.toString());
            writer.flush();
        }catch (IOException ex){
            outText.append( ex.getMessage());
        }
    }
}