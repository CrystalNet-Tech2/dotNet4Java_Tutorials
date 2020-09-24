package IO.StreamWriter;

import system.Console;
import system.io.DirectoryInfo;
import system.io.StreamReader;
import system.io.StreamWriter;

public class Program {

    static void Main(String[] args) {
        try {
            // Get the directories currently on the C drive.
            DirectoryInfo[] cDirs = new DirectoryInfo("c:\\Temp").GetDirectories().toArray();

            // Write each directory name to a file.
            StreamWriter sw = new StreamWriter("CDriveDirs.txt");
            try {
                for (DirectoryInfo dir : cDirs) {
                    sw.WriteLine(dir.get_Name());
                }
            } finally {
                sw.Dispose();
                sw.close();
            }

            // Read and show each line from the file.
            String line = "";
            StreamReader sr = new StreamReader("CDriveDirs.txt");
            try {
                while ((line = sr.ReadLine()) != null) {
                    Console.WriteLine(line);
                }
            } finally {
                sw.Dispose();
                sw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
