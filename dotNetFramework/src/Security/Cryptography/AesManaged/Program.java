package Security.Cryptography.AesManaged;

import com.dotNet4Java.types.BigByte;
import system.ArgumentNullException;
import system.Console;
import system.io.MemoryStream;
import system.io.StreamReader;
import system.io.StreamWriter;
import system.security.cryptography.AesManaged;
import system.security.cryptography.CryptoStream;
import system.security.cryptography.CryptoStreamMode;
import system.security.cryptography.ICryptoTransform;

public class Program {
    public static void main(String[] arg) {
        String original = "Here is some data to encrypt!";
        try {
            // Create a new instance of the AesManaged
            // class.  This generates a new key and initialization
            // vector (IV).
            AesManaged myAes = new AesManaged();
            try {

                // Encrypt the String to an array of bytes.
                BigByte[] encrypted = EncryptStringToBytes_Aes(original, myAes.get_Key(), myAes.get_IV());

                // Decrypt the bytes to a String.
                String roundtrip = DecryptStringFromBytes_Aes(encrypted, myAes.get_Key(), myAes.get_IV());

                //Display the original data and the decrypted data.
                Console.WriteLine("Original:   {0}", original);
                Console.WriteLine("Round Trip: {0}", roundtrip);
            } finally {
                myAes.Dispose();
                myAes.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    static BigByte[] EncryptStringToBytes_Aes(String plainText, BigByte[] Key, BigByte[] IV) throws Exception {
        // Check arguments.
        if (plainText == null || plainText.length() <= 0)
            throw new ArgumentNullException("plainText");
        if (Key == null || Key.length <= 0)
            throw new ArgumentNullException("Key");
        if (IV == null || IV.length <= 0)
            throw new ArgumentNullException("IV");
        BigByte[] encrypted;


        // Create an AesManaged object
        // with the specified key and IV.
        AesManaged aesAlg = new AesManaged();
        try {
            aesAlg.set_Key(Key);
            aesAlg.set_IV(IV);

            // Create an encryptor to perform the stream transform.
            ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.get_Key(), aesAlg.get_IV());

            // Create the streams used for encryption.
            MemoryStream msEncrypt = new MemoryStream();
            try {
                CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write);
                try {
                    StreamWriter swEncrypt = new StreamWriter(csEncrypt);
                    try {
                        //Write all data to the stream.
                        swEncrypt.Write(plainText);
                    } finally {
                        swEncrypt.Dispose();
                        swEncrypt.close();
                    }
                    encrypted = msEncrypt.ToArray();
                } finally {
                    csEncrypt.Dispose();
                    csEncrypt.close();
                }
            } finally {
                msEncrypt.Dispose();
                msEncrypt.close();
            }
        } finally {
            aesAlg.Dispose();
            aesAlg.close();
        }

        // Return the encrypted bytes from the memory stream.
        return encrypted;
    }

    static String DecryptStringFromBytes_Aes(BigByte[] cipherText, BigByte[] Key, BigByte[] IV) throws Exception {
        // Check arguments.
        if (cipherText == null || cipherText.length <= 0)
            throw new ArgumentNullException("cipherText");
        if (Key == null || Key.length <= 0)
            throw new ArgumentNullException("Key");
        if (IV == null || IV.length <= 0)
            throw new ArgumentNullException("IV");

        // Declare the String used to hold
        // the decrypted text.
        String plaintext = null;

        // Create an AesManaged object
        // with the specified key and IV.
        AesManaged aesAlg = new AesManaged();
        try {
            aesAlg.set_Key(Key);
            aesAlg.set_IV(IV);

            // Create a decryptor to perform the stream transform.
            ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.get_Key(), aesAlg.get_IV());

            // Create the streams used for decryption.
            MemoryStream msDecrypt = new MemoryStream(cipherText);
            try {
                CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read);
                try {
                    StreamReader srDecrypt = new StreamReader(csDecrypt);
                    try {

                        // Read the decrypted bytes from the decrypting stream
                        // and place them in a String.
                        plaintext = srDecrypt.ReadToEnd();
                    } finally {
                        srDecrypt.Dispose();
                        srDecrypt.close();
                    }
                } finally {
                    csDecrypt.Dispose();
                    csDecrypt.close();
                }
            } finally {
                msDecrypt.Dispose();
                msDecrypt.close();
            }
        } finally {
            aesAlg.Dispose();
            aesAlg.close();
        }

        return plaintext;
    }
}
