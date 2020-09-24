package Security;

import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.BitwiseEnum;
import com.dotNet4Java.api.core.DotNetNativeTypes;
import com.dotNet4Java.types.BigByte;

/**
 * Java equivalent of the C# CryptoStreamMode enumeration type
 */
enum CryptoStreamMode implements BitwiseEnum<CryptoStreamMode> {
    Read(0x0),
    Write(0x1);

    private final int _flags;

    CryptoStreamMode(int flags) {
        _flags = flags;
    }

    @Override
    public long getFlags() {
        return _flags;
    }
}

class RijndaelManaged extends TClrObject {

    public RijndaelManaged() throws EClrError {
        super("System.Security.Cryptography.RijndaelManaged", TClrArray.emptyObjectArray());
    }

    public BigByte[] getKey() throws EClrError {
        return (BigByte[]) getPropertyValue("Key");
    }

    public void setKey(BigByte[] key) throws EClrError {
        setPropertyValue("Key", key);
    }

    public BigByte[] getIV() throws EClrError {
        return (BigByte[]) getPropertyValue("IV");
    }

    public void setIV(BigByte[] iv) throws EClrError {
        setPropertyValue("IV", iv);
    }

    public ICryptoTransform CreateEncryptor(BigByte[] key, BigByte[] iv) throws EClrError {
        DotNetNativeTypes.ClrObject cryptoTransform = invokeClrObjectMethod("CreateEncryptor", TClrArray.of("System.Byte[]", "System.Byte[]"), TClrArray.of(key, iv));
        return cryptoTransform == null ? null : new ICryptoTransform(cryptoTransform);
    }

    public ICryptoTransform CreateDecryptor(BigByte[] key, BigByte[] iv) throws EClrError {
        DotNetNativeTypes.ClrObject cryptoTransform = invokeClrObjectMethod("CreateDecryptor", TClrArray.of("System.Byte[]", "System.Byte[]"), TClrArray.of(key, iv));
        return cryptoTransform == null ? null : new ICryptoTransform(cryptoTransform);
    }

    public void GenerateKey() throws EClrError {
        invokeVoidMethod("GenerateKey");
    }

    public void GenerateIV() throws EClrError {
        invokeVoidMethod("GenerateIV");
    }
}

/**
 * Java equivalent of the C# ICryptoTransform interface
 */
class ICryptoTransform extends TClrObject {

    public ICryptoTransform(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }
}

/**
 * Java equivalent of the C# MemoryStream Class
 */
class MemoryStream extends TClrObject {

    public MemoryStream() throws EClrError {
        super("System.IO.MemoryStream", new Object[]{});
    }

    public MemoryStream(BigByte[] cipherText) throws EClrError {
        super("System.IO.MemoryStream", new Object[]{cipherText}, new String[]{"System.Byte[]"});
    }

    public BigByte[] ToArray() throws EClrError {
        return (BigByte[]) invokeMethod("ToArray");
    }

    public void Close() throws EClrError {
        invokeVoidMethod("Close");
    }
}

/**
 * Java equivalent of the C# CryptoStream Class
 */
class CryptoStream extends TClrObject {

    public CryptoStream(MemoryStream msEncrypt, ICryptoTransform encryptor, CryptoStreamMode mode) throws EClrError {
        super("System.Security.Cryptography.CryptoStream", msEncrypt, encryptor, mode);
    }

    public void Close() throws EClrError {
        invokeVoidMethod("Close");
    }
}

/**
 * Java equivalent of the C# StreamWriter Class
 */
class StreamWriter extends TClrObject {

    public StreamWriter(CryptoStream csEncrypt) throws EClrError {
        super("System.IO.StreamWriter", csEncrypt);
    }

    public void Write(String plainText) throws EClrError {
        invokeVoidMethod("Write", new String[]{"System.String"}, new Object[]{plainText});
    }

    public void Close() throws EClrError {
        invokeVoidMethod("Close");
    }
}

/**
 * Java equivalent of the C# StreamReader Class
 */
class StreamReader extends TClrObject {

    public StreamReader(CryptoStream csDecrypt) throws EClrError {
        super("System.IO.StreamReader", csDecrypt);
    }

    public void Close() throws EClrError {
        invokeVoidMethod("Close");
    }

    public String ReadToEnd() throws EClrError {
        return invokeStringMethod("ReadToEnd");
    }
}

public class RijndaelSecurity {

    // Note: The int[] should have been byte[] but Java and .Net byte types are different. So all the bytes in .Net are represented as integer

    static BigByte[] encryptStringToBytes(String plainText, BigByte[] Key, BigByte[] IV) throws EClrError {

        // Check arguments.
        if (plainText.length() <= 0)
            throw new EClrError("plainText argument is empty");

        if (Key == null || Key.length <= 0)
            throw new EClrError("Key argument is empty or null");

        if (IV == null || IV.length <= 0)
            throw new EClrError("IV argument is empty or null");

        // Create an RijndaelManaged object with the specified key and IV.
        RijndaelManaged rijAlg = new RijndaelManaged();
        rijAlg.setKey(Key);
        rijAlg.setIV(IV);

        // Create a decryptor to perform the stream transform.
        ICryptoTransform encryptor = rijAlg.CreateEncryptor(rijAlg.getKey(), rijAlg.getIV());

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

    static String decryptStringFromBytes(BigByte[] cipherText, BigByte[] Key, BigByte[] IV) throws EClrError {

        // Check arguments.
        if (cipherText == null || cipherText.length <= 0)
            throw new EClrError("cipherText argument is empty or null");

        if (Key == null || Key.length <= 0)
            throw new EClrError("Key argument is empty or null");

        if (IV == null || IV.length <= 0)
            throw new EClrError("IV argument is empty or null");

        // Create an RijndaelManaged object with the specified key and IV.
        RijndaelManaged rijAlg = new RijndaelManaged();
        rijAlg.setKey(Key);
        rijAlg.setIV(IV);

        // Create a decryptor to perform the stream transform.
        ICryptoTransform decryptor = rijAlg.CreateDecryptor(rijAlg.getKey(), rijAlg.getIV());

        // Create the streams used for decryption.
        MemoryStream msDecrypt = new MemoryStream(cipherText);

        CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read);

        StreamReader srDecrypt = new StreamReader(csDecrypt);

        // Read the decrypted bytes from the decrypting stream and place them in a string.
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

            // Encrypt the string to an array of bytes.
            BigByte[] encrypted = encryptStringToBytes(original, myRijndael.getKey(), myRijndael.getIV());

            // Decrypt the bytes to a string.
            String roundtrip = decryptStringFromBytes(encrypted, myRijndael.getKey(), myRijndael.getIV());

            //Display the original data and the decrypted data.
            System.out.println(String.format("Original:   %s", original));
            System.out.println(String.format("Round Trip: %s", roundtrip));

            System.out.println("Press any key to continue.....");
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}
