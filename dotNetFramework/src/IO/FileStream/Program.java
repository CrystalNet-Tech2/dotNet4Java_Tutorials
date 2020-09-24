package IO.FileStream;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.Convert;
import system.Int32;
import system.io.File;
import system.io.FileStream;
import system.text.UTF8Encoding;

public class Program {
    public static void main(String[] arg) {
        String path = "c:\\temp\\MyTest.txt";
        try {
            // Delete the file if it exists.
            if (File.Exists(path)) {
                File.Delete(path);
            }

            //Create the file.
            FileStream fs = File.Create(path);
            try {
                AddText(fs, "This is some text");
                AddText(fs, "This is some more text,");
                AddText(fs, "\r\nand this is on a new line");
                AddText(fs, "\r\n\r\nThe following is a subset of characters:\r\n");

                for (int i = 1; i < 120; i++) {
                    AddText(fs, Character.toString(Convert.ToChar(Int32.ValueOf(i))));
                }
            } finally {
                fs.Dispose();
                fs.close();
            }

            //Open the stream and read it back.
            fs = File.OpenRead(path);
            try {
                BigByte[] b = new BigByte[1024];
                UTF8Encoding temp = new UTF8Encoding(true);
                while (fs.Read(b, 0, b.length) > 0) {
                    Console.WriteLine(temp.GetString(b));
                }
            } finally {
                fs.Dispose();
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void AddText(FileStream fs, String value) throws Exception {
        BigByte[] info = new UTF8Encoding(true).GetBytes(value);
        fs.Write(info, 0, info.length);
    }
}
