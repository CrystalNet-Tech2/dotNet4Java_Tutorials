package IO.MemoryStream;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.Convert;
import system.io.MemoryStream;
import system.io.Path;
import system.io.SeekOrigin;
import system.text.UnicodeEncoding;

public class Program {

    public static void main(String[] arg) {
        int count;
        BigByte[] byteArray;
        char[] charArray;

        try {
            UnicodeEncoding uniEncoding = new UnicodeEncoding();

            // Create the data to write to the stream.
            BigByte[] firstString = uniEncoding.GetBytes("Invalid file path characters are: ");
            BigByte[] secondString = uniEncoding.GetBytes(Path.GetInvalidPathChars());

            MemoryStream memStream = new MemoryStream(100);
            try {
                // Write the first String to the stream.
                memStream.Write(firstString, 0, firstString.length);

                // Write the second String to the stream, byte by byte.
                count = 0;
                while (count < secondString.length) {
                    memStream.WriteByte(secondString[count++]);
                }

                // Write the stream properties to the console.
                Console.WriteLine(
                        "Capacity = {0}, Length = {1}, Position = {2}\n",
                        memStream.get_Capacity(),
                        memStream.get_Length(),
                        memStream.get_Position());

                // Set the position to the beginning of the stream.
                memStream.Seek(0, SeekOrigin.Begin);

                // Read the first 20 bytes from the stream.
                byteArray = new BigByte[(int) memStream.get_Length()];
                count = memStream.Read(byteArray, 0, 20);

                // Read the remaining bytes, byte by byte.
                while (count < memStream.get_Length()) {
                    byteArray[count++] = Convert.ToByte(memStream.ReadByte());
                }

                // Decode the byte array into a char array
                // and write it to the console.
                charArray = new char[uniEncoding.GetCharCount(
                        byteArray, 0, count)];
                uniEncoding.GetDecoder().GetChars(
                        byteArray, 0, count, charArray, 0);
                Console.WriteLine(charArray);
            } finally {
                memStream.Dispose();
                memStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}