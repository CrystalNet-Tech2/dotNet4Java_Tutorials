package Diagnostics.EventLog;

import system.Console;
import system.diagnostics.EntryWrittenEventArgs;
import system.diagnostics.EntryWrittenEventHandler;
import system.diagnostics.EventLog;
import system.diagnostics.EventLogEntryType;

public class Program {
   public static void main(String[] arg) {
        try {

            // check for the event log source on specified machine
            // the Application event log source on MCBcomputer
            if (!EventLog.Exists("Application", "MCBcomputer")) {
                Console.WriteLine("The log does not exist!");
                return;
            }

            EventLog myLog = new EventLog();
            myLog.set_Log("Application");
            myLog.set_MachineName("MCBcomputer");
            Console.WriteLine("There are " + myLog.get_Entries().get_Count() + " entr[y|ies] in the Application log:");

            for (int i = 0; i < myLog.get_Entries().get_Count(); i++) {

                Console.WriteLine("\tEntry: " + myLog.get_Entries().get_Item(i).get_Message());
            }

            // check for Demo event log source existence
            // create it if it not exist
            if (!EventLog.SourceExists("Demo")) {
                EventLog.CreateEventSource("Demo", "Demo");
            }

            EventLog.WriteEntry("AnySource", "writing error to demo log.", EventLogEntryType.Error);
            Console.WriteLine("Monitoring of Application event log began...");
            Console.WriteLine("Press ''q'' and ''Enter'' to quit");

            EntryWrittenEventHandler OnEntryWritten = new EntryWrittenEventHandler() {
                @Override
                public void invoke(Object o, EntryWrittenEventArgs e) throws Exception {
                    Console.WriteLine("written entry: " + e.get_Entry().get_Message());
                }
            };

            while (Console.ReadKey().get_KeyChar() != 'q') {
                // Now we will monitor the new entries that will be written.
                // When you create an EntryWrittenEventHandler delegate
                // you identify the method that will handle the event.
                myLog.add_EntryWritten(OnEntryWritten);

                // EnableRaisingEvents gets or sets a value indicating whether the
                // EventLog instance receives EntryWritten event notifications.
                myLog.set_EnableRaisingEvents(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}