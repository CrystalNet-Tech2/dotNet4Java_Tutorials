package Timers;

import system.Console;
import system.DateTime;
import system.timers.ElapsedEventArgs;
import system.timers.ElapsedEventHandler;
import system.timers.Timer;

public class Program {

    private static Timer aTimer;

    public static void main(String[] arg) {
        try {
            SetTimer();

            Console.WriteLine("\nPress the Enter key to exit the application...\n");

            Console.WriteLine("The application started at {0:HH:mm:ss.fff}", DateTime.get_Now());
            Console.ReadLine();
            aTimer.Stop();
            aTimer.Dispose();

            Console.WriteLine("Terminating the application...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void SetTimer() throws Exception {
        // Create a timer with a two second interval.
        aTimer = new Timer(2000);

        ElapsedEventHandler OnTimedEvent = new ElapsedEventHandler() {
            @Override
            public void invoke(Object o, ElapsedEventArgs e) throws Exception {
                Console.WriteLine("The Elapsed event was raised at {0:HH:mm:ss.fff}",
                        e.get_SignalTime());
            }
        };

        // Hook up the Elapsed event for the timer.
        aTimer.add_Elapsed(OnTimedEvent);
        aTimer.set_AutoReset(true);
        aTimer.set_Enabled(true);
    }
}

// The example displays output like the following:
//       Press the Enter key to exit the application...
//
//       The application started at 09:40:29.068
//       The Elapsed event was raised at 09:40:31.084
//       The Elapsed event was raised at 09:40:33.100
//       The Elapsed event was raised at 09:40:35.100
//       The Elapsed event was raised at 09:40:37.116
//       The Elapsed event was raised at 09:40:39.116
//       The Elapsed event was raised at 09:40:41.117
//       The Elapsed event was raised at 09:40:43.132
//       The Elapsed event was raised at 09:40:45.133
//       The Elapsed event was raised at 09:40:47.148
//
//       Terminating the application...
