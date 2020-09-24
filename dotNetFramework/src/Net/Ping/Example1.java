package Net.Ping;

import com.dotNet4Java.api.core.DotNetNativeTypes;
import com.dotNet4Java.types.BigByte;
import system.ArgumentException;
import system.Console;
import system.net.networkinformation.*;
import system.text.Encoding;
import system.threading.AutoResetEvent;

public class Example1 {
    public static void main(String[] args) {
        try {
            if (args.length == 0)
                throw new ArgumentException("Ping needs a host or IP Address.");

            String who = args[0];
            AutoResetEvent waiter = new AutoResetEvent(false);

            Ping pingSender = new Ping();

            PingCompletedEventHandler PingCompletedCallback = new PingCompletedEventHandler() {
                @Override
                public void invoke(Object o, PingCompletedEventArgs e) throws Exception {
                    // If the operation was canceled, display a message to the user.
                    if (e.get_Cancelled()) {
                        Console.WriteLine("Ping canceled.");

                        // Let the main thread resume.
                        // UserToken is the AutoResetEvent object that the main thread
                        // is waiting for.
                        new AutoResetEvent((DotNetNativeTypes.ClrObject) e.get_UserState()).Set();
                    }

                    // If an error occurred, display the exception to the user.
                    if (e.get_Error() != null) {
                        Console.WriteLine("Ping failed:");
                        Console.WriteLine(e.get_Error().ToString());

                        // Let the main thread resume.
                        ((AutoResetEvent) e.get_UserState()).Set();
                    }

                    PingReply reply = e.get_Reply();

                    DisplayReply(reply);

                    // Let the main thread resume.
                    new AutoResetEvent((DotNetNativeTypes.ClrObject) e.get_UserState()).Set();
                }
            };

            // When the PingCompleted event is raised,
            // the PingCompletedCallback method is called.
            pingSender.add_PingCompleted(PingCompletedCallback);

            // Create a buffer of 32 bytes of data to be transmitted.
            String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            BigByte[] buffer = Encoding.get_ASCII().GetBytes(data);

            // Wait 12 seconds for a reply.
            int timeout = 12000;

            // Set options for transmission:
            // The data can go through 64 gateways or routers
            // before it is destroyed, and the data packet
            // cannot be fragmented.
            PingOptions options = new PingOptions(64, true);

            Console.WriteLine("Time to live: {0}", options.get_Ttl());
            Console.WriteLine("Don't fragment: {0}", options.get_DontFragment());

            // Send the ping asynchronously.
            // Use the waiter as the user token.
            // When the callback completes, it can wake up this thread.
            pingSender.SendAsync(who, timeout, buffer, options, waiter);

            // Prevent this example application from ending.
            // A real application should do something useful
            // when possible.
            waiter.WaitOne();
            Console.WriteLine("Ping example completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DisplayReply(PingReply reply) throws Exception {
        if (reply == null)
            return;

        Console.WriteLine("ping status: {0}", reply.get_Status());
        if (reply.get_Status() == IPStatus.Success) {
            Console.WriteLine("Address: {0}", reply.get_Address().ToString());
            Console.WriteLine("RoundTrip time: {0}", reply.get_RoundtripTime());
            Console.WriteLine("Time to live: {0}", reply.get_Options().get_Ttl());
            Console.WriteLine("Don't fragment: {0}", reply.get_Options().get_DontFragment());
            Console.WriteLine("Buffer size: {0}", reply.get_Buffer().length);
        }
    }
}
