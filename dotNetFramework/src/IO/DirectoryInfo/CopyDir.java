package IO.DirectoryInfo;

import system.Console;
import system.io.Directory;
import system.io.DirectoryInfo;
import system.io.FileInfo;
import system.io.Path;

public class CopyDir {

    public static void CopyAll(DirectoryInfo source, DirectoryInfo target) throws Exception {
        if (source.get_FullName().toLowerCase() == target.get_FullName().toLowerCase()) {
            return;
        }

        // Check if the target directory exists, if not, create it.
        if (Directory.Exists(target.get_FullName()) == false) {
            Directory.CreateDirectory(target.get_FullName());
        }

        // Copy each file into it's new directory.
        for (FileInfo fi : source.GetFiles().toArray()) {
            Console.WriteLine("Copying {0}\\{1}", target.get_FullName(), fi.get_Name());
            fi.CopyTo(Path.Combine(target.ToString(), fi.get_Name()), true);
        }

        // Copy each subdirectory using recursion.
        for (DirectoryInfo diSourceSubDir : source.GetDirectories().toArray()) {
            DirectoryInfo nextTargetSubDir =
                    target.CreateSubdirectory(diSourceSubDir.get_Name());
            CopyAll(diSourceSubDir, nextTargetSubDir);
        }
    }

    public static void main(String[] arg) {
        String sourceDirectory = "c:\\sourceDirectory";
        String targetDirectory = "c:\\targetDirectory";

        try {
            DirectoryInfo diSource = new DirectoryInfo(sourceDirectory);
            DirectoryInfo diTarget = new DirectoryInfo(targetDirectory);

            CopyAll(diSource, diTarget);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}