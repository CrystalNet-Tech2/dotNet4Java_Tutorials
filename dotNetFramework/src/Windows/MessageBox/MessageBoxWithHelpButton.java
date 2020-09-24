package Windows.MessageBox;

import system.Console;
import system.windows.forms.*;

import java.util.EnumSet;

public class MessageBoxWithHelpButton {
    public static void main(String[] arg) {
        try {
            // Configure message box
            String message = "Do you want to abort this operation?";
            String caption = "Abort Operation";
            MessageBoxButtons buttons = MessageBoxButtons.YesNo;
            MessageBoxIcon icon = MessageBoxIcon.Warning;
            MessageBoxDefaultButton defaultButton = MessageBoxDefaultButton.Button2;
            MessageBoxOptions options = MessageBoxOptions.RightAlign;
            // Show message box
            DialogResult msgBoxResult = MessageBox.Show(message, caption, buttons, icon, defaultButton, EnumSet.of(options), true);
            switch (msgBoxResult) {
                case Yes:
                    Console.WriteLine("Yes Button Clicked");
                    break;
                case No:
                    Console.WriteLine("No Button Clicked");
                    break;
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
