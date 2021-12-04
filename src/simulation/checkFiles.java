package simulation;
import java.io.File;

public class checkFiles{

        public String split_PathString(String path){

            String[] result = path.split("/");
            String r = "";
            for (int i = 0; i < result.length - 1; i++) {
                    r += result[i] + "/";
            }
            return r;
        }

        public  boolean fileExists(String fileName){

            // test to see if a file exists
            File file = new File(fileName);  //File file = new File("/Users/al/.bash_history");

            if (file.exists())
            {
                System.out.println("- File founded: " + file.getPath());
                return true;
            }else{
                System.err.println("- File was not founded :" + fileName);
            }

            return false;
        }

        public boolean dirExists(String dir){
            File tmpDir = new File(dir);  ///File tmpDir = new File("/var/tmp");
            boolean exists = tmpDir.exists();
            if (exists && tmpDir.isDirectory()){
                System.out.println("- Folder exists: " + dir);
                return true;
            }else{
                System.err.println("- Folder was not founded !!!! : " + dir);
            }

            return false;
        }

        public static void main(String[] args)
        {

            checkFiles a = new checkFiles();
            System.out.println(a.dirExists("teste/cccc/circuitos_pequenos/"));
            System.out.println(a.fileExists("teste/cccc/circuitos_pequenos/c.v"));

        }
    }

