package Windows.MessageBox;

import system.Console;
import system.windows.forms.DialogResult;
import system.windows.forms.MessageBox;
import system.windows.forms.MessageBoxButtons;

public class MessageBoxWithButtons {

    public static void main(String[] arg) {
        try {
            // Configure message box
            String message = "Do you want to close this window?";
            String caption = "Close Window";
            MessageBoxButtons buttons = MessageBoxButtons.YesNo;
            // Show message box
            DialogResult msgBoxResult = MessageBox.Show(message, caption, buttons);
            if (msgBoxResult == DialogResult.Yes) {
                Console.WriteLine("Yes Button Clicked");
            } else {
                Console.WriteLine("No Button Clicked");
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
