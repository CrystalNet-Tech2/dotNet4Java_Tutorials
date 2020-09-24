package Windows.SaveFileDialog;

import system.Console;
import system.io.Stream;
import system.windows.forms.DialogResult;
import system.windows.forms.SaveFileDialog;

public class Program {

    public static void main(String[] arg) {
        Stream myStream;

        try {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            try {
                saveFileDialog.set_Filter("txt files (*.txt)|*.txt|All files (*.*)|*.*");
                saveFileDialog.set_FilterIndex(2);
                saveFileDialog.set_RestoreDirectory(true);

                if (saveFileDialog.ShowDialog() == DialogResult.OK) {
                    //Get the path of specified file
                    if ((myStream = saveFileDialog.OpenFile()) != null) {
                        // Code to write the stream goes here.
                        myStream.Close();
                    }

                    Console.WriteLine("File Saved Successfully!");
                }
            } finally {
                saveFileDialog.Dispose();
                saveFileDialog.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}