package Diagnostics.Process;

import system.diagnostics.Process;

public class Program {
    public static void main(String[] args) {
        try {
            Process myProcess = new Process();
            {
                myProcess.get_StartInfo().set_UseShellExecute(false);
                // You can start any process, HelloWorld is a do-nothing example.
                myProcess.get_StartInfo().set_FileName("C:\\HelloWorld.exe");
                myProcess.get_StartInfo().set_CreateNoWindow(true);
                myProcess.Start();
                // This code assumes the process you are starting will terminate itself.
                // Given that is is started without a window so you cannot terminate it
                // on the desktop, it must terminate itself or you can do it programmatically
                // from this application using the Kill method.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
