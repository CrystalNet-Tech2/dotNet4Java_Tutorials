package IO.Path;

import com.dotNet4Java.api.EClrError;
import system.Console;
import system.io.Path;

public class Program {
    public static void main(String[] arg) {
        String path1 = "c:\\temp\\MyTest.txt";
        String path2 = "c:\\temp\\MyTest";
        String path3 = "temp";

        try {
            if (Path.HasExtension(path1)) {
                Console.WriteLine("{0} has an extension.", path1);
            }

            if (!Path.HasExtension(path2)) {
                Console.WriteLine("{0} has no extension.", path2);
            }

            if (!Path.IsPathRooted(path3)) {
                Console.WriteLine("The String {0} contains no root information.", path3);
            }

            Console.WriteLine("The full path of {0} is {1}.", path3, Path.GetFullPath(path3));
            Console.WriteLine("{0} is the location for temporary files.", Path.GetTempPath());
            Console.WriteLine("{0} is a file available for use.", Path.GetTempFileName());

        } catch (EClrError error) {
            error.printStackTrace();
        }
    }
}

/* This code produces output similar to the following:
 * c:\temp\MyTest.txt has an extension.
 * c:\temp\MyTest has no extension.
 * The String temp contains no root information.
 * The full path of temp is D:\Documents and Settings\cliffc\My Documents\Visual Studio 2005\Projects\ConsoleApplication2\ConsoleApplication2\bin\Debug\temp.
 * D:\Documents and Settings\cliffc\Local Settings\Temp\8\ is the location for temporary files.
 * D:\Documents and Settings\cliffc\Local Settings\Temp\8\tmp3D.tmp is a file available for use.
 */
