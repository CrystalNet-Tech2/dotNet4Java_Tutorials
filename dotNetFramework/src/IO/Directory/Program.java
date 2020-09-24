package IO.Directory;

import system.String;
import system.collections.IEnumerator;
import system.collections.generic.GenericIEnumerable;
import system.io.Directory;
import system.io.Path;

public class Program {

    public static void main(java.lang.String[] args) {
        final java.lang.String sourceDirectory = "C:\\Temp\\current";
        final java.lang.String archiveDirectory = "C:\\Temp\\archive";

        try {
            GenericIEnumerable txtFiles = Directory.EnumerateFiles(sourceDirectory, "*.txt");
            IEnumerator txtFiles_Enumerator = txtFiles.GetEnumerator().AsType(IEnumerator.class);
            while (txtFiles_Enumerator.MoveNext()) {
                java.lang.String currentFile = txtFiles_Enumerator.get_Current().toString();
                java.lang.String fileName = String.ValueOf(currentFile).Substring(sourceDirectory.length() + 1);
                Directory.Move(currentFile, Path.Combine(archiveDirectory, fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}