package Windows.FontDialog;

import system.Console;
import system.windows.forms.DialogResult;
import system.windows.forms.FontDialog;

public class Program {
    public static void main(String[] arg) {
        try {

            FontDialog fontDialog = new FontDialog();
            try {
                fontDialog.set_ShowColor(true);

                if (fontDialog.ShowDialog() != DialogResult.Cancel) {
                    Console.WriteLine("FontDialog FontName: {0}", fontDialog.get_Font().get_Name());
                    Console.WriteLine("FontDialog Color: {0}", fontDialog.get_Color().get_Name());
                } else
                    Console.WriteLine("FontDialog Cancelled");
            } finally {
                fontDialog.Dispose();
                fontDialog.close();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
