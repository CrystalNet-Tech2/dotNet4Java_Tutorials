package ServiceProcess.ServiceController;

import system.Console;
import system.diagnostics.EventLog;
import system.diagnostics.EventLogEntry;
import system.diagnostics.EventLogEntryCollection;
import system.serviceprocess.ServiceController;
import system.serviceprocess.ServiceControllerStatus;

public class Program {

    public static void main(String[] arg) {
        try {
            ServiceController[] scServices = ServiceController.GetServices().toArray();

            for (ServiceController scTemp : scServices) {
                if (scTemp.get_ServiceName().equalsIgnoreCase("Simple Service")) { //Change Service Name
                    // Display properties for the Simple Service sample
                    // from the ServiceBase example.
                    ServiceController sc = new ServiceController("Simple Service");
                    Console.WriteLine("Status = " + sc.get_Status());
                    Console.WriteLine("Can Pause and Continue = " + sc.get_CanPauseAndContinue());
                    Console.WriteLine("Can ShutDown = " + sc.get_CanShutdown());
                    Console.WriteLine("Can Stop = " + sc.get_CanStop());
                    if (sc.get_Status() == ServiceControllerStatus.Stopped) {
                        sc.Start();
                        while (sc.get_Status() == ServiceControllerStatus.Stopped) {
                            Thread.sleep(1000);
                            sc.Refresh();
                        }
                    }
                    // Issue custom commands to the service
                    // enum SimpleServiceCustomCommands
                    //    { StopWorker = 128, RestartWorker, CheckWorker };
                    sc.ExecuteCommand(SimpleServiceCustomCommands.StopWorker);
                    sc.ExecuteCommand(SimpleServiceCustomCommands.RestartWorker);
                    sc.Pause();
                    while (sc.get_Status() != ServiceControllerStatus.Paused) {
                        Thread.sleep(1000);
                        sc.Refresh();
                    }
                    Console.WriteLine("Status = " + sc.get_Status());
                    sc.Continue();
                    while (sc.get_Status() == ServiceControllerStatus.Paused) {
                        Thread.sleep(1000);
                        sc.Refresh();
                    }
                    Console.WriteLine("Status = " + sc.get_Status());
                    sc.Stop();
                    while (sc.get_Status() != ServiceControllerStatus.Stopped) {
                        Thread.sleep(1000);
                        sc.Refresh();
                    }
                    Console.WriteLine("Status = " + sc.get_Status());
                    String[] argArray = new String[]{"ServiceController arg1", "ServiceController arg2"};
                    sc.Start(argArray);
                    while (sc.get_Status() == ServiceControllerStatus.Stopped) {
                        Thread.sleep(1000);
                        sc.Refresh();
                    }
                    Console.WriteLine("Status = " + sc.get_Status());
                    // Display the event log entries for the custom commands
                    // and the start arguments.
                    EventLog el = new EventLog("Application");
                    EventLogEntryCollection elec = el.get_Entries();
                    for (int i = 0; i < elec.get_Count(); i++) {
                        EventLogEntry ele = elec.get_Item(i);
                        if (ele.get_Source().indexOf("SimpleService.OnCustomCommand") >= 0 |
                                ele.get_Source().indexOf("SimpleService.Arguments") >= 0)
                            Console.WriteLine(ele.get_Message());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class SimpleServiceCustomCommands {
        public static final int StopWorker = 128;
        public static final int RestartWorker = 129;
        public static final int CheckWorker = 130;
    }
}

// This sample displays the following output if the Simple Service
// sample is running:
//Status = Running
//Can Pause and Continue = True
//Can ShutDown = True
//Can Stop = True
//Status = Paused
//Status = Running
//Status = Stopped
//Status = Running
//4:14:49 PM - Custom command received: 128
//4:14:49 PM - Custom command received: 129
//ServiceController arg1
//ServiceController arg2