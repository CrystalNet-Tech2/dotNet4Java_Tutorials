package Security.Cryptography.RC2CryptoServiceProvider;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.io.MemoryStream;
import system.security.cryptography.CryptoStream;
import system.security.cryptography.CryptoStreamMode;
import system.security.cryptography.ICryptoTransform;
import system.security.cryptography.RC2CryptoServiceProvider;
import system.text.Encoding;
import system.text.StringBuilder;

public class Program {
    public static void main(String[] arg) {
        try {
            // Create a new instance of the RC2CryptoServiceProvider class
            // and automatically generate a Key and IV.
            RC2CryptoServiceProvider rc2CSP = new RC2CryptoServiceProvider();

            Console.WriteLine("Effective key size is {0} bits.", rc2CSP.get_EffectiveKeySize());

            // Get the key and IV.
            BigByte[] key = rc2CSP.get_Key();
            BigByte[] IV = rc2CSP.get_IV();

            // Get an encryptor.
            ICryptoTransform encryptor = rc2CSP.CreateEncryptor(key, IV);

            // Encrypt the data as an array of encrypted bytes in memory.
            MemoryStream msEncrypt = new MemoryStream();
            CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write);

            // Convert the data to a BigByte array.
            String original = "Here is some data to encrypt.";
            BigByte[] toEncrypt = Encoding.get_ASCII().GetBytes(original);

            // Write all data to the crypto stream and flush it.
            csEncrypt.Write(toEncrypt, 0, toEncrypt.length);
            csEncrypt.FlushFinalBlock();

            // Get the encrypted array of bytes.
            BigByte[] encrypted = msEncrypt.ToArray();

            ///////////////////////////////////////////////////////
            // This is where the data could be transmitted or saved.
            ///////////////////////////////////////////////////////

            //Get a decryptor that uses the same key and IV as the encryptor.
            ICryptoTransform decryptor = rc2CSP.CreateDecryptor(key, IV);

            // Now decrypt the previously encrypted message using the decryptor
            // obtained in the above step.
            MemoryStream msDecrypt = new MemoryStream(encrypted);
            CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read);

            // Read the decrypted bytes from the decrypting stream
            // and place them in a Text.StringBuilder class.

            StringBuilder roundtrip = new StringBuilder();

            int b = 0;

            do {
                b = csDecrypt.ReadByte();

                if (b != -1) {
                    roundtrip.Append((char) b);
                }
            } while (b != -1);

            // Display the original data and the decrypted data.
            Console.WriteLine("Original:   {0}", original);
            Console.WriteLine("Round Trip: {0}", roundtrip);

            Console.ReadLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}