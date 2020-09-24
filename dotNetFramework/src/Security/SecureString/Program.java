package Security.SecureString;

import system.Console;
import system.ConsoleKey;
import system.ConsoleKeyInfo;
import system.componentmodel.Win32Exception;
import system.diagnostics.Process;
import system.security.SecureString;

public class Program {
    public static void main(String[] arg) {

        try {
            // Instantiate the secure String.
            SecureString securePwd = new SecureString();

            Console.Write("Enter password: ");
            String password = Console.ReadLine();

            for (char c : password.toCharArray()) {
                securePwd.AppendChar(c);
            }

            try {
                Process.Start("Notepad.exe", "MyUser", securePwd, "MYDOMAIN");
            } catch (Win32Exception e) {
                Console.WriteLine(e.get_Message());
            } finally {
                securePwd.Dispose();
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
