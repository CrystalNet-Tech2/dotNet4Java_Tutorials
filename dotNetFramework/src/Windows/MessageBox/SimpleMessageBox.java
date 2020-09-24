package Windows.MessageBox;

import system.windows.forms.MessageBox;

public class SimpleMessageBox {

    public static void main(String[] arg) {
        try {
            // Show message box
            MessageBox.Show("Simple MessageBox");
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
