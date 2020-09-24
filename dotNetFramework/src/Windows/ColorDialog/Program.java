package Windows.ColorDialog;

import system.Console;
import system.windows.forms.ColorDialog;
import system.windows.forms.DialogResult;

public class Program {

    public static void main(String[] arg) {
        try {

            ColorDialog MyDialog = new ColorDialog();
            try {
                // Keeps the user from selecting a custom color.
                MyDialog.set_AllowFullOpen(false);
                // Allows the user to get help. (The default is false.)
                MyDialog.set_ShowHelp(true);

                // Update the text box color if the user clicks OK
                if (MyDialog.ShowDialog() == DialogResult.OK) {
                    Console.WriteLine("Selected color is {0}", MyDialog.get_Color());
                }

            } finally {
                MyDialog.Dispose();
                MyDialog.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}