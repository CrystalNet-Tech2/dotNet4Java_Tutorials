package Windows.OpenFileDialog;

import system.io.Stream;
import system.io.StreamReader;
import system.windows.forms.DialogResult;
import system.windows.forms.MessageBox;
import system.windows.forms.MessageBoxButtons;
import system.windows.forms.OpenFileDialog;

public class Program {

    public static void main(String[] arg) {
        String fileContent = "";
        String filePath = "";

        try {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            try {
                openFileDialog.set_InitialDirectory("c:\\");
                openFileDialog.set_Filter("txt files (*.txt)|*.txt|All files (*.*)|*.*");
                openFileDialog.set_FilterIndex(2);
                openFileDialog.set_RestoreDirectory(true);

                if (openFileDialog.ShowDialog() == DialogResult.OK) {
                    //Get the path of specified file
                    filePath = openFileDialog.get_FileName();

                    //Read the contents of the file into a stream
                    Stream fileStream = openFileDialog.OpenFile();

                    StreamReader reader = new StreamReader(fileStream);
                    try {
                        fileContent = reader.ReadToEnd();
                    } finally {
                        reader.Dispose();
                        reader.close();
                    }
                    MessageBox.Show(fileContent, "File Content at path: " + filePath, MessageBoxButtons.OK);
                }
            } finally {
                openFileDialog.Dispose();
                openFileDialog.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}