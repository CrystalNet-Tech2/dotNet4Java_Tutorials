package Security.Cryptography.HMACSHA384;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.io.*;
import system.security.cryptography.HMACSHA384;
import system.security.cryptography.RNGCryptoServiceProvider;

public class Program {
    public static void main(String[] Fileargs) {
        String dataFile;
        String signedFile;
        try {
            //If no file names are specified, create them.
            if (Fileargs.length < 2) {
                dataFile = "text.txt";
                signedFile = "signedFile.enc";

                if (!File.Exists(dataFile)) {
                    // Create a file to write to.
                    StreamWriter sw = File.CreateText(dataFile);
                    try {
                        sw.WriteLine("Here is a message to sign");
                    } finally {
                        sw.Dispose();
                        sw.close();
                    }
                }
            } else {
                dataFile = Fileargs[0];
                signedFile = Fileargs[1];
            }
            try {
                // Create a random key using a random number generator. This would be the
                //  secret key shared by sender and receiver.
                BigByte[] secretkey = new BigByte[64];
                //RNGCryptoServiceProvider is an implementation of a random number generator.
                RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
                try {
                    // The array is now filled with cryptographically strong random bytes.
                    rng.GetBytes(secretkey);

                    // Use the secret key to sign the message file.
                    SignFile(secretkey, dataFile, signedFile);

                    // Verify the signed file
                    VerifyFile(secretkey, signedFile);
                } finally {
                    rng.Dispose();
                    rng.close();
                }
            } catch (IOException e) {
                Console.WriteLine("Error: File not found", e);
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    // Computes a keyed hash for a source file and creates a target file with the keyed hash
    // prepended to the contents of the source file.
    public static void SignFile(BigByte[] key, String sourceFile, String destFile) throws Exception {
        // Initialize the keyed hash object.
        HMACSHA384 hmac = new HMACSHA384(key);
        try {
            FileStream inStream = new FileStream(sourceFile, FileMode.Open);
            try {
                FileStream outStream = new FileStream(destFile, FileMode.Create);
                try {
                    // Compute the hash of the input file.
                    BigByte[] hashValue = hmac.ComputeHash(inStream);
                    // Reset inStream to the beginning of the file.
                    inStream.set_Position(0);
                    // Write the computed hash value to the output file.
                    outStream.Write(hashValue, 0, hashValue.length);
                    // Copy the contents of the sourceFile to the destFile.
                    int bytesRead;
                    // read 1K at a time
                    BigByte[] buffer = new BigByte[1024];
                    do {
                        // Read from the wrapping CryptoStream.
                        bytesRead = inStream.Read(buffer, 0, 1024);
                        outStream.Write(buffer, 0, bytesRead);
                    } while (bytesRead > 0);
                } finally {
                    outStream.Dispose();
                    outStream.close();
                }
            } finally {
                inStream.Dispose();
                inStream.close();
            }
        } finally {
            hmac.Dispose();
            hmac.close();
        }
        return;
    }

    // Compares the key in the source file with a new key created for the data portion of the file. If the keys
    // compare the data has not been tampered with.
    public static boolean VerifyFile(BigByte[] key, String sourceFile) throws Exception {
        boolean err = false;
        // Initialize the keyed hash object.
        HMACSHA384 hmac = new HMACSHA384(key);
        try {
            // Create an array to hold the keyed hash value read from the file.
            BigByte[] storedHash = new BigByte[hmac.get_HashSize() / 8];
            // Create a FileStream for the source file.
            FileStream inStream = new FileStream(sourceFile, FileMode.Open);
            try {
                // Read in the storedHash.
                inStream.Read(storedHash, 0, storedHash.length);
                // Compute the hash of the remaining contents of the file.
                // The stream is properly positioned at the beginning of the content,
                // immediately after the stored hash value.
                BigByte[] computedHash = hmac.ComputeHash(inStream);
                // compare the computed hash with the stored value

                for (int i = 0; i < storedHash.length; i++) {
                    if (computedHash[i] != storedHash[i]) {
                        err = true;
                    }
                }
            } finally {
                inStream.Dispose();
                inStream.close();
            }
        } finally {
            hmac.Dispose();
            hmac.close();
        }
        if (err) {
            Console.WriteLine("Hash values differ! Signed file has been tampered with!");
            return false;
        } else {
            Console.WriteLine("Hash values agree -- no tampering occurred.");
            return true;
        }
    }
}