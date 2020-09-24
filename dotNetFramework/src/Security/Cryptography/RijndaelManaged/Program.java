package Security.Cryptography.RijndaelManaged;

import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.types.BigByte;
import system.Exception;
import system.io.MemoryStream;
import system.io.StreamReader;
import system.io.StreamWriter;
import system.security.cryptography.CryptoStream;
import system.security.cryptography.CryptoStreamMode;
import system.security.cryptography.ICryptoTransform;
import system.security.cryptography.RijndaelManaged;

public class Program {

    static BigByte[] encryptStringToBytes(String plainText, BigByte[] Key, BigByte[] IV) throws java.lang.Exception {

        // Check arguments.
        if (plainText.length() <= 0)
            throw new Exception("plainText argument is empty");

        if (Key == null || Key.length <= 0)
            throw new Exception("Key argument is empty or null");

        if (IV == null || IV.length <= 0)
            throw new Exception("IV argument is empty or null");

        // Create an RijndaelManaged object with the specified key and IV.
        RijndaelManaged rijAlg = new RijndaelManaged();
        rijAlg.set_Key(Key);
        rijAlg.set_IV(IV);

        // Create a decryptor to perform the stream transform.
        ICryptoTransform encryptor = rijAlg.CreateEncryptor(rijAlg.get_Key(), rijAlg.get_IV());

        // Create the streams used for encryption.
        MemoryStream msEncrypt = new MemoryStream();
        CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write);

        StreamWriter swEncrypt = new StreamWriter(csEncrypt);
        //Write all data to the stream.
        swEncrypt.Write(plainText);
        swEncrypt.Close();

        // Return the encrypted bytes from the memory stream.
        BigByte[] result = msEncrypt.ToArray();

        msEncrypt.Close();
        csEncrypt.Close();
        swEncrypt.Close();

        return result;
    }

    static String decryptStringFromBytes(BigByte[] cipherText, BigByte[] Key, BigByte[] IV) throws java.lang.Exception {

        // Check arguments.
        if (cipherText == null || cipherText.length <= 0)
            throw new EClrError("cipherText argument is empty or null");

        if (Key == null || Key.length <= 0)
            throw new EClrError("Key argument is empty or null");

        if (IV == null || IV.length <= 0)
            throw new EClrError("IV argument is empty or null");

        // Create an RijndaelManaged object with the specified key and IV.
        RijndaelManaged rijAlg = new RijndaelManaged();
        rijAlg.set_Key(Key);
        rijAlg.set_IV(IV);

        // Create a decryptor to perform the stream transform.
        ICryptoTransform decryptor = rijAlg.CreateDecryptor(rijAlg.get_Key(), rijAlg.get_IV());

        // Create the streams used for decryption.
        MemoryStream msDecrypt = new MemoryStream(cipherText);

        CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read);

        StreamReader srDecrypt = new StreamReader(csDecrypt);

        // Read the decrypted bytes from the decrypting stream and place them in a String.
        String result = srDecrypt.ReadToEnd();

        msDecrypt.Close();
        csDecrypt.Close();
        srDecrypt.Close();

        return result;
    }

    public static void main(String[] arg) {
        try {

            String original = "Here is some data to encrypt!";

            // Create a new instance of the RijndaelManaged class.
            // This generates a new key and initialization vector (IV).
            RijndaelManaged myRijndael = new RijndaelManaged();
            myRijndael.GenerateKey();
            myRijndael.GenerateIV();

            // Encrypt the String to an array of bytes.
            BigByte[] encrypted = encryptStringToBytes(original, myRijndael.get_Key(), myRijndael.get_IV());

            // Decrypt the bytes to a String.
            String roundtrip = decryptStringFromBytes(encrypted, myRijndael.get_Key(), myRijndael.get_IV());

            //Display the original data and the decrypted data.
            System.out.println(String.format("Original:   %s", original));
            System.out.println(String.format("Round Trip: %s", roundtrip));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}