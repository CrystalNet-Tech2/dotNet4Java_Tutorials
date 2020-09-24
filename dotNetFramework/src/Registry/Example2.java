package Registry;

import microsoft.win32.Registry;
import microsoft.win32.RegistryValueKind;
import system.Console;

//Example2 in C#; https://docs.microsoft.com/en-us/dotnet/api/microsoft.win32.registry?view=dotnet-plat-ext-3.1

public class Example2 {
    public static void main(String[] args) {
        try {
            // The name of the key must include a valid root.
            final String userRoot = "HKEY_CURRENT_USER";
            final String subkey = "RegistrySetValueExample";
            final String keyName = userRoot + "\\" + subkey;

            // An int value can be stored without specifying the
            // registry data type, but long values will be stored
            // as strings unless you specify the type. Note that
            // the int is stored in the default name/value
            // pair.
            Registry.SetValue(keyName, "", 5280);
            Registry.SetValue(keyName, "TestLong", 12345678901234L, RegistryValueKind.QWord);

            // Strings with expandable environment variables are
            // stored as ordinary strings unless you specify the
            // data type.
            Registry.SetValue(keyName, "TestExpand", "My path: %path%");
            Registry.SetValue(keyName, "TestExpand2", "My path: %path%",
                    RegistryValueKind.ExpandString);

            // Arrays of strings are stored automatically as
            // MultiString. Similarly, arrays of Byte are stored
            // automatically as Binary.
            String[] strings = {"One", "Two", "Three"};
            Registry.SetValue(keyName, "TestArray", strings);

            // Your default value is returned if the name/value pair
            // does not exist.
            String noSuch = (String) Registry.GetValue(keyName,
                    "NoSuchName",
                    "Return this default if NoSuchName does not exist.");
            Console.WriteLine("\r\nNoSuchName: {0}", noSuch);

            // Retrieve the int and long values, specifying
            // numeric default values in case the name/value pairs
            // do not exist. The int value is retrieved from the
            // default (nameless) name/value pair for the key.
            int tInteger = (int) Registry.GetValue(keyName, "", -1);
            Console.WriteLine("(Default): {0}", tInteger);
            long tLong = (long) Registry.GetValue(keyName, "TestLong", Long.MIN_VALUE);
            Console.WriteLine("TestLong: {0}", tLong);

            // When retrieving a MultiString value, you can specify
            // an array for the default return value.
            String[] tArray = (String[]) Registry.GetValue(keyName,
                    "TestArray",
                    new String[]{"Default if TestArray does not exist."});
            for (int i = 0; i < tArray.length; i++) {
                Console.WriteLine("TestArray({0}): {1}", i, tArray[i]);
            }

            // A String with embedded environment variables is not
            // expanded if it was stored as an ordinary String.
            String tExpand = (String) Registry.GetValue(keyName,
                    "TestExpand",
                    "Default if TestExpand does not exist.");
            Console.WriteLine("TestExpand: {0}", tExpand);

            // A String stored as ExpandString is expanded.
            String tExpand2 = (String) Registry.GetValue(keyName,
                    "TestExpand2",
                    "Default if TestExpand2 does not exist.");
            Console.WriteLine("TestExpand2: {0}...",
                    tExpand2.substring(0, 40));

            Console.WriteLine("\r\nUse the registry editor to examine the key.");
            Console.WriteLine("Press the Enter key to delete the key.");
            Console.ReadLine();
            Registry.getCurrentUser().DeleteSubKey(subkey);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}