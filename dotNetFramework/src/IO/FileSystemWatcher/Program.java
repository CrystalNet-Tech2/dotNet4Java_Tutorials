package IO.FileSystemWatcher;

//The following example creates a FileSystemWatcher to watch the directory specified at run time.
// The component is set to watch for changes in LastWrite and LastAccess time, the creation,
// deletion, or renaming of text files in the directory. If a file is changed, created, or deleted,
// he path to the file prints to the console. When a file is renamed, the old and new paths print to the console.

import com.dotNet4Java.IClrType;
import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.api.Enums.EnumUtils;
import system.Console;
import system.Enum;
import system.Environment;
import system.Type;
import system.io.*;

import java.util.EnumSet;

public class Program {
    public static void main(String[] arg) {
        try {
            Run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Run() throws Exception {
       // String[] args = Environment.GetCommandLineArgs();
        String[] args ={"C:\\Temp", "C:\\Temp"};

        // If a directory is not specified, exit program.
        if (args.length != 2) {
            // Display the proper way to call the program.
            Console.WriteLine("Usage: Watcher.exe (directory)");
            return;
        }

        // Create a new FileSystemWatcher and set its properties.
        FileSystemWatcher watcher = new FileSystemWatcher();
        try {
            watcher.set_Path(args[1]);

            // Watch for changes in LastAccess and LastWrite times, and
            // the renaming of files or directories.
            watcher.set_NotifyFilter(EnumSet.of(NotifyFilters.LastAccess, NotifyFilters.LastWrite, NotifyFilters.FileName, NotifyFilters.DirectoryName));

            // Only watch text files.
            watcher.set_Filter("*.txt");

            // Define the event handlers.
            FileSystemEventHandler OnChanged = new FileSystemEventHandler() {
                @Override
                public void invoke(Object o, FileSystemEventArgs e) throws Exception {
                    // Specify what is done when a file is changed, created, or deleted.
                    IClrType type = TClrAssembly.findType("System.IO.WatcherChangeTypes");
                    Console.WriteLine("File: {0} {1}", e.get_FullPath(), type.getEnumName((int)EnumUtils.setToInteger(e.get_ChangeType())));
                }
            };

            RenamedEventHandler OnRenamed = new RenamedEventHandler() {
                @Override
                public void invoke(Object o, RenamedEventArgs e) throws Exception {
                    // Specify what is done when a file is renamed.
                    Console.WriteLine("File: {0} renamed to {1}", e.get_OldFullPath(), e.get_FullPath());
                }
            };

            // Add event handlers.
            watcher.add_Changed(OnChanged);
            watcher.add_Created(OnChanged);
            watcher.add_Deleted(OnChanged);
            watcher.add_Renamed(OnRenamed);

            // Begin watching.
            watcher.set_EnableRaisingEvents(true);

            // Wait for the user to quit the program.
            Console.WriteLine("Press 'q' to quit the sample.");
            while (Console.Read() != 'q') ;
        } finally {
            watcher.Dispose();
            watcher.close();
        }
    }
}
