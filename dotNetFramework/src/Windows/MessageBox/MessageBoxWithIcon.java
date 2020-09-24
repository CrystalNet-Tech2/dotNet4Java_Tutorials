package Windows.MessageBox;

import system.Console;
import system.windows.forms.DialogResult;
import system.windows.forms.MessageBox;
import system.windows.forms.MessageBoxButtons;
import system.windows.forms.MessageBoxIcon;

public class MessageBoxWithIcon {
    public static void main(String[] arg) {
        try {
            // Configure message box
            String message = "Do you want to abort this operation?";
            String caption = "Abort Operation";
            MessageBoxButtons buttons = MessageBoxButtons.AbortRetryIgnore;
            MessageBoxIcon icon = MessageBoxIcon.Warning;
            // Show message box
            DialogResult msgBoxResult = MessageBox.Show(message, caption, buttons, icon);
            switch (msgBoxResult) {
                case Abort:
                    Console.WriteLine("Abort Button Clicked");
                    break;
                case Retry:
                    Console.WriteLine("Retry Button Clicked");
                    break;
                case Ignore:
                    Console.WriteLine("Ignore Button Clicked");
                    break;
                default:
                    Console.WriteLine("No Button Clicked");
                    break;
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
