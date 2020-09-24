package IO.BinaryWriter;

import system.Console;
import system.io.BinaryReader;
import system.io.BinaryWriter;
import system.io.File;
import system.io.FileMode;

//The following code example demonstrates how to store and retrieve application settings in a file.
public class Program {

    private final static String fileName = "C:\\Temp\\AppSettings.dat";

    public static void main(String[] arg) {
        try {
            WriteDefaultValues();
            DisplayValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteDefaultValues() throws Exception {
        BinaryWriter writer = null;

        writer = new BinaryWriter(File.Open(fileName, FileMode.Create));
        try {
            writer.Write(1.250F);
            writer.Write("c:\\Temp");
            writer.Write(10);
            writer.Write(true);
        } finally {
            writer.Dispose();
            writer.close();
        }
    }

    public static void DisplayValues() throws Exception {
        float aspectRatio;
        String tempDirectory;
        int autoSaveTime;
        boolean showStatusBar;

        if (File.Exists(fileName)) {
            BinaryReader reader = new BinaryReader(File.Open(fileName, FileMode.Open));
            try {
                aspectRatio = reader.ReadSingle();
                tempDirectory = reader.ReadString();
                autoSaveTime = reader.ReadInt32();
                showStatusBar = reader.ReadBoolean();
            } finally {
                reader.Dispose();
                reader.close();
            }

            Console.WriteLine("Aspect ratio set to: " + aspectRatio);
            Console.WriteLine("Temp directory is: " + tempDirectory);
            Console.WriteLine("Auto save time set to: " + autoSaveTime);
            Console.WriteLine("Show status bar: " + showStatusBar);
        }
    }
}