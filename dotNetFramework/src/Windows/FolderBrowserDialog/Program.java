package Windows.FolderBrowserDialog;

import system.Console;
import system.Environment_SpecialFolder;
import system.windows.forms.DialogResult;
import system.windows.forms.FolderBrowserDialog;

public class Program {
    public static void main(String[] arg) {
        try {
            FolderBrowserDialog MyDialog = new FolderBrowserDialog();
            try {
                // Set the dialog description.
                MyDialog.set_Description("Example Folder");
                // Set the root Folder to Desktop
                MyDialog.set_RootFolder(Environment_SpecialFolder.Desktop);

                //Don"t show the New Folder button
                MyDialog.set_ShowNewFolderButton(false);

                if (MyDialog.ShowDialog() == DialogResult.OK) {
                    Console.WriteLine("SelectedPath: {0}", MyDialog.get_SelectedPath());
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