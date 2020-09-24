package Registry;

import microsoft.win32.Registry;
import microsoft.win32.RegistryKey;
import system.Console;

public class Example1 {

    public static void main(String[] args) {

        try {
            // Create a RegistryKey, which will access the HKEY_USERS
            // key in the registry of this machine.
            RegistryKey rk = Registry.getUsers();

            // Print out the keys.
            PrintKeys(rk);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
    }

    static void PrintKeys(RegistryKey rkey) throws Exception {

        // Retrieve all the subkeys for the specified key.
        String[] names = rkey.GetSubKeyNames();

        int icount = 0;

        Console.WriteLine("Subkeys of " + rkey.get_Name());
        Console.WriteLine("-----------------------------------------------");

        // Print the contents of the array to the console.
        for (String s : names) {
            Console.WriteLine(s);

            // The following code puts a limit on the number
            // of keys displayed.  Comment it out to print the
            // complete list.
            icount++;
            if (icount >= 10)
                break;
        }
    }
}
